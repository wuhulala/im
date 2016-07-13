#IM
>spring boot + spring security + thymeleaf

##先实现spring boot 的热部署
1. VM options修改为-javaagent:{path}\springloaded-1.2.5.RELEASE.jar -noverify
2. intellij idea 14.1.14 可以使用 ctrl + F9实现不重启服务器
3. ![图片](http://127.0.0.1/images/springboot.png)

##实现单对单聊天与实时刷新当前在线用户
1. 基于springboot实战上的websocket改变
2. ![图片](http://127.0.0.1/images/websocket.png)
