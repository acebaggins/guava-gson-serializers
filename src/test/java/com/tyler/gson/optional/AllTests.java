package com.tyler.gson.optional;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GuavaOptionalTypeFactoryTest.class,
		JDKOptionalTypeFactoryTest.class })
public class AllTests {

}
