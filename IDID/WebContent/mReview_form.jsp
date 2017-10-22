<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
</head>
<body>
	<!-- <form action="mReview_write" method="post" enctype="multipart/form-data"> -->
	<form method="post" enctype="multipart/form-data">
		<table border="1" cellspacing="0" class='table table-striped'>
			<tr>
				<td><div align="center">제목:</div></td>
				<td><input type="text" name="R_TITLE" id="R_TITLE" size="50"
					maxlength="100" value="" /></td>
			</tr>
			<tr>
				<td><div align="center">내용</div></td>
				<td><textarea name="R_SUBSTANCE" id="R_SUBSTANCE" rows="10" cols="52"></textarea></td>
			</tr>
			<tr>
				<td><div align="center">파일첨부</div></td>
				<td><input type="file" name="R_FILENAME" id="R_FILENAME" />
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="commit" value="작성하기" />
					<input type="button" onclick="back()" value="뒤로가기" />
				</td>
			</tr>
		</table>
	</form>
</body>
<script>

$ (function(){
	$("#commit").click(function(){
		var _R_TITLE = $("#R_TITLE").val();
		var _R_SUBSTANCE = $("#R_SUBSTANCE").val();
		var _R_FILENAME = $("#R_FILENAME")[0].files[0];
		alert(_R_FILENAME);
		var formData = new FormData();
		formData.append("_R_TITLE", _R_TITLE);
		formData.append("_R_SUBSTANCE", _R_SUBSTANCE);
		formData.append("_R_FILENAME", _R_FILENAME);
		$.ajax({
			type :'post',
			url: 'mReview_write',
			data : formData,
			processData: false,
			contentType: false,
			success: function(data){
				alert('확인');
				$('#printP').html(data);	
			},
			error : function (error){
				
			}
		
		});
		$('#commit').submit();
	})
}) 
	function back(){
		history.back();
	}
</script>
</html>