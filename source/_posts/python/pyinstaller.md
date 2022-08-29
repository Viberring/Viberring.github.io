# Python Installer For Windows

## background
I need to package python program to `exe` and run on win10 and win7
and there are some problems I encountered which are not very comfortable to solve.

## PyInstaller
[official site](https://pyinstaller.org/en/stable/usage.html)

there are some options for build, usually you just need one command
`pyinstaller myscript.py` without other options.

there are three options that need pay more attentions
- -D 
which build the program to one directory
- -F
which build the program to one file
- -w, --windowed, --noconsole
do not show console window for i/o

so when you build the program you always will use these options.

## Problem
1. when you build the program into one file with `-F`, the program
will start slowly.
so you will build the program into directory wieh `-D`
2. when you use `-D` you face the problem to deliver them to end user
so you need another help
3. [Enigma Virtual Box](https://enigmaprotector.com/en/downloads.html) will help you to do the next build process

## Enigma Virtual Box
when you build the program into directory, you can then do next build.
when you build widh `-D` you will get a `dist` directory and all 
the related file are there including the `exe` file.
then you use `Enigma Virtual Box` to build.

- first you choose the target `exe` file 





## Related

[1-blog](https://blog.csdn.net/qq_25921925/article/details/103949384)

[2-blog](https://blog.csdn.net/qq_40994692/article/details/)