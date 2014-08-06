package com.tyler.gson;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	com.tyler.gson.immutable.AllTests.class,
	com.tyler.gson.optional.AllTests.class, })
public class AllTests {

}
