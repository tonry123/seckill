package exception;

/**
 * @Author: Li Guangwei
 * @Descriptions: TODO
 * @Date: 2018/6/6 12:59
 * @Version: 1.0
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String s) {
        super(s);
    }

    public SeckillException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
