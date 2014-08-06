package com.tyler.gson.immutable;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImmutableListDeserializerTest {
	private final ImmutableList<String> testList = ImmutableList.of( "a", "b", "c" );
	private final String json = "{ \"list\" : [\"a\", \"b\", \"c\" ] }";
	
	@Test
	public void testDeserializeImmutableInterface_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );
		
		assertThat( tester.list, Is.is( testList ));		
	}
	
	@Test( expected = ClassCastException.class )
	public void testDeserializeImmutableInterface_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer()).create();
		ImmutableInterface tester = gson.fromJson( "{ \"list\" : [1,2,3] }", ImmutableInterface.class );
		assertNotNull( tester.list.get(0 ).getClass() );
	}
	
	@Test
	public void testDeserializeImplemntation_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new ImmutableListDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );
		
		assertThat( tester.list, Is.is( (List<String>)testList ));		
	}
	
	@Test( expected = ClassCastException.class )
	public void testDeserializeImmutableImplemntation_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new ImmutableListDeserializer()).create();
		ImmutableImplementation tester = gson.fromJson( "{ \"list\" : [1,2,3] }", ImmutableImplementation.class );

		assertNotNull( tester.list.get(0 ).getClass() );
	}
		
	private static final class ImmutableInterface { 
		private final ImmutableList<String> list;
		
		private ImmutableInterface(){
			this.list = ImmutableList.of();
		}		
	}
	
	private static final class ImmutableImplementation { 
		private final List<String> list;
		
		private ImmutableImplementation(){
			this.list = ImmutableList.of();
		}		
	}
}
