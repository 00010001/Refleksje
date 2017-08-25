package com.javaprodev.actions;

import com.javaprodev.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by X on 8/24/2017.
 */
public class ConstructorAction<T> extends AbstractAction{

    private List<Object> params;

    public ConstructorAction(Class<T> clazz, List<Object> params) {
        super(clazz);
        this.params = params;
    }





    @Override
    public Person perform() {

        try {
            return (Person) super.getClazz().getConstructor(int.class,boolean.class, String.class,String.class).newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


}
