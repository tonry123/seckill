package service.impl;

import dao.SeckillDao;
import dao.SuccessKilledDao;
import dao.cache.RedisDao;
import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import entity.SuccessKilled;
import enums.SeckillStateEnum;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Guangwei
 * @Descriptions: TODO
 * @Date: 2018/6/6 13:03
 * @Version: 1.0
 */
@Service
public class SeckillServiceImpl implements service.SeckillService{


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;
    //用于混淆MD5
    private final String salt = "sjkfaj349<jkddfasdt'a;'gla";
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        //优化：缓存优化
        //1:访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null) {
            //2:访问数据库
            seckill = seckillDao.queryById(seckillId);
            if(seckill == null){
                return new Exposer(false,seckillId);
            }else {
                redisDao.putSeckill(seckill);
            }
        }


        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();

        //系统当前时间
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime() ||
                nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMD5(seckillId); //TODO
        return new Exposer(true,md5,seckillId);
    }

    //创建MD5
    private String getMD5(long seckillId){
        String base = seckillId +"/"+ salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        try{


            //执行秒杀逻辑：减库存，记录秒杀行为
            Date nowtime = new Date();
            int updateCount = seckillDao.reduceNumber(seckillId,nowtime);
            if(updateCount <= 0){
                //没有跟新记录，秒杀结束
                throw new SeckillCloseException("seckill is closed");
            }else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
                if(insertCount <= 0){
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                }else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
                }
            }
        }
        catch (SeckillCloseException e1){
            throw e1;
        }
        catch (RepeatKillException e2 ){
            throw e2;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            //编译器异常转化为运行期异常
            throw new SeckillException("seckill inner error"+e.getMessage());
        }
    }
}
