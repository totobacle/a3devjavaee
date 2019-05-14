package fr.imie.contact.servlets;

import fr.imie.contact.entities.BankAccount;
import fr.imie.contact.entities.Person;
import fr.imie.contact.repositories.BankAccountRepository;
import fr.imie.contact.repositories.PersonRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/transferaccount/*")
public class TransferBankServlet extends HttpServlet {

    @Inject
    private BankAccountRepository bankAccountRepository;
    @Inject
    private PersonRepository personRepository;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            BigDecimal ammount = new BigDecimal(request.getParameter("ammount"));
            BankAccount debitAccount = bankAccountRepository.findById(Integer.parseInt(request.getParameter("debit")));
            BankAccount creditAccount = bankAccountRepository.findById(Integer.parseInt(request.getParameter("credit")));

            debitAccount.setBalance(debitAccount.getBalance().subtract(ammount));
            creditAccount.setBalance(creditAccount.getBalance().add(ammount));

            bankAccountRepository.save(debitAccount);
            bankAccountRepository.save(creditAccount);
        }

        List<BankAccount> accounts = bankAccountRepository.findAll();
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("/WEB-INF/views/transferBank.jsp").forward(request,response);
    }
}
