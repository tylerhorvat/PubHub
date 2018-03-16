package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class TestTagDAO {
	  public static void main(String[] args){
	      TagDAO dao = new TagDAOImpl();
	      //dao.addTag(new Tag("FICTION"));
	      //BookDAO b = new BookDAOImpl();
	      //Book book = new Book();
	      //book.setIsbn13("2222222222222");
	      //book.setAuthor("dope");
	      //book.setTitle("cool book");
	      //book.setPrice(20);
	      
	      //dao.addTag(new Tag(null));
		  
		  //dao.addTagToBook(book, new Tag("FICTION"));
		  //dao.addTagToBook(book, new Tag("HUMOR"));
		  
		  List<Book> list = dao.getAllBooksWithTag(new Tag("FICTION"));

		  for (int i = 0; i < list.size(); i++){
		      Book b = list.get(i);
		      System.out.println(b.getTitle());
		  }
    }
}
