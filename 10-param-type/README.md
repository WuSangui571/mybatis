### 输入参数类型之——简单类型

比如：Integer、String、Character、Date等都是简单类型

paremeterType属性是什么？

告诉MyBatis框架，我的这个方法的参数类型是什么类型

MyBatis框架自身有类型自动推断机制，所以大部分情况下（输入参数是简单类型），paremeterType属性都是可以省略不写的。

底层SQL语句最终是类似这样的

select * from t_student where id = ?
而JDBC代码是一定要给?传值的
怎么传值？ps.setXxxx(第几个问号，传什么值);
    ps.setLong(1,1L)
    ps.setString(1,"zhangsan")
    ps.setDate(1,new Date(...))
    ps.setInt(1,100)
中的其中一种

MyBatis底层到底调用哪个setXxx方法，取决于paremeterType属性的值

注意：MyBatis框架实际上内置了很多别名，可以参考开发手册

### 输入参数类型之——Map集合

其中的`parameterType="map"`可以省略

```
<insert id="insertStudentByMap" parameterType="map">
    INSERT INTO t_student
    VALUES (NULL,#{name},#{age},#{height},#{birth},#{sex})
</insert>
```

### 输入参数类型之——pojo实体

其中的`parameterType="com.sangui.pojo.Student"`可以省略

```
<insert id="insertStudentByPojo" parameterType="Student">
    INSERT INTO t_student
    VALUES (NULL,#{name},#{age},#{height},#{birth},#{sex})
</insert>
```

### 输入参数类型之——多个参数

比如你的需求是：

```
List<Student> selectByNameAndSex(String name,Character sex);
```

那么解决办法是（要修改#{}里面的内容）

```
<select id="selectByNameAndSex">
    SELECT *
    FROM t_student
    <!--注意低版本MyBatis中使用的是#{0}和#{1}-->
    WHERE name = #{arg0} and sex = #{arg1}
</select>
```

或者

```
<select id="selectByNameAndSex">
    SELECT *
    FROM t_student
    WHERE name = #{param1} and sex = #{param2}
</select>
```

混着用也行

```
<select id="selectByNameAndSex">
    SELECT *
    FROM t_student
    WHERE name = #{arg0} and sex = #{param2}
</select>
```

新的方式是，可以使用@Param注解，来重命名

```
// 下面两个都可以，其中value可以省略不写
// List<Student> selectByNameAndSex(@Param(value = "name") String name, cCharacter sex);
List<Student> selectByNameAndSex(@Param("name") String name, @Param("sex")Character sex);
```

```
<select id="selectByNameAndSex">
    SELECT *
    FROM t_student
    WHERE name = #{name} and sex = #{sex}
</select>
```

这样xml文件里就可以使用原来的名字了（注意此时仍可以在xml中使用param1和param2作为参数名字，但arg0和arg1则不行了）

### @Param注解源码分析

还是以刚才那个多参数的程序为例子

```java
@Test
public void testSelectByNameAndSex(){
    SqlSession sqlSession = SqlSessionUtil.openSession();
    StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
    String name = "张三";
    Character sex = '男';
    List<Student> students = mapper.selectByNameAndSex(name,sex);
    for (Student student : students) {
        System.out.println(student);
    }
}
```

其中的

```
List<Student> students = mapper.selectByNameAndSex(name,sex);
```

这个函数，通过断点，进入这个方法，分析得出，它内部执行的其实是这个方法：

```
Object invoke(Object proxy, Method method, Object[] args)
```

+ Object proxy
  + 代理对象
+ Method method
  + 目标方法
+ Object[] args
  + 目标方法的参数
  + 在这个例子中这个对象的值为：Object[] args = {"张三",’男‘};

然后，继续往里边走，底层有个成员变量SortedMap<Integer, String> names

+ 里面有put了(0,"name"),(1,"sex")

然后，他会根据方法上是否有注解，走不同的程序，若我们有注解，他会new一个Map集合

Map<String, Object> param = new ParamMap<>()

然后执行（其中的entry就是param里的一对key+value）

```
param.put(entry.getValue(),args[entry.getKey()]);// 就是put了("name",arg[0])和("sex",arg[1])，
```

若我们的@Param(value = "name")里value不等于param1, 则会把("param1",arg[0])放进去，同理后面的@Param("sex")放("param2",arg[1])

最后返回这个param（也就是说这个param里面有四个值）

### 查询结果返回一个大Map

比如，要返回的结果数据类型如下：Map<String,Map<String, Object>>，String是这个大Map的key，Map<String, Object>是这个大Map的value，这个value是个小Map,key是一个个属性名，value是一个个对应的属性值

怎么办？

在抽象方法中添加MapKey注解，表示将查询的的结果Map中的id设置为大Map的value。

```
@MapKey("id")
Map<String,Map<String, Object>> selectAllReturnBigMaps();
```

而测试程序正常写就好，

```
@Test
public void testSelectAllReturnBigMaps() {
    CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
    Map<Long,Map<String, Object>> maps = mapper.selectAllReturnBigMaps();
    // {1={id=1, produce_time=2020-10-11, brand=宝马520Li}, 2={id=2, produce_time=2020-11-11, brand=奔驰E300L}, 4={id=4, produce_time=2000-10-11, brand=丰田霸道}}
    System.out.println(maps);
}
```

