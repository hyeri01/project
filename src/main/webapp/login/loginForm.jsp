<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="/project/css/loginForm.css">
</head>
<body>

	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- content -->
	<div class="col-xs-12">
		
			<!-- login table -->
			<table class="table table-borderless">
				<tr>
					<td class="title" colspan="5">로그인</td>
				</tr>

			    <tr>
			        <td class="login_id">
			            <p>아이디</p>
			            <input type="text" name="mem_id" autocomplete="off" id="mem_id">
			        </td>
			    </tr>

			    <tr>
			        <td class="login_pw">
			            <p>비밀번호</p>
			            <input type="password" name="mem_password" id="mem_password">
			        </td>
			    </tr>

			    <tr>
			        <td class="not_no">
			            <input type="submit" value="Login" class="login_btn">
			        </td>
			    </tr>

			    <tr>
			        <td class="login_chk">
			            Forgotten <a href="#">ID</a> or <a href="#">Password</a>
			        </td>
			    </tr>
			</table>


			<!-- sign table -->
			<table class="table table-borderless" style="margin-bottom: 100px;">
				<!-- sign btn 위 message -->
			    <tr class="sign">
			        <td><span>아직 회원이 아니세요?</span></td>
			    </tr>
				
				<!-- sign btn -->
			    <tr>
			        <td class="not_no">
						<a href="/project/join/joinForm.jsp">
			            	<input type="submit" value="sign up" class="sign_btn">
						</a>
			        </td>
			    </tr>
				
				<!-- sign btn 밑 message -->
			    <tr class="sign">
			        <td><span>회원가입 시 3,000원 쿠폰 지급 및 무료 선물 포장 제공</span></td>
			    </tr>
			</table>
			
	</div>

  
        
        
	<!-- footer -->
	<jsp:include page="../footer.jsp" />

<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>


<script>

	/* ======= login 유효성 검사 ======= */
	
	$(function() {
		$(".login_btn").on("click", function() {
			
			var id = $("#mem_id").val();
			var pwd = $("#mem_password").val();
			
			
			$.ajax({
				url : "../loginChecker",
				type : "POST",
				data : {mem_id:id, mem_password:pwd},
				success : function (data) {
					if(data == "success"){
						location.href = "../home.jsp"
					}else if(data == "Check pwd") {
						alert("아이디 혹은 비밀번호를 다시 확인해 주세요.");
					}else if(data == "Check Id"){
						alert("아이디 혹은 비밀번호를 다시 확인해 주세요.");
					}
				},
				
				error : function(request, status, error) {
					alert("아이디 혹은 비밀번호를 입력해 주세요.");
				}
				
				
			});
			
			
		});
		
	})





</script>

</body>
</html>