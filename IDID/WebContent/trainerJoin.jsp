<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
       <link rel="stylesheet" type="text/css" href="css/join.css"/>
     <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
      <script>
         function openDaumPostcode(){
            new daum.Postcode({
               oncomplete:function(data){
                  document.getElementById('post1').value=data.postcode1;
                  document.getElementById('post2').value=data.postcode2;
                  document.getElementById('addr1').value=data.address;
                  document.getElementById('addr2').focus();
               }
            }).open();
            
         }
      
      function forward(obj){
          if(obj.value=='중복체크'){
            document.regist2.action="check";
            document.regist2.submit();
         }
      }
       
      </script>
      <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
		<style>
			body{
				font-family: 'Jeju Gothic', serif;
			}
         h5.msg{
         text-align:right;
         color: red;
         margin-bottom:5px;
         margin-right:145px;
      }
      
      #post1,#post2{
	width:150px;	
}
#addrsearch{
width:120px;	
margin-bottom:5px;
}


#addr1{
margin-bottom:5px;	
	
}
      </style>
   </head>
   <body>
   <div class="flag">
   <br/><br/>
         <h1>트레이너가입페이지</h1>
      </div>
      <div class="header">
      </div>
      <div class="container">
         <div class="login-block">
             <h1>Regist</h1>
             <h5 class="msg">${msg}</h5>
             <form name="regist2" action="trainerSubmit" method="post" id="regist" enctype="multipart/form-data">
                <table>
                   <tr>
                      <td>ID</td>
                      <td><input type="text" id="id" name="id" placeholder="ID" value="${tId}"/></td>
                      <td><input type="button" onclick="forward(this)" value="중복체크"/></td>
                   </tr>
                   <tr>
                      <td>PW</td>
                      <td><input type="password" name="pw" id="pw" placeholder="PW"/></td>
                   </tr>
                   <tr>
                      <td>PWCheck</td>
                      <td><input type="password" id="pw2" placeholder="PWCheck"/></td>
                      
                   </tr>
                   <tr>
                      <td colspan="2"><p id="duplicate" name="check"></p></td>
                   </tr>
                   <tr>
                      <td>Name</td>
                      <td><input type="text" id="name" name="name" placeholder="Name"/></td>
                   </tr>
                   <tr>
                      <td>Gender</td>
                      <td>남자<input type="radio" id="gender1" class="gender" name="gender" value="남자" checked="checked"/>
                            여자<input type="radio" id="gender2" class="gender" name="gender" value="여자"/></td>
                   </tr>
                   <tr>
                      <td>Birth</td>
                      <td><input type="text" id="birth" name="birth" placeholder="Ex)19950731"/></td>
                   </tr>
                   <tr>
                      <td>Address</td>
                      <td><input type="text" name="post1" id="post1" placeholder="우편번호1" /> - <input type="text" id="post2" name="post2" placeholder="우편번호2"/>
                            <input type="button" onclick="openDaumPostcode()" id="addrsearch" value="우편번호 찾기"/><br/>
                            <input type="text" id="addr1" name="addr1"  placeholder="주소"/>
                            <input type="text" id="addr2" name="addr2"  placeholder="상세주소"/></td>
                   </tr>
                   <tr>
                      <td>Email</td>
                      <td><input type="mail" id="email" name="email" placeholder="Email"/></td>
                   </tr>
                <tr>
                   <td class="Photo">Photo</td>
                     <td><input type="file" name="profilePhoto" class="profile"/></td>
                </tr>
                  <tr>
                </table>
                  <input type="button" id="registSubmit" onclick="forward(this)" value="회원가입" style="margin-left:130px;"/>
              </form>
         </div>
      </div>
   </body>
   <script>
   $("#registSubmit").click(function(){
          var id = $('#id').val();
          var pw = $('#pw').val();
          var pw2 = $('#pw2').val();
          var name=$('#name').val();
          var birth=$('#birth').val();
          var birth=$('.gender').val();
          var gender=$('#gender').val();
          var email=$('#email').val();
          var post1=$('#post1').val();
          var post2=$('#post2').val();
          var addr2=$('#addr1').val();
          var addr2=$('#addr2').val();
          
          if(id == "" || pw == "" || pw2 == "" || name == "" || birth == "" || email == "" || post1 == "" || post2 == "" || addr1 == "" || addr2 == "" || gender=="")
          {
             alert("입력하지않은 사항이있습니다.");      
          } 
            if(id !== "" && pw !== "" && pw2 !== "" && name !== "" && birth !== "" && email !== "" && post1 !== "" && post2 !== "" && addr1 !=="" && addr2 !==""  && gender !=="")
            {      
               if (pw !== "" && pw2 !== "") {
               if (pw !== pw2){
                  document.getElementById("duplicate").innerHTML = "암호틀림";
               }
               if(pw==pw2){
                  $("#regist").submit();
               }
            }
         }});
   
      $(function() {
         $('#pw').keyup(function() {
            $('font[name=check]').text('');
         });

         $('#pw2').keyup(function() {
            if ($('#pw').val() != $('#pw2').val()) {
               $('p[name=check]').text('');
               $('p[name=check]').html("암호틀림");
            } else {
               $('p[name=check]').text('');
               $('p[name=check]').html("암호맞음");
            }
         });
      });
   </script>
</html>