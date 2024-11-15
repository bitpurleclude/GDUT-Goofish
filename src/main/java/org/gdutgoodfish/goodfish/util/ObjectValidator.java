package org.gdutgoodfish.goodfish.util;

import org.gdutgoodfish.goodfish.annotation.CannotNull;

import java.lang.reflect.Field;

public class ObjectValidator {

    public static boolean validate(Object obj) {
        if (obj == null) {
            return false;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CannotNull.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
