package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/BookSearch")
public class BookSearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagName = request.getParameter("tag");
		
		TagDAO tagDAO = DAOUtilities.getTagDAO();
		
		List<Book> books = tagDAO.getAllBooksWithTag(new Tag(tagName));
		
		request.setAttribute("books", books);
		request.setAttribute("tag", tagName);
		
		request.getRequestDispatcher("bookSearch.jsp").forward(request, response);
	}

}
