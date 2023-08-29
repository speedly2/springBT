package com.ses.test3.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ses.test3.domain.SeqMember;

@Mapper
public interface SeqDAO {

	int insertSelectKey(SeqMember sm);

}
