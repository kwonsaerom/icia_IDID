<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
</script>
<title>Insert title here</title>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<style>
body{
font-family: 'Jeju Gothic', serif;
}
h1{
   text-align: center;
}
table{
   margin : auto;
   width:100%;
   text-align: center;
   border:1px solid white;
}
.exForm{
   border-radius: 5px;
   margin-left: 50px;
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

</style>
</head>
<body>
<form name="exForm" class="exForm">
<h1>운동관리 페이지</h1>
   ${tExerciseListHtml}
<div id="elist">

</div>
</form>
</body>
<script>
function exreg(obj){
   $.ajax({ 
      type: 'post' , 
      url: './tExerciseList' ,
      dataType : 'html' ,
      success: function(data) {
         $("#elist").html(data); 
         } 
   });   
}
function exlist(obj){
   $.ajax({ 
      type: 'post' , 
      url: 'mExercisetList.jsp' ,
      dataType : 'html' ,
      success: function(data) {
         $("#elist").html(data); 
         } 
   });   
}
</script>
</html>