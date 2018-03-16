package examples.pubhub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

/*
 * This servlet will take you to the homepage for the Book Publishing module (level 100)
 */
@WebServlet("/BookPublishing")
public class BookPublishingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Books from the Database
		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();
		
		TagDAO tagDAO = DAOUtilities.getTagDAO();
		//HashMap<String, List<Tag>> bookTags = new HashMap<>();
		//ArrayList<List<Tag>> tags = new ArrayList<>();
		ArrayList<Tag> tags = new ArrayList<>();
		//for(Book book : bookList) {
		Book book = dao.getBookByISBN("2222222222222");
			List<Tag> booktags = tagDAO.getAllTagsForBook(book);
			tags.addAll(booktags);
			//book.setTagList(tagNames);
		//}
	
		
		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("books", bookList);
		request.getSession().setAttribute("tags", booktags);
		//request.getSession().setAttribute("tags", bookTags);
		
		
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
	}
}
