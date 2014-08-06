package com.tyler.gson.optional;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public abstract class OptionalTypeFactory implements TypeAdapterFactory {
	
	public static final OptionalTypeFactory forJDK(){
		return new JavaOptionalTypeFactory();
	}
	
	public static final OptionalTypeFactory forGuava(){
		return new JavaOptionalTypeFactory();
	}	
	
	
	protected abstract <T> boolean isOptionalType( final TypeToken<T> typeToken );
	protected abstract <E,T> OptionalTypeAdapter<E,T> newOptionalAdapter( final TypeAdapter<T> elementAdapter );
	
	@SuppressWarnings("unchecked")	
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
		checkArgument( isOptionalType( typeToken ), "cannot create optional from typeToken: " + typeToken);
		
		final Type type = typeToken.getType();
		final Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
		final TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));
		
		return (TypeAdapter<T>) newOptionalAdapter(elementAdapter);
	}
		
	protected static abstract class OptionalTypeAdapter<E,T> extends TypeAdapter<E> {
		protected final TypeAdapter<T> elementAdapter;
		
		protected OptionalTypeAdapter( final TypeAdapter<T> elementAdapter ){
			this.elementAdapter = elementAdapter;
		}
		
		protected abstract boolean isPresent( E e );
		protected abstract T getValue( E e );
		protected abstract E presentOptional( T t );
		protected abstract E emptyOptional();
		
		public void write( final JsonWriter out, final E value) throws IOException {
			if (value == null || !isPresent( value )) {
				out.nullValue();
				return;
			}
			
			elementAdapter.write(out, getValue( value ));
		}

		public E read( final JsonReader in ) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return emptyOptional();
		}
			
			return presentOptional( elementAdapter.read(in));
		}
	}
}
