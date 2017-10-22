<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
</script>
</head>
<body>
	<!-- <form action="comment_write" method="post"> -->
		<div id="txt">
		</div>
			<textarea rows="5" cols="50" style="resize:none;" name="comment"></textarea><br/>
			<input type="submit" onclick="javascript:C_AJAX('comment_write','#txt')" value="댓글달기"/>
			<!-- onclick="javascript:C_AJAX('comment_write','#txt')" -->
	<!-- </form> -->
</body>
<script>
	function C_AJAX(url, position){
		$.ajax({
			url:url,
			type:"get",
			success: function(html){
				$(position).html(html);
			},
			error: function(error){
				console.log(error);
			}
		
		});
	}

</script>
</html>