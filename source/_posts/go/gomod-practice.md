---
title: Go Mod Practice
description: develop your own go package
date: 2022-11-22 20:00:00
tags:
- go
categories:
- go
---

# How to use go mod 


## Basic
the main idea is we use `other` module in our `main` module.

1. we create two simple module with `package main` and `package other`
    - how to create `module`        
        > cd /home/workspace/master && `go mod init master`

        > cd /home/workspace/slave && `go mod init slave`
2. we create `func` that can be Exported
    - what kind of `func` can be Exported
        
        func start with `Uppercase`
        > func `E`xport() {}
3. then we add `dependency`
    - how to add `other module` to `main module`

        repalce module name with relative path
        > replce slave => ../slave

## Compile and run

### Compile
In the main module `go build` will build the executable file
then you can run `./x`

### Install
Where is the install path ? 

`go list -f '{{.Target}}'` will tell you, 
by decault it is your `GOPATH/bin`

You can set the `install path` with
`go env -w GOBIN=/path/to/your/bin`

```sh
export $PATH=$PATH:/path/to/your/bin
```

after `go install`, you can run `x` in the shell anywhere

## Ref
[official doc](https://go.dev/doc/tutorial/create-module)