<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
   function forward(obj){
      if(obj.value=='등록하기'){
         document.pickform.action="programPick";
         document.pickform.submit();
      }
   }
</script>
<link rel="stylesheet" type="text/css" href="css/trainerPick.css"/>
</head>
<body>
   <div class="pick">
      <form name="pickform">
      <h1>트레이너 등록 페이지</h1>
         트레이너 이름 : ${li} <br/>
         프로그램 선택 : 
         <select id="program" name="p_name"><option value='선택'>프로그램 선택</option></select><br/><br/>
      <input type="button" onclick="forward(this)" value="등록하기"/>
      </form>
   </div>
</body>
<script>
function tname() {
    var selectBox = document.getElementById("p_t_name");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    
    $.ajax({
       url: './trainerPick',
       data: {selectedValue:selectedValue},
       
       success:function(data)
       {
          console.log(data);
          
          $('#program').html(data);
       },
       
       error:function(error)
       {
          console.log(error);
       }
    });
}
</script>
</html>