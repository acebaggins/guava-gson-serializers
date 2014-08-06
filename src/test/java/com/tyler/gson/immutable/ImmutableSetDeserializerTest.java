package com.tyler.gson.immutable;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImmutableSetDeserializerTest {
	private final ImmutableSet<String> testSet = ImmutableSet.of( "a", "b", "c" );
	private final String json = "{ \"set\" : [\"a\", \"b\", \"c\" ] }";
	
	@Test
	public void testDeserializeImmutableInterface_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSet.class, new ImmutableSetDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );
		
		assertThat( tester.set, Is.is( testSet ));		
	}
	
	@Test( expected = ClassCastException.class )
	public void testDeserializeImmutableInterface_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSet.class, new ImmutableSetDeserializer()).create();
		ImmutableInterface tester = gson.fromJson( "{ \"set\" : [1,2,3] }", ImmutableInterface.class );	
		
		assertNotNull( Iterables.get( tester.set, 0).getClass() );
	}
	
	@Test
	public void testDeserializeImplemntation_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(Set.class, new ImmutableSetDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );
		
		assertThat( tester.set, Is.is( (Set<String>)testSet ));		
	}
	
	@Test( expected = ClassCastException.class )
	public void testDeserializeImmutableImplemntation_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(Set.class, new ImmutableSetDeserializer()).create();
		ImmutableImplementation tester = gson.fromJson( "{ \"set\" : [1,2,3] }", ImmutableImplementation.class );
		
		assertNotNull( Iterables.get( tester.set, 0).getClass() );
	}
		
	private static final class ImmutableInterface { 
		private final ImmutableSet<String> set;
		
		private ImmutableInterface(){
			this.set = ImmutableSet.of();
		}		
	}
	
	private static final class ImmutableImplementation { 
		private final Set<String> set;
		
		private ImmutableImplementation(){
			this.set = ImmutableSet.of();
		}		
	}
}
