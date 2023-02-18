<%@ page import="ir.bankaccount1.controller.PersonController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="tools/portaj.ico">
    <title>Person info Page</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
    <h2>
        ${sessionScope.info}
    </h2>
    <p><strong>جهت ویرایش یا حذف حساب کاربری به ادمین مراجعه کنید</strong></p>
    <a href="main.html">رفتن به صفحه اصلی</a>
</body>
</html>

