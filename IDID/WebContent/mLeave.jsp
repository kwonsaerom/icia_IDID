<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/tLeave.css"/>

<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<style>
body{
font-family: 'Jeju Gothic', serif;
}
p#duplicate {
   margin: 0px;
   text-align: left;
   color: crimson;
   font-weight: bolder;
}
</style>
</head>
<script>
   function forward(obj){
      alert(obj.value);
      if(obj.value=='회원탈퇴'){
         document.deleteform.submit();
      }else if(obj.value=='돌아가기'){
         history.back();
         document.deleteform.submit();
      }
      
   }
</script>
<body>
   <form  name="deleteform" id="delete" action="memberDelete">
   <br/>
   <h1>회원 탈퇴</h1>
   <p style="text-align: left; padding-left: 25px;">아이디 :${m_id}</p><br/>
  	<p> 비밀번호 <input type="password" name="deletePw" id="pw"/></p><br/>
  	 <p>비밀번호 확인 <input type="password" name="pw2" id="pw2"/></p><br/>
   <p id="duplicate" name="check"></p>
   <div class="leavebutton">
      <input type="button" onclick="forward(this)" id="btn1" value="회원탈퇴"/>
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