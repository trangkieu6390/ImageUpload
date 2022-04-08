<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>bai33</title>
		<link href="reset.css" type="text/css" rel="stylesheet"/>
		<link href="style.css" type="text/css" rel="stylesheet"/>
	</head>
	<body>
	
		<div class="swapper">
		<%
			String error = (String)request.getAttribute("error");
		%>
			<h1>Upload ảnh</h1>
			<form action="<%=request.getContextPath() %>/UploadImageController" method="post" enctype="multipart/form-data">
				<label>Hình ảnh: </label>
				<input type="file" value="Chọn tệp" name="file"/><br/><br/>
				<%
					if(error!=null){
				%>
				<p id="error"><%=error %></p>
				<%} %>
				<input type="submit" name="Up hình" value="Up hình"/>
			</form>
		</div>
		
	</body>
</html>