<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New User</title>
	<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
  <jsp:directive.include file="header.jsp" />
  <div align="center">
   <h2 class="pageheading">
      <c:if test="${user != null}">
      Edit User
      </c:if>
      <c:if test="${user == null}">
           Create New User
        </c:if>
   </h2>
  </div>
  <div align="center">
     <c:if test="${user != null}">
     <form action="update_user" method="post" style="max-width: 400px; margin: 0 auto;">
    <input type="hidden" name="userId" value="${user.userId}">
  </c:if>
     <c:if test="${user == null}">
       <form action="create_user" method="post" style="max-width: 400px; margin: 0 auto;">
    </c:if>
  </div>
  <table class="form">
     <tr>
     <td align="right">Email:</td>
     <td align="left">
        <input type="text" name="email" size="20" value="${user.email}" required minlenght="5" maxlenght="30">
     </td>
     </tr>
       <tr>
          <td align="right">Full Name:</td>
          <td align="left">
             <input type="text" name="fullName" size="20" value="${user.fullName}" required minlenght="5" maxlenght="30">
          </td>
      </tr>
        <tr>
            <td align="right">Password:</td>
            <td align="left">
               <input type="password" name="password" size="20" value="${user.password}" required minlenght="4" maxlenght="30">
            </td>
       </tr>
           <tr>
            <td align="right">Phone:</td>
            <td align="left">
               <input type="phone" name="phone" size="20" value="${user.phone}" required minlenght="4" maxlenght="15">
            </td>
       </tr>
       <tr><td>&nbsp;</td></tr>
        <tr>
           <td align="center" colspan="2">
              <button type="submit">Save</button>
              <button type="button" onClick="history.go(-1)">Cancel</button>
           </td>
       </tr>
  </table>
  </form>
</div>
</body>
</html>