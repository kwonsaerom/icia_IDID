<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<title>Insert title here</title>
<script>
</script>
<link rel="stylesheet" type="text/css" href="css/index.css" />
</head>
<body>
	${review_detailHtml}
		${rpListMake}
	<div id="reply">
		${replyWriteHtml}
	</div>
		${replyBtnHtml}
	<!-- <form action='comment_write' method='post'>
		<textarea rows="5" cols="50" style="resize: none;" id="comment" ></textarea>
		<br /> <input type="button" onclick="javascript:reply_ajax('comment_write?','#reply')" value="댓글달기" />
	</form> -->
</body>
<script>
function reply_ajax(url, position){
	var str = document.getElementById('comment');
	var comment = str.value;
	var allData = { "comment": comment };
		$.ajax({
			url: url,
			type: 'post',
			/* dataType: 'json', */
			data: allData,
			success: function(data){
				alert('성공');
				$(position).html(data);
			},
			error: function(error){
				console.log(error);	
			}
		})	
	}

	function C_AJAX(url, position){
		$.ajax({
			url: url,
			type: "get",
			success: function(html){
				$(position).html(html);
			},
			error: function(error){
				console.log(error);
			}
		});
	}
	function listBtn(){
		alert('확인');
		document.listBtn.action = 'mReview';
		document.listBtn.method = 'post';
		document.listBtn.submit();
	}
	
	function back(){
		history.back();
	}
</script>
</html>