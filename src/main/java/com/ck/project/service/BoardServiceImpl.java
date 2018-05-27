package com.ck.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ck.project.dao.BoardDao;
import com.ck.project.model.Board;

@Service
@Transactional(readOnly=true)
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Transactional
	@Override
	public long save(Board board) {
		return boardDao.save(board);
	}

	@Override
	public Board get(long id) {
		return boardDao.get(id);
	}

	@Override
	public List<Board> list() {
		return boardDao.list();
	}

	@Transactional
	@Override
	public void update(long id, Board board) {
		boardDao.update(id, board);
	}

	@Transactional
	@Override
	public void delete(long id) {
		boardDao.delete(id);
	}
}
