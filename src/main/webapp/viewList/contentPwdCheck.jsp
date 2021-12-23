<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<jsp:useBean id="bod" class="model.BoardDTO" scope="page" />
<jsp:setProperty name="bod" property="*" />
<!DOCTYPE html>
<html lang="ko">
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" type="text/css" href="/project/css/contentPwdCheck.css">
<title>Insert title here</title>
</head>
<body>

	<!-- header -->
	<jsp:include page="../header.jsp" />
		
	<div class="container-box"> 
	
		<div class="title">
			<h5>Q&A</h5>
		</div>
		
		<div class="pwdCk">
			<input type="hidden" value="${bod.bod_no}" id="bod_no">
				비밀번호 <input type="password" name="bod_password" 
						autofocus="autofocus" autocomplete="off" id="bod_password">
				<button type="button" id="btn">submit</button>
		</div>
			
	</div>

	
	<!-- footer -->
	<jsp:include page="../footer.jsp" />

	
	<!-- jQuery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>
	$(function(){
		$("#btn").click(function() {
			var no = $("#bod_no").val();
			var pwd = $("#bod_password").val();
			
			$.ajax({
				url : "/project/ContentPwdCheck",
				type : "POST",
				data : {bod_no:no, bod_password:pwd},
				success : function(data) {
					if(data == 1){
						location.href = "../ContentSelect?bod_no=" + no;
					}else {
						alert('비밀번호를 확인하세요.');
					}
				},
				
				error : function(request, status, error) {
					alert("에러");
					alert("code:" + request.status);
				}
		
			
			
			});
		
		
		
		});
		
		
	});
	
	






</script>
</body>
</html>