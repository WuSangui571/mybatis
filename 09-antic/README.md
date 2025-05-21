mybatis小技巧

1. #{} 和 ${} 的区别

   + #{}

     + ```
       2025-05-21 09:43:26.691 [main] DEBUG com.sangui.mapper.CarMapper.selectByCarType - ==>  Preparing: SELECT id, car_num as carNum, brand, guide_price as guidePrice, produce_time as produceTime, car_type as carType FROM t_car WHERE car_type = ?
       2025-05-21 09:43:26.731 [main] DEBUG com.sangui.mapper.CarMapper.selectByCarType - ==> Parameters: 新能源(String)
       2025-05-21 09:43:26.784 [main] DEBUG com.sangui.mapper.CarMapper.selectByCarType - <==      Total: 2
       ```

   + ${}

     + ```
       2025-05-21 09:49:25.485 [main] DEBUG com.sangui.mapper.CarMapper.selectByCarType - ==>  Preparing: SELECT id, car_num as carNum, brand, guide_price as guidePrice, produce_time as produceTime, car_type as carType FROM t_car WHERE car_type = 新能源
       2025-05-21 09:49:25.521 [main] DEBUG com.sangui.mapper.CarMapper.selectByCarType - ==> Parameters: 
       ```

   + 区别

     #{}：底层使用PreparedStatement，先进行SQL语句的编译，然后给SQL语句的？占位符传值（避免SQL注入风险）

     ${}：底层使用Statement，先进行SQL语句的拼接，然后再对SQL语句编译。（存在SQL注入风险）

     优先使用#{}

   + 何时使用 ${} ？

     在需要拼接SQL语句的时候使用，如

     ```xml
     <select id="selectGuidePriceByAscOrDesc" resultType="com.sangui.pojo.Car">
         SELECT
             id,car_num as carNum,brand,guide_price as guidePrice,produce_time as produceTime,car_type as carType
         FROM
             t_car
         ORDER BY
             guide_price ${descOrEsc}
     </select>
     ```

     需要传关键字的时候用

2. 向SQL语句当中拼接表名，就需要使用${} 

   现实业务中，可能会存在分表存储数据的情况，因为一张表存的话，数据量太大，查询效率太低。

   可以将这些数据有规律的分表查询，这样在查询的时候效率就比较高，因为扫描的数据量就少了。

   日志表：专门存储日志信息的，如果t_log只有一张表，这张表中每天都会产生很多数据，慢慢的，这个表中的数据会很多

   怎么解决？

   ​	每天生成一个新表，每张表以当天日期作为名称，例如

   ​		t_log_20250521

   ​		t_log_20250522

   ​		...

​	你想知道某一天的日志信息怎么办？

​		直接查：t_log_20250522即可

3. 批量删除：一次性删除多条记录

   批量删除的SQL语句有两种方式

   第一种or： delete from t_car where id=1 or id=2 or id=3;（后面学动态sql再说）

   第二章in：delete from t_car where id in (1,2,3);（这里使用` delete from t_car where id in (${ids})`，必须是${}，不能是#{}）

4. 模糊查询

   根据汽车品牌进行模糊查询

   ```
   select * from t_car where brand like '%奔驰%';
   select * from t_car where brand like '%比亚迪%';
   ```

   + 第一种方案（用#{}，在java程序里拼接）

     ```
     String brand= "比亚迪";
     List<Car> cars = mapper.selectByBrand("%" + brandLike + "%");
     ```

     ```
     brand like #{brand};
     ```

   + 第二四种方式（使用#{}，在sql中拼接，用的最多）

     ```
     String brand = "比亚迪";
     List<Car> cars = mapper.selectByBrandLike(brand);
     ```

     ```
     brand like "%"#{brand}"%"
     ```

   + 第三种方式（使用#{}和concat函数，这个函数是专门用于字符串拼接的）

     ```
     String brand = "比亚迪";
     List<Car> cars = mapper.selectByBrandLike(brand);
     ```

     ```
     brand like concat("%",#{brand},"%");
     ```

   + 第四种方案（用${}和concat函数，鸡肋）

     ```
     String brand = "比亚迪";
     List<Car> cars = mapper.selectByBrandLike(brand);
     ```

     ```
     brand like concat("%","${brand}","%")
     ```

   5. 别名机制

      ```
      <!--起别名-->
      <typeAliases>
          <typeAlias type="com.sangui.pojo.Car"/>
      	<typeAlias type="com.sangui.pojo.Log"/>
      </typeAliases>
      ```

      ```
      <!--起别名-->
      <typeAliases>
          <package name="com.sangui.pojo"/>
      </typeAliases>
      ```

      type是指定给哪个类型其别名
      alias是指定别名
      别名不区分大小写
      但是namespace不能起别名，只能resultType起别名
      <typeAlias type="com.sangui.pojo.Car" alias="Car"/>
      <typeAlias type="com.sangui.pojo.Car"/>
      其中alias是可以省略的，有默认的别名，默认是这个类的简名，如com.sangui.pojo.Car的简名是Car
      更加方便的是这个方式：
      <package name="com.sangui.pojo"/>
      将这个包下的所有的类自动起别名，别名就是类简名，不区分大小写

   6. mybatis-config.xml文件中的mappers标签

      

   

   