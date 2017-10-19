<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
   #table{
      width: 100%;
      height: 350px;
   }
   #table input[type="text"]{
      width: 80%;
   }
   #table input[type="date"]{
      width: 80%;
   }
   #table select{
      width: 80%;
   }
</style>
</head>
<body>
   <form name="foodForm">
      <div id="flist">
         <table class="table table-striped" id="table">
            <tr>
               <td colspan="3"><h2>식단을 등록하세요</h2></td>
            </tr>
            <tr>
               <td>작성날짜 :</td>
               <td colspan="2"><input type="date" name="fdate" id="fdate" /></td>
            </tr>
            <tr>
               <td>
                  식사시간 :
               </td>
               <td colspan="2">
                  <select name="fday" id="fday">
                     <option>아침</option>
                     <option>점심</option>
                     <option>저녁</option>
                  </select> 
               </td>
            </tr>
            <tr>
               <td>
                  음식분류 : 
               </td>
               <td colspan="2">
                  <select name="foodkind" id="foodkind" onchange="fkind();"> ${fklist} </select> 
               </td>
            </tr>
            <tr>
               <td>
                  음식 이름 :
               </td>
               <td colspan="2"> 
                  <select name="foodname" id="foodname">
      
                  </select>
               </td>
            </tr>
            <tr>
               <td>섭취량 : </td>
               <td colspan="2">
                  <input type="text" name="famount" id="famount">g
               </td>
            </tr>
            <tr>
               <td colspan="3"><input type="button" id="register" value="등록 하기" /></td>
            </tr>
         </table>
         <hr />
          선택날부터<input type="date" name="select1" id="select1"/> 선택날까지<input type="date" name="select2" id="select2"/>
            <input type="button" id="listButton" value="등록 정보 보기"/>
    </div>
   <div id="fInfo">
   
   </div>
   </form>
</body>
<script type="text/javascript">
$(function(){ 
   $("#register").click(function(){
      var _fdate = $("#fdate").val();
      var _fday = $("#fday").val();
      var _foodkind = $("#foodkind").val();
      var _foodname = $("#foodname").val();
      var _famount = $("#famount").val();
      $.ajax({ 
         type: 'post' , 
         url: './tFoodReg' , 
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
         url: './tFoodInfo' , 
         dataType : 'html' ,
         data : {select1:_select1,select2:_select2},
         success: function(data) { 
            $("#fInfo").html(data); 
            } 
      });   
   })   
})
   function fkind() {
      var selectBox = document.getElementById("foodkind");
      var selectedValue = selectBox.options[selectBox.selectedIndex].value;
      alert(selectedValue + '을  선택하셨습니다.');
      $.ajax({
         url : './mFood',
         data : {
            selectedValue : selectedValue
         },

         success : function(data) {
            $('#foodname').html(data);
            console.log(data);
         },
         error : function(error) {
            console.log(error);
         }
      });
   }
</script>
</html>