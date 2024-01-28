package com.book1.entity;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsersTest {
    public static void main(String[] args) {
       Users user2 = new Users();
        user2.setEmail("you1@gmail.com");
        user2.setFullName("Mr President1");
        user2.setPassword("power");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(user2);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("A Usres object was persisted");
    }
}
