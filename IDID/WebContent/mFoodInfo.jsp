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
table {
   margin : auto;
   text-align: center;
}
th{
   width: 150px;
   text-align: center;
   background-color: lightsteelblue;
   border-bottom: 1px solid gray;
}
</style>
<body>
<form name="mainForm">
<br/>
<table border=1>
   등록정보
   <tr>
      <th>작성날짜</th>
      <th>섭취시간</th>
      <th>음식분류</th>
      <th>음식이름</th>
      <th>섭취한그람수</th>
      <th>섭취한총칼로리</th>
   </tr>
   ${Mflist}
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