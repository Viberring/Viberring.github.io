---
title: some operation about port
description: port operations
date: 2022-05-18 14:16:52
tags:
categories:
- linux
---

# Port

```bash
sudo lsof -i -P -n | grep LISTEN
sudo netstat -tulpn | grep LISTEN
sudo ss -tulpn | grep LISTEN
sudo lsof -i:22 ## see a specific port such as 22 ##
sudo nmap -sTU -O IP-address-Here
```


## Ref
[port operation](https://www.cyberciti.biz/faq/unix-linux-check-if-port-is-in-use-command/)