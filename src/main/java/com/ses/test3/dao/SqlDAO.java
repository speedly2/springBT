package com.ses.test3.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ses.test3.domain.Info;
import com.ses.test3.domain.SeqMember;

@Mapper
public interface SqlDAO {

	int insertInfo(ArrayList<Info> list);

	ArrayList<Info> selectInfo();

	int deleteInfo();

	int insertSeqMember(ArrayList<SeqMember> list);

	ArrayList<SeqMember> selectSeqMember();

}
