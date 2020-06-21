package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class TestTagDAO {

	  public static void main(String[] args){
		
         TagDAO dao = new TagDAOImpl();
         
	    List<Book> list = dao.getAllBooks("adventure");

	    for (int i = 0; i < list.size(); i++){
	      Book t = list.get(i);
	      System.out.println(t.getAuthor());
	    }
	    
//	   dao.addTag(book, "fiction");
//	    String tag= "witches and glitches";
//	    dao.removeTag(book, tag);
//	    BookDAO dao1= new BookDAOImpl();
//	    List<Book> list1=dao1.getAllBooks();
//	    
//	    for(int i=0; i<list1.size(); i++) {
//	    	Book b=list1.get(i);
//	    	System.out.println(b.toString());
//	    }
	    
	    
	  }
	}