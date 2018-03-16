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
	
	<h1>PUBHUB <small>Edit Tags - ${isbn}</small></h1>
	<hr class="book-primary">
	
	<div class="list-group" align="center">
	    <p>Click the tag you wish to delete, or add a tag at the bottom of this page</p>
	    <form action="RemoveTag?isbn=${isbn}" method="post">
	        <c:forEach var="tag" items="${tags}" >
	            <input type="hidden" name="isbn" value="${isbn}">
	            <input type="hidden" name="tag" value="${tag.tagName}">
                <button type="submit" class="list-group-item list-group-item-action active">
                    ${tag.tagName}
                </button>
            </c:forEach>
	    </form>
    </div>
	
	<form action="AddTag?isbn=${isbn}" method="post">
			<input type="hidden" name="isbn" value="${isbn}" >
			<input type="text" name="tag">
			<button class="btn btn-primary">Add Tag</button>
		</form>
	
	
	
<!-- Footer -->
<jsp:include page="footer.jsp" />