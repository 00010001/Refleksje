package com.javaprodev.actions;

import com.javaprodev.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 8/25/2017.
 */
public class Construct<T> {
    Constructor<T> objectConstructor;
    Constructor longestConstructor;
    List<Object> userParams;
    List<Class> constructorParams;
    Class clazz;


    public Construct(Class<T> clazz) {
        ProcessConstruct(clazz);
    }

    public Constructor<T> ProcessConstruct(Class<T> clazz) {

        this.clazz = clazz;
        getLongestConstructor();
        getConstructorParams();
        getInputParams();
        constructObjectConstructor();
        return objectConstructor;

    }

    public T create() {
        Constructor x = objectConstructor;
        System.out.println(objectConstructor);

        try {
            return objectConstructor.newInstance(userParams.toArray());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void constructObjectConstructor() {
        //   SimpleStringProperty x = new SimpleStringProperty(constructorParams.get(0), "dogName", "dogName");
        for (Object userParam : userParams) {
            System.out.println(userParam);
        }
        // objectConstructor = Person.class.getConstructor(int.class,boolean.class, String.class,String.class).newInstance(userParams.toArray());
        try {
            objectConstructor = (Constructor<T>) clazz.getConstructor(constructorParams.get(0), constructorParams.get(1), constructorParams.get(2), constructorParams.get(3));
            // objectConstructor = (Constructor<T>)Person.class.getConstructor(constructorParams.get(0), constructorParams.get(1), constructorParams.get(2), constructorParams.get(3));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //    (constructorParams.get(0),constructorParams.get(1),constructorParams.get(2),constructorParams.get(3),constructorParams.get(4)).newInstance(userParams.toArray());
    }

    private void getConstructorParams() {
        constructorParams = new ArrayList<>();
        System.out.println("Constructor params");
        for (Parameter parameter : longestConstructor.getParameters()) {
            constructorParams.add(parameter.getType());
            System.out.println(parameter.getType());
        }
    }

    private void getInputParams() {
        userParams = new ArrayList<>();

        //userParams.add(new SimpleStringProperty(constructorParams.get(0), "dogName", "dogName"));
        userParams.add(1000);
        userParams.add(false);
        userParams.add("nameX");
        userParams.add("lastNameY");

        System.out.println("User Paramas");
        for (Object userParam : userParams) {
            System.out.println(userParam);
        }

    }

    private void getLongestConstructor() {

        Constructor[] constructors = clazz.getConstructors();
        longestConstructor = constructors[0];

        for (Constructor c : constructors) {
            if (c.getParameters().length > longestConstructor.getParameters().length) {
                longestConstructor = c;
            }
        }
    }
}
