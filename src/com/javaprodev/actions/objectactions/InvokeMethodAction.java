package com.javaprodev.actions.objectactions;

import com.javaprodev.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by X on 8/24/2017.
 */
public class InvokeMethodAction extends ObjectAction {
    List<String> setters;
    String mySetter;
    Class parameterClass;

    public InvokeMethodAction(Class clazz, String param, Object value, Class parameterClass) {
        super(clazz, param, value);
        this.parameterClass = parameterClass;
        findSetters();
        findMySetter();
    }


    public Object perform(Person person) {
        try {

            Method method = super.getClazz().getMethod(mySetter, parameterClass);
            method.invoke(person, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }





    private void findMySetter(){
        for (String setter : setters) {
           // System.out.println(setter);
            if( Pattern.compile(Pattern.quote(param), Pattern.CASE_INSENSITIVE).matcher(setter).find()){
                mySetter = setter;
                //System.out.println(mySetter);
            }
        }
    }


    void findSetters() {
        setters = new ArrayList<String>();
        Method[] methods = super.getClazz().getDeclaredMethods();
        for (Method method : methods)
            if (isSetter(method)){
                setters.add(method.getName());
                //System.out.println(method.getName());
            }
    }

    private boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) &&
                method.getReturnType().equals(void.class) &&
                method.getParameterTypes().length == 1 &&
                method.getName().matches("^set[A-Z].*");
    }
}
