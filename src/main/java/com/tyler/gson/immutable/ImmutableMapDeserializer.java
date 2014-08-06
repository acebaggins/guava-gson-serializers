package com.tyler.gson.immutable;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapDeserializer extends BaseMapDeserializer<ImmutableMap<?,?>> {

	@Override
	protected ImmutableMap<?,?> buildFrom( final Map<?, ?> map ) {
		return ImmutableMap.copyOf( map );
	}
	
}