<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IDID</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
</head>
<body>
<h1>ID / PW 찾기</h1>
<form name="idpwForm">
   <div id="searchid">
      ID 찾기<br/>
      이름 : <input type="text" name="name"/><br/>
      이메일 : <input type="text" name="email"/>
      <input type="button" id="id" onclick="idsearch()" value="ID찾기"/><br/>
      회원님의 아이디는 ${m_id} ${t_id} 입니다.
   </div>
   <hr/>
   <div>
      PW 찾기<br/>
      아이디 : <input type="text" name="pwid"/><br/>
      이름 : <input type="text" name="pwname"/><br/>
      이메일 : <input type="text" name="pwemail"/>
      <input type="button" onclick="pwsearch()" value="PW찾기"/><br/>
      회원님의 비밀번호는 ${m_pw} ${t_pw} 입니다.
   </div>
   <input type="button" onclick="back()" value="돌아가기"/>
</form>
</body>
<script>
   function idsearch(){
      idpwForm.action="./searchId";
      idpwForm.method="post";
      idpwForm.submit();
   }
   function pwsearch(){
      idpwForm.action="./pwSearch";
      idpwForm.method="post";
      idpwForm.submit();
   }
   function back(){
      idpwForm.action="./index";
      idpwForm.submit();
   }
   $(function() {
      var msg = '${msg}';
      if(msg == "검색실패")
         alert("입력사항이 옳바르지 않습니다.");
   });
</script>
</html>