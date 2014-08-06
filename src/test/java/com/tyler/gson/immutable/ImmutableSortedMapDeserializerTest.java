package com.tyler.gson.immutable;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.SortedMap;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImmutableSortedMapDeserializerTest {
	private final ImmutableSortedMap<String,String> testMap = ImmutableSortedMap.of( "a", "aa", "b", "bb", "c", "cc" );
	private final String json = "{ \"map\" : { \"b\" : \"bb\", \"a\" :\"aa\",  \"c\" : \"cc\" } }";
	
	@Test
	public void testDeserializeImmutableInterface_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSortedMap.class, new ImmutableSortedMapDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );

		assertEquals( tester.map, testMap );		
	}
	
	@Test
	public void testDeserializeImplemntation_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(SortedMap.class, new ImmutableSortedMapDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );
		
		assertThat( tester.map, Is.is( (Map<String,String>) testMap ));		
	}

	private static final class ImmutableInterface { 
		private final ImmutableSortedMap<String,String> map;
		
		private ImmutableInterface(){
			this.map = ImmutableSortedMap.of();
		}		
	}
	
	private static final class ImmutableImplementation { 
		private final SortedMap<String,String> map;
		
		private ImmutableImplementation(){
			this.map = ImmutableSortedMap.of();
		}		
	}
}
