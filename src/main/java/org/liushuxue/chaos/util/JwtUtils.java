package org.liushuxue.chaos.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.liushuxue.chaos.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtils {
    /**
     * 签发人
     */
    @Value("${jwt.issuer}")
    private String issuer;
    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * 有效期
     */
    @Value("${jwt.expire}")
    private int expire;


    public static final String HEADER = "Authorization";
    /**
     * 生成token
     *
     */
    public  String getToken(String userJson)  {
        Calendar calendar = Calendar.getInstance();
        Date issuedTime = calendar.getTime();
        // 七天有效期
        calendar.add(Calendar.MINUTE, expire);
        Date expiresTime = calendar.getTime();

        Algorithm algorithm = getAlgorithm();

        try {
            return JWT.create()
                    .withIssuer(issuer)         // 设置发行人
                    .withSubject(userJson)
                    .withIssuedAt(issuedTime)   // 设置签发时间
                    .withExpiresAt(expiresTime) // 设置过期时间
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            return null;
        }
    }


    /**
     * 解析token，并返回User对象
     */
    public  DecodedJWT  parseToken(String token) throws BaseException {

        try {
            Algorithm algorithm = getAlgorithm();

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);

        }catch (JWTDecodeException de) {
            throw new BaseException(ResultStatusEnum.UNAUTHORIZED, "密钥格式不正确");

        }catch (SignatureVerificationException de) {
            throw new BaseException(ResultStatusEnum.UNAUTHORIZED,"密钥签证不正确");

        }catch (TokenExpiredException tee) {
            throw new BaseException(ResultStatusEnum.UNAUTHORIZED,"密钥已过期");

        }catch (InvalidClaimException ice) {
            throw new BaseException(ResultStatusEnum.UNAUTHORIZED,"非法密钥");

        }catch (JWTVerificationException jwte) {
            throw new BaseException(ResultStatusEnum.UNAUTHORIZED,"密钥解析错误");
        }

    }

  public String getUserFromToken(String token) throws BaseException {
        DecodedJWT jwt = parseToken(token);
        return jwt.getSubject();
  }
    /**
     * 获取自定义密钥
     */
    private  Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secretKey);
    }
}