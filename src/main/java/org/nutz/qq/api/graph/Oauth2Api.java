package org.nutz.qq.api.graph;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.qq.bean.Comm;
import org.nutz.qq.bean.Error;
import org.nutz.qq.bean.graph.req.AuthorizeReq;
import org.nutz.qq.bean.graph.req.GetUserInfoReq;
import org.nutz.qq.bean.graph.req.MeReq;
import org.nutz.qq.bean.graph.req.TokenReq;
import org.nutz.qq.bean.graph.resp.GetUserInfoResp;
import org.nutz.qq.bean.graph.resp.MeResp;
import org.nutz.qq.bean.graph.resp.TokenResp;
import org.nutz.qq.util.HttpUtil;
import org.nutz.qq.util.Util;

import static org.nutz.qq.bean.Comm.GRAPH_GATE;

/**
 * Copyright 2018 Freshplay Co ltd
 *
 * @author howechiang
 */
public class Oauth2Api {

    /**
     * 获取Authorization Code
     *
     * @param req
     * @return
     */
    public static String authorize(AuthorizeReq req) {
        try {
            if (Strings.isBlank(req.getClient_id())) {
                throw new NullPointerException("client_id为空");
            } else if (Strings.isBlank(req.getRedirect_uri())) {
                throw new NullPointerException("redirect_uri为空");
            } else if (Strings.isBlank(req.getState())) {
                throw new NullPointerException("state为空");
            } else {
                return Comm.GRAPH_GATE + Comm.GRAPH_OAUTH2_AUTHORIZE + "?response_type=" + req.getResponse_type()
                        + "&client_id=" + req.getClient_id()
                        + "&redirect_uri=" + req.getRedirect_uri()
                        + "&state=" + req.getState()
                        + "&scope=" + req.getScope()
                        + "&display=" + req.getDisplay();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 通过Authorization Code获取Access Token
     *
     * @param req
     * @return
     */
    public static TokenResp token(TokenReq req) {

        try {
            if (Strings.isBlank(req.getClient_id())) {
                throw new NullPointerException("client_id为空");
            } else if (Strings.isBlank(req.getClient_secret())) {
                throw new NullPointerException("client_secret为空");
            } else if (Strings.isBlank(req.getCode())) {
                throw new NullPointerException("code为空");
            } else if (Strings.isBlank(req.getRedirect_uri())) {
                throw new NullPointerException("redirect_uri为空");
            } else {
                String str = HttpUtil.get(Comm.GRAPH_GATE + Comm.GRAPH_OAUTH2_TOKEN + "?grant_type=" + req.getGrant_type()
                        + "&client_id=" + req.getClient_id() + "&code=" + req.getCode()
                        + "&client_secret=" + req.getClient_secret() + "&redirect_uri=" + req.getRedirect_uri());
                if (str.indexOf("access_token") >= 0) {
                    TokenResp resp = Util.pstr2Object(TokenResp.class, str);
                    return resp;
                } else {
                    NutMap resp = Json.fromJson(NutMap.class, Util.jsonp2Json(str));
                    throw new Exception(Error.getError(resp.getInt("errcode")).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 此接口用于获取个人信息。开发者可通过openID来获取用户的基本信息。
     * 特别需要注意的是，如果开发者拥有多个移动应用、网站应用，可通过获取用户的unionID来区分用户的唯一性，因为只要是同一QQ互联平台下的不同应用，unionID是相同的。
     * 换句话说，同一用户，对同一个QQ互联平台下的不同应用，unionID是相同的。（已支持官网自助获取：点此查看操作指引）
     *
     * @param req
     * @return
     */
    public static MeResp me(MeReq req) {

        try {
            if (Strings.isBlank(req.getAccess_token())) {
                throw new NullPointerException("access_token为空");
            } else {
                String str = HttpUtil.get(Comm.GRAPH_GATE + Comm.GRAPH_OAUTH2_ME + "?access_token=" + req.getAccess_token()
                        + "&unionid=" + req.getUnionid());
                if (str.indexOf("openid") >= 0) {
                    MeResp resp = Json.fromJson(MeResp.class, Util.jsonp2Json(str));
                    return resp;
                } else {
                    NutMap resp = Json.fromJson(NutMap.class, Util.jsonp2Json(str));
                    throw new Exception(Error.getError(resp.getInt("error")).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 获取登录用户在QQ空间的信息，包括昵称、头像、性别及黄钻信息（包括黄钻等级、是否年费黄钻等）
     *
     * @param req
     * @return
     */
    public static GetUserInfoResp getUserInfo(GetUserInfoReq req) {

        try {
            if (Strings.isBlank(req.getAccess_token())) {
                throw new NullPointerException("access_token为空");
            } else {
                String json = HttpUtil.get(Comm.GRAPH_GATE + Comm.GRAPH_USER_GET_USER_INFO + "?access_token=" + req.getAccess_token()
                        + "&oauth_consumer_key=" + req.getOauth_consumer_key()
                        + "&unionid=" + req.getOpenid());
                if (json.indexOf("nickname") >= 0) {
                    GetUserInfoResp resp = Json.fromJson(GetUserInfoResp.class, json);
                    return resp;
                } else {
                    NutMap resp = Json.fromJson(NutMap.class, json);
                    throw new Exception(new Error(-1, resp.getString("msg")).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw Lang.wrapThrow(e);
        }
    }
}
