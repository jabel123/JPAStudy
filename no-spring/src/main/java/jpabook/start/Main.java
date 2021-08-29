package jpabook.start;

import jpabook.start.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try
        {
            tx.begin();
            logic(em);
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
        }
        finally
        {
            em.close();
        }

        emf.close();

    }

    private static void logic(EntityManager em)
    {
        Member m = new Member();
        m.setAge(10l);
        m.setName("주현태");
        em.persist(m);
    }
}