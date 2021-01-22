package com.angi;

import com.angi.entity.Product;
import com.angi.entity.User;
import com.angi.mapper.ProductMapper;
import com.angi.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;


    /**
     * 使用乐观锁
     */
    @Test
    public void testConcurrentUpdate(){
        Product product1 = productMapper.selectById(1L);
        System.err.println(product1+"--angi");

        Product product2 = productMapper.selectById(1L);
        System.err.println(product2+"--awei");

        product1.setPrice(product1.getPrice()+50);
        productMapper.updateById(product1);

        product2.setPrice(product2.getPrice()-30);
        int result = productMapper.updateById(product2);
        System.err.println(result);
        if (result==0){
            product2=productMapper.selectById(1L);
            product2.setPrice(product2.getPrice()-30);
            productMapper.updateById(product2);
        }

        Product product3 = productMapper.selectById(1L);
        System.err.println(product3);
    }

    /**
     * 查所有
     */
    @Test
    void testSelectList() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.err::println);
    }

    /**
     * 通过多个id批量查询
     */
    @Test
    void testSelectBatchId() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.err::println);
    }

    /**
     * 通过QueryWrapper封装查询条件
     */
    @Test
    public void testSelectByQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jone").or().likeRight("email", "test");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.err::println);
    }

    /**
     * 通过Map封装查询条件
     */
    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
//        map.put("name","Jone");
        map.put("age", 18);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.err::println);
    }

    /**
     * SelectPage分页
     */
    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 3);
        page = userMapper.selectPage(page, null);
        //分页数据集合
        page.getRecords().forEach(System.err::println);

        System.err.println(page.getCurrent());
        System.err.println(page.getPages());
        System.err.println(page.getSize());
        System.err.println(page.getTotal());
        System.err.println(page.hasNext());
        System.err.println(page.hasPrevious());
    }

    /**
     * 返回指定的列
     */
    @Test
    public void testSelectByColumn(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("name","age");
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.err::println);

    }

    /**
     * 返回指定的列的分页
     */
    @Test
    public void testSelectPageByColumn() {
        Page<Map<String, Object>> page = new Page<>(1, 3);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        Page<Map<String, Object>> mapPage = userMapper.selectMapsPage(page, queryWrapper);
        List<Map<String, Object>> records = mapPage.getRecords();
        records.forEach(System.err::println);
        System.err.println(mapPage.getCurrent());
        System.err.println(mapPage.getTotal());
        System.err.println(mapPage.getSize());
        System.err.println(mapPage.getPages());
        System.err.println(mapPage.hasPrevious());
        System.err.println(mapPage.hasNext());
    }

    /**
     * 插入一个user
     */
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setName1("angi");
        user.setAge(18);
        user.setEmail("angi@qq.com");

        int result = userMapper.insert(user);
        System.err.println("受影响的行数："+result);
        System.err.println(user.getId());
    }

    /**
     * 更新一个user
     */
    @Test
    public void testUpdateById(){
        User user=new User();
        user.setId(1352540287473369089L);
        user.setEmail("aweissb@qq.com");
        int result = userMapper.updateById(user);
        System.err.println(result);
    }


    /**
     * 更新通过updateWrapper
     */
    @Test
    public void testUpdateWrapper(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name","awei");
        updateWrapper.eq("name","liuwei");
        User user=new User(null,"hehe",18,"xxxx");
        int result = userMapper.update(user, updateWrapper);
        System.err.println(result);
    }

    /**
     * 根据id删除记录
     */
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(4L);
        System.err.println(result);
    }

    /**
     * 根据id批量删除
     */
    @Test
    public void testDeleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
        System.err.println(result);
    }

    /**
     * 根据条件删除
     */
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","angi");
        int result = userMapper.deleteByMap(map);
        System.err.println(result);
    }

}
