package exception;

/**
 * 秒杀关闭异常
 * @Author: Li Guangwei
 * @Descriptions: TODO
 * @Date: 2018/6/6 12:57
 * @Version: 1.0
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String s) {
        super(s);
    }

    public SeckillCloseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
