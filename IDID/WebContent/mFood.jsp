<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>Insert title here</title>
</head>
<style>
   .food{
      margin-left:10%;
      width: 80%;
      text-align: center;
      height: 500px;
   }
   #fdate{
      width: 40%;
   }
   #fday{
      width: 40%;
   }
   #foodkind{
      width: 40%;
   }
   #foodname{
      width: 40%;
   }
   #famount{
      width: 40%;
   }
   .food td{
      padding-top: 5px;
      padding-bottom: 5px;
      border-bottom: 1px solid lightgray;
   }
   #register{
      width: 350px;
      height: 35px;
   }
</style>
<body>
<form name = "foodForm">
<div id="flist">
   <table class="food">
      <tr>
         <td colspan="3"><h2>식단일지 작성페이지</h2></td>
      </tr>
      <tr>
         <td colspan="3">작성 날짜 : <input type="date" name="fdate" id="fdate"/></td>
      </tr>
      <tr>   
         <td colspan="3">
            식사 시간 : 
            <select name="fday" id="fday">
               <option>아침</option>
                  <option>점심</option>
                  <option>저녁</option>
            </select>
         </td>
      </tr>
      <tr>
         <td colspan="3">
            음식 분류 : 
            <select name="foodkind" id="foodkind" onchange="fkind();">
               ${fklist}
            </select>
         </td>
      </tr>
      <tr>
         <td colspan="3">
            음식 이름 : <select name="foodname" id="foodname">
            
            </select>
         </td>
      </tr>
      <br/>
      <%-- <c:if test="${fklist eq '음식을 선택하세요'}">
      섭취량<input type="text" name="famount" id="famount">g
      </c:if>
      <c:if test="${fklist eq '육류'}">
      섭취량<input type="text" name="famount" id="famount">g
      </c:if>
      <c:if test="${fklist eq '과일'}">
      섭취량<input type="text" name="famount" id="famount">개
      </c:if> --%>
      <tr>
         <td colspan="3">섭취량 : <input type="text" name="famount" id="famount">&nbsp g
         </td>
      </tr>
      <tr>
         <td colspan="3" style="text-align:center;"><input type="button" id="register" value="등록 하기"/></td>
      </tr>
   </table>
   <hr/>
   
   <p style="text-align: center;">
      선택날부터<input type="date" name="select1" id="select1"/> 선택날까지<input type="date" name="select2" id="select2"/>
      <input type="button" id="listButton" value="등록 정보 보기"/>
   </p>
   
   </div>
   <hr/>
    </div>
   <div id="fInfo">
   
   </div>
</form> 
</body>
<script>
/* function foodReg(){
   foodForm.action="./mFoodReg";
   foodForm.submit();
} */
$(function(){ 
   $("#register").click(function(){
      var _fdate = $("#fdate").val();
      var _fday = $("#fday").val();
      var _foodkind = $("#foodkind").val();
      var _foodname = $("#foodname").val();
      var _famount = $("#famount").val();
      $.ajax({ 
         type: 'post' , 
         url: './mFoodReg' , 
         dataType : 'html' ,
         data : {fdate:_fdate,fday:_fday,foodkind:_foodkind,foodname:_foodname,famount:_famount},
         success: function(data) {
            alert("등록완료");
            $("#flist").html(data); 
            } 
      });   
   })   
})
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
/* function foodInfo(){
   foodForm.action="./mFoodInfo";
   foodForm.submit();
} */
</script>
<script type="text/javascript">
   function fkind(){
      var selectBox = document.getElementById("foodkind");
       var selectedValue = selectBox.options[selectBox.selectedIndex].value;
       alert(selectedValue+'을  선택하셨습니다.');
       $.ajax({
           url: './mFood',
           data: {selectedValue:selectedValue},
           
           success:function(data)
           {
              $('#foodname').html(data);
              console.log(data);
           },
           error:function(error)
           {
              console.log(error);
           }
        });
   }
  </script>
</html>