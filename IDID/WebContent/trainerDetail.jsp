<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
<script>
   function forward(obj){
      if(obj.value=='돌아가기'){
         history.back();
         document.detailForm.submit();
      }
   }
</script>
<style>
.button {
  color:gray;
  
  }
  
   
 body{
    font-size: 11px;
    font-family: 'Open Sans', sans-serif;
    color: #4A4A4A ;
    text-align: center;
}



.wrap {
    width:350px;
    height:540px;
    background:#FFF;
    margin:20px auto;
    padding: 10px;
}

/*==================================================
 * Effect 8
 * ===============================================*/
.effect8
{
      position:relative;       
      margin-top:150px;
      margin-left:360px;
    -webkit-box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
       -moz-box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
}
.effect8:before, .effect8:after
{
    content:"";
    position:absolute; 
    z-index:-1;
    -webkit-box-shadow:0 0 20px rgba(0,0,0,0.8);
    -moz-box-shadow:0 0 20px rgba(0,0,0,0.8);
    box-shadow:0 0 20px rgba(0,0,0,0.8);
    top:10px;
    bottom:10px;
    left:0;
    right:0;
    -moz-border-radius:100px / 10px;
    border-radius:100px / 10px;
} 
.effect8:after
{
    right:10px; 
    left:auto;
    -webkit-transform:skew(8deg) rotate(3deg); 
       -moz-transform:skew(8deg) rotate(3deg);     
        -ms-transform:skew(8deg) rotate(3deg);     
         -o-transform:skew(8deg) rotate(3deg); 
            transform:skew(8deg) rotate(3deg);
}  

.wrap img {
    width: 100%;
    margin-top: 15px;
}

p{ 
    margin-top: 15px;
    text-align: justify;
}

h1{
    font-size: 30px;
    font-weight: bold;
    margin-top: 5px; 
    text-shadow: 1px 1px 3px rgba(0,0,0,0.3);
}


 p{
  text-align:center;
  font-size: 15px;
 }

</style>
</head>

<body>
   <form name="detailForm" class="wrap effect8">
   <br/>
     <h1>  ${name}
${profilePhoto}</h1>
<br/>
    <p>  나이 : ${age}세<br/>
   성별 : ${gender}<br/>
   생년월일 : ${birth1}년 ${birth2}월 ${birth3}일 <br/>
   이메일 주소 : ${email}<br/><br/></p>
   <input type="button" class="button" value="돌아가기" onclick="forward(this)"/>
   </form>
</body>
</html>