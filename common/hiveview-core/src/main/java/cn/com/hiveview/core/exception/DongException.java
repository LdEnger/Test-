package cn.com.hiveview.core.exception;

/**
 * 项目基础异常类。
 *
 * @author wanggz
 */
public class DongException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DongException() {
        super();
    }

    public DongException(String message) {
        super(message);
    }

    public DongException(String message, Throwable cause) {
        super(message, cause);
    }

    public DongException(Throwable cause) {
        super(cause);
    }
}
