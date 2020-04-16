package org.nutz.qq.bean.graph.req;

/**
 * Created by Jianghao on 2020/4/14
 *
 * @author Freshplay Inc.
 */
public class GetUserInfoReq {

    /**
     * 可通过使用Authorization_Code获取Access_Token 或来获取。
     * access_token有3个月有效期。
     */
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * 	申请QQ登录成功后，分配给应用的appid
     */
    private String oauth_consumer_key;

    public String getOauth_consumer_key() {
        return oauth_consumer_key;
    }

    public void setOauth_consumer_key(String oauth_consumer_key) {
        this.oauth_consumer_key = oauth_consumer_key;
    }

    /**
     * 用户的ID，与QQ号码一一对应。
     * 可通过调用https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN 来获取。
     */
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public GetUserInfoReq(String access_token, String oauth_consumer_key, String openid) {
        this.access_token = access_token;
        this.oauth_consumer_key = oauth_consumer_key;
        this.openid = openid;
    }

    public GetUserInfoReq() {
    }
}
