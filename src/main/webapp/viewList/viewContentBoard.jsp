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
<title>Content</title>
<link rel="stylesheet" type="text/css" href="/project/css/viewNoticeContent.css">
</head>
<body>

<!-- 로그인 한 id 값 저장 -->
<c:set var="id" value="${sessionScope.mem_id}" />
<input type="hidden" value="${id}" id="mem_id">
<c:set var="auth" value="${sessionScope.mem_auth}" />
<input type="hidden" value="${auth}" id="auth">


	
	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	
	<div class="container col-xs-12">
		<div class="container-box"> 
			<div class="title">
				<h5>Q&A</h5>
			</div>
		
		<c:forEach var="bod" items="${list}">	
			<input type="hidden" value="${bod.bod_writer}" id="bod_writer">
			<table class="table-borderless table">
				
				<tr>
					<td class="con_title"><c:out value="${bod.bod_title}"/> Written by <c:out value="${bod.bod_writer}"/></td>
					<td class="con_date"><c:out value="${fn:substring(bod.bod_reg_date, 0, 11)}"/></td>
					<td class="con_hits"><c:out value="${bod.bod_hits}"/></td>
				</tr> 
			
			<tr>
				<td class="con_content" colspan="3" style="height:auto;">
					<pre><c:out value="${bod.bod_content}"/></pre>
				</td>
			</tr>
				
				
			</table>
		
			<div class="con_button_box">
				<a class="con_bnt_bk" href="/project/BoardSelect">Back to list</a>
				
				<div class="ed_del">
					<a class="con_bnt_edit" href="/project/ContentSelect?bod_no=${bod.bod_no}&modify=1">Edit</a>
					<a class="con_bnt_del" href="/project/BoardDelete?bod_no=${bod.bod_no}&bod_writer=${bod.bod_writer}">Delete</a>
				</div>
			</div>
		
		
		
		
			<!-- 댓글 -->
			<div id="com_input">
			</div>
			<table class="table" id="com_table">
				<!-- comment view -->
				<tr>
					<td class="com_view" colspan="2">Comment View</td>
				</tr>
			</table>

		 

		 	<form action="CommentInsert" method="post">
		 		<input type="hidden" value="${param.bod_no}" name="b_no" id="b_no">
		 		
				<table class="table table-borderless comment">
				<thead>
					<tr class="in">
						<td>Comment Write</td>
					</tr>
				</thead>
				<!-- 댓글 작성자 -->
				<tr class="in">
					<td><input class="in_writer" type="text" value="${id}" name="com_writer" readonly="readonly" ></td>
				</tr>
				
				<!-- 댓글 본문 -->
				<tr class="in"> 
					<td>
					<textarea class="in_content" id="content" class="form-control" placeholder="100자 이내로 입력 가능합니다." name="com_content"></textarea>
					</td>
				</tr>
					
				<tr class="in">
					<td><input class="in_btn" type="submit" value="submit!"></td>
				</tr>
				</table>
			</form>

		
		</c:forEach>	
		</div>
	</div>

	
	
	
	
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	
	
	
<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	/* 본문 내용 입력하지 않았을 시, alert */
	$(function () {
		$(".in_btn").click(function() {
			var content = $(".in_content").val()
			if (content == ""){
				alert("댓글 내용을 입력하세요.")
				return false
			}
		});
	});

	/* 본문 내용 delete btn click 시, confirm(확인, 삭제) */
	$(function () {
		
		
	    $('.con_bnt_del').click(function () {
	    	
			var auth = $("#auth").val();
			
			
			if (auth == '0'){
				if(!confirm('삭제하시겠습니까?')){
					return false;	
				}
			}else{
				var id = $("#mem_id").val();
				var writer = $("#bod_writer").val();
				
				if(id == writer){
					if(!confirm('삭제하시겠습니까?')){
						return false;	
					}
				}else {
					alert('작성자 외 삭제가 불가능합니다.');
					return false;
				}
			}
			
			
			
	    	
	    	
	        
	    });
	});


	/* Comment table ajax */
	$(document).ready(function() {
		
		var b_no = $("#b_no").val();
		var mem_id = $("#mem_id").val();
		var auth = $("#auth").val();
	
		$.ajax({
			url : "CommentSelect",
			type : "POST",
			data : {b_no:b_no},
			success : function (data) {
				
				
				var tableElement = "<tr>";
				
				$.each(data, function(i) { 
					
					var b_no = data[i].b_no;
					var com_no = data[i].com_no;
					var	com_writer = data[i].com_writer;
					var com_reg_date = data[i].com_reg_date;
					var com_content = data[i].com_content;
					var idNo = "delete" + com_no;
					
					tableElement += '<td class="com_writer" style="width:50px;">' + data[i].com_writer + '</td>'
									+ '<td class="com_date">' + com_reg_date.substring(0, 19) 
									+ '<a href="#" id="' + idNo + '"> x </a></td></tr>'
									+ '<tr><td class="com_content" colspan="2">' + com_content + '</td></tr>';
									
									
					$(document).on("click", "#"+idNo, function() {
						
						if(auth == '0'){
							if(!confirm('정말 삭제하시겠습니까?')){
								return false;
							}else {
								location.href = '/project/CommentDelete?b_no=' + b_no + '&com_no=' + com_no + '&com_writer=' + com_writer;
							}
						}else{
							if(mem_id == com_writer){
								if(!confirm('정말 삭제하시겠습니까?')){
									return false;
								}else {
									location.href = '/project/CommentDelete?b_no=' + b_no + '&com_no=' + com_no + '&com_writer=' + com_writer;
								}
							}else {
								alert('작성자 외 권한이 없습니다.');
								return false;
							}
						}
						
						
					});
				
				});
				
				$("#com_table").append(tableElement);
				
				
				
			},
			
			error : function(request, status, error) {
				alert("에러");
				alert("code:" + request.status);
			}
			
		}); 
		
		
	});




</script>
</body>
</html>