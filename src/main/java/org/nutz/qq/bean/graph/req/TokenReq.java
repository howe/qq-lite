package org.nutz.qq.bean.graph.req;

/**
 * Copyright 2018 Freshplay Co ltd
 *
 * @author howechiang
 */
public class TokenReq {

    /**
     * 授权类型，在本步骤中，此值为“authorization_code”。
     */
    private String grant_type = "authorization_code";

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    /**
     * 申请QQ登录成功后，分配给网站的appid。
     */
    private String client_id;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    /**
     * 申请QQ登录成功后，分配给网站的appkey。
     */
    private String client_secret;

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    /**
     * 上一步返回的authorization code。
     * 如果用户成功登录并授权，则会跳转到指定的回调地址，并在URL中带上Authorization Code。
     * 例如，回调地址为www.qq.com/my.php，则跳转到：
     * http://www.qq.com/my.php?code=520DD95263C1CFEA087******
     * 注意此code会在10分钟内过期。
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 	与上面一步中传入的redirect_uri保持一致。
     */
    private String redirect_uri;

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public TokenReq(String client_id, String client_secret, String code, String redirect_uri) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
    }

    public TokenReq() {
    }
}
