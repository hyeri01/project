<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/project/css/boardForm.css">
<title>Write</title>
</head>
<body>


<!-- 세션 값 (id) 변수 id로 지정 -->
<c:set var="id" value="${sessionScope.mem_id}" />


	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	
	<div class="container">
		<div class="container-box"> 
			
			<div class="title">
				<h5>Q&A</h5>
			</div>
	
			<form action="/project/boardInsert" method="post">
				<table class="table-borderless table">
				
					<!-- 제목 / 작성자  -->
					<tr> 
						<td class="bod_form_title">
							<input id="title" class="form-control" 
									type="text" name="bod_title" 
									style="font-size: small;" placeholder="제목을 입력하세요." 
									required="required" autocomplete="off">
						</td>
						
						<!-- 작성자 (login한 사람의 회원 아이디로 저장) -->
						<td class="bod_form_title">
							<input class="form-control" 
									type="text" name="bod_writer" value="${id}" 
									style="font-size: small; width: 100px" readonly="readonly">
						</td>
					</tr>
					
					
					<!-- 본문 -->
					<tr> 
						<td colspan="2" style="font-size: small;">
							<textarea id="content" class="form-control" name="bod_content" wrap="hard"
										style="font-size: small;" placeholder="본문 내용을 입력하세요." required="required"></textarea> 
						</td>
					</tr>	
					
					<!-- 비밀글 (비밀번호) -->
					<tr> 
						<td class="bod_form_title">
							비밀번호 <input id="pwd" class="form-control" 
									type="password" name="bod_password" 
									style="font-size: small; width: 180px;" 
									placeholder="4자 이내로 입력하세요" required="required">
						</td>
					</tr>		
					
				</table>
					
				<!-- 목록으로 돌아가기 / 글 등록하기 -->
				<div class="button_box">
					<!-- 목록 -->
					<a href="/project/BoardSelect">Back to list</a>
					
					<!-- 등록 -->
					<input type="submit" value="submit" id="submit">
				</div>
				
			</form>

		   </div>
		</div>
		
		
		
		
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	

	

	<!-- jQuery cdn -->
	<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>


</body>

<script>

	/* 제목 / 본문 / 비밀번호 모두 적었을 시, 게시글 등록 확인 alert */
    $(function () {
        $('#submit').click(function () {
        	
        	var title = $('#title').val();
        	var content = $('#content').val();
        	var pwd = $('#pwd').val();
        	
        	
        	if (title != "" && content != "" & pwd != ""){
        		if (!confirm('등록하시겠습니까?')) {
                    return false;
                }else {
                	alert("게시글이 등록되었습니다.")
                }
        	}
            
        });
    });
	

</script>

</html>


