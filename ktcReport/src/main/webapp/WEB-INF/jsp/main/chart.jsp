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
	$('.time').each(function() {
		var time = $(this).text();
		var format = getFormatTime(time);
		console.log(time + ' > ' + format);
		$(this).text(format);
	});
	
	rateCal();
	
	minCheck();
	maxCheck();
	/*
		이벤트 종료
	*/
});

function getFormatTime(time) {
	if (time == null || time == '')
		return '';
	var hours = parseInt(time / 60 / 60);
	var min = parseInt(time / 60) % 60;
	var sec = parseInt(time % 60);
	hours = hours >= 10 ? hours : '0' + hours;
	min = min >= 10 ? min : '0' + min;
	sec = sec >= 10 ? sec : '0' + sec;
	return hours + ':' + min + ':' + sec;
}

function rateCal() {
	var sum = 0;
	$('.rate').each(function() {
		var count = parseInt($(this).text());
		sum += count;
	});
	
	$('.rate').each(function() {
		var count = parseInt($(this).text());
		
		$(this).text(count + ' (' + (count / sum * 100).toFixed(2) + '%)')
	})
}

function minCheck() {
	var min = -1;
	var minIdx = -1;
	$('.min').each(function(idx) {
		var data = parseInt($(this).text());
		if(min == -1 || min > data) {
			min = data;
			minIdx = idx;
		}
	});
	
	$('.min').eq(minIdx).closest('tr').css('border', '3px solid red');
}

function maxCheck() {
	var max = -1;
	var maxIdx = -1;
	$('.max').each(function(idx) {
		var data = parseInt($(this).text());
		if(max == -1 || max < data) {
			max = data;
			maxIdx = idx;
		}
	});
	
	$('.max').eq(maxIdx).closest('tr').css('border', '3px solid blue');
}
</script>
<style>
table {
	margin-top: 15px;
	font-size: 1.1rem;
    border-collapse: collapse;
	width: 100%;
}
table th, table td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>
    <!-- 본문 영역 (시작) -------------------------->
    <table>
    	<c:if test="${idx != 3 && idx != 5 }">
    	<thead>
    		<tr>
				<c:forEach var="t" items="${title }">
				<th>${t }</th>
    			</c:forEach>
    		</tr>
    	</thead>
    	</c:if>
    	<tbody>
    		<c:if test="${idx == 2 }">
			<c:forEach var="list" items="${list }" varStatus="status">
				<tr>
					<td>${list.dataName }</td>
					<td class="min max">${list.col0 }</td>
				</tr>
			</c:forEach>
    		</c:if>
    		
    		<c:if test="${idx == 3 }">
			<c:forEach var="list" items="${list }" varStatus="status">
				<tr>
					<td>${list.lang }</td>
					<c:forEach var="total" items="${list.year }">
					<c:if test="${status.index == 0 }">
					<td rowspan="5" style="border-left:5px double black;">${total.dataDate }</td>
					</c:if>
					<td>${total.currentSession }</td>
					</c:forEach>
				</tr>
			</c:forEach>
    		</c:if>
    		
    		<c:if test="${idx == 5 }">
			<c:forEach var="list" items="${list }" varStatus="status">
				<tr>
					<td>${list.lang }</td>
					<c:forEach var="total" items="${list.year }">
					<td style="border-left:5px double black;">${total.dataDate }</td>
					<td>${total.pageView }</td>
					</c:forEach>
				</tr>
			</c:forEach>
    		</c:if>
    		
    		<c:if test="${idx == 6 }">
			<c:forEach var="list" items="${list }" varStatus="status">
				<tr style="border-top: 5px double black;">
					<td colspan="3"><b>${list.month }</b></td>
				</tr>
				<c:forEach var="rank" items="${list.rank }" varStatus="rankStatus">
				<tr>
					<td>${rankStatus.count }</td>
					<td>${rank.dataName }</td>
					<td>${rank.col0 }</td>
				</tr>
				</c:forEach>
			</c:forEach>
    		</c:if>
    		
    		<c:if test="${idx != 2 && idx != 3 && idx != 5 && idx != 6 }">
			<c:forEach var="list" items="${list }" varStatus="status">
    		<tr>
				<c:if test="${idx == 4 || idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 || idx == 14 }">
					<td>${status.count }</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td>
    					<c:if test="${list.lang == 'ko' }">국문</c:if>
    					<c:if test="${list.lang == 'en' }">영문</c:if>
    					<c:if test="${list.lang == 'jp' }">일문</c:if>
    					<c:if test="${list.lang == 'cn' }">중문(간체)</c:if>
    					<c:if test="${list.lang == 'tw' }">중문(번체)</c:if>
    				</td>
				</c:if>
				
				<c:if test="${idx == 4 }">
    				<td>${list.LANG }</td>
				</c:if>
				
				<c:if test="${idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 }">
    				<td>${list.dataName }</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 9 || idx == 11 || idx == 12 }">
    				<td>${list.preSession }</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 }">
    				<td<c:if test="${list.dataName != null && list.dataName != 'TOTAL' }"> class="rate"</c:if>>${list.currentSession }</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 9 || idx == 11 || idx == 12 }">
    				<td><fmt:formatNumber value="${list.compareSession * 100 }" pattern=".00" />%</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 }">
    				<td>${list.newSession }</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 }">
    				<td><fmt:formatNumber value="${list.newSessionRate * 100 }" pattern=".00" />%</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 }">
    				<td>${list.avgSessionPageView }</td>
				</c:if>
				
				<c:if test="${idx == 4 || idx == 7 || idx == 81 || idx == 82 || idx == 9 || idx == 10 || idx == 11 || idx == 12 }">
    				<td class="time">${list.avgSessionTime }</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td>
    					<a href="http://koreatourcard.kr${list.dataName }" target="_blank">
    					http://koreatourcard.kr${list.dataName }
    					</a>
    				</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td>${list.prePageView }</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td>${list.currentPageView }</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td><fmt:formatNumber value="${list.comparePageView * 100 }" pattern=".00" />%</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td>${list.naturalPageView }</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td>${list.visitCount }</td>
				</c:if>
				
				<c:if test="${idx == 14 }">
    				<td class="time">${list.visitAvgTime }</td>
    			</c:if>
    		</tr>
   			</c:forEach>
    		</c:if>
    	</tbody>
    </table>
	<!-- 본문 영역 (종료) -->
</body>
</html>