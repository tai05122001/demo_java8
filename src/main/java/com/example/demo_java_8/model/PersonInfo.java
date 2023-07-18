package com.example.demo_java_8.model;

public class PersonInfo {
    private String name ;
    private String phone;
    private String address;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return  "Name: " + getName() + " Phone: " + getPhone() + " Address: "+ getAddress()+" Age: "+ getAge();
    }

    public PersonInfo(String name, String phone, String address, int age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }
}
