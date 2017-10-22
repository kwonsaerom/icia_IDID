<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
<style>

  body{
  font-family: 'Jeju Gothic', serif;
      max-height: none;
   }
   #pListContainer{
      display: inline-block;
      width: 500px;
      margin-left: 200px;
   }
   .table{
         width: 500px;
         box-shadow: 5px 5px 5px 5px gray;
   }
   table{
      table-layout: fixed;
      word-break:break-all;
      width: 80%; 
      
   }
   .index{
      width: 70px;
   }
   .Info{
      width: 550px;
      height:150px;
      max-width:250px;
   }
   td{
      width: 250px;
   }
   .delay{
      width: 250px;
   }
   
</style>
</head>
<body>
      <!-- <div id="printP"> -->
         
         ${pList}
      <!-- </div> -->
</body>
</html>