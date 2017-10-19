<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
   .exdiary{
      border-radius: 5px;
      margin-left: 25px;
      text-align: center;
      width: 100%;
   }
   #eInfo{
      border-radius: 5px;
      width: 100%;
   }
   table{
      width: 100%;
      text-align: center;
      border:1px solid white;
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
   <div class="exdiary">
      <h1>트레이너가 제시하는 운동일지 보기</h1>
      선택날부터&nbsp<input type="date" name="select1" id="select1" /> 
      선택날까지<input type="date" name="select2" id="select2" /> 
      <input type="button" id="listButton" value="등록정보 보기" />
   </div>
   <div id="eInfo">
            
            
   </div>
</body>
<script>
$(function(){ 
   $("#listButton").click(function(){ 
      var _select1 = $("#select1").val();
      var _select2 = $("#select2").val();
      $.ajax({ 
         type: 'post' , 
         url: './tExerciseInfo' , 
         dataType : 'html' ,
         data : {select1:_select1,select2:_select2},
         success: function(data) { 
            $("#eInfo").html(data); 
            } 
      });   
   })   
})
</script>
</html>