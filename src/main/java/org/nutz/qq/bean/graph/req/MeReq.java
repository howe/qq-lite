package org.nutz.qq.bean.graph.req;

/**
 * Created by Jianghao on 2020/4/13
 *
 * @author Freshplay Inc.
 */
public class MeReq {

    /**
     * 在Step1中获取到的access token。
     */
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * UnionId
     */
    private int unionid;

    public int getUnionid() {
        return unionid;
    }

    public void setUnionid(int unionid) {
        this.unionid = unionid;
    }
}
