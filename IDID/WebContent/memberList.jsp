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
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<script src = "amcharts/amcharts.js"> </script>
<script src = "amcharts/serial.js"> </script>
</head>
<style>

body {
font-family: 'Jeju Gothic', serif;
}
h1{
   text-align: center;
   margin-left: 15px;
}
body{
   margin : auto;
   text-align: center;
}
table{
   margin-top: 25px;
      margin-left : 50px;
      text-align: center;
      border:1px solid white;
      width: 100%;
}
td{
   width: 150px;
}
th{
   width: 150px;
   text-align: center;
   background-color: lightsteelblue;
   border-bottom: 1px solid gray;
}
#showGraph{
   
    width: 100%;
    padding-left: 0px;

}
#showMember{

}
.memberInfo{
   margin-left:100px;
}

.mainForm{
   text-align: center;
}
.memberI{
   text-align: left;
   width: 500px;
   height: 350px;
}
.memberI td{
   border-bottom: 1px solid lightgray;
   margin-bottom: 5px;
}
.return{
   width: 350px;
   height: 35px;
   margin-top: 15px;
   margin-right: 150px;
}
</style>
<body>
        <div>
      ${mListMake}
   </div>

      <div class="memberInfo">
      <div id="showMember">
      ${tmDetail }
      </div>
      <div id="showGraph">
      
      </div>
      </div>
</body>
<script>
function graph(obj){
   $.ajax({ 
      type: 'post' , 
      url: './tmfoodgraph' ,
      dataType : 'html' ,
      success: function(data) {
         $("#showGraph").html(data); 
         } 
   });   
}
function memberdetail(obj){
   $.ajax({ 
      type: 'post' , 
      url: './mDetail' ,
      dataType : 'html' ,
      success: function(data) {
         $("#showMember").html(data); 
         } 
   });   
}
function back(){
   mainForm.action="tMain.jsp";
   mainForm.submit();
 }
</script>
</html>