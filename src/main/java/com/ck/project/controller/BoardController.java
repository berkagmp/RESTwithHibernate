package com.ck.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.project.model.Board;
import com.ck.project.service.BoardService;

@RestController
@RequestMapping("/api")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@PostMapping("/board")
	public ResponseEntity<?> save(@RequestBody Board board) {
		long id = boardService.save(board);
		return ResponseEntity.ok().body("New Article has been saved with ID: " + id);
	}

	@GetMapping("/board/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		Board board = boardService.get(id);
		return ResponseEntity.ok().body(board);
	}

	@GetMapping("/board")
	public ResponseEntity<List<Board>> list() {
		logger.info("Entered in " + this.getClass().getName());
		List<Board> boards = boardService.list();
		
		if(boards.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(boards);
		}else {
			return ResponseEntity.ok().body(boards);
		}
	}

	@PutMapping("/board/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Board board) {
		boardService.update(id, board);
		return ResponseEntity.ok().body("Article has been updated successfully");
	}

	@DeleteMapping("/board/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		boardService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Article has been deleted successfully");
	}

}
