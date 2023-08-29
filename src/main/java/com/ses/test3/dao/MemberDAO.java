package com.ses.test3.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ses.test3.domain.Member;

@Mapper
public interface MemberDAO {

	void insert(Member member);

	String getId();

}
