package com.ck.project.service;

import java.util.List;

import com.ck.project.model.Board;

public interface BoardService {
	long save(Board board);

	Board get(long id);

	List<Board> list();

	void update(long id, Board board);

	void delete(long id);
}
