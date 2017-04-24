package com.akurey.jruiz.akrealm.model;

import io.realm.RealmObject;

/**
 * Created by jruiz on 4/4/2017.
 */

public class Person extends RealmObject {
    public String name;
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
