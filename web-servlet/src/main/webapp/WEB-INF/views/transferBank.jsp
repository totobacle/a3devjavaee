<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer Account</title>
</head>
<body>
    <c:url var="save" value="/transferaccount/save" />
    <form method="post" action="${save}">
        <table>
            <thead>
            <tr>
                <th>Origine</th>
                <th>Destinataire</th>
                <th>Somme à créditer</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><select name="debit">
                    <option></option>
                    <c:forEach var="account" items="${accounts}">
                        <option value="${account.id}">${account.owner.firstName} ${account.owner.lastName} ${account.balance}</option>
                    </c:forEach>
                </select></td>
                <td><select name="credit">
                    <option></option>
                    <c:forEach var="account" items="${accounts}">
                        <option value="${account.id}">${account.owner.firstName} ${account.owner.lastName} ${account.balance}</option>
                    </c:forEach>
                </select></td>
                <td><input name="ammount"/></td>
                <td><input type="submit" /></td>
            </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
