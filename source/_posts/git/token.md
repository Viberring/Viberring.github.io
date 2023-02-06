---
title: Github Token
description: the usage of github token
date: 2023-02-06 21:35:00
tags:
- github
categories:
- github
---

# Token 

## github token

github change their auth checking by `token`.

1. first generate token in github

2. token usage

- with new repo
    
    `git push https://<tokenhere>@github.com/<user>/<repo>.git`

- with old repo

    `git remote set-url origin https://<token>@github.com/<user>/<repo>.git`

3. done








### LINK TO REFERENCES & RESOURCES
- [stackoverflow](https://stackoverflow.com/questions/68775869/message-support-for-password-authentication-was-removed-please-use-a-personal#:~:text=From%202021%2D08%2D13%2C,a%20PAT%20on%20your%20system.)
