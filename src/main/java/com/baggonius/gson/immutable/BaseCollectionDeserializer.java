package com.baggonius.gson.immutable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.baggonius.gson.common.ImmutableDeserializerException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

abstract class BaseCollectionDeserializer<E> implements JsonDeserializer<E> {

  protected abstract E buildFrom(Collection<?> collection);

  public E deserialize(JsonElement json, Type type, JsonDeserializationContext context) {

    try {
      Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
      Type parameterizedType = Types.collectionOf(typeArguments[0]).getType();
      Collection<?> collection = context.deserialize(json, parameterizedType);

      return buildFrom(collection);
    } catch (Exception e) {
      throw new ImmutableDeserializerException(e);
    }
  }
}
