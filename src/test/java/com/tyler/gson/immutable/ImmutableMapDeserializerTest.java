package com.tyler.gson.immutable;


import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImmutableMapDeserializerTest {
	private final ImmutableMap<String,String> testMap = ImmutableMap.of( "a", "aa", "b", "bb", "c", "cc" );
	private final String json = "{ \"map\" : { \"a\" :\"aa\", \"b\" : \"bb\", \"c\" : \"cc\" } }";
	
	@Test
	public void testDeserializeImmutableInterface_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableMap.class, new ImmutableMapDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );
		
		assertThat( tester.map, Is.is( testMap ));		
	}
	
	@Test
	public void testDeserializeImplemntation_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new ImmutableMapDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );
		
		assertThat( tester.map, Is.is( (Map<String,String>) testMap ));		
	}
	
	@Test
	public void testDeserializeImplemntation_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new ImmutableMapDeserializer()).create();
		final ImmutableImplementation2 tester = gson.fromJson( json, ImmutableImplementation2.class );
		
		assertThat( tester.map, Is.is( (Map<String,String>) testMap ));		
	}

	private static final class ImmutableInterface { 
		private final ImmutableMap<String,String> map;
		
		private ImmutableInterface(){
			this.map = ImmutableMap.of();
		}		
	}
	
	private static final class ImmutableImplementation { 
		private final Map<String,String> map;
		
		private ImmutableImplementation(){
			this.map = ImmutableMap.of();
		}		
	}
	
	private static final class ImmutableImplementation2 { 
		private final HashMap<String,String> map;
		
		private ImmutableImplementation2(){
			this.map = Maps.newHashMap();
		}		
	}
}
