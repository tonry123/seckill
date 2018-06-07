package dao;

import entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Guangwei
 * @Descriptions: TODO
 * @Date: 2018/6/5 23:11
 * @Version: 1.0
 */
public interface SeckillDao {

    /**
    *@description: 减库存
    *@date: 2018/6/6 8:04
    *@param: [seckillId, killTime]
    *@return: int
    */
   int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

   /**
   *@description: 查询秒杀记录
   *@date: 2018/6/6 8:06
   *@param: [seckillDao]
   *@return: entity.Seckill
   */
   Seckill queryById(long seckillId);

   /**
   *@description:根据偏移量查询秒杀商品列表
   *@date: 2018/6/6 8:07
   *@param: [offset, limit]
   *@return: java.util.List<entity.Seckill>
   */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
