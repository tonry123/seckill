package exception;

/**
 * 重复秒杀异常（运行期异常）
 * @Author: Li Guangwei
 * @Descriptions: TODO
 * @Date: 2018/6/6 12:52
 * @Version: 1.0
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String s) {
        super(s);
    }

    public RepeatKillException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
