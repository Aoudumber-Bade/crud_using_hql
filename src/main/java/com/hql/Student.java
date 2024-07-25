package com.hql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllData", query = "FROM Student"),
    @NamedQuery(name = "getSingleData", query = "FROM Student WHERE id = :id"),
})
public class Student {
    @Id 
    @GeneratedValue
    private int id;
    private String name;
    private String className;
    private String gender;
    private int age;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public Student(int id, String name, String className, String gender, int age) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.gender = gender;
        this.age = age;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", className='" + getClassName() + "'" +
            ", gender='" + getGender() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }

}
