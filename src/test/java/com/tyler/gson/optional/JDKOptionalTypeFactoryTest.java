package com.tyler.gson.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JDKOptionalTypeFactoryTest {

	@Test
	public void test() {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forJDK() ).create();
		final String json = "{ \"optional\" : \"NOPTIONAL!\" }";
				
		final OptionalTester tester = gson.fromJson(json, OptionalTester.class);
		assertTrue( tester.optional.isPresent() );
		assertEquals( "NOPTIONAL!", tester.optional.get() );		
	}

	private static final class OptionalTester { 
		private final Optional<String> optional;
		
		private OptionalTester(){
			this.optional = Optional.empty();
		}		
	}
}
