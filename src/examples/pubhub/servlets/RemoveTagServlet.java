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

@WebServlet("/RemoveTag")

public class RemoveTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		//BookDAO bookDao = DAOUtilities.getBookDAO();
		//TagDAO tagDao = DAOUtilities.getTagDAO();
		//Tag tag = new Tag (newTag);
		//Book book = bookDao.getBookByISBN(isbn13);
		
		boolean isSuccess = false;
		String isbn13 = request.getParameter("isbn");
		String removeTag = request.getParameter("tag");
		
		System.out.println(isbn13);
		System.out.println(removeTag);
	    BookDAO bookDao = DAOUtilities.getBookDAO();
		TagDAO tagDao = DAOUtilities.getTagDAO();
		///Tag tag = new Tag (newTag);
		Book book = bookDao.getBookByISBN(isbn13);
		
		if (book != null) {
			isSuccess = tagDao.removeTag(book, new Tag(removeTag));
			//request.setAttribute("tag", tag);
		} 
		//else 
			//isSuccess = false;
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Tag successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("UpdateTags?isbn13=" + isbn13);
		}else {
			request.getSession().setAttribute("message", "There was a problem deleting a tag from this book");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("tagUpdate.jsp").forward(request, response);
		}
		
	}
}
