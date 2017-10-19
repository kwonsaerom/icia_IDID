<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
body{
   margin : auto;
}
#physial{
   border-radius: 5px;
    margin-left: 25px;
    width: 100%;
}
#inbody{
   width: 550px;
   height: 550px;
   margin-left: 200px;
}
.info{
   width: 65px;
}
.commit{
   height: 50px;
}
</style>
<body>
<div id="physial">
   <form name="mainForm">
      <table class="table table-striped" id="inbody">
         <tr style="text-align: center;"><td colspan="2"><h1>신장 / 체중 등록</h1></td></tr>
            <tr><td class="info">작성날짜 : </td><td><input type="date" name="Pdate" id="Pdate" /></td></tr>
            <tr><td class="info">신장 : </td><td><input type="text" name="Pheight" id="Pheight"/></td></tr>
            <tr><td class="info">체중 : </td><td><input type="text" name="Pweight" id="Pweight"/></td></tr>
            <tr class="commit" style="text-align: center;"><td colspan="2"><input type="button" id ="commit" value="등록하기"/></td></tr>
            <tr class="commit" style="text-align: center;"><td colspan="2"><input type="button" onclick="back()" value="돌아가기"/></td></tr>
         </table>

   </form>
 </div>
</div>
</body>
<script>
$(function(){
   $('#commit').click(function(){
      var _Pdate = $("#Pdate").val();
      var _Pheight = $("#Pheight").val();
      var _Pweight = $("#Pweight").val();
      $.ajax({ 
         type: 'post' , 
         url: './mPhysial' , 
         dataType : 'html' ,
         data : {Pdate:_Pdate,Pheight:_Pheight,Pweight:_Pweight},
         success: function(data) { 
            $("#physial").html(data); 
            alert('등록성공');
            } 
      });   
   })
})
   function back(){
      mainForm.action="mMain.jsp";
      mainForm.submit();
   }
</script>
</html>