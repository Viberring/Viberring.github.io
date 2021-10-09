---
title: select poll epoll
description: select poll epoll
date: 2021-09-30 23:23:34
tags:
- async
categories:
- iv
---

# select vs. poll vs. epoll

non-blocking socket I/O.

## select
- fd_set is a bit mask and therefore has some fiexed size
linux (df default to 1024)
- iterate the loop to check event O(n)

## poll
- with not size limit, cuz with array store
- O(n)

## epoll
- O(1)


## Ref
[compare](https://www.ulduzsoft.com/2014/01/select-poll-epoll-practical-difference-for-system-architects/)
[io-multiplexing](https://xuri.me/2017/08/06/io-multiplexing-in-linux.html)