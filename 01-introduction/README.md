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
   Resources.getResourceAsStream("mybatis-config.xml");
   ```

   以后凡是遇到resources这个单词，大部分情况下，这种加载资源的方式就是从类的根路径下开始加载（开始查找）

   优点：采用这种方式，从类路径当中加载资源，项目的移植性很强，项目从windows移植到linux，代码不需要修改，因为这个资源文件一直都在类路径当中。

3. ```java
   InputStream is  = new FileInputStream("d:\\mybatis-config.xml");
   ```

   采用这种方式也可以。

   缺点：可移植性太差，程序不够兼容，可能会移植到其他的操作系统当中，导致以上路径无效，还需要修改Java代码中的路径，这样违背了OCP原则。

4. 已经验证了：

   MyBatis核心配置文件的名字，不一定是：mybatis-config.xml。可以是其他名字。

   MyBatis核心配置文件存放的路径，也不一定是在类的根路径下。可以放到其他地方，但为了项目的移植性，健壮性，最好将这个配置文件放到类路径下面。

5. ```java
   InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
   ```

   ClassLoader.getSystemClassLoader() 获取系统的类加载器。

   系统类加载器都有一个方法叫做：getResourceAsStream

   它就是从类路径当中加载资源的。

   通过源代码分析发现：

   ​    Resources.getResourceAsStream("mybatis-config.xml");

   ​    底层的源代码其实就是：

   ​    InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");

6. CarMapper.xml文件的名字是固定的吗？CarMapper.xml文件时固定的吗？

   都不是固定的

   ```
   <mapper resource="com/CarMapper2.xml"/>
   ```

​	resource属性，这种方式是从类路径当中加载资源的	

​	<mapper url="file:///D:/CarMapper.xml"/>

​	url属性，这种方式是从绝对路径中加载资源的

### 6. 关于MyBatis的事务管理机制，（深度剖析）

1. 在mybatis-config.xml文件中，可以通过以下的配置进行mybatis的事务管理

   ```
   <transactionManager type="JDBC"/>
   ```

2. type属性的值包括两个

   1. JDBC(jdbc)

   2. MANAGED(managed)

      type后面的值，只有以上两个值可以选，不区分大小写。

3. 在MyBatis中提供了两种事务管理机制：

   1. 第一种：JDBC事务管理器
   2. 第二种：MANAGED事务管理器

4. JDBC事务管理器：

   mybatis框架自己管理事务，自己采用原生的JDBC代码去管理事务:

   ```java
   conn.setAutoCommt(false);// 开启事务
   // 业务处理代码......
   conn.commit();// 手动提交事务
   ```

   使用JDBC事务管理器的话，底层创建的事务管理器对象：JdbcTransaction对象。

   如果你编写的代码是下面的代码：

   ​    SqlSession sqlSession = sqlSessionFactory.openSession(true);

   ​    表示没有开启事务，因为这种方式压根不会执行：conn.setAutoCommit(false);

   ​    在JDBC事务中，没有执行conn.AutoCommit(false);那么autoCommit就是true

   ​    如果autoCommit是true，就表示没有开启事务，只要执行任意一条DML语句就提交一次。 

5. MANAGED事务管理器：

   MyBatis不再负责事务的管理了，事务管理交给其他容器来负责，例如：Spring。

   我不管事务了，你来负责吧。

   对于我们当前的单纯的只有MyBatis的情况下，如果配置为MANAGED那么事务这块是没人管的，没有人管理事务表示事务压根没有开启。

   没有人管理事务就是没有事务。

6. JDBC中的事务

   如果你没有在JDBC代码中执行：conn.setAutoCommit(false);的话，默认的autoCommit是true。

7. 重点：

   以后注意了，只要你的autoCommit是true，就表示没有开启事务。

   只有你的autoCommit是false的时候，才表示开启了事务