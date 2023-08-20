package datagenerator;

import annotations.BooleanValue;
import annotations.Email;
import annotations.FirstName;
import annotations.IntegerValue;
import exceptions.InstanceCreationException;
import exceptions.SetterInvokingException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import utils.DataUtils;

public final class DataGenerator {
  private DataGenerator() {}

  public static <T> T generate(Class<T> tClass) {
    T instance = getInstance(tClass);
    Field[] fields = tClass.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(IntegerValue.class) && field.getType() == int.class) {
        setValueFromAnnotation(instance, field, DataUtils::generateIntegerValue);
      }
     else if (field.isAnnotationPresent(BooleanValue.class) && field.getType() == boolean.class) {
        setValueFromAnnotation(instance, field, DataUtils::generateBooleanValue);
      }
      else if (field.isAnnotationPresent(FirstName.class) && field.getType() == String.class) {
        setValueFromAnnotation(instance, field, DataUtils::getRandomFirstName);
      }
      else if (field.isAnnotationPresent(Email.class) && field.getType() == String.class) {
        setValueFromAnnotation(instance, field, DataUtils::getRandomEmail);
      }
    }
    return instance;
  }
  
  private static <T> void setValueFromAnnotation(T instance, Field field, DataUtils.ValueGenerator generator) {
    try {
      MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(instance.getClass(),MethodHandles.lookup());
      MethodHandle setter = lookup.findSetter(instance.getClass(), field.getName(), field.getType());
      setter.invoke(instance, generator.generateValue(field));
    } catch (Throwable e) {
      throw new SetterInvokingException(e);
    }
  }
  private static <T> T getInstance(Class<T> tClass) {
    T instance;
    try {
      instance = tClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new InstanceCreationException(e);
    }
    return instance;
  }
}
