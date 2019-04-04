package fr.imie.contact.servlets;

import fr.imie.contact.*;
import fr.imie.contact.entities.*;
import fr.imie.contact.repositories.*;

import javax.inject.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@WebServlet("/bankaccount/*")
public class BankAccountServlet extends HttpServlet {

  @Inject
  private BankAccountRepository bankAccountRepository;
  @Inject
  private PersonRepository personRepository;


  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getMethod().equalsIgnoreCase("post")) {

        BigDecimal balance = new BigDecimal(request.getParameter("balance"));
        Integer owner_id = Integer.parseInt(request.getParameter("owner"));
        Person owner =personRepository.findById(owner_id);

        BankAccount account = new BankAccount();
        account.setBalance(balance);
        account.setOwner(owner);

        bankAccountRepository.save(account);
    }
    List<BankAccount> accounts = bankAccountRepository.findAll();
    List<Person> persons = personRepository.findAll();
    request.setAttribute("accounts", accounts);
    request.setAttribute("persons", persons);
    request.getRequestDispatcher("/WEB-INF/views/bankaccount.jsp").forward(request, response);
  }

}
