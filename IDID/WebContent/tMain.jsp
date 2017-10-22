<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
		<style>
			.col-md-2,h1,.col-md-11{
				font-family: 'Jeju Gothic', serif;
			}
		</style>
</head>
<body>
	<div class="col-md-11" id="header">
		<a href="tMain" class="home">Home</a>
		<a href="logout" class="etc">로그아웃</a>
		<a href="tInfo" class="etc">내정보</a>
	</div>
	<div class="row"></div>
	<div class="col-md-11" id="content">
		<div class="col-md-2">
				<br>
				<li><a href="javascript:AJ('tProgram','#printP')">프로그램 등록</a></li>
				<li><a href="javascript:AJ('memberList','#printP')">회원관리</a></li>
				<li><a href="javascript:AJ('tFood_main','#printP')">식단관리</a></li>
				<li><a href="javascript:AJ('tExercise_main','#printP')">운동관리</a></li>
				<li><a href="javascript:AJ('mReview','#printP')">후기게시판</a></li>
		</div>
		<div id="printP" class="col-md-9">
		<br/>
			${mListMake}
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
	};
	
	$(document).ready(function(){
        $.ajax({ 
           type: 'post' , 
           url: './memberList' , 
           dataType : 'html' ,
           success: function(data) { 
              $("#printP").html(data); 
           }
        })
  })
</script>
</html>
