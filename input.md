# 构建MenuOS系统

1. 将指定文件拷贝到本地：
```shell
git clone https://github.com/mengning/linuxnet.git
```
此过程可能需要输入github账号和密码。
![](https://img2018.cnblogs.com/blog/1159589/201912/1159589-20191205203448231-329234055.png)

2. 进入目录``linuxnet``，使用``make``命令编译根文件系统：
```shell
cd linuxnet
make rootfs
```
3. 在``Makefile``文件中有一条指令用于开启menu系统（不需要自己输入）：
```shell
qmenu -kernel ../../linux-3.18.6/arch/x86/bzImage -initrd ../rootfs.img
```
![](https://img2018.cnblogs.com/blog/1159589/201912/1159589-20191205203508070-318162709.png)

# 测试QEMU

1. 在QEMU中完成TCP客户端和服务器发送和接收hello/hi以验证MenuOS正常工作：
```shell
MenuOS>> replyhi
# Please input hello...
MenuOS>> hello
```

![](https://img2018.cnblogs.com/blog/1159589/201912/1159589-20191205203722622-532727367.png)

* 结果显示TCP客户端和服务器正常工作，打印出了预期的信息

2. 修改Makefile内容，``rootfs``标签下启动QEMU的命令尾部添加``-s``

![](https://img2018.cnblogs.com/blog/1159589/201912/1159589-20191205203748364-1937590936.png)

3. 重新``make rootfs``，启动QEMU

![](https://img2018.cnblogs.com/blog/1159589/201912/1159589-20191205203802697-1331608278.png)

4. 启动调试，连接到server
```shell
gdb
(gdb) file ../../linux-3.18.6/vmlinux
(gdb) target remote:1234    # 连接到gdbserver
```
5. 设置断点，验证gdb对``start_kernel``和``sys_socketcall``这两个内核函数的跟踪是否可行
```shell
(gdb) break start_kernel
(gdb) break sys_socketcall
```

![](https://img2018.cnblogs.com/blog/1159589/201912/1159589-20191205203810157-489961735.png)


* 结果显示gdb可以追踪到``start_kernel``函数，断点在``init/mian.c``的501行；也可以追踪到``sys_socketcall``函数，断点在``net/socket.c``文件的2492行