<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<title>IDID</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" media="screen" href="https://fontlibrary.org/face/hussar-szturm" type="text/css"/>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" type="text/css"/>
<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/earlyaccess/jejugothic.css"/>

<style>
.col-md-2, .col-md-11 {
   font-family: 'Jeju Gothic', serif;
}
#homeInfo {
	width: 300px;
	height: 300px;
	border-radius: 300px;
	margin: 0 auto;
	overflow: hidden;
}

#homeInfo img {
	height: auto;
	width: 200px;
}

.idid {
	font-family: 'HussarSzturm';
	font-size: 55px;
}

.index {
	font-size: 30px;
	color: lightslategrey;
	text-align: left;
}

h3 {
	font-family: 'Nanum Pen Script', serif;
	font-size: 30px;
	text-align: left;
}

.information {
	padding-left: 160px;
}

#homeInfoForm {
	padding-left: 160px;
	margin-bottom: 50px;
}

h2 {
	font-family: 'Jeju Gothic', serif;
	font-size: 30px;
	text-align: left;
}
</style>
</head>
<body>
	<div class="col-md-11" id="header">
		<a href="index" class="home">Home</a> <a href="loginForm" class="etc">로그인　　</a>>
	</div>
	<div class="row"></div>
	<div class="col-md-11" id="content">

		<div class="col-md-2">
			<br />
			<li><a href="javascript:AJ('homeInfo','#printP')">홈페이지 소개</a></li>
			<li><a href="javascript:AJ('trainerInfo','#printP')">트레이너 소개</a></li>
			<li><a href="javascript:AJ('programInfo','#printP')">프로그램 안내</a></li>
			<li><a href="javascript:AJ('mReview','#printP')">후기게시판</a></li>
		</div>
		<div id="printP" class="col-md-9">
			<div id="homeInfoForm">
				<br />
				<h1 class="idid">IDID</h1>
				<br /> <img id="homeInfo" alt="소개이미지" src="image/운동하자.jpg">
			</div>
			<div class="information">
				<h2 class="index">01</h2>
				<h3>친구와의 약속, 잦은 야근과 회식, 작심삼일에 그치는 운동은 늘 다이어트 의지를 흔들곤 합니다. 그럴 땐
					트레이너와 이야기를 나누세요. IDID가 흔들리는 당신의 마음을 잡아드립니다.</h3>
				<h3>끊임없는 피드백을 통해, 체계적인 식단과 효율적인 운동 등 다양한 프로그램들을 통해 코칭 받으실 수
					있습니다.</h3>
				<br />
				<h2 class="index">02</h2>
				<h3>굶는 다이어트를 지양하고 놀라운 식습관 변화를 이끌어내는 생활밀착형 사이트로 트레이너들이 직접 짜주는
					식단과 한 눈에 보기 쉬운 그래프를 통해 여러분의 변화를 직접 확인해보세요.</h3>
				<h3>IDID는 사이트를 이용하는 모든 이의 삶이 늘 건강하도록 노력하고 있습니다. 지금 바로 체험해 보세요.</h3>
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
			</div>
		</div>
	</div>
	<div class="footer">
		<h5>여기는 footer</h5>

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
	   $(function() {
	       var msg = '${msg}';
	       if(msg == '탈퇴 완료'){
	          alert(msg);
	       }
	    });
	</script>
</html>