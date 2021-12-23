<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Account</title>
<link rel="stylesheet" type="text/css" href="/project/css/account.css">
</head>
<body>

<!-- 로그인 정보 저장 -->
<c:set var="id" value="${sessionScope.mem_id}" />
<c:set var="auth" value="${sessionScope.mem_auth}" />


	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- content -->
	<div class="container col-xs-12">
		<div class="container-box"> 
	    <c:forEach var="mem" items="${m_list}">
			<table class="table table-borderless">
			    <thead>
					<tr>
					    <td class="title" colspan="5">
					        <h4>You Account</h4>
					    </td>
					</tr>
			    </thead>
			
			    <tbody>
			        <tr>
			            <td>
			            <h4>Account</h4>
			            <hr>
			                <ul>
			                    <li>
			                        <span>이름</span> <br>
			                    </li>
			                    <li>
			                        <span><c:out value="${mem.mem_name}"/></span>
			                    </li>
			                    <li>
			                        <a class="ac_edit" href="/project/MemberListSelect?mem_id=${id}&modify=1&auth=${auth}">회원정보 수정</a>
			                    </li>
			                </ul>
			            </td>
			        </tr>
			    </tbody>
			
			
			    <tfoot>
			        <tr>
			            <td>
			                <h4>ORDER HISTORY</h4>
			                <hr>
			                <ul>
			
			                    <li>
			                        <span>주문 내역</span>
			                        <br>
			                        <span class="page">0/0</span>
			                        <a class="board_list" href="#">주문 내역 보기</a>
			                    </li>
			                    <li>
			                        <span>적립금</span>
			                        <br>
			                        <span class="page">0원</span>
			                    </li>
			                    <li>
			                        <span>쿠폰</span>
			                        <br>
			                        <span class="page">0개</span>
			                    </li>
			                    <li>
			                        <span>게시물 관리</span>
			                        <br>
			                        <a class="board_list" href="#">모든 게시물 보기</a>
			                    </li>
			
			                </ul>
			
			            </td>
			        </tr>
			    </tfoot>
			</table>
			</c:forEach>
    	</div>
	</div>


        
        
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
</body>
</html>