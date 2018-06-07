package dao;

import entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Li Guangwei
 * @Descriptions: TODO
 * @Date: 2018/6/6 8:09
 * @Version: 1.0
 */
public interface SuccessKilledDao {

    /**
    *@description:插入购买明细，可过滤重复
    *@date: 2018/6/6 8:09
    *@param: [seckillId, userPhone]
    *@return: int
    */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
    *@description: 根据id查询successkilled并携带秒杀产品对象实体
    *@date: 2018/6/6 8:11
    *@param: [seckilled]
    *@return: entity.SuccessKilled
    */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
