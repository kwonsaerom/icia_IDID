<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원이 등록한 식단일지 보기</h1>
선택날부터<input type="date" name="select1" id="select1" /> 선택날까지<input
            type="date" name="select2" id="select2" /> <input type="button"
            id="listButton" value="등록정보 보기" />
            <div id="fInfo">
            </div>
</body>
<script>
$(function(){ 
   $("#listButton").click(function(){ 
      var _select1 = $("#select1").val();
      var _select2 = $("#select2").val();
      $.ajax({ 
         type: 'post' , 
         url: './mFoodInfo' , 
         dataType : 'html' ,
         data : {select1:_select1,select2:_select2},
         success: function(data) { 
            $("#fInfo").html(data); 
            } 
      });   
   })   
})
</script>
</html>