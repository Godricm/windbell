package cn.afterturn.boot.bussiness.response;

/**
 * 返回给前台的错误提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
public class ErrorResponse extends Response {

    public ErrorResponse(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public ErrorResponse() {
        super();
        this.code = 500;
        this.msg = "失败";
    }
}