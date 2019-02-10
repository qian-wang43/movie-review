package ca.sheridancollege.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.sheridancollege.beans.Movie;
import ca.sheridancollege.beans.Review;
import ca.sheridancollege.dao.DAO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAO dao = new DAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        dao.populate();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		List<Movie> movieList = dao.queryAllMovie();

		request.setAttribute("movieList", movieList);
		request.getRequestDispatcher("view.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode=request.getParameter("mode");
		if(mode.equals("search")) {
			String search = request.getParameter("search").trim();
			String sort = request.getParameter("sort");
			if(search==null||search.equals("")) 
				search="";
			
			List<Movie> movieList = dao.queryMoviesByUserInput(search,sort);
			request.setAttribute("movieList", movieList);
			request.getRequestDispatcher("view.jsp").forward(request,response);
		}
		if(mode.equals("review")){
			int movieId=Integer.parseInt(request.getParameter("movieId"));			
			
			Movie movie = dao.queryMovieById(movieId);
			List<Review> reviewList=movie.getReviewList();

			request.setAttribute("reviewList", reviewList);
			request.setAttribute("movieId", movieId);
			request.getRequestDispatcher("reviewPage.jsp").forward(request,response);
			
		}
		if(mode.equals("addReview")) {
			int movieId=Integer.parseInt(request.getParameter("movieId"));	
			Movie movie = dao.queryMovieById(movieId);
			String userName = request.getParameter("userName").trim();
			String comment = request.getParameter("comment");
			Review review=new Review(userName,comment);
			dao.insertReview(review);
			
			List<Review> reviewList=movie.getReviewList();
			reviewList.add(review);
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("movieId", movieId);
			request.getRequestDispatcher("reviewPage.jsp").forward(request,response);
			
		}
		
	}

}
