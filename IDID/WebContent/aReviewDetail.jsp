<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function back(){
   history.back();
   document.deleteForm.submit();
}
</script>
<style>
table {
   text-align: center;
}
</style>
</head>
<body>
<form name="deleteForm">
<table border='1'>
   <tr>
      <td>글 번호</td>
      <td>${r_code}</td>
   </tr>
   <tr>
      <td>글 제목</td>
      <td>${r_title}</td>
   </tr>
   <tr>
      <td>작성자 아이디</td>
      <td>${r_m_id}</td>
   </tr>
   <tr>
      <td>글 내용</td>
      <td>${r_substance}</td>
   </tr>
</table>
<br/>
<a href="rvDelete?r_code=${r_code}"><input type="button" value="삭제"/></a>
<input type="button" value="돌아가기" onclick="back()"/>
</form>
</body>
</html>