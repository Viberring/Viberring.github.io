---
title: mysql-with-docker-problem
description: mysql-with-docker-problem
date: 2021-04-26 14:16:52
tags:
categories:
- linux
---

# Mysql With Docker

### Virtual Machi Static Ip Setting

#### Host IP
![host ip](/images/linux/mac-net.jpg)

#### look dns
```
cat /etc/resolv.conf
```

#### Linux Static IP setting
```sh
cd /etc/netplan
vim 00-installer-config.yaml
sudo netplan apply
```
```yml
network:
    ethernets:
        ens33:
            addresses: [ip/24]
            dhcp4: no
            optional: true
            gateway4: your-gateway
            nameservers:
                [dns]
    version: 2
    render: NetworkManager
```

### Problem 

when i start two mysql contaienr in docker,
I can ping the mysql server but I can not connect
to mysql server from host.
I found out that I mirror the volums from docker
to linux which lead to this problem.


### Ref
- [A](https://blog.csdn.net/qq_27520051/article/details/91478732)