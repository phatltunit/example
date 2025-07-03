/*******************************************************************************
 * Class        ：BeanUtils
 * Created date ：2025/06/06
 * Lasted date  ：2025/06/06
 * Author       ：PhatLT
 * Change log   ：2025/06/06：1.0 PhatLT Initial creation
 ******************************************************************************/
package vn.com.phat.example.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtils {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    private BeanUtils() {
        // Private constructor to hide the implicit public one
    }

    public static void copyNonNullProperties(Object source, Object destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source and destination objects cannot be null.");
        }

        Class<?> sourceClass = source.getClass();
        Class<?> destinationClass = destination.getClass();

        for (Field sourceField : sourceClass.getDeclaredFields()) {
            if (isFieldEligibleForCopy(sourceField)) {
                copyField(source, destination, sourceField, destinationClass);
            }
        }
    }

    private static boolean isFieldEligibleForCopy(Field field) {
        return !(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()));
    }

    private static void copyField(Object source, Object destination, Field sourceField, Class<?> destinationClass) {
        boolean originalAccessible = sourceField.isAccessible();
        try {
            sourceField.setAccessible(true);
            Object value = sourceField.get(source);

            if (value != null) {
                setDestinationField(destination, sourceField, destinationClass, value);
            }
        } catch (IllegalAccessException e) {
            logger.error("Could not access field {}: {}", sourceField.getName(), e.getMessage());
        } finally {
            sourceField.setAccessible(originalAccessible);
        }
    }

    private static void setDestinationField(Object destination, Field sourceField, Class<?> destinationClass, Object value) throws IllegalAccessException {
        Field destinationField = null;
        boolean originalAccessible = false;
        try {
            destinationField = destinationClass.getDeclaredField(sourceField.getName());
            originalAccessible = destinationField.isAccessible();
            if (destinationField.getType().isAssignableFrom(sourceField.getType())) {
                destinationField.setAccessible(true);
                destinationField.set(destination, value);
            }
        } catch (NoSuchFieldException e) {
            // Field not found in destination, ignore as per requirement
        } finally {
            if (destinationField != null) {
                destinationField.setAccessible(originalAccessible);
            }
        }
    }
}
