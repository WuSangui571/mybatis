### 1. resources目录

放在这个目录当中的，一般都是资源文件，配置文件

直接放到resources目录下的资源，等同于放到了类的根路径下。

### 2. 开发步骤

1. 打包方式jar

2. 引入依赖

   + mybatis依赖
   + mysql驱动依赖

3. 编写MyBatis核心配置文件：mybatis-config.xml

   注意：

   + 这个文件名不是必须叫做mybatis-config.xml，可以用其他名字，只是大家都采用这个名字。
   + 这个文件存放的位置也不是固定的，可以随意，但一般情况下，会放到类的根路径下。

   mybatis-config.xml文件中的配置信息不理解没关系，先把连接数据库的信息修改一下即可。其他的别动

4. 编写XXXMapper.xml文件

   在这个配置文件中编写SQL语句。

   这个文件名也不是固定的，放的位置也不是固定的，我们这里给他起个名字，叫做CarMapper.xml

   我们把它暂时放到类的根路径下

5. 在mybatis-config.xml文件中指定XXXMapper.xml文件的路径。

   ```xml
   <mapper resource="CarMapper.xml"/>
   ```

   注意：resources属性自动会从类的根路径下开始查找资源。

6. 编写MyBatis程序。（使用MyBatis的类库，编写MyBatis程序，连接数据库，做增删改查就行了。）

   在MyBatis当中，负责执行SQL语句的那个对象叫做什么呢？

   SqlSession

   SqlSession是专门用来执行SQL语句的，是一个Java程序和数据库之间的会话。

   要想获取SqlSession对象，需要先获取SqlSessionFactory对象，通过SqlSessionFactory工厂来生产SqlSession对象。

   怎么获取SqlSessionFactory对象呢？

   ​    需要首先获取SqlSessionFactoryBuilder对象。

   ​    通过SqlSessionFactoryBuilder对象的build方法，来获取一个SqlSessionFactory对象。

   MyBatis核心对象包括：

   ​    SqlSessionFactoryBuilder

   ​    SqlSessionFactory
   ​    SqlSession

### 3. 从XML中构建SqlSessionFactory

通过官方的这句话，你能想到什么呢？ 

1. 在MyBatis中一定有一个很重要的对象，这个对象是：SqlSessionFactory对象。
2. SqlSessionFactory对象的创建需要XML。

XML是什么?

​	它一定是一个配置文件。

### 4. MyBatis中有两个主要的配置文件

1. 其中一个是：mybatis-config.xml，这是核心配置文件，配置连接数据库的信息等。（一个）
2. 另一个是：XXXXMapper.xml，这个文件是专门用来编写SQL语句的配置文件。（一个表一个）

​	t_user表，一般会对应一个UserMapper.xml

​	t_student表，一般会对应一个StudentMapper.xml

### 5. 关于第一个程序的细节

1. MyBatis中的SQL语句结尾的";"，可以省略

2. ```java
   Resources.getResourceAsStream()
   ```

   以后凡是遇到resources这个单词，大部分情况下，这种加载资源的方式就是从类的根路径下开始加载（开始查找）