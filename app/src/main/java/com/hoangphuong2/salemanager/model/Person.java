package com.hoangphuong2.salemanager.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MrAn on 13-May-16.
 */
public class Person {
    public int idPerson;
    public String name;
    public String address;
    public String email;
    public String note;
    public int sex;
    public int idCompany;
    public List<Phone> listPhone = new ArrayList<>();

    public Person() {}

    public Person(int idPerson, String name, String address, String email, String note, int sex, int idCompany, List<Phone> listPhone) {
        this.idPerson = idPerson;
        this.name = name;
        this.address = address;
        this.email = email;
        this.note = note;
        this.sex = sex;
        this.idCompany = idCompany;
        this.listPhone = listPhone;
    }

    public static Comparator<Person> COMPARE_BY_NAME = new Comparator<Person>() {
        public int compare(Person one, Person other) {
            return one.name.toLowerCase().compareTo(other.name.toLowerCase());
        }
    };
}
