<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
   textarea{
      resize: none;
   }
   table{
      width: 700px;
      padding-right: 50px;
   }
   table,tr{
      border-top:1px solid goldenrod;
   }
   .button{
      text-align: right;
      
   }
   .pInsert{
      padding-left: 150px;
   }
   #pName{
      width: 80%;
   }
   #price{
      text-align: right;
   }
   .price{
      padding-bottom: 20px;
      padding-top: 20px;
   }
</style>
</head>
<body>

<form action="pInsert" method="post" class="pInsert">
   <h1>프로그램 등록 페이지</h1>
   <h2>프로그램 등록</h2>
   <table>
      <tr>
         <td style="background:#f8e8db; height:50px;">이름:</td> 
         <td>${t_id}</td>
      </tr>
      <tr>
         <td style="background:#f8e8db; height:50px;">프로그램명:</td>
         <td><input type="text" name="pName" value="" id="pName"/></td>
      </tr>
      <tr>
         <td colspan="2">
            프로그램 소개(500자 이내):<br/><textarea name="pInfo" rows="10" cols="97"></textarea>
         </td>
      </tr>
      <tr id="price">
         <td colspan="2" class="price">가격:<input type="text" name="pPrice" value="" />원</td>
      <tr style="height:50px;">
         <td colspan="2" class="button">
            <input type="submit" value="등록하기" />
            <input type="button" onclick="back()" value="돌아가기" />
         </td>
      </tr>
   </table>
</form>
</body>
<script>
   function back(){
      history.back();
   }
</script>
</html>