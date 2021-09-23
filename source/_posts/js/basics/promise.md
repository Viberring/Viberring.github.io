# Promise
in js, we know we always use `callback` to do async things.
and now we have `promise` and `async/await`.

## Macro vs Micro

## Why

## How
1. `promise` promise you two result `resolve` and `reject`.
2. `promise` have three state `pending && fullfiled && reject`
3. `promise` have a default value `undefined` and the `value`
updated by `resolve(x) || reject(err)`
4. state change is the final step in code blocks which means
when `resolve` or `reject` are called the left code will no
be executed.
5. the `state` and `result` are accessed by `.then .catch .finally`

## Promise && Async/Await
i think `async/await is syntactic sugar on top of promises`
cuz when you create a `async fn` you got a `promise` in 
the console, just like `promise`.


### Why Async/Await
i think you can no longer use `then chain` anymore, instead
you can use `async && await` like normal code.

