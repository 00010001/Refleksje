package com.javaprodev;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by X on 8/25/2017.
 */
public class DynamicUtils {

    static List<Field> getFieldsWithAnnotations(Class clazz, Class annotationClass) {

        List<Field> dynamicFields = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                dynamicFields.add(field);
            }
        }
        return dynamicFields;
    }

    static void updateProperties(Object object, HashMap<String, Object> properties) throws NoSuchFieldException, IllegalAccessException {

        System.out.println("updating dynamic properties");

        Iterator it = properties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            Field fieldToModify = object.getClass().getDeclaredField((String) pair.getKey());

            if (isDynamic(fieldToModify, object.getClass())) {
                fieldToModify.setAccessible(true);
                fieldToModify.set(object, pair.getValue());
            } else {
                System.out.println(fieldToModify.getName() + " field is not dynamic");
            }


            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    private static boolean isDynamic(Field field, Class objectClass) throws NoSuchFieldException, IllegalAccessException {

        System.out.println("checkForDynamic");
        List<Field> dynamicFields = new ArrayList<>();
        dynamicFields = DynamicUtils.getFieldsWithAnnotations(objectClass, DynamicProperty.class);

        for (Field dynamicField : dynamicFields) {
            if (dynamicField.getName().equals(field.getName())) {
                return true;
            }
        }
        return false;


    }

}
