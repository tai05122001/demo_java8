package com.example.demo_java_8.model;

public class Person {
    private int id ;
    private String name ;
    private String phone;
    private String address;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person(int id, String name, String phone, String address ,int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }
    public int comparePerson(Person a ){
        return  this.getAge()>a.getAge()?1:(this.getAge()==a.getAge()?0:-1);

    }
    public boolean getAgeGraterThan18(){
        return this.getAge()> 18;
    }
    

    @Override
    public String toString() {
        return "ID: " + getId() + "\nName: " + getName() + "\nPhone: " + getPhone() + "\nAddress: "+ getAddress()+"\nAge: "+ getAge()+"\n---------------------\n";
    }
}
