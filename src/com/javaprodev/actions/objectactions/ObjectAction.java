package com.javaprodev.actions.objectactions;

import com.javaprodev.Person;
import com.javaprodev.actions.AbstractAction;

/**
 * Created by X on 8/24/2017.
 */
public class ObjectAction<T> extends AbstractAction {

    String param;
    Object value;

    public ObjectAction(Class clazz, String param, Object value) {
        super(clazz);
        this.param = param;
        this.value = value;
    }


    @Override
    public T perform() {
        return null;
    }
}
