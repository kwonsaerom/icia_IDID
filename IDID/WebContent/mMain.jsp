<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src = "amcharts/amcharts.js"> </script>
<script src = "amcharts/serial.js"> </script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
		<style>
			.col-md-2,.col-md-11{
				font-family: 'Jeju Gothic', serif;
			}
		</style>
</head>
<body>

   <div class="col-md-11" id="header">
      <a href="mMain.jsp" class="home">Home</a>
      <a href="logout" class="etc">로그아웃</a>
      <a href="mInfo" class="etc">내정보</a>
   </div>
   <div class="row"></div>
   <div class="col-md-11" id="content">
      <div class="col-md-2">
            <br/>
            <li><a href="javascript:AJ('trainerInfo','#printP')">트레이너 소개</a></li>
            <li><a href="javascript:AJ('programInfo','#printP')">진행중인 프로그램</a></li>
            <li><a href="javascript:AJ('trainerPick','#printP')">트레이너 등록</a></li>
            <li><a href="mSchedule.jsp">일정 관리</a></li>
            <li><a href="javascript:AJ('mfoodgraph','#printP')">일지 그래프보기</a></li>
            <li><a href="javascript:AJ('mReview','#printP')">후기게시판</a></li>
      </div>
      <div id="printP" class="col-md-9">
         <form name="graphForm">
         
         </form>
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
   $(document).ready(function(){
         $.ajax({ 
            type: 'post' , 
            url: './mfoodgraph' , 
            dataType : 'html' ,
            success: function(data) { 
               $("#printP").html(data); 
            }
         })
   })
   </script>
   <script>
   /* $(document).ready(function(){
         $.ajax({ 
            type: 'post' , 
            url: './mExercisegraph' , 
            dataType : 'html' ,
            success: function(data) { 
               $("#printP").html(data); 
               } 
         });
   }) */
   /* $(function(){
   var pages = ['./mfoodgraph','./mExercisegraph'];
   var defer = new $.Deferred();
   var next = defer;
   
   for(var i=0; i<pages.length; i++){
      next = next.then(function(){
         return $.ajax(pages[i]).done(function(){
            $("#printF").html(data); 
            console.log('페이지 내용 : '+text);
         })
      });
   }
   }) */
</script>
</html>