---
title: Go Env
description: set up go env
date: 2021-10-09 09:54:09
tags:
- go
categories:
- go
---

# Installation
[official website](https://golang.google.cn/dl/)
1. download mac-darwin-***.tar.gz
2. unpack the `compress dir` to the directory whereever you create
like `$HOME/xxx/xxx`
3. add `ENV Config` in `.bash_proifle` or `.zshrc` 
    - set the `GOROOT` to the dir you unpack  
        `export GOROOT=$HOME/xxx/xxx`
    - set go terminal command  
        `export PATH=${PATH}:${GOROOT}/bin`
    - set `GOPATH` where you develop  
        `export GOPATH=/Users/viber/go`
    - set `proxy`   
        `export GO111MODULE=on` 
        `export GOPROXY=https://goproxy.cn`

there is a simple explain
```sh
wget https://go.dev/dl/go1.19.1.linux-amd64.tar.gz

directory for go related things: ~/programming/go
directory for go compiler/tools: ~/programming/go/go-1.4
directory for go software      : ~/programming/go/packages
GOROOT, GOPATH, PATH are set as following:
export GOROOT=/home/user/programming/go/go-1.4
export GOPATH=/home/user/programming/go/packages
export PATH=$PATH:$GOROOT/bin:$GOPATH/bin
```



# GOROOT && GOPATH && GOMODULE

## GOROOT
I think it is the source code directory.  
where compiler and standard libraries exist.  
it is always the installation directory.

## GOPATH
`Workspace directory` where go code belongs.  
if no GOPATH set, it will default to a subdirectory  
named `go` in the user's home direcory -> `~/go`  

## GOMODULE
`Module` is a collection `package`  
`go mod init foo/bar`

```go.mod go.sum```



## Relation
[stackoverflow](https://stackoverflow.com/questions/7970390/what-should-be-the-values-of-gopath-and-goroot)