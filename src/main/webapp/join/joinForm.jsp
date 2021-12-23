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
<link rel="stylesheet" type="text/css" href="/project/css/joinForm.css">
</head>
<body>

	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- content -->
	<div class="col-xs-12">
		
		<!-- join table -->
		<form id="frm" method="post">
			<table class="table table-borderless">
				<tr>
					<td class="title" colspan="5">회원가입</td>
				</tr>

			    <tr>
			        <td class="join_font">
			            <p>아이디</p>
			            <input type="text" name="mem_id" autocomplete="off" id="mem_id" maxlength="20">
			            <span id="error_box_id" style="font-size: 5px"></span>
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
			            <input type="text" name="mem_name" id="mem_name" autocomplete="off">
			            
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>연락처</p>
			            <input type="text" name="mem_tel" placeholder="예)010-1234-1234" id="mem_tel" autocomplete="off">
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>E-mail</p>
			            <input type="text" name="mem_email_id" style="width:25%;" id="mem_email_id" autocomplete="off">
			            @
			            <select name="mem_email_email" style="height:40px;">
			            	<option>naver.com</option>
			            	<option>gmail.com</option>
			            	<option>daum.net</option>
			            </select>
			        </td>
			    </tr>
			    
			    <tr>
			        <td class="join_font">
			            <p>생년월일</p>
			            <input type="text" name="mem_bday" placeholder="예)2021-01-31" id="mem_bday" autocomplete="off">
			        </td>
			    </tr>

			    <tr>
			        <td class="join_btn">
			            <input type="button" value="가입하기" class="join_btn">
			        </td>
			    </tr>
			    
			</table>
			
		</form>
	</div>

  
        
        
	<!-- footer -->
	<jsp:include page="../footer.jsp" />


	<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>


<script>

	/* ===== id 중복확인 ==== */
	$(function() {
		$("#mem_id").on("keyup", function() {
			
			var id = $("#mem_id").val();
			
			$.ajax({
				url : "../idCheck",
				type : "POST",
				data : {mem_id:id},
				success : function (data) {
					if(data == 1){
						$("#error_box_id").html("이미 사용 중인 아이디입니다.");
						return false;
					}else if(data == 2){
						$("#error_box_id").html("아이디를 입력해주세요");
					}else {
						$("#error_box_id").html("사용 가능한 아이디입니다.");
					}
				},
				
				error : function(request, status, error) {
					alert("에러");
					alert("code:" + request.status);
				}
			});	
		});	
	});
	
	
	
	
	/* ===== password 유효성 검사 ==== */
	$(function() {
		$("#mem_pwd").on("keyup", function() {
			
			var pwd = $("#mem_pwd").val();
			
			if(pwd.length < 8 || pwd.length > 17){
				$("#error_box_pwd").html("8~16자 영문 대소문자, 숫자 및 특수문자 두 가지 이상을 혼합하여 사용하세요.");
			}else if(pwd.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~,-])|([!,@,#,$,%,^,&,*,?,_,~,-].*[a-zA-Z0-9])/)){
				$("#error_box_pwd").html("사용 가능한 비밀번호입니다.")
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

	
	/* 가입하기 btn 클릭 시, input 창 빈칸 확인 => 빈칸 없을 시, form 주소 JoinInsert */
	
	$(function() {
		$(".join_btn").click(function() {
			
			var id = $("#mem_id").val();
			var pwd = $("#mem_pwd").val();
			var pwd_ck = $("#mem_pwd_ck").val();
			var name = $("#mem_name").val();
			var tel = $("#mem_tel").val();
			var email_id = $("#mem_email_id").val();
			var bday = $("#mem_bday").val();
			const formElement = $("#frm");
			
			
			if(id == ""){
				alert('아이디 항목은 필수 입력입니다.');
				$("#mem_id").focus();
				return false;
			}else if (pwd == ""){
				alert('비밀번호 항목은 필수 입력입니다.');
				$("#mem_pwd").focus();
				return false;
			}else if (pwd_ck == "") {
				alert('비밀번호를 확인해 주세요.');
				$("#mem_pwd_ck").focus();
				return false;
			}else if (pwd != pwd_ck){
				alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
				$("#mem_pwd_ck").focus();
				return false;
			}else if (name == ""){
				alert('이름을 기입해 주세요.');
				$("#mem_name").focus();
				return false;
			}else if (tel == ""){
				alert('전화번호를 기입해 주세요.');
				$("#mem_tel").focus();
				return false;
			}else if (email_id == ""){
				alert('이메일을 기입해 주세요.');
				$("#mem_email_id").focus();
				return false;
			}else if (bday == ""){
				alert('생년월일을 기입해 주세요.');
				$("#mem_bday").focus();
				return false;
			}else {
				alert(id + '님 가입을 축하드립니다!');
				formElement.attr("action", "/project/joinInsert");
				formElement.submit();
			}
			
			
			
		});
		
	});


</script>
	




</body>
</html>