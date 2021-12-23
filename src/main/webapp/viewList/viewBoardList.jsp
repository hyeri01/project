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
<link rel="stylesheet" type="text/css" href="/project/css/contactBoard.css">
<meta charset="UTF-8">
<title>Q&A</title>
</head>
<body>
<c:set var="id" value="${sessionScope.mem_id}" />
<c:set var="auth" value="${sessionScope.mem_auth}" />

	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	
	<div class="container col-xs-12">
		<div class="container-box">
			
			<div class="title">
				<h5>Q&A</h5>
			</div>
			
            <table class="table-borderless table">
                
				<c:forEach var="bod" items="${list}">
                <tr>
                <c:choose>
					<c:when test="${auth != 0}">
	                    <td class="bod_no"><c:out value="${bod.bod_no}"/></td>
	                    <td class="bod_title">
	                        <a href="/project/viewList/contentPwdCheck.jsp?bod_no=${bod.bod_no}">
								<c:out value="${bod.bod_title}"/>
								[<c:out value="${bod.com_count}"/>]
							</a>
	                    </td>
                    </c:when>
                    <c:otherwise>
						<td><input type="checkbox" id="chk" value="${bod.bod_no}" name="chk"></td>
						<td class="bod_no"><c:out value="${bod.bod_no}"/></td>
						<td class="bod_title">
							<a href="/project/ContentSelect?bod_no=${bod.bod_no}">
							<c:out value="${bod.bod_title}"/>
							[<c:out value="${bod.com_count}"/>]
							</a>
						</td>
					</c:otherwise>
				</c:choose>
                    <td class="bod_writer"><c:out value="${bod.bod_writer}"/></td>
                    <td class="bod_date"><c:out value="${fn:substring(bod.bod_reg_date, 0, 11)}"/></td>
                    <td class="bod_count"><c:out value="${bod.bod_hits}"/></td>
                </tr>
                </c:forEach>
            </table>
            
            <c:choose>
				<c:when test="${auth != 0}">
					<div class="btn">
						<button id="write" onclick="writer()">Write</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="au_btn">
						<button id="write" onclick="writer()">Write</button>
						<button id="del_btn">delete</button>
					</div>
				</c:otherwise>
			</c:choose>
			
			<div class="form_search">
				<form action="BoardSearch" method="post" class="search">
				   	<select class="form-select form-select-sm" name="select" style="width: 90px;">
                        <option value="bod_title">title</option>
                        <option value="bod_writer">writer</option>
                    </select>
					<input class="in_search" type="text" placeholder="search" name="search" value="" autocomplete="off">
				</form>
			</div>
			
        </div>

    </div>
	
	
	
	
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	
	
<!-- jQuery cdn -->
 <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
 
	<!-- 세션 id값 조건 -->


	
	<c:choose>
		<c:when test="${id != null}">
			<script>
				$(function writer() {
					
					$("#write").click(function() {
						location.href = "/project/insert/contentInsertForm.jsp"
					})
					
				});
			</script>
		</c:when>
		
		<c:otherwise>
			<script>
			$(function writer() {
				
				$("#write").click(function() {
					alert("로그인 후 게시물을 등록하실 수 있습니다.")
					location.href = "/project/login/loginForm.jsp"
				})
				
			});
			</script>
		</c:otherwise>
	
	</c:choose>

<script>
	$(function() {
		$("#del_btn").click(function() {
			
			var chkArr = [];
			$("input[id='chk']:checked").each(function(i) {
				chkArr.push($(this).val());
			});
			
			if(chkArr == ""){
				alert('선택된 항목이 없습니다. 삭제할 항목을 선택해 주세요.');
				return false;
			}else {
				if(!confirm('정말 삭제하시겠습니까?')){
					return false;
				}else {
					$.ajax({
						url : "/project/BoardDelete",
						type : "POST",
						data : {chkArr:chkArr},
						traditional : true,
						success : function(data) {
							alert('삭제되었습니다.');
							location.reload();
						},
						
						error : function(request, status, error) {
							alert("에러");
							alert("code:" + request.status);
						}
						
					});
				}
				
			}
			
			
			
		});
	});



</script>


</body>
</html>