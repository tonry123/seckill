package dao;

import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    //注入dao实现类
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void reduceNumber() throws Exception {
        List<Seckill> list = seckillDao.queryAll(0,100);
        for(Seckill seckill : list){
            System.out.println(seckill);
        }
    }

    @Test
    public void queryAll() throws Exception {
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,date);
        System.out.println("updateCount="+updateCount);
    }

}