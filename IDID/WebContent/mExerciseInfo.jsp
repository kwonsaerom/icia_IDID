<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
table {
   width:80%;
   text-align: center;
   margin-left: 10%;
}
th{
   width: 150px;
   text-align: center;
   background-color: lightsteelblue;
   border-bottom: 1px solid gray;
}
h1{
   text-align: center;
}
form{
   text-align: center;
}
</style>
<body>
<br/>
<table border=1>
   <h1>등록정보</h1>
      <tr>
      <th>작성날짜</th>
      <th>운동이름</th>
      <th>운동한시간(분)</th>
      <th>소비칼로리(cal)</th>
      </tr>
   ${Melist}
   </table>
   <form name="mainForm">
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