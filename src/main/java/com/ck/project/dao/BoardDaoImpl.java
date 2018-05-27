package com.ck.project.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ck.project.model.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long save(Board board) {
		// save(board) -> board.getId()
		sessionFactory.getCurrentSession().save(board);
		return board.getId();
	}
	
	@Override
	public Board get(long id) {
		// get(T, id)
		return sessionFactory.getCurrentSession().get(Board.class, id);
	}
	
	@Override
	public List<Board> list() {
		// Obtains the current session
		Session session = sessionFactory.getCurrentSession();
		
		/*
		 *  The CriteriaBuilder (I) used to construct criteria queries, 
		 *  	compound selections, expressions, predicates, orderings. 
		 *  
		 *  The CriteriaQuery (I) defines functionality 
		 *  	that is specific to top-level queries.
		 *  
		 *  Root (I): A root type in the from clause. 
		 *  	Query roots always reference entities.
		 *  
		 *  Query <- CriteriaQuery <- CriteriaBuilder / Root
		 */
		CriteriaBuilder cb = session.getCriteriaBuilder();
		
		CriteriaQuery<Board> cq = cb.createQuery(Board.class);
		Root<Board> root = cq.from(Board.class);
		
		cq.select(root);
		
		Query<Board> query = session.createQuery(cq);
		
		return query.getResultList();
	}
	
	@Override
	public void update(long id, Board board) {
		/* 
		 * session.byId(T).load(id)
		 * 	setter()
		 * session.flush()
		 */
		Session session = sessionFactory.getCurrentSession();
		
		Board tempBoard = session.byId(Board.class).load(id);
			tempBoard.setTitle(board.getTitle());
			tempBoard.setContents(board.getContents());
			tempBoard.setUserid(board.getUserid());
		
		// flush: Sync between session state and DB
		session.flush();
	}

	@Override
	public void delete(long id) {
		// session.delete(session.byId(T).load(id))
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.byId(Board.class).load(id));
	}
}
