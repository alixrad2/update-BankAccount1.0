<%@ page import="ir.bankaccount1.model.entity.Person" %>
<%@ page import="ir.bankaccount1.model.bl.PersonBL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
    <link rel="shortcut icon" href="tools/portaj.ico">
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>

<% if (request.getSession().getAttribute("role") == null && !request.getSession().getAttribute("role").equals("admin")) {
    response.sendRedirect("login.html");
}
%>
<form action="/admin">
    Enter id = <input type="number" name="id" placeholder="Enter id"><br>
    Enter name = <input type="text" name="name" placeholder="Enter name"><br>
    Enter family = <input type="text" name="family" placeholder="Enter Family"><br>
    Enter Nc_number = <input type="text" name="nc_number" placeholder="Enter nc_number"><br>
    Enter father Name = <input type="text" name="father_name" placeholder="enter father_name"><br>
    Enter CC_number = <input type="text" name="cc_number" placeholder="Enter cc_number"><br>
    Enter create_date = <input type="datetime-local" name="create_date" placeholder="1/9/2023"><br>
    Enter role:<input type="text" name="role" placeholder="Role">
      <input  type="submit" onclick="save()" value="ذخیره">
      <input  type="submit" onclick="remove()" value="حذف">
      <input  type="submit" onclick="edit()" value="ویرایش">
</form>

<br><br><br>


<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>name</th>
        <th>family</th>
        <th>father_name</th>
        <th>nc_number</th>
        <th>cc_number</th>
        <th>create_date</th>
        <th>Operation</th>
    </tr>
    </thead>

    <tbody>
    <% for (Person person : PersonBL.findAll()) {%>
    <tr>
        <td><%=person.getId()%>
        </td>
        <td><%=person.getName()%>
        </td>
        <td><%=person.getFamily()%>
        </td>
        <td><%=person.getFather_name()%>
        </td>
        <td><%=person.getNc_number()%>
        </td>
        <td><%=person.getCc_number()%>
        </td>
        <td><%=person.getCreate_date()%>
        </td>
        <td>
            <button onclick="edit(
                <%=person.getId()%>,
                <%=person.getCc_number()%>
                )">Edit
            </button>
            <button onclick="remove(<%=person.getId()%>)">Remove</button>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<script src="assets/js/index.js"></script>
</body>
</html>
