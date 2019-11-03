package com.baggonius.gson.optional;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public abstract class OptionalTypeFactory implements TypeAdapterFactory {

  @Deprecated // handled better by other libraries
  public static OptionalTypeFactory forJDK() {
    return new JavaOptionalTypeFactory();
  }

  public static OptionalTypeFactory forGuava() {
    return new GuavaOptionalTypeFactory();
  }

  protected abstract <T> boolean isOptionalType(TypeToken<T> typeToken);

  protected abstract <E, T> OptionalTypeAdapter<E, T> newOptionalAdapter(TypeAdapter<T> elementAdapter);

  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
    if (!isOptionalType(typeToken))
      return null;

    Type type = typeToken.getType();
    Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
    TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));

    return (TypeAdapter<T>) newOptionalAdapter(elementAdapter);
  }
}
