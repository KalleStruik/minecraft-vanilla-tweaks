package nl.kallestruik.vanillatweaks.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static Field getField(Class clazz, String fieldName)
            throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    public static Object getValueFromField(Object instance, String fieldName) {
        try {
            Field connectedChannelsField = getField(instance.getClass(), fieldName);
            connectedChannelsField.setAccessible(true); //required if field is not normally accessible

            return connectedChannelsField.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
