package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {
	
	public boolean addTag(Tag tag);
	public boolean addTagToBook(Book book, Tag tag);
	public boolean removeTag(Book book, Tag tag);
	public List<Tag> getAllTags();
	public List<Book> getAllBooksWithTag(Tag tag);
	public List<Tag> getAllTagsForBook(Book book);

}
