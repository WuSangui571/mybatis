package com.sangui.test;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sangui.mapper.CarMapper;
import com.sangui.pojo.Car;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-24
 * @Description:
 * @Version: 1.0
 */
public class CarMapperTest {
    @Test
    public void testSelectAllWithPageHelper(){
        int pageNum = 1;
        int pageSize = 3;
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        PageHelper.startPage(pageNum, pageSize);

        List<Car> cars = mapper.selectAllWithPageHelper();

        // navigatePages 代表总共有几个页码可供点击
        int navigatePages = 3;
        PageInfo<Car> pageInfo = new PageInfo<>(cars,navigatePages);
        /*
        PageInfo{
        pageNum=1,
        pageSize=3,
        size=3,
        startRow=1,
        endRow=3,
        total=11,
        pages=4,
        list=Page{count=true, pageNum=1, pageSize=3, startRow=0, endRow=3, total=11, pages=4, reasonable=false, pageSizeZero=false}
        [Car(id=1, carNum=100999, brand=宝马520Li888, guidePrice=10889, produceTime=2088-88-88, carType=新能源999),
        Car(id=2, carNum=1002, brand=奔驰E300L, guidePrice=55, produceTime=2020-11-11, carType=新能源),
        Car(id=4, carNum=1003, brand=丰田霸道, guidePrice=30, produceTime=2000-10-11, carType=燃油车)],
        prePage=0,
        nextPage=2,
        isFirstPage=true,
        isLastPage=false,
        hasPreviousPage=false,
        hasNextPage=true,
        navigatePages=10,
        navigateFirstPage=1,
        navigateLastPage=4,
        navigatepageNums=[1, 2, 3, 4]}
         */
        System.out.println(pageInfo);

        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Test
    public void testSelectAll(){
        int pageNum = 2;
        int pageSize = 10;
        int startIndex = (pageNum - 1) * pageSize;
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll(startIndex,pageSize);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null,"100","bc",22L,"2022-22-22","新能源");
        int count = mapper.insert(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByPrimaryKey(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectByPrimaryKey(1L);
        System.out.println(car);
    }
}
