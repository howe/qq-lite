package org.nutz.qq.api.graph;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.qq.bean.Comm;
import org.nutz.qq.bean.Error;
import org.nutz.qq.bean.graph.req.AuthorizeReq;
import org.nutz.qq.bean.graph.req.TokenReq;
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
                return GRAPH_GATE + Comm.GRAPH_OAUTH2_AUTHORIZE + "?response_type=" + req.getResponse_type()
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
}
