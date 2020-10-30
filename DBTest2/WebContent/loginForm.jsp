<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//쿠키값 가져옴
    Cookie[] cookies = request.getCookies();
	String id ="";
	String checked = "";
	String oldURL = request.getParameter("page");
	//쿠키값이 있으면
		for(int i=0; i<cookies.length; i++){
	
		//쿠키의 키와 id 가 일치하면 
		if(cookies[i].getName().equals("id")){
		//일치하는 키의 값이 id 값이 된다.(기본값)
		id = cookies[i].getValue();
		//id기억하기를 했었으면(id 값이 있으면) 체크표시
		if(id!=null){
		checked="checked";
}
}
}

%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>

<body>
	<form action="/LoginAction" method="POST">
		id <input type="text" name="id" value="<%=id%>" required ><br> pwd<input
			type="password" name="pwd" required ><br> <input type="checkbox"
			name="remember" <%=checked%>>id기억<br> <input
			type="submit" value="로그인">
			
			 <input type="hidden"  name="oldURL" value=<%=oldURL %>>
	</form>
</body>

</html>