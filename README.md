# Java Multithreading, Concurrency & Performance Optimization

- By using multiple threads we can execute multiple related tasks simultaneously, making the application more responsive. And executing multiple tasks in parallel may achieve higher performance.
- Multiple threads in a single process share:
  1) The heap
  2) Code
  3) The process's open files
  4) The process's metadata
- The OS maintains a dynamic priority for each thread to prioritize interactive threads and to avoid starvation for any particular thread in the system.
  - The algorithm for scheduling thread is OS specific, and is fairly complex, but the general goal is exactly that.

## Thread Creation
- Thread class - Encapsulates all thread related functionality
- Three ways to run code on a new thread
  - Implement **Runnable** interface, and pass to a new Thread object
  - Extend Thread class, and create an object of that class.
  - Implement **Callable** (requires ExecutorService)
- We need to call start() to actually start the new thread and ask the OS to execute it.

## ExecutorService Interface
- The Concurrency API abstracts thread management for us i.e. it enables complex processing involving threads without us having to manage threads directly.
- The **ExecutorService** is an interface that provides services for the creation and management of threads.
- The **Executors** utility class provides static methods that return **ExecutorService** implementation.
- A "thread pool" is a set of reusable worker threads available to execute tasks.

### Types of ExecutorService
- Single thread pool executor
  - a single thread is used; tasks are processed sequentially
- Cached thread pool executor
  - creates new threads as needed and reuses threads that have become free
  - care needed as the number of threads can become very large
- Fixed thread pool executor
  - creates a fixed number of threads which is specified at the start.
 
## Future<V> Interface
- A Future<V> is used to obtain the result from a Callable's call() method.
- A Future<V> object represents the result of an asynchronous computation. Methods are provided to check if the computation is complete (isDone()) and to retrieve the result of that computation (get()).
- The result can only be retrieved using the method V get() when the computation has completed, blocking if necessary until it is ready.

## Thread Termination - Why and When?
- Thread consume resources
  - Memory and kernel resources
  - CPU cycles and cache memory
- If a thread finished its work, but the application is still running, we want to clean up the thread's resources
- If a thread is misbehaving, we want to stop it
- By default, the application will not stop as long as at least one thread is still running.
- If the method does not respond to the interrupt signal by throwing the **InterruptedException**,  we need to check for that signal and handle it ourselves.

### Daemon Threads
- Background threads that do not prevent the application from exiting if the main thread terminates
- To prevent a thread from blocking our app from exiting, we set the thread to be a **Daemon** thread.
  - The only way to programmatically stop the application is to make the thread a daemon. 

## Thread Coordination - Why do we need it?
- Different threads run independently
- Order of execution is out of our control

### Thread Coordination - Thread.join(..)
- More control over independent threads
- Safely collect and aggregate results
- Gracefully handle runaway threads using **Thread.join(timeout)**

## Performance Optimization
- Performance in Multithreading
  - **Latency** - The time to completion of a task. Measured in time units
  - **Throughput** - The amount of tasks completed in a given period. Measured in tasks/time unit

### Latency
- Theoretical reduction of latency by N = Performance improvement by a factor of N
- N = ?
  - How many subtasks/threads to break the original task?
    - On a general purpose computer: N = number of cores
    - #threads = #cores is optiomal only if all threads are runnable and can run without interruption (no IO / blocking calls / sleep etc.)
    - The assumption is nothing else is running that consumes a lot of CPU
- Does breaking original task and aggregating results come for free?
- Can we break any task into subtasks?

### Throughput
- By serving each task on a different thread, in parallel, we can improve throughput by N
- N = #threads = #cores
- Using a Fixed Thread Pool, we maintain constant number of threads, and eliminate the need to recreate the threads
- Significant performance improvement (xN)
