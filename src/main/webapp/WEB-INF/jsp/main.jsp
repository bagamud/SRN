<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Информационный портал УГИБДД</title>
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="container-xl" action="${pageContext.request.contextPath}/dashboard">
    <img class="mb-4" src="${pageContext.request.contextPath}/img/gibdd.png" alt="">
    <h1>Журнал учета недостатков улично-дорожной сети</h1>
    <button class="btn btn-lg btn-primary" type="submit">Войти</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2020-2020</p>
</form>
</body>
</html>