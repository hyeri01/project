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
<title>Edit Account</title>
<link rel="stylesheet" type="text/css" href="/project/css/joinForm.css">
</head>
<body>

<!-- 로그인 한 id 값 저장 -->
<c:set var="id" value="${sessionScope.mem_id}" />


	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- content -->
	<div class="col-xs-12">
		
		<!-- join table -->
		<c:forEach var="mem" items="${m_list}">
		<form name="frm" id="frm" method="post">
			<table class="table table-borderless">
				<tr>
					<td class="title" colspan="5">Edit Account</td>
				</tr>

			    <tr>
			        <td class="join_font">
			            <p>아이디</p>
			            <input type="text" name="mem_id" autocomplete="off" id="mem_id" value="${id}" maxlength="20" readonly="readonly">
			        </td>
			    </tr>

			    <tr>
			        <td class="join_font">
			            <p>비밀번호</p>
			            <input type="password" name="mem_password" id="mem_pwd" maxlength="16">
			            <span id="error_box_pwd" style="font-size: 5px"></span>
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>비밀번호 확인</p>
			            <input type="password" id="mem_pwd_ck" maxlength="16">
			            <span id="error_box_pwd_ck" style="font-size: 5px"></span>
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>이름</p>
			            <input type="text" name="mem_name" value="${mem.mem_name}">
			            
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>연락처</p>
			            <input type="text" name="mem_tel" value="${mem.mem_tel}">
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>E-mail</p>
			            <input type="text" name="mem_email_id" style="width: 20%" 
			            value="${fn:substringBefore(mem.mem_email, '@')}">
			            @
			            <select name="mem_email_email" style="height: 40px">
			            	<option>naver.com</option>
			            	<option>gmail.com</option>
			            	<option>daum.net</option>
			            </select>
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>생년월일</p>
			            <input type="text" name="mem_bday" value="${mem.mem_bday}">
			        </td>
			    </tr>

			    <tr>
			        <td class="join_btn">
			            <input type="button" value="Edit Account" class="edit_btn">
			        </td>
			    </tr>

				<tr>
					<td class="join_btn">
						<input type="button" value="Delete Account" class="delete_btn">
					</td>
				</tr>			    
			</table>
			
			</form>
			</c:forEach>
		
	</div>

  
        
        
	<!-- footer -->
	<jsp:include page="../footer.jsp" />


	<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>



<script>

	/* 회원 수정 btn click 시, 비밀번호 입력란 "" check */
	$(function () {
		$(".edit_btn").click(function() {
			
			var pwd = $("#mem_pwd").val();
			var pwd_ck = $("#mem_pwd_ck").val();
			const formElement = $("#frm");
			
			if(pwd == ""){
				alert('비밀번호 항목은 필수 입력입니다.');
				$("#mem_pwd").focus();
				return false;
			}else if(pwd_ck == ""){
				alert('비밀번호를 확인해 주세요.');
				$("#mem_pwd_ck").focus();
				return false;
			}else if(pwd != pwd_ck){
				alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
				$("#mem_pwd_ck").focus();
				return false;
			}else {
				alert('회원 정보 수정이 완료되었습니다.');
				formElement.attr("action", "/project/MemberUpdate");
				formElement.submit();
			}
			
			
		});
		
		
	});
	
	
	
	/* delete btn click 시, form 주소 MemberListDelete */
	$(function() {
		$(".delete_btn").click(function() {
			
			var pwd = $("#mem_pwd").val();
			var pwd_ck = $("#mem_pwd_ck").val();
			const formElement = $("#frm");
			
			if(pwd == ""){
				alert('비밀번호 항목은 필수 입력입니다.');
				$("#mem_pwd").focus();
				return false;
			}else if(pwd_ck == ""){
				alert('비밀번호를 확인해 주세요.');
				$("#mem_pwd_ck").focus();
				return false;
			}else if (pwd != pwd_ck){
				alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
				$("#mem_pwd_ck").focus();
				return false;
			}else{
				if(!confirm('탈퇴하면 적립금이 삭제됩니다. 정말 탈퇴하시겠습니까?')){
					return false;
				}else{
					alert("회원 탈퇴되었습니다.");
					formElement.attr("action", "/project/MemberDelete");
					formElement.submit();
				}
				
			}
		})
	})

	
	
	
	/* ===== password 유효성 검사 ==== */
	$(function() {
		$("#mem_pwd").on("keyup", function() {
			
			var pwd = $("#mem_pwd").val();
			
			if(pwd.length < 8 || pwd.length > 17){
				$("#error_box_pwd").html("8~16자 영문 대소문자, 숫자 및 특수문자를 사용하세요.")
			}
			
			if(pwd == ""){
				$("#error_box_pwd").html(" ");
			}
			
		});
		
	});
	
	
	/* ===== password 중복 확인 ==== */
	$(function() {
		$("#mem_pwd_ck").on("keyup", function() {
			
			var pwd = $("#mem_pwd").val();
			var pwd_ck = $("#mem_pwd_ck").val();
			
			if(pwd != "" || pwd_ck != ""){
				if(pwd == pwd_ck){
					$("#error_box_pwd_ck").html("비밀번호가 일치합니다.")
				}else {
					$("#error_box_pwd_ck").html("비밀번호가 일치하지 않습니다.");
				}
			}
			
			if(pwd_ck == ""){
				$("#error_box_pwd_ck").html(" ");
			}
			
			
		});
		
	});



</script>
	




</body>
</html>