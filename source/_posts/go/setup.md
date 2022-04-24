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
