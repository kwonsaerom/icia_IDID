<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
   .all{
      margin-left: 100px;
   }
</style>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="amcharts/amcharts.js"> </script>
<script src="amcharts/serial.js"> </script>
<script>
   var foodData = ${mfoodlist }${tmfoodlist}
  var exData = ${mExerciselist }${tmExerciselist}
  var phData = ${mPhysiallist }${tmPhysiallist}
</script>
<script>
AmCharts.makeChart( "foodchart", {
     "type": "serial",
     "dataProvider": foodData,
     "categoryField": "country",
     "categoryAxis": {
        "autoGridCount": false,
        "gridCount": foodData.length,
        "gridPosition": "start"
     },
     "graphs": [{
        "valueField": "visits1",
        "type": "column",
        "fillAlphas": 0.8,
        "angle": 30,
        "depth3D": 20,
        "balloonText": "[[category]]: <b>[[value]]</b>"
     },{
         "valueField": 'visits1',
         "type": "line",
         "fillAlphas": 0, // this line is redundant since the default is 0 (no fill) anyway
         "bullet": "round",
         "lineColor": "#8d1cc6",
         "balloonText": "[[category]]: <b>[[value]]</b>"
      }]
   });
AmCharts.makeChart( "exchart", {
     "type": "serial",
     "dataProvider": exData,
     "categoryField": 'country',
     "categoryAxis": {
        "autoGridCount": false,
        "gridCount": exData.length,
        "gridPosition": "start"
     },
     "graphs" : [{
        "valueField": 'visits1',
        "type": "line",
        "fillAlphas": 0, // this line is redundant since the default is 0 (no fill) anyway
        "bullet": "round",
        "lineColor": "#8d1cc6",
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
   <div class="all">
      <h2>--섭취 음식 칼로리 그래프--</h2>
      <div id="foodchart" style="width:800px; height : 400px;"> </div>
      <hr/>
      <h2>--활동 운동 칼로리 그래프--</h2>
      <div id="exchart" style="width: 800px; height: 400px;"></div>
      <hr/>
      <h2>--체중변화 그래프--</h2>
      <div id="phchart" style="width: 800px; height: 400px;"></div>
   </div>
</body>
<script>
</script>
</html>