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
密码：获取邮箱smtp授权码，具体操作如下
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


补充：2018-08-14 14:02:27

朋友在测试时发现的一些问题，网易163邮箱的发件时间跟收件时间是一致的。也可能是网易根本没有管你什么时候发邮件，邮件服务器什么时候接受到邮件时间就是什么时候，所以163邮箱的邮件时间不能更改。QQ邮箱可以更改，其他的问题暂未测试。

发现QQ邮箱写邮件的时候图片上传只是先上传到一个缓存的图片服务器，直接转html格式的图片链接会有一个有效期限过了这个有效期限图片就会自动失效。这里建议用一些不会过期的网络图片的url。否则图片过一段时间将无法查看。


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

## 终极攻略：

发现之前更改时间的效果并不是那么满意然后想要用终极级解决方案

技术方面思考，怎么说，因为请求拦截可以分为两种，

一种是在浏览器获得数据前，提前对数据进行更改然后转发给浏览器。有点类似代理的效果，所有的流量都走代理，拦截指定的页面，修改，返回转发修改后的数据。

另外一种就是要在浏览器渲染前，这种的话如果浏览器功能不够强劲的话可能要自己写一个浏览器了。发现谷歌浏览器的ADblock插件能拦截广告，看来能在ADblock上面找找解决方案。不过ADblock提供的自己写的过滤规则，只能隐藏掉某些规则底下的数据。而我们要做的是更改。所以放弃了。

不过通过ADblock强大的功能不难发现，谷歌其实允许插件获取返回的数据进行修改展示。

然后就找到了FindR这个神器，可以替换页面上面任何文本。


### 使用方式

首先下载使用Chrome浏览器，下载地址：https://www.google.cn/chrome/

然后在浏览器输入：https://chrome.google.com/webstore/category/extensions

（这边要说明在chrome商店下载插件是要翻墙滴，翻墙请下载蓝灯：https://raw.githubusercontent.com/getlantern/lantern-binaries/master/lantern-installer.exe  
，下载完蓝灯之后打开蓝灯，可以看到在右下角有个蓝灯标志![web](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/lantern.png?raw=true)，然后就可以在商店里搜刮资源了）

在搜索框里输入 findR

![search](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/search.png?raw=true)按回车

搜索结果找到 findR

![add](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/addfindR.png?raw=true) 添加至chrome

可以看到浏览器右上角有个 findR图标 

![add](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/FindRTab.png?raw=true) 

点击图标 点设置按钮

![add](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/FindROperate.png?raw=true) 

进入配置页

![Setting](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/FindRSetting.png?raw=true) 

如上，我想要添加一条规则把任何页面的酷酷酷改成小柯柯真棒。点SAVE可以保存这条规则，选中规则 点DELETE可以删除选中的规则。

### 实战：

我打算吧邮箱中的8月17号替换掉

![mailPage](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/MailPage.png?raw=true) 

添加如下规则

![regular](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/FindRRegular.png?raw=true) 

刷新页面后结果

![regular](https://github.com/guoxiaolongonly/MailSender/blob/master/imgs/FindRResult.png?raw=true) 

可以看到所有的8月17日都替换成了柯柯最厉害。

大概就是这样。。

### 这边有以下几个问题值得注意以下

1.这个修改QQ邮箱暂时不支持。因为QQ邮箱是页面内部跳转。不能及时获取到邮箱页面的更改。

2.这个修改有一个弊端就是所有的匹配项都会更改，如果你想要把同一天的邮件替换成不同日期，那建议每天发一条邮件。

3.后面的更改会覆盖之前的更改 比如你先设置了 2018年08月17日 14:47 (星期五) 更改为 嘻嘻。   后面添加   8月17日 改为 柯柯真厉害 ，这样的话会生效后面一条，也就是说

2018年08月17日 14:47 (星期五) 更改的结果会变成  2018年0柯柯真厉害 14:47 。 所以建议邮件内容的时间更改放到列表的时间更改的后面。













