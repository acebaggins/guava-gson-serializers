package com.tyler.gson.immutable;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.hamcrest.core.Is;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ImmutableSetDeserializerTest {
	private final ImmutableSet<String> testSet = ImmutableSet.of( "a", "b", "c" );
	private final String json = "{ \"set\" : [\"a\", \"b\", \"c\" ] }";

	@Test
	public void testDeserializeImmutableInterface() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableSet.class, new ImmutableSetDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );

		assertThat( tester.set, Is.is( testSet ));
	}

	@Test
	public void testDeserializeImplementation() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(Set.class, new ImmutableSetDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );

		assertThat( tester.set, Is.is( (Set<String>)testSet ));
	}

	@Test
	public void testDeserializeNested() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(Set.class, new ImmutableSetDeserializer()).create();
		final Parent tester = gson.fromJson("{'set': [{'name':'nested'}]}",Parent.class);

		assertEquals(tester.set.iterator().next().name, "nested");
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

	private static class Parent {
		private Set<Nested> set;

		private static class Nested {
			private String name;

			public boolean equals(Object other) {
				if(!(other instanceof Nested))
					return false;
				return name.equals(((Nested) other).name);
			}
		}
	}
}
