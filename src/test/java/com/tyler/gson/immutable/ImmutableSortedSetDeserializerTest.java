package com.tyler.gson.immutable;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.SortedSet;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImmutableSortedSetDeserializerTest {
	private final ImmutableSortedSet<String> testSet = ImmutableSortedSet.of( "a", "b", "c" );
	private final String json = "{ \"set\" : [ \"b\", \"a\", \"c\" ] }";
	
	@Test
	public void testDeserializeImmutableInterface_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSortedSet.class, new ImmutableSortedSetDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );
		
		assertThat( tester.set, Is.is( testSet ));

	}
	@Test
	public void testDeserializeImmutableInterface_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSortedSet.class, new ImmutableSortedSetDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );

		assertEquals( Iterables.get( tester.set, 0), "a" );
		assertEquals( Iterables.get( tester.set, 1), "b" );
		assertEquals( Iterables.get( tester.set, 2), "c" );
	}
	
	@Test( expected = ClassCastException.class )
	public void testDeserializeImmutableInterface_3() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSortedSet.class, new ImmutableSortedSetDeserializer()).create();
		ImmutableInterface tester = gson.fromJson( "{ \"set\" : [1,2,3] }", ImmutableInterface.class );	
		
		assertNotNull( Iterables.get( tester.set, 0).getClass() );
	}
	
	@Test
	public void testDeserializeImplemntation_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(SortedSet.class, new ImmutableSortedSetDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );
		
		assertThat( tester.set, Is.is( (Set<String>)testSet ));		
	}
	
	@Test
	public void testDeserializeImplemntation_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(SortedSet.class, new ImmutableSortedSetDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );
		
		assertEquals( Iterables.get( tester.set, 0), "a" );
		assertEquals( Iterables.get( tester.set, 1), "b" );
		assertEquals( Iterables.get( tester.set, 2), "c" );
	}
	
	@Test( expected = ClassCastException.class )
	public void testDeserializeImmutableImplemntation_3() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(SortedSet.class, new ImmutableSortedSetDeserializer()).create();
		ImmutableImplementation tester = gson.fromJson( "{ \"set\" : [1,2,3] }", ImmutableImplementation.class );
		
		assertNotNull( Iterables.get( tester.set, 0).getClass() );		
	}
		
	
	
	private static final class ImmutableInterface { 
		private final ImmutableSortedSet<String> set;
		
		private ImmutableInterface(){
			this.set = ImmutableSortedSet.of();
		}		
	}
	
	private static final class ImmutableImplementation { 
		private final SortedSet<String> set;
		
		private ImmutableImplementation(){
			this.set = Sets.newTreeSet();
		}		
	}
}
