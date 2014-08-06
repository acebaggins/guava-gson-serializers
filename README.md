gson-serializers
================

Expanded serialization for Gson to include Guava ImmutableCollections, ImmutableMaps, and Optionals.

**New** For the Java 8 release, the JDK Optional class is now supported.

All of the JDK based ImmutableCollections from [the Guava immutable collections page] (https://code.google.com/p/guava-libraries/wiki/ImmutableCollectionsExplained) are supported.




####Why?
Deserializing ImmutableCollections became an issue for me when I had to serialize something I hadn't intended to serialize. Rather than changing code or compromising thread-safety it may be a better idea just to let ImmutableCollections continue being ImmutableCollections. 

Being able to deserialize a List as an ImmutableList isn't something I've done but.. it's there.

Serializing/deserializing Optionals was less of a real-world scenario and more of something I did for fun.

###How?

A few ways. 


For a specific class..

```java
		final Gson gson = new GsonBuilder().registerTypeAdapter(ImmutableList.class, new ImmutableListDeserializer()).create();
```
or, if the mood grabs you..

```java
		final Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new ImmutableListDeserializer()).create();
```
TypeAdapters has convenience methods to grab all of the adapters.
```java
  Map<Type, JsonDeserializer<?>> adapters = TypeAdapters.immutableTypeMap(); //returns the immutable interfaces and their implementation
```
On the other hand,
```java
  Map<Type, JsonDeserializer<?>> adapters = TypeAdapters.immutableImplemntationMap(); //returns the jdk interfaces and their corresponding immutable collection
```

To use the Optional type factory..
```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forGuava() ).create();
```
Or, if you want to use the new JDK Optional class.. 
```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory( OptionalTypeFactory.forJDK() ).create();
```

###What?

Targeted for JDK 8. 


#####Currently supported
* ImmutableList (List)
* ImmutableSet (Set)
* ImmutableSortedSet (SortedSet)
* ImmutableMap (Map)
* ImmutableSortedMap (SortedMap) 
* [Optionals] (https://code.google.com/p/guava-libraries/wiki/UsingAndAvoidingNullExplained#Optional)
* JDK 8 Optionals

###Where? 
That's a weird question.

###Who? 
Built by me for the wonderful Guava and Gson libraries. 
