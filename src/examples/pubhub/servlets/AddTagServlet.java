package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/AddTag")
public class AddTagServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess = false;
		String isbn13 = request.getParameter("isbn");
		String newTag = request.getParameter("tag");
		System.out.println(newTag.isEmpty());
		System.out.println(isbn13);
		System.out.println(newTag);
	    BookDAO bookDao = DAOUtilities.getBookDAO();
		TagDAO tagDao = DAOUtilities.getTagDAO();
		///Tag tag = new Tag (newTag);
		Book book = bookDao.getBookByISBN(isbn13);
		
		if (book != null && !newTag.isEmpty()) {
			isSuccess = tagDao.addTagToBook(book, new Tag(newTag));
			//request.setAttribute("tag", tag);
		} 
		else 
			isSuccess = false;
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Tag successfully added");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("UpdateTags?isbn13=" + isbn13);
		}else {
			request.getSession().setAttribute("message", "There was a problem adding a tag to this book");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("tagUpdate.jsp").forward(request, response);
		}
		
		
		//response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		
		//request.getRequestDispatcher("tagUpdate.jsp")
		
	}

}