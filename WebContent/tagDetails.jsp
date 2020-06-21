	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
	       <form action="SearchBook?isbn=${book.isbn13}" method="get">
			<input name="isbn13" value="${book.isbn13}">
		      <input type="submit" value ="Search">
			</form>
			
			
		<h1>PUBHUB <small>Book Publishing</small></h1>
		<hr class="book-primary">

		
			
			<table class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Author:</td>
					<td>Price:</td>
					<td>Tag Name:</td>
					</tr>
				
					<tr> </tr>
		        	<c:forEach var="book" items="${books}">
						<tr>
						<td><c:out value="${book.isbn13}" /></td>		
						<td><c:out value="${book.author}" /></td>
						<td><c:out value="${book.price}" /></td>
						<td><c:out value="isbn13" /></td>
						</tr>	
		</c:forEach>
		</thead>
					
		</table>

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />