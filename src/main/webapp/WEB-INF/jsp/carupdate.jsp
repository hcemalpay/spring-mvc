<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
${cartoupdate}
<h1>Car Update</h1>
<form action="/car/doupdate" method="post">
    <input type="hidden" name="carid" value="${cartoupdate.id}"/>
    Plate <input type="text" name="plate" value="${cartoupdate.plate}"/>
    Power <input type="number" name="power" value="${cartoupdate.power}"/>
    <input type="submit">
</form>


</body>
</html>
