package org.nutz.qq.util;

import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.AlgorithmParameters;
import java.security.Key;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jianghao on 2018/3/6
 *
 * @howechiang
 */
public class Util {

    /**
     * 签名
     * <p>
     *
     * @param params   待签名字符串
     * @param key      加密钥
     * @param signType 加密方式
     * @return 签名
     */
    public static String getSign(NutMap params, String key, String signType, String signName) {

        try {
            if (Lang.isEmpty(params)) {
                throw new NullPointerException("待签名字符串为空");

            } else if (Strings.isBlank(key)) {
                throw new NullPointerException("加密钥为空");
            } else {
                if (Strings.equalsIgnoreCase(signType, "HMAC-SHA256")) {
                    return Lang.sha256(Util.Url.encode(Util.buildParmas(params, new String[]{signName})) + key);
                } else {
                    return Lang.md5(Util.Url.encode(Util.buildParmas(params, new String[]{signName})) + key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 校验签名
     * <p>
     *
     * @param params 待签名字符串
     * @param key    加密钥
     * @return 结果
     */
    public static Boolean checkSign(NutMap params, String key, String signType, String signName) {

        String sign = params.getString("sign");
        if (Lang.isEmpty(params) || Strings.isBlank(key)) {
            return false;
        } else {
            if (Strings.equalsIgnoreCase(signType, "HMAC-SHA256")) {
                if (Strings.equalsIgnoreCase(Lang.sha256(Util.Url.encode(Util.buildParmas(params, new String[]{signName})) + key), sign)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (Strings.equalsIgnoreCase(Lang.md5(Util.Url.encode(Util.buildParmas(params, new String[]{signName})) + key), sign)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * Map排序
     * <p>
     *
     * @param params 待排序对象
     * @param order  排序方式
     * @return 排序后的对象
     */
    public static Map<String, Object> sorting(Map<String, Object> params, String order) {

        if (Lang.isEmpty(params)) {
            return null;
        } else {
            Map<String, Object> map = new LinkedHashMap<>();
            if (Strings.equalsIgnoreCase(order, "desc")) {
                params.entrySet().stream()
                        .sorted(Map.Entry.<String, Object>comparingByKey().reversed())
                        .forEachOrdered(x -> map.put(x.getKey(), x.getValue()));
            } else {
                params.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEachOrdered(x -> map.put(x.getKey(), x.getValue()));
            }
            return map;
        }
    }

    /**
     * 构建参数
     * <p>
     *
     * @param params 待构建的map
     * @param f      过滤字段
     * @return 构建过的字符串
     */
    public static String buildParmas(Map<String, Object> params, String[] f) {

        if (Lang.isEmpty(f)) {
            return buildParmas(params);
        } else {
            Arrays.asList(f).stream().forEach(params::remove);
            return buildParmas(params);
        }
    }

    /**
     * 构建参数
     * <p>
     *
     * @param params 待构建的map
     * @return 构建过的字符串
     */
    public static String buildParmas(Map<String, Object> params) {

        if (Lang.isEmpty(params)) {
            return null;
        } else {
            params = Util.sorting(params, "asc");
            StringBuffer sb = new StringBuffer();
            params.forEach((k, v) -> {
                if (Lang.isNotEmpty(v)) {
                    sb.append(k + "=" + v + "&");
                }
            });
            return Strings.removeLast(sb.toString().replaceAll("$package", "package")
                    .replaceAll(" , ", ",").replaceAll(" ,", ",")
                    .replaceAll(", ", ","), '&');
        }
    }

    /**
     * URL转解码
     */
    public static class Url {
        /**
         * 加密
         * <p>
         *
         * @param s
         * @return
         */
        public static String encode(String s) {
            if (Strings.isBlank(s)) {
                throw new NullPointerException("s加密对象为空");
            } else {
                try {
                    return URLEncoder.encode(s, Encoding.UTF8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    throw Lang.wrapThrow(e);
                }
            }
        }

        /**
         * 解密
         * <p>
         *
         * @param s
         * @return
         */
        public static String decode(String s) {
            if (Strings.isBlank(s)) {
                throw new NullPointerException("s解密对象为空");
            } else {
                try {
                    return URLDecoder.decode(s, Encoding.UTF8);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw Lang.wrapThrow(e);
                }
            }
        }
    }

    /**
     * BASE64加解密
     */
    public static class Base64 {

        /**
         * 加密
         * <p>
         *
         * @param s
         * @return
         */
        public static String encode(String s) {
            try {
                if (Strings.isBlank(s)) {
                    throw new NullPointerException("s加密对象为空");
                } else {
                    return org.nutz.repo.Base64.encodeToString(s.getBytes(Encoding.CHARSET_UTF8), true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw Lang.wrapThrow(e);
            }
        }

        /**
         * 解密
         * <p>
         *
         * @param s
         * @return
         */
        public static String decode(String s) {
            try {
                if (Strings.isBlank(s)) {
                    throw new NullPointerException("s解密对象为空");
                } else {
                    return new String(org.nutz.repo.Base64.decode(s));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw Lang.wrapThrow(e);
            }
        }
    }

    /**
     * 解密微信小程序加密用户信息
     * <p>
     *
     * @param encryptedData 敏感数据
     * @param sessionKey    登录返回的key
     * @param iv            解密算法初始向量
     * @return
     */
    public static String decryptionData(String encryptedData, String sessionKey, String iv) {

        try {
            // 被加密的数据
            byte[] dataByte = org.nutz.repo.Base64.decode(encryptedData);
            // 加密秘钥
            byte[] keyByte = org.nutz.repo.Base64.decode(sessionKey);
            // 偏移量
            byte[] ivByte = org.nutz.repo.Base64.decode(iv);
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Key spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (!Lang.isEmpty(resultByte)) {
                return new String(resultByte, "UTF-8");
            }
            throw new Exception("解析数据失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw Lang.wrapThrow(e);
        }
    }

//    /**
//     * 微信小程序调起支付API签名方法
//     *
//     * @param appId 小程序ID
//     * @param rp    参数对象
//     * @param key   支付密码
//     * @return 结果
//     */
//    public static String getRequestPaymentPaySign(String appId, RequestPayment rp, String key) {
//        try {
//            if (Strings.isBlank(appId)) {
//                throw new NullPointerException("appId为空");
//            } else if (Strings.isBlank(key)) {
//                throw new NullPointerException("key为空");
//            } else if (Lang.isEmpty(rp)) {
//                throw new NullPointerException("rpm为空");
//            } else {
//                StringBuffer sb = new StringBuffer();
//                sb.append("appId=").append(appId);
//                sb.append("&nonceStr=").append(rp.getNonceStr());
//                sb.append("&package=prepay_id=").append(rp.get$package());
//                sb.append("&signType=").append(rp.getSignType());
//                sb.append("&timeStamp=").append(rp.getTimeStamp());
//                sb.append("&key=").append(key);
//                return Lang.md5(sb.toString()).toUpperCase();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw Lang.wrapThrow(e);
//        }
//    }

    /**
     * 比对数组
     *
     * @param obj
     * @param objs
     * @return
     */
    public static boolean equals(Object obj, Object... objs) {
        if (Lang.isEmpty(obj) || Lang.isEmpty(objs)) {
            return false;
        } else {
            for (Object o : Arrays.asList(objs)) {
                if (Lang.equals(obj, o)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isOneEmpty(Object... objs) {
        for (Object o : Arrays.asList(objs)) {
            if (Lang.isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOneBlank(String... sts) {
        if (Lang.isEmpty(sts)) {
            return true;
        } else {
            for (String s : Arrays.asList(sts)) {
                if (Strings.isBlank(s)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isAllEmpty(Object... objs) {
        if (Lang.isEmpty(objs)) {
            return true;
        } else {
            for (Object o : Arrays.asList(objs)) {
                if (Lang.isNotEmpty(o)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isAllBlank(String... sts) {
        if (Lang.isEmpty(sts)) {
            return true;
        } else {
            for (String s : Arrays.asList(sts)) {
                if (Strings.isNotBlank(s)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String jsonp2Json(String jsonp) {
        return jsonp.replaceAll("callback\\(", "").replaceAll("\\);", "");
    }

    /**
     * Str参数转Object
     *
     * @param pstr   Str参数
     *               例如化腾家经常遇到的 access_token=FCB2D79C7BD26E17972E506BE262BADA&expires_in=7776000&refresh_token=4EB482EC2AF6B3A5759478A41F081739
     * @param toType
     * @param <T>
     * @return
     */
    public static <T> T pstr2Object(Class<T> toType, String pstr) {
        if (Strings.isBlank(pstr)) {
            return null;
        } else {
            String[] ps = pstr.split("&");
            NutMap map = new NutMap();
            for (String p : ps) {
                String[] val = p.split("=");
                map.setv(val[0], val[1]);
            }
            return Lang.map2Object(map, toType);
        }
    }
}
