package com.gyl.visit.core.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;

public class TokenUtil {

    //密钥
    private static String PUBLIC_KEY = "agkllkjajflkgsajkjkldsafhsdklfjlksadhfojksaddflkklahfdfsshk";
    //token 过期时间(30天）
    public static final long TOKEN_MAX_TIME = 2592000000L;
    private static String exp = "exp";

    public static JSONObject buildPayload(String cid) {
        JSONObject payload = new JSONObject();
        payload.put("exp", System.currentTimeMillis() + TOKEN_MAX_TIME);
        payload.put("cid", cid);
        return payload;
    }

    public static String createToken(String cid) throws JOSEException {
        JSONObject payloadJO = buildPayload(cid);
        String token = createToken(payloadJO);
        return token;
    }

    public static String createToken(JSONObject payloadJO) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(payloadJO);
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(PUBLIC_KEY.getBytes());
        //签名
        jwsObject.sign(jwsSigner);
        //生成token
        return jwsObject.serialize();

    }

    /**
     * @param token
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    public static TokenValidResult valid(String token) throws ParseException, JOSEException {
        //     解析token
        JWSObject jwsObject = JWSObject.parse(token);
        //获取到载荷
        Payload payload = jwsObject.getPayload();
        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(PUBLIC_KEY.getBytes());
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            JSONObject jsonObject = payload.toJSONObject();
            //判断token是否过期

            if (jsonObject.containsKey(exp)) {
                Long expTime = Long.valueOf(jsonObject.get(exp).toString());
                Long nowTime = System.currentTimeMillis();
                //判断是否过期
                if (nowTime > expTime) {
                    //token已经过期
                    return new TokenValidResult(-2);
                } else {
                    //校验成功
                    TokenValidResult result = new TokenValidResult(1);
                    result.setData(jsonObject);
                }
            }
        } else {
            // token无效
            return new TokenValidResult(-1);
        }
        return new TokenValidResult(-3, "Token is invalid");
    }

}

