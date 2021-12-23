<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Oldie</title>
<link rel="stylesheet" type="text/css" href="/project/css/mainPage.css">
</head>
<body>

<c:set var="id" value="${sessionScope.mem_id}" />
<c:set var="auth" value="${sessionScope.mem_auth}" />
	
	
	<div class="header container">

		<!-- Category -->
		<nav class="category">
	    <div>
	        <a href="#">
	            <h5>SHOP</h5>
	        </a>
	        <a href="#">
	            <h5>ABOUT</h5>
	        </a>
	        <a href="#">
	            <h5>STORES</h5>
	        </a>
	        <a href="/project/BoardSelect">
	            <h5>CONTACT</h5>
	        </a>
	    </div>
		</nav>

		<!-- 로고 -->
		<div id="logo_div">
			<a href="/project/home.jsp">
			<img id="logo" alt="logo" src="/project/imges/7yT6.gif">
			</a>
		</div>
	
		
		<!-- login -->
	
		<c:choose>
			<c:when test="${id != null}">
			<c:choose>
				<c:when test="${auth != 0}">
					<nav class="login">
		                <div>
		                    <a href="logout" id="logout" href="/project/home.jsp">
		                        <h5>logout</h5>
		                    </a>
		                </div>
		                <div>
		                    <a href="/project/MemberListSelect?mem_id=${id}&auth=${auth}" >
		                        <h5>Account</h5>
		                    </a>
		                </div>
		                <div>
		                    <a href="#">
		                        <h5>cart</h5>
		                    </a>
		                </div>
		           	</nav>
				</c:when>
				
				<c:otherwise>
					<nav class="login">
		                <div>
		                    <a href="logout" id="logout" href="/project/home.jsp">
		                        <h5>logout</h5>
		                    </a>
		                </div>
		                <div>
		                    <a href="/project/MemberListSelect?auth=${auth}" >
		                        <h5>List</h5>
		                    </a>
		                </div>
		                <div>
		                    <a href="#">
		                        <h5>cart</h5>
		                    </a>
		                </div>
		           	</nav>
				</c:otherwise>
			</c:choose>
			</c:when>
			
			
			<c:otherwise>
				<nav class="login">
	                <div>
	                    <a href="/project/login/loginForm.jsp">
	                        <h5>login</h5>
	                    </a>
	                </div>
	                <div>
	                    <a href="/project/join/joinForm.jsp">
	                        <h5>join</h5>
	                    </a>
	                </div>
	                <div>
	                    <a href="#">
	                        <h5>cart</h5>
	                    </a>
	                </div>
	            </nav>
			</c:otherwise>
			
		</c:choose>
	</div>
	
       
</body>
</html>