<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
</script>
<style>
h1{
   text-align: center;
}
table{
   margin : auto;
   text-align: center;
   border:1px solid white;
   width: 100%;
}
.tfoodForm{
   border-radius: 5px;
   width: 100%;
   margin-left: 50px;
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
<form name="tfoodForm" class="tfoodForm">
<h1>식단관리 페이지</h1>
${foodMListHtml}
<br/>
<div id="flist">

</div>
</form>
</body>
<script>
/* $(function(){ 
   $("#fdregister").click(function(){
      var id=$(this).attr('id');
      console.log(id);
      $.ajax({ 
         type: 'post' , 
         url: './tFoodList' ,
         dataType : 'html' ,
         success: function(data) {
            $("#flist").html(data); 
            } 
      });   
   })   
}) */
function fdreg(obj){
   $.ajax({ 
      type: 'post' , 
      url: './tFoodList' ,
      dataType : 'html' ,
      success: function(data) {
         $("#flist").html(data); 
         } 
   });   
}
function fdlist(obj){
   $.ajax({ 
      type: 'post' , 
      url: 'mFoodtList.jsp' ,
      dataType : 'html' ,
      success: function(data) {
         $("#flist").html(data); 
         } 
   });   
}
</script>
</html>