<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- fontAwesome kit code -->
<script src="https://kit.fontawesome.com/6147d10ea4.js" crossorigin="anonymous"></script>
<!-- DatePicker CDN -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>KTC Report</title>

<script type="text/javascript">
$(document).ready(function(){
	/*
		값 설정 시작
	*/
	/*
		값 설정 종료
	*/

	/*
		페이지 이동 시작
	*/
	/*
		페이지 이동 종료
	*/
	
	/*
		이벤트 시작
	*/
	$('.excelUpload').on('click', function() {
		var key = $(this).data('key');
		var form = new FormData($('#excelForm' + key)[0]);
	    var input = $('#fileInput' + key).val();
		doExcelUploadProcess(form, input, key);
	});
	/*
		이벤트 종료
	*/
});
function doExcelUploadProcess(form, input, key) {
    if(input == null || input == '') {
    	alert('파일을 등록해주세요.');
    	return false;
    }
   	var sp = input.split('.');
   	var fileType = sp[sp.length - 1];
   	var date = $('#year').val() + '-' + $('#month').val();
   	if(fileType != null && (fileType == 'xlsx' || fileType == 'xls' || fileType == 'xlsm')) {
	    $.ajax({
	        url : '/rest/main/excelUpload/' + key + '/' + date
	        , data : form
	        , processData : false
	        , contentType : false
	        , type : 'POST'
	        , success : function(data) {
	        	console.log(data);
	        }
	        , error : function(e) {
	        	console.log(e.result);
	        }
	    });
   	} else {
   		alert('엑셀파일(xlsx, xls, xlsm)만 \n등록 가능합니다.');
   	}
}
</script>
</head>
<body>
    <!-- 본문 영역 (시작) -------------------------->
	<div style="border: 1px solid black;margin: 15px;display: grid;grid-template-columns: 40% 30% 30%;">
		<h3>등록 일자</h3>
		<input type="number" id="year" value="2021" style="font-size: 2rem;">
		<select id="month" style="font-size: 2rem;">
			<c:forEach var="i" begin="1" end="12">
			<option value="${i }">${i } 월</option>
			</c:forEach>
		</select>
	</div>
	<c:forEach var="k" items="${keyList }">
	<div style="border: 1px solid black;margin: 15px;display: grid;grid-template-columns: 40% 30% 30%;">
		<h3>${k.keyTitle }</h3>
		<form id="excelForm${k.keyName }" name="excelForm${k.keyName }" method="post" enctype="multipart/form-data">
			<input type="file" id="fileInput${k.keyName }" name="fileInput${k.keyName }">
		</form>
		<input type="button" class="adminBtn excelUpload" data-key="${k.keyName }" value="엑셀 등록">
	</div>
    </c:forEach>
	<!-- 본문 영역 (종료) -->
</body>
</html>