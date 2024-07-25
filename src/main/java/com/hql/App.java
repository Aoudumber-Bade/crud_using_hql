package com.hql;

import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Full HQL Operations");
        System.out.println("1. Add Student Data");
        System.out.println("2. Update Students Data");
        System.out.println("3. Delete Students Data");
        System.out.println("4. Get Single Students Data");
        System.out.println("5. Get All Students Data");

        System.out.print("Enter your Choice -> ");
        Scanner scan = new Scanner(System.in);
        int ch = scan.nextInt();
        scan.nextLine();

        switch (ch) {
            case 1:
                
                addStudent(session,transaction,scan);
                break;
                
                case 2:
                updateStudent(session,transaction,scan);
                break;
                
                case 3:
                deleteStudet(session,transaction,scan);
                break;
                
                case 4:
                getSingleData(session);
                break;
                
                case 5:
                getAllData(session);
                break;
                
            default:
                break;
        }

    }

    private static void addStudent(Session s,Transaction tx,Scanner sc) {

       System.out.println("Add New Student"); 

       System.out.print("Enter Student Name -> ");
       String name = sc.nextLine();

       System.out.print("Enter Class Name -> ");
       String className = sc.nextLine();

       System.out.print("Enter Gender -> ");
       String gender = sc.nextLine();

       System.out.print("Enter Age -> ");
       int age = sc.nextInt();

       Student s1 = new Student();
       s1.setName(name);
       s1.setClassName(className);
       s1.setGender(gender);
       s1.setAge(age);

       s.save(s1);

       tx.commit();

       System.out.println("Student Added");

    }

    private static void updateStudent(Session s,Transaction tx, Scanner sc) {

        System.out.println("Update Student");

        Query getAll = s.getNamedQuery("getAllData");
        List<Student> students = getAll.getResultList();

        System.out.print("All Student ID's  -> ");
        for(Student st : students) {
            System.out.print(st.getId()+ " ");
        }
        System.out.println();

        System.out.print("Enter ID -> ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("1. Name");
        System.out.println("2. Class Name");
        System.out.println("3. Gender");
        System.out.println("4. Age");
       
        System.out.print("What you want to update -> ");
        int ch = sc.nextInt();
        sc.nextLine();

        String colName = "";
        String newVal = "";

        switch (ch) {
            case 1:
            colName = "name";
            System.out.print("Enter New Name -> ");
            newVal = sc.nextLine();

                break;

            case 2:
            colName="className";
            System.out.print("Enter New Class Name -> ");
            newVal = sc.nextLine();

                break;

            case 3:
            colName="gender";
            System.out.print("Enter New Gender -> ");
            newVal = sc.nextLine();
                
                break;

            case 4:
            colName="age";
            System.out.print("Enter New Age -> ");
            newVal = sc.nextLine();
                
                break;
        
            default:
            System.out.println("Invalid Choice!!!!");
                break;
        }

        String updQuery = "Update Student set "+colName+" = :value Where id = :id";

        Query update = s.createQuery(updQuery);
        update.setParameter("value", newVal);
        update.setParameter("id", id);

        update.executeUpdate();
        tx.commit();

        System.out.println("Data Updated Succesfully!!!1");

    }

    

    private static void deleteStudet(Session s,Transaction tx, Scanner sc) {
        Query getAll = s.getNamedQuery("getAllData");
        List<Student> students = getAll.getResultList();

        System.out.print("All Student ID's  -> ");
        for(Student st : students) {
            System.out.print(st.getId()+ " ");
        }
        System.out.println();

        System.out.print("Enter ID To Delete The Student -> ");
        int id = sc.nextInt();

        String delQuery = "Delete from Student Where id = :id";

        Query delete = s.createQuery(delQuery);
        delete.setParameter("id", id);

        delete.executeUpdate();
        tx.commit();

        System.out.println("Student Deleted Succesfully!!!");
    }

    private static void getSingleData(Session s) {
        
        Query getSingle = s.getNamedQuery("getSingleData");
        getSingle.setParameter("id", 7);
        Student student = (Student) getSingle.getSingleResult();

        System.out.println("Student Data");

        System.out.println("----------------------------------------");
        System.out.println("ID -> "+student.getId());
        System.out.println("Name -> "+student.getName());
        System.out.println("Class -> "+student.getClassName());
        System.out.println("Gender -> "+student.getGender());
        System.out.println("Age -> "+student.getAge());
        System.out.println("----------------------------------------");
    }

    private static void getAllData(Session s) {

        Query getAll = s.getNamedQuery("getAllData");
        List<Student> students = getAll.getResultList();

        System.out.println("All Student Data");

        for(Student student : students) {
            System.out.println("----------------------------------------");
            System.out.println("ID -> "+student.getId());
            System.out.println("Name -> "+student.getName());
            System.out.println("Class -> "+student.getClassName());
            System.out.println("Gender -> "+student.getGender());
            System.out.println("Age -> "+student.getAge());
            System.out.println("----------------------------------------");
        }
    }

}
