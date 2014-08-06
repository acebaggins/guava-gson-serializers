package com.tyler.gson.immutable;

import java.util.Collection;

import com.google.common.collect.ImmutableSortedSet;

public class ImmutableSortedSetDeserializer extends BaseCollectionDeserializer<ImmutableSortedSet<?>> {
	
	@Override
	protected ImmutableSortedSet<?> buildFrom( final Collection<?> collection ) {			
		return ImmutableSortedSet.copyOf( collection );
	}
	
}