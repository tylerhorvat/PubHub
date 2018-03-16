package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {

	Connection connection = null;
	PreparedStatement stmt = null;
	
	@Override
	public boolean addTag(Tag tag) {
		
		//List<Tag> tags = getAllTags();
		//if(!tags.contains(tag)) {
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO tags VALUES (?)";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1,  tag.getTagName());
				
				if (stmt.executeUpdate() != 0)
					return true;
				else 
					return false;
				
			} catch (PSQLException e) {
				System.out.println(":)");
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
		//}
		
		//else
			//return false;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean removeTag(Book book, Tag tag) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags where isbn_13 = ? AND tag_name = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag.getTagName());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public List<Tag> getAllTags() {
		// TODO Auto-generated method stub
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Tag tag = new Tag(rs.getString("tag_name"));
				tags.add(tag);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}

	@Override
	public List<Book> getAllBooksWithTag(Tag tag) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * from book_tags WHERE tag_name = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getTagName());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				BookDAO dao = DAOUtilities.getBookDAO();
				Book book = dao.getBookByISBN(rs.getString("isbn_13"));
				
				//book.setIsbn13(rs.getString("isbn_13"));
				//book.setAuthor(rs.getString("author"));
				//book.setTitle(rs.getString("title"));
				//book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				//book.setPrice(rs.getDouble("price"));
				//book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return books;
	}
	
	@Override
	public boolean addTagToBook(Book book, Tag tag) {
		// TODO Auto-generated method stub
		addTag(tag);
		//List<Book> books = getAllBooksWithTag(tag);
		
		//if (!books.contains(book)) {
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO book_tags VALUES (?, ?)";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, book.getIsbn13());
				stmt.setString(2, tag.getTagName());
				
				if (stmt.executeUpdate() != 0)
					return true;
				else
					return false;
			} catch (PSQLException e) {
				System.out.println(":)");
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
		//}
		
		//else
			//return false;
	}

	@Override
	public List<Tag> getAllTagsForBook(Book book) {
		// TODO Auto-generated method stub
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag(rs.getString("tag_name"));
				tags.add(tag);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}

	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


}
