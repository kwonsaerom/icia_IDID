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
<script src = "amcharts/amcharts.js"> </script>
<script src = "amcharts/serial.js"> </script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>
</head>
<style>
body{
font-family: 'Jeju Gothic', serif;
}
</style>
<script>
var foodData = ${mfoodlist }
var exData = ${mfoodlist }
var phData = ${mPhysiallist }
</script>
<script>
AmCharts.makeChart( "foodchart", {
     "type": "serial",
     "dataProvider": foodData,
     "categoryField": "country",
     "categoryAxis": {
        "autoGridCount": false,
        "gridCount": foodData.length,
        "gridPosition": "start",
        "labelRotation": 90
     },
     "graphs": [{
        "valueField": "visits1",
        "type": "column",
        "fillAlphas": 0.8,
        "angle": 30,
        "depth3D": 15,
        "balloonText": "[[category]]: <b>[[value]]</b>"
     }]
   });
AmCharts.makeChart( "exchart", {
     "type": "serial",
     "dataProvider": exData,
     "categoryField": "country",
     "categoryAxis": {
        "autoGridCount": false,
        "gridCount": foodData.length,
        "gridPosition": "start",
        "labelRotation": 90
     },
     "graphs" : [{
        "valueField": "visits1",
        "type": "line",
        "fillAlphas": 0, // this line is redundant since the default is 0 (no fill) anyway
        "bullet": "round",
        "lineColor": "#8d1cc6"
     }]
   });
AmCharts.makeChart( "phchart", {
    "type": "serial",
    "dataProvider": phData,
    "categoryField": 'country',
    "categoryAxis": {
       "autoGridCount": false,
       "gridCount": phData.length,
       "gridPosition": "start"
    },
    "graphs" : [{
       "valueField": 'visits1',
       "type": "line",
       "fillAlphas": 0, // this line is redundant since the default is 0 (no fill) anyway
       "bullet": "round",
       "lineColor": "#01DF01",
       "balloonText": "[[category]]: <b>[[value]]</b>"
    },{
       "valueField": 'visits1',
       "type": "column",
        "fillAlphas": 0.8,
        "angle": 30,
        "depth3D": 20,
        "balloonText": "[[category]]: <b>[[value]]</b>"
    }]
  });
</script>
<body>
   <div class="col-md-11" id="header">
      <a href="mMain" class="home">Home</a>
      <a href="logout" class="etc">로그아웃</a>
      <a href="mInfo" class="etc">내정보</a>
   </div>
   <div class="row"></div>
   <div class="col-md-11" id="content">
      <div class="col-md-2">
            <br/>
            <li><a href="javascript:AJ('tExercisemList.jsp','#printP')">운동 일정 보기</a></li>
            <li><a href="javascript:AJ('tFoodmList.jsp','#printP')">식단 일정 보기</a></li>
            <li><a href="javascript:AJ('mExercise','#printP')">운동 일지 작성</a></li>
            <li><a href="javascript:AJ('mFood','#printP')">식단 일지 작성</a></li>
            <li><a href="javascript:AJ('mPhysial.jsp','#printP')">신장/체중 등록</a></li>
      </div>
      <div id="printP" class="col-md-11">
      </div>
   </div>
</body>
<script>
   function AJ(url, position){
      $.ajax({
         url: url,
         type:"get",
         success: function(html){
            $(position).html(html);
         },
         error: function(error){
            console.log(error);
         }
      });
   }
   $(function(){
         $.ajax({ 
            type: 'post' , 
            url: './mfoodgraph' , 
            dataType : 'html' ,
            success: function(data) { 
               $("#printP").html(data); 
               } 
         });      
   })
</script>
</html>