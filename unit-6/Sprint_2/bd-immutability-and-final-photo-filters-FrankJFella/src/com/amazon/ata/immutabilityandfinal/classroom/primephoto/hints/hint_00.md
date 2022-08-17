In this lesson we've learned that an immutable class is an effective tool when writing multi threaded code. If your code
has to share a resource, shutting down the ability to mutate the shared resource will prevent threads from affecting 
each other. When dealing with an immutable object, you do not need to be concerned about the order in which threads read 
and write to the shared object.

Which resource is being shared across the threads? Try making it immutable. 
