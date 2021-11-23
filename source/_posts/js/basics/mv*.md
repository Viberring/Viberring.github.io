---
title: MV* Pattern
description: About MV* Pattern in Js
date: 2021-10-09 09:54:09
tags:
- js
categories:
- js
---
# MV* 
We are talking about `mvc` `mvvm` `mvp` design pattern
The Main Common is that all of these `pattern` do have
`Model` and `View` and they are seperated by `*` (whatever it is).

## Model
entities which need to be processed.
## View
what you see and interactive with.
## MVC
C => Controller
view -> controller is m2m.
controller control the `views` to display.
like we can request a url and we decide which view 
to return the the browser.

## MVVM
VM => ViewModel
this is two-way bidning 
when you update view then viewModel will automatically be updated
and when you update viewModel then view will automatically be updated.
## MVP
P => Presenter
view -> presenter is o2o.
the `view` communicate with the `presenter` directly
and meantime the `presenter` update the `view` directly




### Ref
[XX](https://medium.com/developers-tomorrow/javascript-interview-question-is-react-an-mvc-or-mvvm-ac2ea2a5127d)
[Well Explained](https://web.archive.org/web/20150219153055/http://joel.inpointform.net/software-development/mvvm-vs-mvp-vs-mvc-the-differences-explained/)