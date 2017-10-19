<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/tLeave.css"/>
<style>
</style>
</head>
<script>
   function forward(obj){
      if(obj.value=='트레이너탈퇴'){
         document.deleteform2.submit();
      }else if(obj.value=='돌아가기'){
         history.back();
         document.deleteform2.submit();
      }
      
   }
</script>
<body>
   <form  name="deleteform2" id="delete" action="trainerDelete">
   <h1>회원 탈퇴</h1>
   아이디 : ${t_id}<br/>
   비밀번호 <input type="password" name="pw" id="pw"/><br/>
   비밀번호 확인 <input type="password" name="pw2" id="pw2"/><br/>
   <p id="duplicate" name="check"></p>
   <div class="leavebutton">
      <input type="button" onclick="forward(this)" id="btn1" id="deleteSubmit" value="트레이너탈퇴"/>
      <input type="button" onclick="forward(this)" id="btn2" value="돌아가기"/>
   </div>
   </form>
</body>
<script>

$(function() {
    $('#pw').keyup(function() {
       $('font[name=check]').text('');
    });

    $('#pw2').keyup(function() {
       if ($('#pw').val() != $('#pw2').val()) {
          $('p[name=check]').text('');
          $('p[name=check]').html("암호틀림");
       } else {
          $('p[name=check]').text('');
          $('p[name=check]').html("암호맞음");
       }
    });
 });
</script>
</html>