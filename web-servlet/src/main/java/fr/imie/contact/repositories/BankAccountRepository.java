package fr.imie.contact.repositories;

import fr.imie.contact.entities.*;

import javax.ejb.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Stateless
public class BankAccountRepository {

  @PersistenceContext
  EntityManager em;

  public List<BankAccount> findAll() {
    return em.createQuery("select ba from BankAccount ba").getResultList();
  }

  public BankAccount findById(Integer id) {
    return em.find(BankAccount.class, id);
  }

  public void updateLess(BigDecimal ammount, Integer id) {
    em.createQuery("update BankAccount set balance= balance-"+ammount+" where id="+id);
  }

  public void updateMore(BigDecimal ammount, Integer id) {
    em.createQuery("update BankAccount set balance= balance+"+ammount+" where id="+id);
  }

  public void save(BankAccount entity) {
    if(entity.getId() == null) {
      em.persist(entity);
    } else {
      em.merge(entity);
    }
  }

}
