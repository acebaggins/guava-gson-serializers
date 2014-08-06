package com.tyler.gson.immutable;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class ImmutableListDeserializer extends BaseCollectionDeserializer<ImmutableList<?>> {
	
	@Override
	protected ImmutableList<?> buildFrom(Collection<?> collection) {
		return ImmutableList.copyOf( collection );
	}
	
}