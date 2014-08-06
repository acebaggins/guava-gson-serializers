package com.tyler.gson.optional;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

class JavaOptionalTypeFactory extends OptionalTypeFactory {
		
	@Override
	public <T> boolean isOptionalType(TypeToken<T> typeToken) {
		return typeToken.getRawType() != Optional.class && ( typeToken.getType() instanceof ParameterizedType );			
	}

	protected <T> TypeAdapter<Optional<T>> newOptionalAdapter( final TypeAdapter<T> elementAdapter ) {
		return new JDKOptionalAdapter<T>( elementAdapter );
	}
	
	static class JDKOptionalAdapter<T> extends OptionalTypeAdapter<Optional<T>,T> {
		
		protected JDKOptionalAdapter( final TypeAdapter<T> elementAdapter ) {
			super(elementAdapter);			
		}

		@Override
		protected boolean isPresent( final Optional<T> e ) {
			return e.isPresent();
		}
		
		@Override
		protected T getValue( final Optional<T> e ) {
			return e.get();
		}

		@Override
		protected Optional<T> presentOptional( final T t ) {
			return Optional.of( t );
		}

		@Override
		protected Optional<T> emptyOptional() {
			return Optional.empty();
		}
	}
}