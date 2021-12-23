<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Member List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
   crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="/project/css/memberList.css">
</head>
<body>

	<!-- header -->
	<jsp:include page="../header.jsp" />
	
	<!-- content -->
	<div class="container col-xs-12">
		<div class="container-box"> 
        
        	<!-- search form -->
         	<form class="search" action="MemberListSearch" method="post">
        		<select class="form-select form-select-sm" name="select" style="width: 90px;">
        			<option value="mem_id">id</option>
        			<option value="mem_name">name</option>
        		</select>
        		<input class="in_search" type="text" placeholder="search" name="search" value="" autocomplete="off">
        	</form>
        	
        	<!-- member List table -->
            <table class="table">

                <thead>
                    <tr>
                        <td colspan="7">
                            <h4>Member List</h4>
                        </td>
                    </tr>
                </thead>
				
                <tbody>
                    <tr class="mem_table">
                    	<td></td>
                        <td>id</td>
                        <td>name</td>
                        <td>tel</td>
                        <td>email</td>
                        <td>birth day</td>
                        <td>regDate</td>
                    </tr>
                    <c:forEach var="mem" items="${list}">
                    <tr class="mem_info">
                    	<td><input type="checkbox" id="chk" value="${mem.mem_no}" name="chk"></td>
                        <td><c:out value="${mem.mem_id}" /></td>
                        <td><c:out value="${mem.mem_name}" /></td>
                        <td><c:out value="${mem.mem_tel}" /></td>
                        <td><c:out value="${mem.mem_email}" /></td>
                        <td><c:out value="${mem.mem_bday}" /></td>
                        <td><c:out value="${fn:substring(mem.mem_reg_date, 0, 19)}" /></td>
                    </tr>
                    </c:forEach>
                </tbody>
                
                <tfoot>
                	<tr>
                		<td colspan="7" class="btn_foot">
                			<button id="del_btn">delete</button>
                		</td>
                	</tr>
                </tfoot>
                
            </table>
            
           	
            
        </div>
    </div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp" />

<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script type="text/javascript">
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
						url : "/project/MemberDelete",
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