<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<style>
body{
font-family: 'Jeju Gothic', serif;
}

   #elist{
      height: 350px;
   }
   table{
      width: 100%;
   }
   .exc{
      height: 350px;
   }
   #exercisename{
      width: 80%;
   }
   #edate{
      width: 80%;
   }
   #exercisename{
      width: 80%;
   }
   #time{
      width: 80%;
   }
   #exregister{
      width: 350px;
      height: 35px;
   }
</style>
</head>
<body>
<form name="exerForm">

   <table class="exc" class="table table-striped">
      <tr>
         <td colspan="3" class="ex"><h2>운동일지 작성페이지</h2></td>
      </tr>
      <tr>
            <td class="ex">작성날짜:</td> 
            <td colspan="2" class="ex"><input type="date" name="edate" id="edate"/></td>
         </tr>
         <tr>
            <td class="ex">운동이름 :</td> 
            <td colspan="2" class="ex">
               <select name="exercisename" id="exercisename">
                  ${exnlist}
               </select>
            </td>
         </tr>
         <tr>
            <td class="ex">운동시간:</td>
            <td colspan="2" class="ex"><input type="text" name="time" id="time"/> 분 </td>
        </tr>
        <tr>
           <td class="ex" colspan="3"><input type="button" id="exregister" value="등록하기"/></td>
        </tr>
        </table>
    <hr/>
     선택날부터<input type="date" name="select1" id="select1"/> 선택날까지<input type="date" name="select2" id="select2"/>
   <input type="button" id="listButton" value="등록 정보 보기"/>
   
   <div id="eInfo">
   
   </div>
   </form>
</body>
<script>
$(function(){ 
   $("#exregister").click(function(){
      var _edate = $("#edate").val();
      var _exercisekind = $("#exercisekind").val();
      var _exercisename = $("#exercisename").val();
      var _time = $("#time").val();
      $.ajax({ 
         type: 'post' , 
         url: './tExerReg' , 
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
         url: './tExerciseInfo' , 
         dataType : 'html' ,
         data : {select1:_select1,select2:_select2},
         success: function(data) { 
            $("#eInfo").html(data); 
            } 
      });   
   })   
})
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