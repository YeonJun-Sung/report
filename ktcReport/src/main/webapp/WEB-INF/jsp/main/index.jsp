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
	$('.moveUpload').on('click', function() {
		var form = $('#upload');
		form.attr('action', '/main/excelUpload');
		form.submit();
	});
	
	$('.moveChart').on('click', function() {
		var idx = $(this).data('idx');
		var date = $('#year').val() + '-' + $('#month').val();
		var preDate = '';
		if($('#month').val() == 1) {
			preDate = ($('#year').val() - 1) + '-' + 12;
		}
		else {
			preDate = $('#year').val() + '-' + ($('#month').val() - 1);
		}
		$('#idx').val(idx);
		$('#date').val(date);
		$('#preDate').val(preDate);
		
		var form = $('#chart');
		form.attr('action', '/main/chart');
		form.attr('target', '_blank');
		form.submit();
	});
	/*
		페이지 이동 종료
	*/
	
	/*
		이벤트 시작
	*/
	/*
		이벤트 종료
	*/
});
</script>
<style>
.moveChart {
	margin: 15px;
	font-size: 1.1rem;
	cursor: pointer;
}
.moveUpload {
	cursor: pointer;
}
</style>
</head>
<body>
	<form id="upload"></form>
	<form id="chart">
		<input type="hidden" id="idx" name="idx">
		<input type="hidden" id="date" name="date">
		<input type="hidden" id="preDate" name="preDate">
	</form>
    <!-- 본문 영역 (시작) -------------------------->
		
	<div style="display: grid;grid-template-columns: 50% 50%;">
		<div class="moveUpload" style="border: 1px solid black;margin: 15px;height: 100%;">
			<h3 style="position: relative;top: 50%;left: 50%;line-height: 0;display: inline;margin-block: 0;">엑셀 업로드</h3>
		</div>
		<div style="border: 1px solid black;margin: 15px;height: 100%;">
			<div style="border: 1px solid black;margin: 15px;display: grid;grid-template-columns: 40% 30% 30%;">
				<h3 style="text-align:center;">통계 확인 일자</h3>
				<input type="number" id="year" value="2021" style="font-size: 2rem;">
				<select id="month" style="font-size: 2rem;">
					<c:forEach var="i" begin="1" end="12">
					<option value="${i }"<c:if test="${i == 3 }"> selected</c:if>>${i } 월</option>
					</c:forEach>
				</select>
			</div>

			<h3 style="text-align:center;">통계 보기</h3>

			<div class="moveChart" data-idx="1">1. 홈페이지 주요 지표</div>
			<div class="moveChart" data-idx="2"><b>2. 일별 방문자 수 > 완료</b></div>
			<div class="moveChart" data-idx="3"><b>3. 어권별 사이트 유입(누적 지표) > 완료</b></div>
			<div class="moveChart" data-idx="4"><b>4. 어권별 사이트 유입(당월 지표) > 완료</b></div>
			<div class="moveChart" data-idx="5"><b>5. 어권별 페이지뷰 추이 > 완료</b></div>
			<div class="moveChart" data-idx="6"><b>6. 국가별 사이트 유입(누적 지표) > 완료</b></div>
			<div class="moveChart" data-idx="7"><b>7. 국가별 사이트 유입(당월 지표) > 완료</b></div>
			<div class="moveChart" data-idx="81"><b>8-1. 디바이스별 사이트 유입 > 완료</b></div>
			<div class="moveChart" data-idx="82"><b>8-2. 운영체제별 사이트 유입 > 완료</b></div>
			<div class="moveChart" data-idx="9"><b>9. 유입채널 : Overview > 완료</b></div>
			<div class="moveChart" data-idx="10"><b>10. Organic Search : 검색 키워드 TOP 10 > 완료</b></div>
			<div class="moveChart" data-idx="11"><b>11. Referral : 외부 도메인 TOP 10 > 완료</b></div>
			<div class="moveChart" data-idx="12"><b>12. Social : 소셜 네트워크 TOP 5 > 완료</b></div>
			<!-- 
			<div class="moveChart" data-idx="13">13. 홈페이지 인기 검색 키워드 TOP 20</div>
			 -->
			<div class="moveChart" data-idx="14"><b>14. 페이지뷰 TOP 10 > 완료</b></div>
		</div>
	</div>
	<!-- 본문 영역 (종료) -->
</body>
</html>