<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" type="text/css"/>
<style>
   body{
      height: 100%;
   }
   #trainerInfo{
      width: 100px;
      margin-right: 10px;
   }
   .trainercontainer{
      width: 800px;
      height: 100%;
   }
   
   h1{
   font-family: 'Jeju Gothic', serif;
   }
   
   
 .trainercontainer{
   font-family: 'Nanum Pen Script', serif;
   }
   
</style>
</head>
<body>
	   <form name="detailForm" id="tForm">
	   <br/>
      <h1>트레이너 소개페이지 입니다.</h1><br/><br/>
      <input type="hidden" name="code" value="" />
         ${tList}
   </form>
</body>
<script>
	function detail(id){
		//Trainer 컨트롤러의 detailMove로 이동
		document.detailForm.action = "trainerDetail";
		document.detailForm.method = "post";
		document.detailForm.code.value = id;//input태그의 value의 값을 id로 줌
		document.detailForm.submit(); // 전송
	}
</script>
</html>