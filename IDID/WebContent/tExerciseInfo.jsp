<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
   margin : auto;
   text-align: center;
}
#table {
   width:80%;
   margin-left:50px;
   text-align: center;
}
</style>
<body>
<form name="mainForm">
<table border=1 class="table table-striped" id="table">
   <tr colspan="4"><h1>등록정보</tr>
   <tr>
      <th>작성날짜</th>
      <th>운동이름</th>
      <th>운동한시간(분)</th>
      <th>소비칼로리(cal)</th>
   </tr>
   ${Telist}
   </table>
   <input type="button" onclick="back()" value="메인으로 돌아가기" />
   </form>
</body>
<script>
   function back(){
      mainForm.action="mMain.jsp";
      mainForm.submit();
   }
</script>
</html>