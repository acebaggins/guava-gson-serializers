package com.baggonius.gson.optional;

import com.google.common.base.Optional;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;

public class GuavaOptionalTypeFactory extends OptionalTypeFactory {

  @Override
  public <T> boolean isOptionalType(TypeToken<T> typeToken) {
    return typeToken.getRawType() == Optional.class && (typeToken.getType() instanceof ParameterizedType);
  }

  @SuppressWarnings("unchecked")
  protected <E, T> OptionalTypeAdapter<E, T> newOptionalAdapter(TypeAdapter<T> elementAdapter) {
    return (OptionalTypeAdapter<E, T>) new GuavaOptionalAdapter<>(elementAdapter);
  }

  @SuppressWarnings("Guava")
  private static class GuavaOptionalAdapter<T> extends OptionalTypeAdapter<Optional<T>, T> {

    protected GuavaOptionalAdapter(TypeAdapter<T> elementAdapter) {
      super(elementAdapter);
    }

    @Override
    protected boolean isPresent(Optional<T> e) {
      return e.isPresent();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") // it is checked
    @Override
    protected T getValue(Optional<T> e) {
      return e.get();
    }

    @Override
    protected Optional<T> presentOptional(T t) {
      return Optional.of(t);
    }

    @Override
    protected Optional<T> emptyOptional() {
      return Optional.absent();
    }
  }
}
