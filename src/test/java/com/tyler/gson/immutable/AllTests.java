package com.tyler.gson.immutable;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ImmutableListDeserializerTest.class,
		ImmutableMapDeserializerTest.class, ImmutableSetDeserializerTest.class,
		ImmutableSortedMapDeserializerTest.class,
		ImmutableSortedSetDeserializerTest.class })
public class AllTests {

}
