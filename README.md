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
- Two ways to run code on a new thread
  - Implement **Runnable** interface, and pass to a new Thread object
  - Extend Thread class, and create an object of that class.
- We need to call start() to actually start the new thread and ask the OS to execute it.

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
