使用MyBatis完成CRUD

1. 什么时CRUD

   C：Create（增）

   R：Retrieve（查、检索）

   U：Update（改）

   D：Delete（删）

2. INSERT

   ```xml
   <insert id="insertCar">
       INSERT INTO t_car(id,car_num,brand,guide_price,produce_time,car_type)
       VALUES (NULL,"1003","丰田霸道","30.0","2000-10-11","燃油车")
   </insert>
   ```

   这样写的问题是？

   值 显然是写死到配置文件当中去了

   这个在实际开发中是不存在的

   一定是前端的form表单提交过来的数据，然后传给Sql语句

   例如：JDBC是怎么写的

   ```java
   String sql = "INSERT INTO t_car(id,car_num,brand,guide_price,produce_time,car_type) VALUES (NULL,?,?,?,?,?)";
   ps.setString(1,xxx);
   ps.setString(2,yyy);
   ...
   ```

   在JDBC当中占位符采用的是?，在MyBatis当中是什么呢？

   + 和?等效的写法是：#{}
   + 在MyBatis中不能使用?占位符，必须使用#{}来代替JDBC当中的?

3. DELETE
   + 需求：根据id删除数据，将id=7的数据删除
4. update
   + 需求：根据id修改某条记录
5. select（查一个）
   + 需求：根据id去查
6. select（查所有）