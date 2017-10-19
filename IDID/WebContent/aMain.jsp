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
<script src = "amcharts/amcharts.js"> </script>
<script src = "amcharts/serial.js"> </script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<style>
   table{
      margin-top: 25px;
   }
   th{
      width: 150px;
      text-align: center;
      background-color: lightsteelblue;
      border-bottom: 1px solid gray;
   }
</style>
</head>

<body>
   <div class="col-md-11" id="header">
      <a href="aMain" class="home">Home</a>
      <a href="logout" class="etc">로그아웃</a>
      <a href="mInfo" class="etc">내정보</a>
   </div>
   <div class="row"></div>
   <div class="col-md-11" id="content">
      <div class="col-md-2">
            <li><a href="javascript:AJ('aMemberList','#printP')">일반회원 리스트</a></li>
            <li><a href="javascript:AJ('aTrainerList','#printP')">트레이너 리스트</a></li>
            <li><a href="javascript:AJ('aReviewDeleteMove','#printP')">리뷰 삭제</a></li>
      </div>
      <div id="printP" class="col-md-9">
      <h1>회원 리스트</h1>
         ${totList}
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
       if(msg ==""|| msg=="실패" || msg==null){
       
       }else{
              alert(msg);
          }
    });
</script>
</html>