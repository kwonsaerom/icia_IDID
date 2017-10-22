<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dao.*"%>

<%
	int listcount = ((Integer) request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	int maxpage = ((Integer) request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer) request.getAttribute("startpage")).intValue();
	int endpage = ((Integer) request.getAttribute("endpage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<style>
#table {
	margin-left: 50px;
}

#wBtn {
	
}
</style>
</head>
<body>
	<h1>리뷰 페이지</h1>
	<%-- <table width=50% border="0" cellspadding="0" cellspacing="0">
<%
if(listcount > 0){
%>
	<tr align="center" valign="middle">
		<td colspan="4">MVC 게시판</td>
		<td align="right">
			<font size='2'>글 개수:${listcount}</font>
		</td>
	</tr>
	<tr align="center" valign="middle">
		<td>
			<div align="center">번호</div>
		</td>
		<td>
			<div align="center">제목</div>
		</td>
		<td>
			<div align="center">작성자</div>
		</td>
		<td>
			<div align="center">날짜</div>
		</td>
		<td>
			<div align="center">조회수</div>
		</td>
	</tr>
	<%
		for(int i=0; i<reviewList.size(); i++){
			Review review = (Review)reviewList.get(i);
	%>
	<tr align="center" valign="middle">
		<td>
		<%= review.getR_code()%>
		</td>
		<td>
			<div align="left">
			<%if(review.get) %>
			</div>
		</td>
	</tr>
</table>
 --%>
	<form action="mReview" method="post">${reviewHtml}</form>
	<%-- <c:if test="${login==0}">

			</c:if>
			<c:if test="${login==1}">
				<!-- <form action="mReview_form" method="post"> -->
				<a href="javascript:AJ('mReview_form','#printP')" id="wBtn"><input
					type="button" value="글쓰기" /></a>
				<!-- </form> -->
			</c:if> --%>
	<!-- <table style="margin-left: 40%;">
		<tr height='20'>
			<td align='center'> -->
	<div>
		<ul>
			<%
				if (nowpage <= 1) {
			%>
			[이전]&nbsp;
			<%
				} else {
			%>
			<a class='pagination'
				href="javascript:AJ('mReview?page=<%=nowpage - 1%>','#printP')">[이전]</a>&nbsp;
			<%
				}
			%>
			<%
				for (int a = startpage; a <= endpage; a++) {
					if (a == nowpage) {
			%>
			[<%=a%>]
			<%
				} else {
			%>
			<a href="javascript:AJ('mReview?page=<%=a%>','#printP')">[<%=a%>]
			</a>
			<%
				}
			%>
			<%
				}
			%>
			<%
				if (nowpage >= maxpage) {
			%>
			[다음]
			<%
				} else {
			%>
			<a href="javascript:AJ('mReview?page=<%=nowpage + 1%>','#printP')">[다음]</a>
			<%
				}
			%>
			<!-- </td>
				<td style=text-align=right;> -->
			<!-- 	</td>
		</tr>
	</table> -->
		</ul>
	</div>
	<%-- <c:if test="${login==0}">
</c:if>
<c:if test="${login==1}">
<!-- <form action="mReview_form" method="post"> -->
	<div align='center'><a href="javascript:AJ('mReview_form','#printP')" id="wBtn"><input type="button" value="글쓰기" /></a></div>
<!-- </form> -->
</c:if> --%>
</body>
<script>
	function back() {
		history.back();
	}
</script>
</html>