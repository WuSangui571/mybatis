### 1、关于MyBatis的缓存

缓存叫做Cache。

缓存的作用，是通过减少I/O的方式，来提高程序的执行效率

MyBatis的缓存：将select语句的查询结果放到缓存（内存）当中，下一次还是这条select语句的时候，不再查询数据库。一方面减少了IO，另一方面不再执行繁琐的查找算法，效率大大提升。

MyBatis缓存包括：

+ 一级缓存
  + 将查询到的数据存储到SqlSession中
+ 二级缓存
  + 将查询到的数据存储到SqlSessionFactory中
+ 或者集成其他第三方的缓存
  + 比如EhCache【Java语言开发的】、Memcache【C语言开发的】

缓存只针对DQL语句，也就是说缓存机制只对应SELECT语句。

### 2、一级缓存

一级缓存默认是开启的，不需要任何配置。

原理：只要使用同一个SqlSession对象执行的同一条SQL语句，就会走缓存。

什么情况下不⾛缓存？

+ 第⼀种：不同的SqlSession对象。
+ 第⼆种：查询条件变化了。 

⼀级缓存失效情况包括两种： 

+ 第⼀种：第⼀次查询和第⼆次查询之间，⼿动清空了⼀级缓存。

  ```java
   sqlSession.clearCache();
  ```

+ 第⼆种：第⼀次查询和第⼆次查询之间，执⾏了增删改操作。【这个增删改和哪张表没有关系，只要 有insert  delete  update操作，⼀级缓存就失效。】

### 3、二级缓存

⼆级缓存的范围是SqlSessionFactory。

使⽤⼆级缓存需要具备以下几个条件：

1. 通过下面设置，全局性地开启或关闭所有映射器配置⽂件中已配置的任何缓存。默认就是true，⽆需设置。

   ```xml
    <setting name="cacheEnabled" value="true">
   ```

2. 在需要使⽤⼆级缓存的SqlMapper.xml⽂件中添加配置：

   ```xml
   <cache />
   ```

3. 使⽤⼆级缓存的实体类对象必须是可序列化的，也就是必须实现 java.io.Serializable 接⼝

4. SqlSession 对象关闭或提交之后，⼀级缓存中的数据才会被写⼊到⼆级缓存当中。此时⼆级缓存才可⽤。

==⼆级缓存的失效：只要两次查询之间出现了增删改操作。⼆级缓存就会失效。【⼀级缓存也会失效】==

1. eviction：指定从缓存中移除某个对象的淘汰算法。默认采⽤LRU策略。

   a.  LRU：Least  Recently  Used。最近最少使⽤。优先淘汰在间隔时间内使⽤频率最低的对象。(其实还有⼀种淘汰算法LFU，最不常⽤。)  

   b. FIFO：First  In  First  Out。⼀种先进先出的数据缓存器。先进⼊⼆级缓存的对象最先被淘汰。 

​       c. SOFT：软引⽤。淘汰软引⽤指向的对象。具体算法和JVM的垃圾回收算法有关。 

​       d. WEAK：弱引⽤。淘汰弱引⽤指向的对象。具体算法和JVM的垃圾回收算法有关。

2. flushInterval：

   a. ⼆级缓存的刷新时间间隔。单位毫秒。如果没有设置。就代表不刷新缓存，只要内存⾜够⼤，⼀ 直会向⼆级缓存中缓存数据。除⾮执⾏了增删改。

3. readOnly：

   a.true：多条相同的sql语句执⾏之后返回的对象是共享的同⼀个。性能好。但是多线程并发可能会存在安全问题。

   b.false：多条相同的sql语句执⾏之后返回的对象是副本，调⽤了clone⽅法。性能⼀般。但安 全。

4. size：

   a. 置⼆级缓存中最多可存储的java对象数量。默认值 1024 。

### 4、MyBatis集成EhCache

集成EhCache是为了代替mybatis⾃带的⼆级缓存。⼀级缓存是⽆法替代的。

mybatis对外提供了接⼝，也可以集成第三⽅的缓存组件。⽐如EhCache、Memcache等。都可以。

EhCache是Java写的。Memcache是C语⾔写的。所以mybatis集成EhCache较为常⻅，按照以下步骤操 作，就可以完成集成： 

第⼀步：引⼊mybatis整合ehcache的依赖。

```xml
 <!--mybatis集成ehcache的组件-->
 <dependency>
 <groupId>org.mybatis.caches</groupId>
 <artifactId>mybatis-ehcache</artifactId>
 <version>1.2.2</version>
 </dependency>
 <!--ehcache需要slf4j的⽇志组件,log4j不好使-->
<dependency>
 <groupId>ch.qos.logback</groupId>
 <artifactId>logback-classic</artifactId>
 <version>1.2.11</version>
 <scope>test</scope>
 </dependency>
```

第⼆步：在类的根路径下新建echcache.xml⽂件，并提供以下配置信息。 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    <!--磁盘存储:将缓存中暂时不使用的对象,转移到硬盘,类似于Windows系统的虚拟内存-->
    <diskStore path="e:/ehcache"/>
  
    <!--defaultCache：默认的管理策略-->
    <!--eternal：设定缓存的elements是否永远不过期。如果为true，则缓存的数据始终有效，如果为false那么还要根据timeToIdleSeconds，timeToLiveSeconds判断-->
    <!--maxElementsInMemory：在内存中缓存的element的最大数目-->
    <!--overflowToDisk：如果内存中数据超过内存限制，是否要缓存到磁盘上-->
    <!--diskPersistent：是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false-->
    <!--timeToIdleSeconds：对象空闲时间(单位：秒)，指对象在多长时间没有被访问就会失效。只对eternal为false的有效。默认值0，表示一直可以访问-->
    <!--timeToLiveSeconds：对象存活时间(单位：秒)，指对象从创建到失效所需要的时间。只对eternal为false的有效。默认值0，表示一直可以访问-->
    <!--memoryStoreEvictionPolicy：缓存的3 种清空策略-->
    <!--FIFO：first in first out (先进先出)-->
    <!--LFU：Less Frequently Used (最少使用).意思是一直以来最少被使用的。缓存的元素有一个hit 属性，hit 值最小的将会被清出缓存-->
    <!--LRU：Least Recently Used(最近最少使用). (ehcache 默认值).缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存-->
    <defaultCache eternal="false" maxElementsInMemory="1000" overflowToDisk="false" diskPersistent="false"
                  timeToIdleSeconds="0" timeToLiveSeconds="600" memoryStoreEvictionPolicy="LRU"/>

</ehcache>
```

第三步：修改SqlMapper.xml文件中的<cache/>标签，添加type属性。

```xml
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

第四步：编写测试程序使用。

```
@Test
public void testSelectById2() throws Exception{
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    
    SqlSession sqlSession1 = sqlSessionFactory.openSession();
    CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
    Car car1 = mapper1.selectById(83L);
    System.out.println(car1);
    
    sqlSession1.close();
    
    SqlSession sqlSession2 = sqlSessionFactory.openSession();
    CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
    Car car2 = mapper2.selectById(83L);
    System.out.println(car2);
}
```

