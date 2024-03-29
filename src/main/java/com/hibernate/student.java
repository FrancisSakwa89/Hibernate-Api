package com.hibernate;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "student")
public class student implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "name", nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
            return id +"\t" + name +"\t" + age;
        }

}
