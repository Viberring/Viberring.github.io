# Happends Before

## What
what is `happends-before`.
so in my words, i think it is all about the order.
when a variable is read/write concurrently, the compiler
will not gurantee the correct result.
cuz the compiler will optimize the initialize of variable.

in c code `int a(b(), c())` , the `b()` and `c()` will not 
execute from left to right.
so to the variable `int a = 1; int b = 2`, when you read the 
`a` and `b`, there are chances b has initialized but a does not.
I think it some kind of the same.

## Why
cuz we need the correct result, we must know the `happends-before`
`serialize access` with `lock` and `channel(in go)`.

## How to in Go

### Happends Before
Within a single goroutine, the happens-before order 
is the order expressed by the program.
A read r of a variable v is allowed to observe a 
write w to v if both of the following hold:
    1. r does not happen before w.
    2. There is no other write w' to v that happens after w but before r.

To guarantee that a read r of a variable v observes a 
particular write w to v, ensure that w is 
the only write r is allowed to observe. That is, 
r is guaranteed to observe w if both of the following hold:
    1. w happens before r.
    2. Any other write to the shared variable v either happens before w or after r.

### Synchronization

#### Initialization
1. If a package p imports package q, the completion 
of q's init functions happens before the start of any of p's.
#### Goroutine creation
2. The go statement that starts a new goroutine 
happens before the goroutine's execution begins.
#### Goroutine destruction
3. The exit of a goroutine is not guaranteed to happen before any event in the program.
#### Channel communication
4. A send on a (buffered) channel happens before the corresponding 
receive from that channel completes.
5. The closing of a channel happens before a receive 
that returns a zero value because the channel is closed.
6. A receive from an unbuffered channel happens 
before the send on that channel completes.
7. The kth receive on a channel with capacity C happens 
before the k+Cth send from that channel completes.
#### Lock
8. For any sync.Mutex or sync.RWMutex variable l and n < m, 
call n of l.Unlock() happens before call m of l.Lock() returns.
9. For any call to l.RLock on a sync.RWMutex variable l, 
there is an n such that the l.RLock happens (returns)
 after call n to l.Unlock and the matching 
 l.RUnlock happens before call n+1 to l.Lock.
#### Once
10. A single call of f() from once.Do(f) happens (returns)
 before any call of once.Do(f) returns.




## Ref
[go doc](https://golang.org/ref/mem)