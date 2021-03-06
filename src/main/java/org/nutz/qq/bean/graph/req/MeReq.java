package org.nutz.qq.bean.graph.req;

/**
 * Created by Jianghao on 2020/4/13
 *
 * @author Freshplay Inc.
 */
public class MeReq {

    /**
     * 用户凭证
     */
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * 是否申请unionID
     */
    private int unionid = 1;

    public int getUnionid() {
        return unionid;
    }

    public void setUnionid(int unionid) {
        this.unionid = unionid;
    }

    public MeReq(String access_token, int unionid) {
        this.access_token = access_token;
        this.unionid = unionid;
    }

    public MeReq() {
    }
}
