package fr.imie.contact.actions;

import com.opensymphony.xwork2.ActionSupport;
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

//@WebServlet("/person/*")
public class PersonAction extends ActionSupport {

  @Inject
  private PersonRepository repository;

  public String save () {
    return SUCCESS;
  }

  public String deleteById () {
    return SUCCESS;
  }

  public String findAll () {
    //List<Person> persons = repository.findAll();
    //request.setAttribute("persons", persons);
    //request.getRequestDispatcher("/WEB-INF/views/person.jsp").forward(request, response);
    return SUCCESS;
  }

  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    if(request.getPathInfo() != null && request.getPathInfo().matches("/delete/[0-9]+")) {
      Integer id = Integer.parseInt(request.getPathInfo().split("/")[2]);
      repository.deleteById(id);
    }

    if (request.getMethod().equalsIgnoreCase("post")) {

      Person person = new Person();
      if(request.getParameter("id") != null) {
        person =repository.findById(Integer.parseInt(request.getParameter("id")));
      }
      person.setFirstName(request.getParameter("firstName"));
      person.setLastName(request.getParameter("lastName"));
      person.setEmail(request.getParameter("email"));
      String text = request.getParameter("birthDate");


      LocalDate date = DateUtils.toLocalDate(text);

      person.setBirthDate(date);

      repository.save(person);
    }

    if(request.getPathInfo() != null && request.getPathInfo().matches("/[0-9]+")) {

      Integer id = Integer.parseInt(request.getPathInfo().split("/")[1]);
      request.setAttribute("id", id);

    }


  }

}
