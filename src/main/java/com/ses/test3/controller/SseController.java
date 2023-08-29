package com.ses.test3.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ses.test3.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SseController {
	private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();
	Long time = Long.MAX_VALUE;
	SseEmitter emitter = null;
	
	@Autowired
	MemberService service;
    
	//로그인할 경우 sse 등록
    @GetMapping("/saveEvent")
	private String saveEvent(@RequestParam(name = "memberid", defaultValue = "default") String id) {
		log.debug("* saveEvent_param: {}", id);
		SseEmitter emitter = new SseEmitter(time);
		CLIENTS.put(id, emitter);
		
		// 503 Error를 방지하기 위한 더미데이터 전송
		try {
			emitter.send(SseEmitter.event().id(id).name("dummy").data("dummy"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 세션이 종료될 경우 저장한 SseEmitter를 삭제한다.
		emitter.onTimeout(() -> {
			log.debug("○ onTimeout callback_id: " + id);
			CLIENTS.remove(id);
		});
		emitter.onCompletion(() -> {
			log.debug("○ onCompletion callback_id " + id);
			CLIENTS.remove(id);
		});	
		
		//출력
		CLIENTS.entrySet().stream().map(Map.Entry::getKey).forEach(k -> log.debug("* 등록 후 key: {}", k));
		
        return "ID: ("+id+") 저장";
    }
	
	@GetMapping(value = "/sendEvent", produces = "text/event-stream")
	public SseEmitter sendEvent(@AuthenticationPrincipal UserDetails user) {
		Set<String> deadIds = new HashSet<>();
		
		log.debug("* sendEvent 접근");
		
		//출력
		CLIENTS.entrySet().stream().map(Map.Entry::getKey).forEach(k -> log.debug("* 전송 전 key: {}", k));
		
		if(CLIENTS == null) {
			try {
				emitter.send(SseEmitter.event().id("anonymousUser").name("dummy").data("dummy"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// Map에 저장된 모든 id, emitters 순회
			CLIENTS.forEach((id, emitters) -> {
				try {
					sendToClient(emitters, id, user.getUsername());
					log.debug("** 로그인한 유저: " + user.getUsername());
					emitter = new SseEmitter(time);
					emitter = emitters;
					
					// 세션이 종료될 경우 저장한 SseEmitter를 삭제한다.
					emitters.onTimeout(() -> {
						CLIENTS.remove(id);
						log.debug("● onTimeout callback_id: " + id);
					});
					emitters.onCompletion(() -> {
						CLIENTS.remove(id);
						log.debug("● onCompletion callback_id: " + id);
					});					
				} catch (Exception e) {
					e.printStackTrace();
					// CLIENTS.remove(id);
					log.debug("disconnected id : {}", id);
				}
			});
		}
		
		//출력
		CLIENTS.entrySet().stream().map(Map.Entry::getKey).forEach(k -> log.debug("* 전송 후 key: {}", k));
		
		deadIds.forEach(CLIENTS::remove);
		
		return emitter;
	}
	
    
	private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                                   .id(id)
                                   .name("connect")
                                   .data(data));
        } catch (IOException exception) {
        	CLIENTS.remove(id);
        	//에러 출력
        	//exception.printStackTrace();
            //throw new RuntimeException("연결 오류!");
        }
    }

	public static Map<String, SseEmitter> getClients() {
		return CLIENTS;
	}
}