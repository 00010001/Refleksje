package com.javaprodev.actions.objectactions;

import com.javaprodev.Person;

import java.lang.reflect.Field;

/**
 * Created by X on 8/24/2017.
 */
public class UpdateFieldAction extends ObjectAction {


    public UpdateFieldAction(Class clazz, String param, Object value) {
        super(clazz, param, value);
    }

    public Object perform(Person person) {
        Field update;
        try {
            update = getClazz().getDeclaredField(param);
            update.setAccessible(true);
            update.set(person, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }
}
