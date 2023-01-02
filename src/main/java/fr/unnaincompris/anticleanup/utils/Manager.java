package fr.unnaincompris.anticleanup.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public interface Manager {
    /**
     * Get called when the client shutdown
     */
    default void stop(){}

    class Enabler {

        /**
         * Create a instance of every class that implement Manager is the "instance" class
         */

        public static <T> void init(T instance) {
            try {
                for (Field field : instance.getClass().getDeclaredFields()) {
                    if (!Manager.class.isAssignableFrom(field.getType())) continue;
                    field.setAccessible(true);
                    Constructor<?> constructor = field.getType().getDeclaredConstructor();
                    field.set(instance, constructor.newInstance());
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        /**
         * Call the stop function on every class that implement Manager is the "instance" class
         */

        public static <T> void stop(T instance) {
            for (Field field : instance.getClass().getDeclaredFields()) {
                try {
                    if (!Manager.class.isAssignableFrom(field.getType())) continue;
                    field.setAccessible(true);
                    Manager manager = ((Manager) instance.getClass().getField(field.getName()).get(instance));
                    if (manager != null)
                        manager.stop();
                } catch(Exception ignored){}
            }
        }
    }

}
