---
title: Go Mod
description: go module
date: 2022-11-21 20:00:00
tags:
- go
categories:
- go
---

# Go Module

When developing a project, what we desperately need is 
to manage our source code and packages

## module in go
### When
when you start a empty project
### Where
In the root directory of your project
### How
```sh
go mod init `your-module-name`
```

### go.mod
the initial `go.mod` file
```
module your-module-name

go 1.19
```

after you add package
```
module your-module-name

go 1.19

require rsc.io/quote v1.5.2
require (
        rsc.io/sampler v1.3.0 // indirect
)
```

### go.sum
The go.sum file contains cryptographic hashes of the module's
direct and indirect dependencies.


## help
```sh
Usage:

	go mod <command> [arguments]

The commands are:

	download    download modules to local cache
	edit        edit go.mod from tools or scripts
	graph       print module requirement graph
	init        initialize new module in current directory
	tidy        add missing and remove unused modules
	vendor      make vendored copy of dependencies
	verify      verify dependencies have expected content
	why         explain why packages or modules are needed
```


## Ref
[official](https://go.dev/doc/tutorial/create-module)