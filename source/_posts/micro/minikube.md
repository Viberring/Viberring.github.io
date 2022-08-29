---
title: minikube
description: minikube usage
date: 2022-05-28 13:32:25
tags:
- k8s
categories:
- k8s
---

# Minikube
[official website](https://minikube.sigs.k8s.io/docs/start/)

## installation
nothing special here, but we need replace the 
docker image by aliyun.

e.g.
> kubectl create deployment hello-minikube --image=k8s.gcr.io/echoserver:1.4

> kubectl create deployment hello-minikube --image=registry.cn-hangzhou.aliyuncs.com/google_containers/echoserver:1.4

if we do not replace this, we might not be able to pull down the image then we can not
start the app.

## Basics
- start k8s cluster
> minikube start
- k8s dashboard
> minikube dashboard