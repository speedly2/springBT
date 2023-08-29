package com.ses.test3.service;

import java.util.ArrayList;

import com.ses.test3.domain.Info;
import com.ses.test3.domain.SeqMember;

public interface SqlService {

	int insertInfo(ArrayList<Info> list);

	ArrayList<Info> selectInfo();

	int deleteInfo();

	int insertSeqMember(ArrayList<SeqMember> list);

	ArrayList<SeqMember> selectSeqMember();

	
}
