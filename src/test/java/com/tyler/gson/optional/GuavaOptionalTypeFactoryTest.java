package com.tyler.gson.optional;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GuavaOptionalTypeFactoryTest {

	@Test
	public void test() {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forGuava() ).create();
		final String json = "{ \"optional\" : \"NOPTIONAL!\" }";
		
		final OptionalTester tester = gson.fromJson(json, OptionalTester.class);
		assertTrue( tester.optional.isPresent() );
		assertEquals( "NOPTIONAL", tester.optional.get() );		
	}

	private static final class OptionalTester { 
		private final Optional<String> optional;
		
		private OptionalTester(){
			this.optional = Optional.absent();
		}		
	}
	

	
}
