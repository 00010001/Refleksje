package com.javaprodev.actions;

/**
 * Created by X on 8/24/2017.
 */
public abstract class AbstractAction implements Performable {
    private Class clazz;

    public AbstractAction(Class clazz){
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
