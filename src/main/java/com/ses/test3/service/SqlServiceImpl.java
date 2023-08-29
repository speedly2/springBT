package com.ses.test3.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ses.test3.dao.SqlDAO;
import com.ses.test3.domain.Info;
import com.ses.test3.domain.SeqMember;

@Service
public class SqlServiceImpl implements SqlService {

	@Autowired
	SqlDAO dao;

	@Override
	public int insertInfo(ArrayList<Info> list) {
		int result = dao.insertInfo(list);
		return result;
	}

	@Override
	public ArrayList<Info> selectInfo() {
		ArrayList<Info> result = dao.selectInfo();
		return result;
	}

	@Override
	public int deleteInfo() {
		int result = dao.deleteInfo();
		return result;
	}

	@Override
	public int insertSeqMember(ArrayList<SeqMember> list) {
		int result = dao.insertSeqMember(list);
		return result;
	}

	@Override
	public ArrayList<SeqMember> selectSeqMember() {
		ArrayList<SeqMember> result = dao.selectSeqMember();
		return result;
	}
	
	
}
