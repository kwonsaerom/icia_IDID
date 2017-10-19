<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
</script>
<title>Insert title here</title>
</head>
<style>
   h2{
      text-align: center;
   }
   #elist{
      width: 80%;
      margin-left: 10%;
   }
   table{
      width: 80%;
      margin-left: 10%;
   }
   tr{
      border-bottom:1px solid gray;
   }
   td{
            padding-bottom:25px;
      padding-top:25px;
      
   }
   #edate{
      width: 60%;
   }
   #exercisename{
      width: 60%;
   }
   #time{
      width: 60%;
   }
</style>
<body>
<form name="exerForm">
<div id="elist">
   <table>
      <tr>
         <td colspan="3"><h2>운동일지 작성페이지</h2></td>
      </tr>
      <tr>
            <td>작성날짜 : </td>
            <td colspan="2">
               <input type="date" name="edate" id="edate"/>
            </td>
         </tr>
         <tr>
            <td>운동이름 :</td> 
            <td colspan="2">
               <select name="exercisename" id="exercisename">
                  ${exnlist}
               </select>
            </td>
         </tr>
         <tr>
            <td>운동시간 : </td>
            <td colspan="2">
               <input type="text" name="time" id="time"/> 분
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="button" id="register" value="등록하기"/>
            </td>
         </tr>
        <tr>
           <td colspan="3" style="text-align:center;">
           선택날부터<input type="date" name="select1" id="select1"/> 선택날까지<input type="date" name="select2" id="select2"/>
            <input type="button" id="listButton" value="등록 정보 보기"/></td>
        </tr>
    </table>
      
    </div>
      <div id="eInfo">
      
   </div>
   </form>
</body>
<script>
/* function exerReg(){
   exerForm.action="./mExerReg";
   exerForm.submit();
} */
/* function exerInfo(){
   exerForm.action="./mExerciseInfo"
   exerForm.submit();
} */
$(function(){ 
   $("#register").click(function(){
      var _edate = $("#edate").val();
      var _exercisekind = $("#exercisekind").val();
      var _exercisename = $("#exercisename").val();
      var _time = $("#time").val();
      $.ajax({ 
         type: 'post' , 
         url: './mExerReg' , 
         dataType : 'html' , 
         data : {edate:_edate,exercisekind:_exercisekind,exercisename:_exercisename,time:_time},
         success: function(data) { 
            alert("등록완료");
            $("#elist").html(data); 
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
         url: './mExerciseInfo' , 
         dataType : 'html' ,
         data : {select1:_select1,select2:_select2},
         success: function(data) { 
            $("#eInfo").html(data); 
            } 
      });   
   })   
})
</script>
<script type="text/javascript">
   /* function exkind(){
      var selectBox = document.getElementById("exercisekind");
       var selectedValue = selectBox.options[selectBox.selectedIndex].value;
       alert(selectedValue+'을  선택하셨습니다.');
       $.ajax({
           url: './mExercise',
           data: {selectedValue:selectedValue},
           
           success:function(data)
           {
              $('#exercisename').html(data);
              console.log(data);
           },
           error:function(error)
           {
              console.log(error);
           }
        });
   } */
  </script>
</html>