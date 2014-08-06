package com.tyler.gson.immutable;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

public class ImmutableSortedMapDeserializer extends BaseMapDeserializer<ImmutableMap<?,?>> {

	@Override
	protected ImmutableMap<?,?> buildFrom( final Map<?, ?> map ) {
		return ImmutableSortedMap.copyOf( map );
	}
	
}