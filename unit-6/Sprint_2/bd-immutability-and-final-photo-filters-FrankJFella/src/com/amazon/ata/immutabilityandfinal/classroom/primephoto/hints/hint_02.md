You've made RGB immutable now, but how are the converter classes (`SepiaConverter`, `GreyscaleConverter`, 
`InversionConverter`) using it? They were calling RGB methods to manipulate the pixels, but now they aren't being
manipulated. Do your RGB methods return a new object? Can you use that in the converters?

If your RGB methods don't return new objects, think about how String works when you call `toUpperCase`. Does it actually
mutate the String? It does return you an all uppercase string though...

This code snippet prints: "This is a hint for you."
```
    String stringy = "This is a hint for you.";
    string.toUpperCase();

    System.out.println(stringy);
```

This code snippet prints: "THIS IS A HINT FOR YOU."
```
    String stringy = "This is a hint for you.";
    stringy = string.toUpperCase();

    System.out.println(stringy);
```
