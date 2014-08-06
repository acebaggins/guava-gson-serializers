package com.tyler.gson.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GuavaOptionalTypeFactoryTest {

	@Test
	public void testSerialize_1() {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forGuava() ).create();
		final String json = "{ \"optional\" : \"NOPTIONAL!\" }";
		
		final OptionalTester tester = gson.fromJson(json, OptionalTester.class);
		assertTrue( tester.optional.isPresent() );
		assertEquals( "NOPTIONAL!", tester.optional.get() );
	}
	
	@Test
	public void testSerialize_2() {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forGuava() ).create();
		final String json = "{  }";
		
		final OptionalTester tester = gson.fromJson(json, OptionalTester.class);
		assertFalse( tester.optional.isPresent() );
	}
	
	@Test( expected = IllegalStateException.class )
	public void testSerialize_3() {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forGuava() ).create();
		final String json = "{  }";
		
		final OptionalTester tester = gson.fromJson(json, OptionalTester.class);
		tester.optional.get();
	}

	private static final class OptionalTester { 
		private final Optional<String> optional;
		
		private OptionalTester(){
			this.optional = Optional.absent();
		}		
	}	
}