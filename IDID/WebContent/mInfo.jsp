<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
      <link rel="stylesheet" type="text/css" href="css/info.css"/>
      <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
      
      <style>
         textarea{
            resize: none;
         }
body{
font-family: 'Jeju Gothic', serif;
}

h1{
font-size:70px;
}
      </style>  
   </head>
   <body>
   <br/><br/><br/><br/><br/>
      <div class="container">
         <div class="info-block">
             <h1>My Info</h1>
              <table>
                   <tr>
                      <td>ID</td>
                      <td><p>${id}</p></td>
                   </tr>
                   <tr>
                      <td>Name</td>
                      <td><p>${name}</p></td>
                   </tr>
                   <tr>
                      <td>Birth</td>
                      <td><p>${birth}</p></td>
                   </tr>
                   <tr>
                         <td>Gender</td>
                         <td><p>${gender}</p></td>
                   </tr>
                   <tr>
                        <td>
                           Domicile
                        </td>
                      <td><p>${domicile}</p></td>
                   </tr>
                   <tr>
                         <td>
                            height
                         </td>
                         <td>
                            <p>${height}</p>
                         </td>
                   </tr>
                   <tr>
                         <td>
                            weight
                         </td>
                         <td><p>${weight}</p></td>
                   </tr>
                   <tr>
                      <td>Email</td>
                      <td><p>${email}</p></td>
                   </tr>
                   <tr>
                      <td>wish</td>
                      <td><textarea cols="80" rows="10" id="wish" name="hope" readonly="readonly">${wish}</textarea></td>
                   </tr>
                </table>
                <div class="join-block">
                 <br/>
                  <a href="mUpdate"><input type="button" value="정보 수정하기" class="join"/></a>
                  <a href="mLeave.jsp"><input type="button" value="탈퇴하기" class="join"/></a>
                  <a href="mMain"><input type="button" value="돌아가기" class="join"/></a>
            </div>   
         </div>
      </div>
   </body>
</html>