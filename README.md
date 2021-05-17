# wenhu

#### 介绍

“闻乎”项目设计，技术采用Spring Cloud Alibab、Spring Boot、MybitsPlus、ElaticSearch、MySQL、Redis、Vue+Element

#### 开发文档

1. [闻乎开发环境搭建笔记](开发文档/闻乎开发环境搭建笔记.md)
2. [闻乎开发文档](开发文档/闻乎开发文档.md)
3. [闻乎数据库设计文档](开发文档/闻乎数据库设计文档.md)

#### 安装教程

1. 更据《闻乎开发环境搭建笔记》启动`nacos`，将`wenhu-back/commons/config/src/main/resources/application-common.yaml`存入nacos云配置中，适当修改其中`server.host`为自己的环境ip,`group`为`wenhu`，`file-extension`为`yaml`，`prefix`为`wenhu-config`
2. 数据库文件存放在《闻乎数据库设计文档》中，执行创建wenhu数据库，执行SQL
3. 启动wenhu-back模块，启动services下admin、creation、hot、message、people、search模块，然后启动网关模块gateway
4. 启动wenhu-front模块，使用npm install安装依赖、启动package.json，启动前台。
5. 在user数据表中添加数据可直接访问用户端，在admin数据表中添加数据访问管理端。
6. 用户端为`127.0.0.1:6999`，管理端为`127.0.0.1:8080`

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5. Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
