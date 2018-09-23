<%@ page language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8}"/>
    <title>Please Wait...</title>
</head>
<body>
<form id="defaultForm" action="<%=request.getContextPath() %>/lim/login.lim" method="post" target="_parent">
    <input name="username" value="test123">
</form>
<script type="text/javascript">
    document.getElementById("defaultForm").submit();
</script>
</body>
</html>