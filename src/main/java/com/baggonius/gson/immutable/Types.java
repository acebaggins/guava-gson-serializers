package com.baggonius.gson.immutable;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;

@SuppressWarnings({"unchecked", "serial", "UnstableApiUsage"})
class Types {

  private Types() {}

  static <K, V> TypeToken<HashMap<K, V>> hashMapOf(Type key, Type value) {
    TypeParameter<K> newKeyTypeParameter = new TypeParameter<K>() {};
    TypeParameter<V> newValueTypeParameter = new TypeParameter<V>() {};
    return new TypeToken<HashMap<K, V>>() {}
      .where(newKeyTypeParameter, Types.typeTokenOf(key))
      .where(newValueTypeParameter, Types.typeTokenOf(value));
  }

  static <E> TypeToken<Collection<E>> collectionOf(Type type) {
    TypeParameter<E> newTypeParameter = new TypeParameter<E>() {};
    return new TypeToken<Collection<E>>() {}
      .where(newTypeParameter, Types.typeTokenOf(type));
  }

  private static <E> TypeToken<E> typeTokenOf(Type type) {
    return (TypeToken<E>) TypeToken.of(type);
  }
}
