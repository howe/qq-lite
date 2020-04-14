package org.nutz.qq.bean.graph.resp;

/**
 * Created by Jianghao on 2020/4/14
 *
 * @author Freshplay Inc.
 */
public class MeResp {

    /**
     * 应用的appID
     */
    private String client_id;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    /**
     * QQ用户的唯一帐号标识，对当前开发者帐号唯一
     */
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * QQ用户的唯一帐号标识，对当前开发者帐号唯一
     */
    private String unionid;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}