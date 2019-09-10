package com.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        register();
        Main.mainMenu();
        UI();

    }

    public static void register(){
        System.out.println("Enter your Name: ");
        String customerName = sc.nextLine();
        if (customerName.length() != 0) {
            System.out.println("Welcome to my mall store..." + customerName.toUpperCase());
            System.out.println("_____________________________________");

        } else {
            System.out.println("_____________________________________");
            System.out.println("Please enter a valid name !!!");
            System.out.println("_____________________________________");
            register();

        }
    }

    public static String UI() {
        int choice;
        do {
            choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice < 1 || choice > 6) {
                    System.out.println("________________________________________________________________________________________");
                    System.out.println("That was an incorrect input (input out of range). Try Again");
                    System.out.println("________________________________________________________________________________________");
                }
            } catch (NumberFormatException ex) {
                System.out.println("_______________________________________");
                System.out.println("Please enter a valid number not x-ter" + " ," + ex);
                ex.printStackTrace();
                System.out.println("___________________________________________");
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter student id: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    create(id, name, age);
                    break;

                case 2:
                    System.out.println("****update*****");
                    System.out.println("Enter student id: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter name: ");
                    name = sc.nextLine();
                    System.out.println("Enter age: ");
                    age = Integer.parseInt(sc.nextLine());

                    update(id, name, age);
                    mainMenu();
                    break;
                case 3:

                    // Delete the Alice from database
                    System.out.println("Enter student id: ");
                    int studentId = Integer.parseInt(sc.nextLine());
                    delete(studentId);
                    break;

                case 4:

//                    ArrayList<student> students = new ArrayList<student>();
//                    if (students != null) {
//                        for (student stu : students) {
//
//                        }
//                        student stu = new student();
//                        name = stu.getName();
//                        id = stu.getId();
//                        age = stu.getAge();
                    readAll();

                    break;


                case 5:
                    System.out.println("__________________________________________________________________________");
                    System.out.println("Thanks for visiting us , Good bye");
                    System.out.println("__________________________________________________________________________");

                    break;


            }


        }while (choice != 5) ;
        return

                UI();
    }


    /**
     * Create a new Student.
     *
     * @param name
     * @param age
     */
    public static void create(int id, String name, int age) {
        student stu = new student();
        stu.setId(id);
        stu.setAge(age);
        stu.setName(name);
        Configuration con = new Configuration().configure().addAnnotatedClass(student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.save(stu);

        tr.commit();
        System.out.println("created student successfully...");
        session.close();
    }

    /**
     * Read all the Students.
     *
     * @return a List of Students
     */
    public static List readAll() {
        student stu;
        Configuration con = new Configuration().configure().addAnnotatedClass(student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Query query = session.createQuery("SELECT s FROM student s");
        Transaction tr = session.beginTransaction();
        List list = query.list();
        System.out.println(list);

//        System.out.println(stu.getId()+" "+ stu.getName()+ " "+stu.getAge());

        tr.commit();
        return list;
    }

    /**
     * Delete the existing Student.
     *
     * @param id
     */
    public static void delete(int id) {
        student stu = new student();
        Configuration con = new Configuration().configure().addAnnotatedClass(student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        stu = (student) session.get(student.class,id);
        if (stu != null) {
            session.delete(stu);
            System.out.println("Student deleted......");
        }else System.out.println("User does not exist....");

        tr.commit();
    }

    /**
     * Update the existing Student.
     *
     * @param id
     * @param name
     * @param age
     */
    public static void update(int id, String name, int age) {
                student stu = new student();
                Configuration con = new Configuration().configure().addAnnotatedClass(student.class);
                ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
                SessionFactory sf = con.buildSessionFactory(reg);
                Session session = sf.openSession();
                Transaction tr = session.beginTransaction();
                stu = (student) session.get(student.class,id);
                stu.setId( id );
                stu.setName(name);
                stu.setAge(age);
                session.saveOrUpdate(stu);

                tr.commit();
                System.out.println("Record updated successfully....");
        }



    public static void mainMenu(){
        System.out.println("\nSelect option\n1.create student\n2.update\n3.delete\n4.view all\n5.Quit");
    }
}
