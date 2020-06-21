package examples.pubhub.servlets;

import java.io.IOException;
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


@WebServlet("/SearchBook")
public class SearchBookByTag extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn13 = request.getParameter("isbn13");
		request.getRequestDispatcher("searchBookByTag.jsp").forward(request, response);
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//SELECT * from books inner join book_tag on books.isbn_13=book_tag.isbn_13 inner join tag on book_tag.id=tag.tag_id1 where tag_name=String isbn13 = req.getParameter("isbn13");

		
		String isbn13 = req.getParameter("isbn13");
		System.out.print(isbn13);
		TagDAO database = DAOUtilities.getTagDAO();
		List<Book> bookList = database.getAllBooks(isbn13);
		
		List<Tag> tagList = database.getAlltagsWithTagName(isbn13);
		
		req.getSession().setAttribute("tags", tagList);
		req.getSession().setAttribute("books", bookList);
		
		req.getRequestDispatcher("searchBookByTag.jsp").forward(req, resp);
}
}
