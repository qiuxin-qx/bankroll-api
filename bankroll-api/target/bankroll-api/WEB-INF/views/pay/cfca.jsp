<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:choose>
<c:when test=" ${!empty error}">
    <p>${error}</p>
</c:when>
<c:otherwise>
    <p>${orderNo}</p>
    <p>${serialNo}</p>
    <form action="${r.action}" method="post">
        <!--
        <input name="plainText" value="${plainText}" />
        -->
        <input name="message" value="${r.message}" />
        <input name="signature" value="${r.signature}" />
        <input type="submit" />
    </form>
</c:otherwise>
</c:choose>
</body>
</html>
