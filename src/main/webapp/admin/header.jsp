<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
    <div>
    <a href="${pageContext.request.contextPath}/admin/">
    <img src="./images/BookstoreAdminLogo.png" />
    </div>
    <div>
		Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a>
		<br/><br/>
	</div>


	<div id="headermenu">
	  <div>
        <a href="list_users">
            <img src="./images/users.png" /><br/>Users
        </a>
    	</div>
    	<div>
            <a href="list_category">
                <img src="./images/category.png" /><br/>Categories
            </a>
        </div>
        <div>
            <a href="list_books">
                <img src="./images/bookstack.png" /><br/>Books
            </a>
        </div>


	</div>

</div>