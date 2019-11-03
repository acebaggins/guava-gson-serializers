# guava-gson-serializers
[![Build Status](https://travis-ci.com/acebaggins/guava-gson-serializers.svg?branch=master)](https://travis-ci.com/acebaggins/guava-gson-serializers)
[![Coverage Status](https://coveralls.io/repos/github/acebaggins/guava-gson-serializers/badge.svg?branch=master)](https://coveralls.io/github/acebaggins/guava-gson-serializers?branch=master)
[![Apache 2.0](https://img.shields.io/github/license/nebula-plugins/nebula-publishing-plugin.svg)](http://www.apache.org/licenses/LICENSE-2.0)


#### Expanded Gson serialization for Guava ImmutableCollections, ImmutableMaps, and Optionals.

I originally included a TypeFactory for both Optional (java.util and Guava) but that was a long time ago and I think
most people have switched over to `java.util.Optional`. 

There are a lot of libraries that handle serialization of the Java 8 classes (Optional, Instant, etc) so I'll 
deprecate `java.util.Optional` factory and keep the original Guava factory and keep this library more Guava-centric.

#### Why?
Re-writing serializers is tedious!

### How?

A few ways. 

For a specific class..

```
Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer()).create();
```

or, if the mood grabs you.. (but really don't do this)
```
Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new ImmutableListDeserializer()).create();
```

or, the easiest way
```
public Gson getGson() {
  return ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder())
    .registerTypeAdapterFactory(OptionalTypeFactory.forJDK()) // you probably dont need this
    .create();
}
```

### Optionals

#### JDK  (deprecated)
```
Gson gson = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeFactory.forJDK()).create();
```

#### Guava
```
Gson gson = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeFactory.forGuava()).create();
```


### What?

##### Currently supported

* ImmutableList
* ImmutableSet
* ImmutableSortedSet
* ImmutableBiMap
* ImmutableMap
* ImmutableBiMap
* ImmutableSortedMap 
* Optionals (java.util and guava)

#### Future support (maybe)

* ImmutableList/SetMultimap
* Whatever anyone asks me to do

### Where? 

Github packages registry!
`https://maven.pkg.github.com/acebaggins/guava-gson-serializers`

### Who? 

Built by me for the wonderful Guava and Gson libraries. 
