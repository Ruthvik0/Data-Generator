package utils;

import annotations.*;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public interface DataUtils {

    interface ValueGenerator {
        Object generateValue(Field field);
    }

     String[] FIRST_NAMES = {
            "Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace"
    };
    String[] LAST_NAMES = {
            "Smith", "Johnson", "Brown", "Davis", "Wilson", "Miller", "Moore"
    };


    static int generateIntegerValue(Field field){
        int constant = field.getAnnotation(IntegerValue.class).constant();
        if(constant!=Integer.MIN_VALUE) return constant;
        int min = field.getAnnotation(IntegerValue.class).min();
        int max = field.getAnnotation(IntegerValue.class).max();
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static boolean generateBooleanValue(Field field) {
        boolean alwaysTrue = field.getAnnotation(BooleanValue.class).alwaysTrue();
        boolean alwaysFalse = field.getAnnotation(BooleanValue.class).alwaysFalse();
        if (alwaysTrue) return true;
        if (alwaysFalse) return false;
        return ThreadLocalRandom.current().nextBoolean();
    }

    static String getRandomFirstName(Field field){
        String constant = field.getAnnotation(FirstName.class).constant();
        if (!constant.isEmpty()) return constant;
        String[] values = field.getAnnotation(FirstName.class).values();
        if (values.length!=0){
            return values[ThreadLocalRandom.current().nextInt(values.length)];
        }
        return FIRST_NAMES[ThreadLocalRandom.current().nextInt(FIRST_NAMES.length)];
    }

    static String getRandomLastName(Field field){
        String constant = field.getAnnotation(LastName.class).constant();
        if (!constant.isEmpty()) return constant;
        String[] values = field.getAnnotation(LastName.class).values();
        if (values.length!=0){
            return values[ThreadLocalRandom.current().nextInt(values.length)];
        }
    return LAST_NAMES[ThreadLocalRandom.current().nextInt(LAST_NAMES.length)];
    }

    static String getRandomEmail(Field field) {
        String constant = field.getAnnotation(Email.class).constant();
        if (!constant.isEmpty()) return constant;
        String[] values = field.getAnnotation(Email.class).values();
        if (values.length!=0){
            return values[ThreadLocalRandom.current().nextInt(values.length)];
        }
        String domain = field.getAnnotation(Email.class).domain();
        return FIRST_NAMES[ThreadLocalRandom.current().nextInt(FIRST_NAMES.length)]+domain;
    }

}
