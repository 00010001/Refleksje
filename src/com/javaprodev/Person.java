package com.javaprodev;

import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by X on 8/24/2017.
 */
public class Person {
    private int height;

    @DynamicProperty
    private boolean adult;

    private String firstName;
    private String lastName;


    //TODO implement
    private int weight;
    private boolean smoker;
    private int[] hairColor; // RGBA
    private int[] eyeColor;

    // private final StringProperty dogName;

    public Person(int height, boolean adult, String firstName, String lastName) {
        this.height = height;
        this.adult = adult;
        this.firstName = firstName;
        this.lastName = lastName;
        //  this.dogName = null;
    }

//    public Person(@NamedArg("dogName") String dogName, int height, boolean adult, String firstName, String lastName) {
//        this.dogName = new SimpleStringProperty(this, "dogName", firstName);
//    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                ", adult=" + adult +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
