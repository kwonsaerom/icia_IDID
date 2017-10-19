<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>IDID</title>
		<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
		<style>
			.col-md-2{
				font-family: 'Jeju Gothic', serif;
			}
		</style>
	</head>
	<body>
		<div class="col-md-11" id="header">
            <a href="index" class="home">Home</a>
            <a href="loginForm" class="etc">로그인</a>
      </div>
      <div class="row"></div>
      <div class="col-md-11" id="content">
         
         <div class="col-md-2">
         		<br/>
               <li><a href="javascript:AJ('homeInfo','#printP')">홈페이지 소개</a></li>
               <li><a href="javascript:AJ('trainerInfo','#printP')">트레이너 소개</a></li>
               <li><a href="javascript:AJ('programInfo','#printP')">프로그램 안내</a></li>
               <li><a href="javascript:AJ('mReview','#printP')">후기게시판</a></li>
           </div>
         <div id="printP" class="col-md-9">
            <h1>메인화면에 이미지가 들어갈 영역입니다.</h1>
         </div>
      </div>
      <div class="footer">
               <h5>여기는 footer</h5>
           
       </div>

	</div>
	</body>
	<script>
	function AJ(url, position){
		$.ajax({
			url: url,
			type:"get",
			success: function(html){
				$(position).html(html);
			},
			error: function(error){
				console.log(error);
			}
		});
	}
	   $(function() {
	       var msg = '${msg}';
	       if(msg == '탈퇴 완료'){
	          alert(msg);
	       }
	    });
	</script>
</html>