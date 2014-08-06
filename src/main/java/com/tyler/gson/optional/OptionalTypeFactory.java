package com.tyler.gson.optional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("unchecked")	
public abstract class OptionalTypeFactory implements TypeAdapterFactory {
	
	public static final OptionalTypeFactory forGuava(){
		return new GuavaOptionalTypeFactory();
	}	
		
	protected abstract <T> boolean isOptionalType( final TypeToken<T> typeToken );
	protected abstract <E,T> OptionalTypeAdapter<E,T> newOptionalAdapter( final TypeAdapter<T> elementAdapter );
		
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
		if( !isOptionalType(typeToken))
			return null;
		
		final Type type = typeToken.getType();
		final Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
		final TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));
		
		return (TypeAdapter<T>) newOptionalAdapter(elementAdapter);
	}
}
