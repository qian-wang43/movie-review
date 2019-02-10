package ca.sheridancollege.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Movie;
import ca.sheridancollege.beans.Review;



public class DAO {

	SessionFactory sessionFactory = new Configuration().configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();

	public void populate() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String[] titles= {"THE FAVOURITE","AQUAMAN","THE KID WHO WOULD BE KING","THE UPSIDE","GREEN BOOK"};
		String[] genres= {"Drama","Action","Fantasy","Drama","Drama"};
		String[] directors= {"Yorgos Lanthimos","Mahershala Ali","Brian Hayes Currie","Nick Vallelonga","Peter Farrelly"};
		int[] stars= {7,6,6,8,9};
		for(int i=0;i<5;i++) {
			Movie movie=new Movie(titles[i],genres[i],directors[i],stars[i]);
			Review review1=new Review("user1","comment1 for "+ titles[i]);
			Review review2=new Review("user2","comment2 for "+titles[i]);
			session.save(review1);
			session.save(review2);
			
			List<Review> reviewList=movie.getReviewList();
			reviewList.add(review1);
			reviewList.add(review2);
			session.save(movie);
			
		}

		session.getTransaction().commit();
		session.close();
	}

	public List<Movie> queryAllMovie() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
				
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Movie> criteria = criteriaBuilder.createQuery(Movie.class);
		
		Root<Movie> root = criteria.from(Movie.class);
		List<Movie> movieList = session.createQuery(criteria).getResultList();

		session.getTransaction().commit();
		session.close();

		return movieList;
	}
	public List<Movie> queryMoviesByUserInput(String search,String sort) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Movie> criteria = criteriaBuilder.createQuery(Movie.class);

		Root<Movie> root = criteria.from(Movie.class);

		if(!search.equals(""))
			criteria.where(criteriaBuilder.like(root.get("title"), "%"+search+"%"));
		switch(sort) {
		case "starRating":
			criteria.orderBy(criteriaBuilder.desc(root.get("star")));
			break;
		case "atoz":
			criteria.orderBy(criteriaBuilder.asc(root.get("title")));
			break;
		case "ztoa":
			criteria.orderBy(criteriaBuilder.desc(root.get("title")));
			break;
			
		}

		List<Movie> movieList = session.createQuery(criteria).getResultList();

		session.getTransaction().commit();
		session.close();

		return movieList;
	}

	public Movie queryMovieById(int movieId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Movie movie = session.get(Movie.class, movieId);
		

		session.getTransaction().commit();
		session.close();

		return movie;
		
		
	}
	

	
	public void insertReview(Review review) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(review);
		
		session.getTransaction().commit();
		session.close();
	}

	
}
