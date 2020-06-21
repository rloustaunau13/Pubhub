package examples.pubhub.dao;

import java.util.List;
import examples.pubhub.model.Tag;
import examples.pubhub.model.Book;

public interface TagDAO {
	public boolean addTag(String isbn13,String tagName);
	public boolean removeTag(String isbn13,String tagName);
	public List<Tag> getAlltags(String isbn13);
	public List<Tag> getAlltagsWithTagName(String tag);
	
	public List<Book> getAllBooks(String tag);
	public List<Tag> getAllTags();
	
	public Tag getBookByTag(String isbn13);
}
