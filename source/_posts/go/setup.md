---
title: Go Env
description: set up go env
date: 2021-10-09 09:54:09
tags:
- go
categories:
- go
---
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
