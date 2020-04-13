package org.nutz.qq.bean;

import org.nutz.lang.Lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jianghao on 2018/3/5
 *
 * @howechiang
 */
public class Error {

    /**
     * 返回码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 说明
     */
    private String expl;

    public String getExpl() {
        return expl;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", expl='" + expl + '\'' +
                '}';
    }

    public Error(int code, String expl) {
        this.code = code;
        this.expl = expl;
    }

    public Error() {
    }

    public static List<Error> list = new ArrayList<>(Arrays.asList(
            new Error(100000, "缺少参数response_type或response_type非法。"),
            new Error(100001, "缺少参数client_id。"),
            new Error(100002, "缺少参数client_secret。"),
            new Error(100003, "http head中缺少Authorization。"),
            new Error(100004, "缺少参数grant_type或grant_type非法。"),
            new Error(100005, "缺少参数code。"),
            new Error(100006, "缺少refresh token。"),
            new Error(100007, "缺少access token。"),
            new Error(100008, "该appid不存在。"),
            new Error(100009, "client_secret（即appkey）非法。"),
            new Error(100010, "回调地址不合法，常见原因请见：回调地址常见问题及修改方法"),
            new Error(100011, "APP不处于上线状态。"),
            new Error(100012, "HTTP请求非post方式。"),
            new Error(100013, "access token非法。"),
            new Error(100014, "access token过期。 token过期时间为3个月。如果存储的access token过期，请重新走登录流程，根据使用Authorization_Code获取Access_Token或使用Implicit_Grant方式获取Access_Token获取新的access token值。"),
            new Error(100015, "access token废除。 token被回收，或者被用户删除。请重新走登录流程，根据使用Authorization_Code获取Access_Token或使用Implicit_Grant方式获取Access_Token获取新的access token值。"),
            new Error(100016, "access token验证失败。"),
            new Error(100017, "获取appid失败。"),
            new Error(100018, "获取code值失败。"),
            new Error(100019, "用code换取access token值失败。"),
            new Error(100020, "code被重复使用。code是一次性有效的，开发者此时应该引导用户重新登录授权，重新获取code，而不是重试token接口"),
            new Error(100021, "获取access token值失败。"),
            new Error(100022, "获取refresh token值失败。"),
            new Error(100023, "获取app具有的权限列表失败。"),
            new Error(100024, "获取某OpenID对某appid的权限列表失败。"),
            new Error(100025, "获取全量api信息、全量分组信息。"),
            new Error(100026, "设置用户对某app授权api列表失败。"),
            new Error(100027, "设置用户对某app授权时间失败。"),
            new Error(100028, "缺少参数which。"),
            new Error(100029, "错误的http请求。"),
            new Error(100030, "用户没有对该api进行授权，或用户在腾讯侧删除了该api的权限。请用户重新走登录、授权流程，对该api进行授权。"),
            new Error(100031, "第三方应用没有对该api操作的权限。请发送邮件进行OpenAPI权限申请。"),
            new Error(100032, "过载，一开始未细分时可以用。"),
            new Error(100033, "缺少UIN参数。"),
            new Error(100034, "缺少skey参数。"),
            new Error(100035, "用户未登陆。"),
            new Error(100036, "RefreshToken失效。"),
            new Error(100037, "RefreshToken已过期"),
            new Error(100038, "RefreshToken已废除"),
            new Error(100039, "RefreshToken到达调用上限。"),
            new Error(100040, "RefreshToken的AppKey非法。"),
            new Error(100041, "RefreshToken,AppID非法。"),
            new Error(100042, "RefreshToken非法。"),
            new Error(100043, "APP处于暂停状态。"),
            new Error(100044, "错误的sign，Md5校验失败，请求签名与官网填写的签名不一致。"),
            new Error(100045, "用户改密token失效。"),
            new Error(100046, "g_tk校验失败。"),
            new Error(100048, "没有设置companyID。"),
            new Error(100049, "APPID没有权限(get_unionid)。"),
            new Error(100050, "OPENID解密失败，一般是openid和appid不匹配。"),
            new Error(100051, "调试模式无权限。"),
            new Error(110401, "请求的应用不存在。"),
            new Error(110404, "请求参数缺少appid。"),
            new Error(110405, "登录请求被限制，请稍后在登录。"),
            new Error(110406, "应用没有通过审核。"),
            new Error(110500, "获取用户授权信息失败。"),
            new Error(110501, "获取应用的授权信息失败"),
            new Error(110502, "设置用户授权失败"),
            new Error(110503, "获取token失败"),
            new Error(110504, "系统内部错误"),
            new Error(110505, "参数错误"),
            new Error(110506, "获取APP info信息失败"),
            new Error(110507, "校验APP info 签名信息失败"),
            new Error(110508, "获取code失败"),
            new Error(110509, "SKEY校验失败"),
            new Error(110510, "Disable"),
            new Error(100001, "网站url格式不正确（网站url长度不能超过256，网站url只能有二级目录）"),
            new Error(100003, "服务器连接超时。"),
            new Error(103001, "网站的mete信息和QQ提供的验证信息不一致。"),
            new Error(103002, "网站不能使用IP地址和端口号。")
    ));

    public static Error getError(int code) {
        for (Error error : list) {
            if (Lang.equals(code, error.getCode())) {
                return error;
            }
        }
        return new Error(-99, "未知");
    }
}
