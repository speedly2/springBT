package com.ses.test3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(){
        log.debug("@ChatController, chat GET()");
        
        return "chat";
    }
}
