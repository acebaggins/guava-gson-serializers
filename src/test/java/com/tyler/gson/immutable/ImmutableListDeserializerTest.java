package com.tyler.gson.immutable;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.google.common.collect.Iterables;
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
	public void testDeserializeImmutableInterface() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer()).create();
		final ImmutableInterface tester = gson.fromJson( json, ImmutableInterface.class );

		assertThat( tester.list, Is.is( testList ));
	}

	@Test
	public void testDeserializeImplementation() {
		final Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new ImmutableListDeserializer()).create();
		final ImmutableImplementation tester = gson.fromJson( json, ImmutableImplementation.class );

		assertThat( tester.list, Is.is( (List<String>)testList ));
	}

	@Test
  public void testDeserializeNested() {
	  final Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new ImmutableListDeserializer()).create();
	  final Parent tester = gson.fromJson("{'list': [{'name':'nested'}]}",Parent.class);

	  assertEquals(Iterables.getOnlyElement(tester.list).name, "nested");
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

	private static class Parent {
		private List<Nested> list;

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
