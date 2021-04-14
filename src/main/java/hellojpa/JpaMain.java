package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /* 등록
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);

            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
            */

            //조회
            //Member findMember = em.find(Member.class, 1L);
            //System.out.println("findMember.id = " + findMember.getId());
            //System.out.println("findMember.name = " + findMember.getName());

            //수정
            //JPA는 가져온 findMember 객체를 관리하고 있으며,
            //트랜젝션이 커밋되는 순간에 변경 사항을 갱신한다.
            //findMember.setName("HelloJPA");

            //JPQL
            //단순 조회가 아니고 조건이 들어간 조회를 하는 경우
            /*
            List<Member> result = em.createQuery("select m from Member AS m", Member.class)
                    .setFirstResult(1)  //1행부터
                    .setMaxResults(10)  //최대 10개
                    .getResultList();

            for(Member member : result){
                System.out.println("member.name = " + member.getName());
            }
            */

            //비영속
            //Member member = new Member();
            //member.setId(100L);
            //member.setName("HelloJPA");

            //영속
            //트랜잭션을 커밋하는 순간 영속성 컨텍스트에 있는 객체가 전달된다.
            //System.out.println("=== BEFORE ===");
            //em.persist(member); //여기서 저장되는게 아님.

            //준영속
            //em.detach(member);  //영속성 컨텍스트에서 분리
            //System.out.println("=== AFTER ===");

            //삭제
            //em.remove(member);

            //영속성 컨텍스트의 이점
            //1차 캐시
            //영속성 컨텍스트에 객체가 존재하기 때문에 쿼리를 전달하지 않고 가져온다.
            /*
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            Member findMember = em.find(Member.class, 101L);  //select 쿼리가 실행되지 않고 바로 가져옴
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            //동일성 보장
            /*
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("result = " + (findMember1 == findMember2)); //true
            */

            //쓰기 지연
            /*
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            //쓰기 지연 SQL 저장소에 저장된다.
            em.persist(member1);
            em.persist(member2);

            System.out.println("===================");  //commit이 실행되기 전까지 쿼리가 전달되지 않는다.
            */

            //변경 감지(Dirty Checking)
            //변경을 감지해서 update 쿼리를 전달한다.
            /*
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
            System.out.println("===================");
             */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
