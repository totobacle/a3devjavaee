package fr.imie.contact.servlets;

import fr.imie.contact.*;
import fr.imie.contact.entities.*;
import fr.imie.contact.repositories.*;

import javax.inject.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.time.*;
import java.util.*;

@WebServlet("/person/*")
public class PersonServlet extends HttpServlet {

  @Inject
  private PersonRepository repository;

  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //if (request.getPathInfo() != null && request.getPathInfo().equalsIgnoreCase("/delete/")) {
    if(request.getPathInfo() != null && request.getPathInfo().matches("/delete/[0-9]+")) {
      Integer id = Integer.parseInt(request.getPathInfo().split("/")[2]);
      repository.deleteById(id);
    }

    if (request.getMethod().equalsIgnoreCase("post")) {
      Person person = new Person();
      person.setFirstName(request.getParameter("firstName"));
      person.setLastName(request.getParameter("lastName"));
      person.setEmail(request.getParameter("email"));

      String text = request.getParameter("birthDate");
      LocalDate date = DateUtils.toLocalDate(text);
      person.setBirthDate(date);

      repository.save(person);
    }

    List<Person> persons = repository.findAll();
    request.setAttribute("persons", persons);
    request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
  }

}
