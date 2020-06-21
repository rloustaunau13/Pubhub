package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;



public class TagDAOImpl implements TagDAO{
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	
	
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




	@Override
	public boolean removeTag(String isbn13, String tagName) {
		// TODO Auto-generated method stub

		
			// TODO Auto-generated method stub
			
			try {
				connection = DAOUtilities.getConnection();	// Get our database connection from the manager
				String sql = "DELETE from book_tag where isbn_13=?;DELETE from tag where tag_name=?";	// Our SQL query
				stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
				
				stmt.setString(2, tagName);
				stmt.setString(1,isbn13);
				
				System.out.println(stmt);
				
				// So long as the ResultSet actually contains results...
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
	public List<Tag> getAlltags(String isbn13) {
		// TODO Auto-generated method stub
		
		List<Tag> tags = new ArrayList<>();

			try {
				connection = DAOUtilities.getConnection();
				String sql = "SELECT * FROM tag inner join book_tag on tag.tag_id1 = book_tag.id where isbn_13=?";
				stmt = connection.prepareStatement(sql);
				
				
				
				stmt.setString(1,isbn13);
				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Tag tag = new Tag();

					tag.setTag_id(rs.getString("tag_id1"));
				    tag.setTag_name(rs.getString("tag_name"));
					
					
					tags.add(tag);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
			
			return tags;
		}




	@Override
	public List<Book> getAllBooks(String tag) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		try {
			
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * from books inner join book_tag on books.isbn_13=book_tag.isbn_13 inner join tag on book_tag.id=tag.tag_id1 where tag_name=?";	// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			stmt.setString(1, tag);
			
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();

				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				
				// The SQL DATE datatype maps to java.sql.Date... which isn't well supported anymore. 
				// We use a LocalDate instead, because this is Java 8.
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				
				// The PDF file is tricky; file data is stored in the DB as a BLOB - Binary Large Object. It's
				// literally stored as 1's and 0's, so this one data type can hold any type of file.
				book.setContent(rs.getBytes("content"));
				
				// Finally we add it to the list of Book objects returned by this query.
				
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
	public boolean addTag(String isbn13, String tagName) {
		// TODO Auto-generated method stub
		try {
			
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "UPDATE tag SET tag_name =?  FROM tag as t Inner join book_tag on book_tag.id=t.tag_id1 WHERE book_tag.isbn_13=?";	// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			stmt.setString(1, tagName);
			stmt.setString(2,isbn13);
			
			System.out.println(stmt);
			
			// So long as the ResultSet actually contains results...
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
	public List<Tag> getAllTags(){
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * from tag";
			stmt = connection.prepareStatement(sql);
			
			
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setTag_id(rs.getString("tag_id1"));
			    tag.setTag_name(rs.getString("tag_name"));
				
				
				tags.add(tag);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}




	@Override
	public Tag getBookByTag(String isbn13) {
		Tag newTag =new Tag();
		
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * from tag inner join book_tag on book_tag.id = tag.tag_id1  where book_tag.isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,isbn13);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				
				newTag.setTag_id(rs.getString("tag_id1"));
			    newTag.setTag_name(rs.getString("tag_name"));
				
				
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return newTag;
	}




	@Override
	public List<Tag> getAlltagsWithTagName(String tag) {
		
		
		List<Tag> tags1 = new ArrayList<>();
		try {
			
		

			connection = DAOUtilities.getConnection();
			String sql = "SELECT * from tag where tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,tag);
			
			
			
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag1 = new Tag();

				tag1.setTag_id(rs.getString("tag_id1"));
			    tag1.setTag_name(rs.getString("tag_name"));
				
				
				tags1.add(tag1);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		
		return tags1;
	}

}
