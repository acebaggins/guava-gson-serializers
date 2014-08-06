package com.tyler.gson.immutable;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

public class ImmutableSetDeserializer extends BaseCollectionDeserializer<ImmutableSet<?>> {	
	
	@Override
	protected ImmutableSet<?> buildFrom(Collection<?> collection) {
		return ImmutableSet.copyOf( collection );
	}
	
}