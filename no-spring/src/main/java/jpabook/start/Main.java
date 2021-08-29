package jpabook.start;

import com.querydsl.jpa.impl.JPAQuery;
import jpabook.start.domain.Member;
import jpabook.start.domain.QMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember qMember = new QMember("m");
        List<Member> members = query.from(qMember)
                .where(qMember.name.eq("주현태"))
                .orderBy(qMember.age.desc())
                .fetch();

        System.out.println("회원수 : " + members);
    }
}