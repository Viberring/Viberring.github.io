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
```sh
├── AUTHORS                 // 
├── CONTRIBUTING.md         // 
├── CONTRIBUTORS            // 
├── LICENSE                 // 
├── PATENTS                 // 
├── README.md               // 
├── SECURITY.md             // 
├── VERSION                 // 
├── api                     // 
├── bin                     // go gofmt
├── codereview.cfg          // 
├── doc                     // 
├── lib                     // 
├── misc                    // 
├── pkg                     // 
├── src                     // 
└── test                    // 
```
Everything about with `go language` like `compiler`,`tool`,`lib` etc located.

It's the SDK for go programming language.
## GOPATH
```sh
├── bin
│   ├── gophernotes
│   └── tour
├── pkg
│   ├── mod
│   └── sumdb
└── src
    └── gomacro.imports
```
It's your `Workspace directory`.

Before `go mod`, you should put your source code 
in `src` directory, so you only have one `single workspace`.

`pkg` is the third packaeg you install with `go install` is
located.

`bin` is where the executable program built by go located.

Well with `go mond`, you are free.

## GOMODULE
you can check the config
```sh
go env GO111MODULE
```

then, you can open/close the config
```sh
go env -w GO111MODULE=on
```

`Module` is a collection `package`  
`go mod init foo/bar`

```
go.mod
go.sum
```

# Installation
[official website](https://golang.google.cn/dl/)
```sh
wget https://go.dev/dl/go1.19.1.linux-amd64.tar.gz
```
## Default
1. download `os-***.tar.gz`
2. unpack `os-***.tar.gz` to `/usr/local/go`
3. add `PATH env` to your `.zshrc`
```sh
export PATH=$PATH:/usr/local/go/bin
```

now, you can check `go env`, there are some different
between different `os` and you can see that
by defalt `GOPATH` and some other config 
is located in `/your-home-dir/go`
```
GO111MODULE="on"
GOARCH="amd64"
GOBIN=""
GOCACHE="/your-home-dir/go-build"
GOENV="/your-home-dir/go/env"
GOEXE=""
GOEXPERIMENT=""
GOFLAGS=""
GOHOSTARCH="amd64"
GOHOSTOS="darwin"
GOINSECURE=""
GOMODCACHE="/your-home-dir/go/pkg/mod"
GONOPROXY=""
GONOSUMDB=""
GOOS="darwin"
GOPATH="/your-home-dir/go"
GOPRIVATE=""
GOPROXY="https://goproxy.cn"
GOROOT="/usr/local/go"
GOSUMDB="sum.golang.org"
GOTMPDIR=""
GOTOOLDIR="/usr/local/go/pkg/tool/darwin_amd64"
GOVCS=""
GOVERSION="go1.18.1"
GCCGO="gccgo"
GOAMD64="v1"
AR="ar"
CC="clang"
CXX="clang++"
CGO_ENABLED="1"
GOMOD="/dev/null"
GOWORK=""
CGO_CFLAGS="-g -O2"
CGO_CPPFLAGS=""
CGO_CXXFLAGS="-g -O2"
CGO_FFLAGS="-g -O2"
CGO_LDFLAGS="-g -O2"
PKG_CONFIG="pkg-config"
GOGCCFLAGS="-fPIC -arch x86_64 -m64 -pthread -fno-caret-diagnostics -Qunused-arguments -fmessage-length=0 -fdebug-prefix-map=/var/folders/79/81cqxn_55ps7j_fy0_gs5rr40000gn/T/go-build569736138=/tmp/go-build -gno-record-gcc-switches -fno-common"
```


## Custom
1. download `os-***.tar.gz`
2. unpack the `os-**.tar.gz` to your custom directory 
like `$HOME/xxx/xxx`
3. set `ENV` in `.bash_proifle` or `.zshrc`
    - set the `GOROOT` to the dir you unpack  
        `export GOROOT=$HOME/xxx/xxx`
    - set go terminal command  
        `export PATH=${PATH}:${GOROOT}/bin`
    - set `GOPATH` where you develop  
        `export GOPATH=$HOME/go`
    - set `proxy`   
        `export GO111MODULE=on` 
        `export GOPROXY=https://goproxy.cn`

there is a simple explain
```sh
directory for go related things: ~/programming/go
directory for go compiler/tools: ~/programming/go/go-1.4
directory for go software      : ~/programming/go/packages
GOROOT, GOPATH, PATH are set as following:
export GOROOT=/home/user/programming/go/go-1.4
export GOPATH=/home/user/programming/go/packages
export PATH=$PATH:$GOROOT/bin:$GOPATH/bin
```



## Relation
[stackoverflow](https://stackoverflow.com/questions/7970390/what-should-be-the-values-of-gopath-and-goroot)

[goroot-gopath](https://www.yasint.dev/gopath-goroot)