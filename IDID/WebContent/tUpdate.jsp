<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
      <link rel="stylesheet" type="text/css" href="css/update.css"/>
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
      </script>
   </head>
   <body>
     <div class="container">
         <div class="update-block">
             <h1>My Info</h1>
             <form action="tUpdateSubmit" method="post" id="update">
                <table>
                   <tr>
                      <td>ID</td>
                      <td><input type="text" id="id" name="id" placeholder="ID" value='${id}' readonly="readonly"/></td>
                   </tr>
                   <tr>
                      <td>PW</td>
                      <td><input type="password" name="pw" id="pw" placeholder="PW"/></td>
                   </tr>
                   <tr>
                      <td>Name</td>
                      <td><input type="text" id="name" name="name" placeholder="Name" value='${name}'/></td>
                   </tr>
                   <tr>
                      <td>Birth</td>
                      <td><input type="text" id="birth" name="birth"value='${birth}'/></td>
                   </tr>
                   <tr>
                         <td colspan="2" style="text-align:center;">생일은 6자리로 입력해주세요 Ex)19890622</td>   
                   </tr>
                   <tr>
                         <td>Gender</td>
                         <td>
                            <input type="text" id="gender" name="gender" value="${gender}" name="gender"/>
                         </td>
                   </tr>
                   <tr>
                        <td>
                           Domicile
                        </td>
                      <td>
                         <input type="text" value="${domicile}" readonly="readonly"/>
                      </td>
                   </tr>
                   <tr>
                      <td>Address</td>
                      <td>
                      <input type="text" name="post1" id="post1" placeholder="우편번호1" /> - <input type="text" id="post2" name="post2" placeholder="우편번호2"/>
                            <input type="button" onclick="openDaumPostcode()" id="addrsearch" value="우편번호 찾기"/><br/>
                            <input type="text" id="addr1" name="addr1"  placeholder="주소"/>
                            <input type="text" id="addr2" name="addr2"  placeholder="상세주소"/>
                      </td>
                   </tr>
                   
                   <tr>
                      <td>Email</td>
                      <td><input type="email" id="email" name="email" placeholder="Email" value="${email}"/></td>
                   </tr>
                   <tr>
                      <td>profilePhoto</td>
                      <td><input type="text" id="profilePhoto" name="profilePhoto" value="${profilePhoto}"/></td>
                   </tr>
                </table>
                <input type="submit" id="updateSubmit" value="정보수정"/>
              </form>
         </div>
      </div>
   </body>
   <script>
   $("#updateSubmit").click(function(){
      $("#update").submit();
   });
   </script>
</html>