package com.baggonius.gson.optional;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

@Deprecated
  // changing this library to be guava only
class JavaOptionalTypeFactory extends OptionalTypeFactory {

  @Override
  public <T> boolean isOptionalType(TypeToken<T> typeToken) {
    return typeToken.getRawType() == Optional.class && (typeToken.getType() instanceof ParameterizedType);
  }

  @SuppressWarnings("unchecked")
  protected <E, T> OptionalTypeAdapter<E, T> newOptionalAdapter(TypeAdapter<T> elementAdapter) {
    return (OptionalTypeAdapter<E, T>) new JDKOptionalAdapter<>(elementAdapter);
  }

  public static class JDKOptionalAdapter<T> extends OptionalTypeAdapter<Optional<T>, T> {

    JDKOptionalAdapter(TypeAdapter<T> elementAdapter) {
      super(elementAdapter);
    }

    @Override
    protected boolean isPresent(Optional<T> e) {
      return e.isPresent();
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent") // it's fine
    protected T getValue(Optional<T> e) {
      return e.get();
    }

    @Override
    protected Optional<T> presentOptional(T t) {
      return Optional.of(t);
    }

    @Override
    protected Optional<T> emptyOptional() {
      return Optional.empty();
    }
  }
}
