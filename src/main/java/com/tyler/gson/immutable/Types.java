package com.tyler.gson.immutable;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

@SuppressWarnings ( {"unchecked", "serial"} ) 
public class Types {	
	public static <K,V> TypeToken<Map<K,V>> mapOf(final Type key, final Type value) {
		return new TypeToken<Map<K,V>>() {}
		.where( newTypeParameter(), typeTokenOf( key ))		
		.where( newTypeParameter(), typeTokenOf( value ));   
	}
	
	public static <K,V> TypeToken<HashMap<K,V>> hashmapOf(final Type key, final Type value) {
		return new TypeToken<HashMap<K,V>>() {}
		.where( newTypeParameter(), typeTokenOf( key ))		
		.where( newTypeParameter(), typeTokenOf( value ));   
	}
	
	public static <E> TypeToken<Collection<E>> collectionOf( final Type type ){
		return new TypeToken<Collection<E>>(){}
			.where( newTypeParameter(), typeTokenOf( type ));
	}
	
	public static <E> TypeToken<Optional<E>> optionalOf( final Type type ){
		return new TypeToken<Optional<E>>(){}
		.where( newTypeParameter(), typeTokenOf( type ) );
	}
	
	public static <E> TypeToken<E> parametrizedOf( final Class<E> clazz, final Type type ){
		return new TypeToken<E>( clazz ){}
		.where( newTypeParameter(), typeTokenOf( type ) );
	}
	
	private static <E> TypeParameter<E> newTypeParameter(){
		return new TypeParameter<E>(){};
	}
	
	private static <E> TypeToken<E> typeTokenOf( final Type type ){
		return (TypeToken<E>) TypeToken.of( type );
	}
}
