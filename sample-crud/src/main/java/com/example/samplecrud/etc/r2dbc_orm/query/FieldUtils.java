package com.example.samplecrud.etc.r2dbc_orm.query;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FieldUtils {
  public static Field[] getAllFields(Class<?> clazz) {

    return getFields(clazz, new ArrayList<>());
  }

  private static Field[] getFields(Class<?> clazz, List<Field> result) {
    result.addAll(Arrays.asList(clazz.getDeclaredFields()));
    /* 슈퍼 클래스가 Object면 바로 리턴. */
    if (clazz.getSuperclass().equals(Object.class)) return result.toArray(new Field[0]);
    else {
      return getFields(clazz.getSuperclass(), result);
    }
  }
}
