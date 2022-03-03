package com.hlh.comprehensiveexercise.service;

import com.hlh.comprehensiveexercise.entity.Brand;
import com.hlh.comprehensiveexercise.mapper.BrandMapper;
import com.hlh.comprehensiveexercise.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @description: Brand业务类
 * @author: hlh
 * @date: 2022-02-28
 **/
public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return List<Brand>
     */
    public List<Brand> selectAll() {
        //1. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //2. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3. 调用方法
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    /**
     * 添加
     *
     * @param brand brand对象
     */
    public void add(Brand brand) {
        //1. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //2. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3. 调用方法
        mapper.add(brand);
        //4. 提交事务
        sqlSession.commit();
        //5. 释放资源
        sqlSession.close();
    }

    /**
     * 根据id查询
     *
     * @return Brand
     */
    public Brand selectById(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    /**
     * 修改
     *
     * @param brand brand对象
     */
    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * 删除
     * @param id 主键id
     */
    public void delete(int id){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper =sqlSession.getMapper(BrandMapper.class);
        //根据id删除商品
        mapper.delete(id);
        //事务提交
        sqlSession.commit();
        sqlSession.close();
    }

}
