package uz.jurayev.account.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Man implements Person {

    private String name;
    private int age;
    private String city;
    private String country;

    public Man(String name, int age, String city, String country) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.country = country;
    }

    @Override
    public void introduce(String name) {
        System.out.println("My name is " + name);
    }

    @Override
    public void sayAge(int age) {
        System.out.println("I am " + this.age + " years old");
    }

    @Override
    public void sayWhereFrom(String city, String country) {
        System.out.println("I'm from " + this.city + ", " + this.country);
    }
}
