package com.ses.test3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ses.test3.dao.MemberDAO;
import com.ses.test3.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO dao;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void insert(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getMemberpw());
		log.debug("암호화된 비번 {} : {}", member.getMemberpw(), encodedPassword);
		member.setMemberpw(encodedPassword);
		
		dao.insert(member);
	}

	@Override
	public String getId() {
		String lastId = dao.getId();
		log.debug("service_param(lastId): {}", lastId);
		return lastId;
	}
	
	

}
