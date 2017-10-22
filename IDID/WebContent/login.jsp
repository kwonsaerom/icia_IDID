<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IDID</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<style>
body{
font-family: 'Jeju Gothic', serif;
}
</style>
</head>
<body>
   <div class="flag">
   <br/><br/><br/><br/>
      <h1>로그인페이지</h1>
   </div>
   <div class="header">
      <h1>
      </h1>
   </div>
   <div class="container">
      <div class="login-block">
         <h1>Login</h1>
         <br/>
         <!-- <div>
            <input type="radio" checked="checked" id="memberJoin" name="join"
               value="일반회원" onclick="onOff(this.value)" />일반회원 <input
               type="radio" name="join" id="trainerJoin" value="트레이너"
               onclick="onOff(this.value)" />트레이너
         </div> -->
         <!-- <div id="memLog">
            <form action="mLogin" method="post" id="login">
            일반회원
               <input type="text" value="" placeholder="Username" id="username"
                  name="userId" /> <input type="password" value=""
                  placeholder="Password" id="password" name="userPw" /> <input
                  type="button" id="loginSubmit" value="로그인" />
            </form>
         </div>
         <div id="trainerLog" style="display:none">
            <form action="tLogin" method="post" id="login">
            트레이너
               <input type="text" value="" placeholder="Username" id="username"
                  name="userId" /> <input type="password" value=""
                  placeholder="Password" id="password" name="userPw" /> <input
                  type="button" id="loginSubmit" value="로그인" />
            </form>
         </div>
         <a href="idPwSearch"><input type="button" value="ID/PW 찾기"
            id="idPwSearch" /></a>
      </div>
      <div class="join-block">
         <a href="memberJoin"><input type="button" value="일반회원가입"
            class="join" /></a> <a href="trainerJoin"><input type="button"
            value="트레이너회원가입" class="join" /></a> <input type="button" value="돌아가기"
            class="join" />
      </div> -->
         <div class="login_conts">
            <div class="login_inconts">
               <div class="login_form_v2">
                  <fieldset>
                     <legend>로그인 정보 입력</legend>
                     <div class="mem_choice">
                        <label for="memY"> <input id="memY" title="회원"
                           name="isNonMember" type="radio" checked class="chk" value="Y"
                           onClick="changeField(this);" style="width: 20px; height: 20px;" />일반회원
                        </label> 
                        <label for="memN"> <input id="memN" title="트레이너"
                           name="isNonMember" type="radio" class="chk" value="N" 
                           onClick="changeField(this);" style="width: 20px; height: 20px;" />트레이너
                        </label>
                        <label for="memA"> <input id="memA" title="관리자"
                           name="isNonMember" type="radio" class="chk" value="A" 
                           onClick="changeField(this);" style="width: 20px; height: 20px;" />관리자
                        </label>
                     </div>
                     <div id="memLogin" class="mem_login" style="display:;">
                        <div class="save_idW">
                           <form action="mLogin" method="post">
                        <ul>
                           <li><label for="loginName">일반회원 아이디</label><input type="text" value="" placeholder="Username" id="username" name="userId"/></li>
                           <li><label for="passWord">일반회원 비밀번호</label><input type="password" value="" placeholder="Password" id="password" name="userPw"/></li>
                        </ul>

                              <input type="submit" id="loginSubmit" value="로그인" class="btn_login">
                           </form>
                        </div>
                     </div>

                     <div id="nonLogin" class="mem_login" style="display: none;">
                        <div class="save_idW">
                           <form action="tLogin" method="post">
                        <ul>
                           <li><label for="loginName">트레이너 아이디</label><input type="text" value="" placeholder="Username" id="username" name="userId"/></li>
                           <li><label for="passWord">트레이너 비밀번호</label><input type="password" value="" placeholder="Password" id="password" name="userPw"/></li>
                        </ul>
                              <input type="submit" id="loginSubmit" value="로그인" class="btn_login">
                           </form>
                           </div>
                        </div>

                        <div id="adLogin" class="mem_login" style="display:none;">
                        <div class="save_idW">
                           <form action="aLogin" method="post">
                        <ul>
                           <li><label for="loginName">관리자 아이디</label><input type="text" value="" placeholder="Username" id="username" name="userId"/></li>
                           <li><label for="passWord">관리자 비밀번호</label><input type="password" value="" placeholder="Password" id="password" name="userPw"/></li>
                        </ul>
                              <input type="submit" id="loginSubmit" value="로그인" class="btn_login">
                           </form>
                        </div>
                     </div>
                     </div>
                     <div class="join-block">
                        <a href="memberJoin"><input type="button" value="일반회원가입"
                           class="join" /></a> 
                           <a href="trainerJoin"><input type="button"
                           value="트레이너회원가입" class="join" /></a> 
                           <input type="button" onclick="back()" value="돌아가기" class="join" />
                     </div>
                     <a href="idPwSearch.jsp"><input type="button" id="IdPwSearch"
                        value="ID/PW 찾기" /></a>
               </div>
</body>
<script>
   function back(){
      history.back();
   }
   function checkForm() {
      alert('로그인');
      document.getElementById('#logBtn').submit();
   }
   function changeField(obj) {
      try {
         var form = document.login_form;
         var memLogin = document.getElementById("memLogin");
         var nonLogin = document.getElementById("nonLogin");
         var adLogin = document.getElementById("adLogin");
         var memCaptcha = document.getElementById("mem_captcha");

         if (obj.value == 'Y') {
            if (memCaptcha) {
               memCaptcha.style.display = "none";
            }
            memLogin.style.display = "";
            adLogin.style.display = "none";
            nonLogin.style.display = "none";
            form.authMethod.value = "memLogin";
            form.loginName.value = "";
            form.passWord.value = "";
         } else if(obj.value=='N'){
            if (memCaptcha) {
               memCaptcha.style.display = "";
            } 
            memLogin.style.display = "none";
            adLogin.style.display = "none";
            nonLogin.style.display = "";
            form.authMethod.value = "nonLogin";
         }else {
             if (memCaptcha) {
               memCaptcha.style.display = "";
            } 
            adLogin.style.display = "";
            memLogin.style.display = "none";
            nonLogin.style.display = "none";
            form.authMethod.value = "adLogin";
            }
         } catch (ex) {
         Logger.LoggingMsg('로그인 자바스크립트 오류');
      }
   }
   
   $("#loginSubmit").click(function(){
        var userId = $('#username').val();
        var userPw = $('#password').val();
        console.log(userId);
        console.log(userPw);
        if(userId == "" || userPw == ""){
           alert("아이디 또는 비번를 입력해주세요!");      
        } 
           
          if(userId != "" && userPw != ""){
           $("#login").submit();
        } 
     });
 $(function() {
    var msg = '${msg}';
    if(msg == "실패")
       alert("로그인정보가 옳바르지 않습니다.");
 });
 
 document.addEventListener('keyup', function(e){
     var userId = $('#username').val();
      var userPw = $('#password').val();
        //13은 enter임
        if(e.keyCode==13){
           if(userId == "" || userPw == ""){
               alert("아이디 또는 비번를 입력해주세요!");      
            } 
               
              if(userId != "" && userPw != ""){
               $("#login").submit();
            } 
         }
  });
</script>
</html>