package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewTags")
public class ViewTagsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String tag = request.getParameter("tag");
		//BookDAO bookDao = DAOUtilities.getBookDAO();
		//TagDAO tagDao = DAOUtilities.getTagDAO();
		
		//Book book = bookDao.getBookByISBN(isbn13);
		//List<Book> tags = tagDao.getAllBooksWithTag(new Tag(tag));
		
		//request.setAttribute("tags", tags);
		
		request.getRequestDispatcher("bookSearch.jsp").forward(request, response);
		
	}

}
