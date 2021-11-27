package com.spring.training22.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.training22.board.dao.BoardDao;
import com.spring.training22.board.dto.BoardDto;

@Service
public class BoardServiceimpl implements BoardService  {
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public void insertBoard(BoardDto boardDto) {
		
		boardDao.insert(boardDto);
	}
	
	@Override
	public List<BoardDto> getBoardList() {
		return boardDao.selectAll();
	}
	
	@Override
	public BoardDto getOneBoard(int num) {
		
		boardDao.increaseReadCount(num);
		return boardDao.selectOne(num);
	}
	
	@Override
	public boolean deleteBoard(BoardDto boardDto) {
		
		boolean isSucceed = false;
		
		if(boardDao.validateuserCheck(boardDto) != null) {
			boardDao.delete(boardDto.getNum());
			isSucceed = true;
		}
		
		return isSucceed;
	}
	
	@Override
	public boolean updateBoard(BoardDto boardDto) {
		
		boolean isSucceed = false;
		
		if(boradDao.validateuserCheck(boardDto) != null) {
			boardDao.update(boardDto);
			isSucceed = true;
		}
		return isSucceed;
	}

}
