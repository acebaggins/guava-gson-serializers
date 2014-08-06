package com.tyler.gson.immutable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.JsonDeserializer;

public class TypeAdapters {
	
	public static final Map<Type, JsonDeserializer<?>> immutableTypeMap(){
		final Map<Type, JsonDeserializer<?>> typeMap = new HashMap<Type, JsonDeserializer<?>>(5);
		
		typeMap.put( ImmutableList.class, new ImmutableListDeserializer() );
		typeMap.put( ImmutableSet.class,  new ImmutableSetDeserializer() );
		typeMap.put( ImmutableMap.class, new ImmutableMapDeserializer() );
		typeMap.put( ImmutableSortedSet.class, new ImmutableSortedSetDeserializer() );
		typeMap.put( ImmutableSortedMap.class, new ImmutableSortedMapDeserializer() );
		
		return typeMap;
	}
	
	public static final Map<Type, JsonDeserializer<?>> immutableImplemntationMap(){
		final Map<Type, JsonDeserializer<?>> typeMap = new HashMap<Type, JsonDeserializer<?>>(5);
		
		typeMap.put( List.class, new ImmutableListDeserializer() );
		typeMap.put( Set.class,  new ImmutableSetDeserializer() );
		typeMap.put( Map.class, new ImmutableMapDeserializer() );
		typeMap.put( SortedSet.class, new ImmutableSortedSetDeserializer() );
		typeMap.put( SortedMap.class, new ImmutableSortedMapDeserializer() );
		
		return typeMap;		
	}	
}
