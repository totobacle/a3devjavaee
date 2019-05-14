<%@page pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <meta charset="utf-8" />
  <title></title>
</head>
<body>
<c:url var="context" value="/person/" />
<form method="post" action="${context}save">
  <table>
    <thead>
      <tr>
        <th>Id</th>
        <th>Prénom</th>
        <th>Nom</th>
        <th>Email</th>
        <th>Date de naissance</th>
        <th>Supprimer</th>
      </tr>
    </thead>
    <tbody>
    <c:if test="${ empty id}">
      <tr>
        <td>*</td>
        <td><input name="firstName" /></td>
        <td><input name="lastName" /></td>
        <td><input name="email" /></td>
        <td><input name="birthDate" /></td>
        <td><input type="submit" /></td>
      </tr>
    </c:if>
      <c:forEach var="person" items="${persons}">
        <c:if test="${person.id eq id}">
          <tr>
            <td><input type="hidden" name="id" value="${person.id}">${person.id}</td>
            <td><input name="firstName" value="${person.firstName}">${person.firstName}</td>
            <td><input name="lastName" value="${person.lastName}">${person.lastName}</td>
            <td><input name="email" value="${person.email}">${person.email}</td>
            <fmt:parseDate value="${person.birthDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
            <fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" var="goodDate"/>
            <td><input name="birthDate" value="${goodDate}">${goodDate}</td>
            <td><a href="${context}delete/${person.id}" onclick="return confirm('Etes-vous sûr de vouloir supprimer cette personne ?')">Supprimer</a></td>
            <td><a href="${context}">Annuler</a></td>
            <td><input type="submit" /></td>
          </tr>
        </c:if>
        <c:if test="${person.id ne id}">
          <tr>
            <td>${person.id}</td>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.email}</td>
            <td>${person.birthDate}</td>
            <td><a href="${context}delete/${person.id}" onclick="return confirm('Etes-vous sûr de vouloir supprimer cette personne ?')">Supprimer</a></td>
            <td><button onclick="edit(event);">Modifier</button></td>
<%--            <td><a href="${context}${person.id}">Modifier</a></td>--%>
          </tr>
        </c:if>
      </c:forEach>
    </tbody>
  </table>
</form>
<script>
  function edit(event) {
    event.preventDefault();
    console.log(event.target);
    var tr = event.target.parentNode.parentNode;

    var children = Array.from(tr.children);
    children.splice(-2,2);
    children.forEach(function (td, col) {
      if (col===0){
        var name = 'id';
      } else {
        var name = document.querySelector('tbody').firstElementChild.children[col].firstElementChild.name;
      }
      var value = td.textContent;


      td.innerHTML='<input value="'+value+'" name="'+name+'" />';
    })
    // Arrays.of(trs.children.forEach(function (td) {
    //   console.log(td);
    // });
    // trs.forEach(function (y) {
    //   console.log(1);
    // })
    // var newInput = document.createElement("input");
    // newInput.setAttribute("name", "firstName");
  }

  function deleteConfirm() {

  }
</script>
</body>
</html>
