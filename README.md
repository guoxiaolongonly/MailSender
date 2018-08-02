# MailSender

支持QQ、网易163等邮箱登录可修改邮件发送时间


## 设计意图

因为朋友的特殊需求，需要自己设置邮件的发送时间。

## 解决方案
也没想到smtp协议居然支持配置发送时间，看来需求是能实现的。

javax.mail包提供了一整套的邮件系统的API
我们在Message类中刚好提供了setSentDate这个方法，看来问题得以解决。


## 实现

具体实现比较简单，主要是javax.mail包封装邮件发送，加swing,awt包做成窗口界面。蛋疼的是swing和awt用的很生涩，加上最后的jar转成可执行文件也花了点时间，后面用exe4j成功转换。

实现这一块没什么好讲的，大家可以自行看代码。

如果有小兄弟关注富文本编辑这一块：实现是不可能实现的，只能依托于其他第三方html文本生成这样。

因为登录和富文本生成这一块要依托于第三方所以操作会比较麻烦一些，请看使用说明


## 使用说明

![main](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/main1.jpg?raw=true)

帐号：邮箱帐号   例:719243738@qq.com
密码：邮箱密码应该也可以,但是腾讯有一个smtp授权码，具体操作如下
![account](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/accountConfig1.jpg?raw=true)

1. 进入邮箱首页，点击-设置

![account](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/accountConfig2.jpg?raw=true)

2. 点击-账户  ，然后往下滑

![account](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/accountConifg3.jpg?raw=true)

3. 开启POP3/SMTP服务，获取密码粘贴到密码区域


收件人：多个收件人用分号分开;例：719243738@qq.com;719243738@qq.com

主题：邮件主题

发送时间：例：2018-08-01 21:17:08  请严格遵循时间格式,符号用英文字符

正文：正文需要html  例：<div>这是一个酷酷的内容</div>

大家可以根据下图操作



1.进入QQ邮箱点击写邮件

![edit](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/editConfig1.jpg?raw=true)

2.编写完邮件后点击-格式，点击- </> 符号

![edit](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/editconfig2.jpg?raw=true)

3.复制文本粘贴到文本区域




配置相关：发送主机需要区分，如果不是qq邮箱的账号，请使用相应邮箱的主机发送。如下

![setting](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/settingconfig.jpg?raw=true)

全部填写完成如下

![setting](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/complete.jpg?raw=true)

最后点击发送即可发送

最后顺便贴一下从web访问的时候修改html来达到修改邮箱时间的效果，如果能写一个html拦截器应该就能以假乱真了。

进入任意web页面，比如收件箱

![web](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/webmodifyq.jpg?raw=true)

可以看到第一封邮件的时间是昨天23：00

![web](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/webmodify3.png?raw=true)

按F12选择Elements，点击小箭头图标，选中刚才的那个时间控件

可以看到在栏上面的“昨天 23:00”

![web](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/webmodify2.jpg?raw=true)

最后面点击修改成任意你想要修改的文字即可






