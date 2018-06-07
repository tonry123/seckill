package service;

import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;

import java.util.List;

/**
 * 站在使用者的角度设计接口
 */
public interface SeckillService {

    /**
    *@description: 查询所有秒杀记录
    *@date: 2018/6/6 12:38
    *@param: []
    *@return: java.util.List<entity.Seckill>
    */
    List<Seckill> getSeckillList();

    /**
    *@description: 查询单个秒杀记录
    *@date: 2018/6/6 12:39
    *@param: [seckillId]
    *@return: entity.Seckill
    */
    Seckill getById(long seckillId);

    /**
    *@description: 秒杀开启是输出秒杀接口地址
     * 否者输出系统时间和秒杀时间
    *@date: 2018/6/6 12:40
    *@param: [seckillId]
    *@return: void
    */
    Exposer exportSeckillUrl(long seckillId);

    /**
    *@description: 执行秒杀任务
    *@date: 2018/6/6 12:47
    *@param: [seckillId, userPhone, md5]
    *@return: void
    */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
