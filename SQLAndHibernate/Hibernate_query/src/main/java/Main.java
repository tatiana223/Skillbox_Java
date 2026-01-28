import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(LinkedPurchaseList.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory
                .openSession();

        session.beginTransaction();

        List<PurchaseList> purchases = session.createQuery("FROM PurchaseList", PurchaseList.class).list();

        for (PurchaseList purchase : purchases) {
            Student student = session.createQuery(
                    "From Student Where name = :name", Student.class)
                    .setParameter("name", purchase.getStudentName())
                    .uniqueResult();

            Course course = session.createQuery(
                            "FROM Course WHERE name = :name", Course.class)
                    .setParameter("name", purchase.getCourseName())
                    .uniqueResult();


            if (student != null && course != null) {
                LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
                linkedPurchase.setStudentId(student.getId());
                linkedPurchase.setCourseId(course.getId());

                session.save(linkedPurchase);
            }
        }
        session.getTransaction().commit();
        session.close();

        System.out.println("Таблицы созданы");



    }
}
