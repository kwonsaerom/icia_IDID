<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      <link rel="stylesheet" type="text/css" href="css/info.css"/>
      <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
      <title>Insert title here</title>
      <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>

   </head>
   <style>
body{
font-family: 'Jeju Gothic', serif;
}
</style>
   <body>
   <br/><br/><br/><br/><br/><br/>
      <div class="container">
         <div class="info-block">
            <h1>Login</h1>
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
                  <td>Domicile</td>
                  <td><p>${domicile}</p></td>
               </tr>
               <tr>
                  <td>Email</td>
                  <td><p>${email}</p></td>
               </tr>
               <tr>
                  <td>profilePhoto</td>
                  <td><p>${profilePhoto}</p></td>
               </tr>
            </table>
            <div class="join-block">
                  <a href="tUpdate"><input type="button" value="정보 수정하기" class="join"/></a>
                  <a href="tLeave.jsp"><input type="button" value="탈퇴하기" class="join"/></a>
                  <a href="tMain"><input type="button" value="돌아가기" class="join"/></a>
            </div>   
         </div>
      </div>
   </body>
</html>