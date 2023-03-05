<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Car Form</h1>
<form enctype="multipart/form-data" action="car/insert" method="post">

    <table border="1">
        <tr>
            <td >
                <label>Plate</label>
            </td>
            <td >
                <label> <input type="text" name="plate"/> </label>
            </td>
        </tr>
        <tr>
            <td >
                <label>Power</label>
            </td>
            <td >
                <label> <input type="number" name="power"/> </label>
            </td>
        </tr>
        <tr>
            <td >
                Insurance
            </td>
            <td>
                <input type="file" name="insurance"/>
            </td>
        </tr>
    </table>
    <input type="submit">
</form>
</body>
</html>
