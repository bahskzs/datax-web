# Datax-web 基础入门讲解

整理人： bahskzs

官方仓库: https://github.com/WeiYe-Jing/datax-web
fork仓库: https://github.com/bahskzs/datax-web

### 1.前置环境

- python2.7 (建议2.7 可以用3,但是需要替换datax的对应python文件)
- jdk8
- mysql 8/5.7


### 2.部署

#### 2.1 部署目录：

```
# 部署目录如下
/opt/module
	|---------  datax
	|---------  datax-web-2.1.2
```

#### 2.2 操作

```
# 解压datax datax
tar -zxvf datax-web-2.1.2.tar.gz
tar -zxvf datax.tar.gz

# 部署
./bin/install.sh

# 修改数据库配置
vi /opt/module/datax-web-2.1.2/modules/datax-admin/conf/bootstrap.properties
DB_HOST=localhost
DB_PORT=3306
DB_USERNAME=root
DB_PASSWORD=123456
DB_DATABASE=datax_web


# 启动
./bin/start-all.sh
```

