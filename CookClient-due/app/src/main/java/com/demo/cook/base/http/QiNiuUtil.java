package com.demo.cook.base.http;

import android.util.Base64;
import android.util.Log;

import com.demo.cook.base.local.Storage;
import com.google.gson.Gson;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class QiNiuUtil {

    //image fail path web cloud
    public enum Prefix{
        IMAGE_HEAD("image/head/"),
        IMAGE_RECIPE_COVER("image/recipe/cover/"),
        IMAGE_RECIPE_STEP("image/recipe/step/"),
        IMAGE_RECIPE_PRODUCT("image/product/");

        private String prefix;
        Prefix(String prefix){
            this.prefix=prefix;
        }
        public String getPrefix() {
            return prefix;
        }
    }

    private static String createNetPath(Prefix prefix,String filePath){
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        return prefix.getPrefix()+UUID.randomUUID().toString()+suffix;
    }


    // reuse uploadManager一般地，need create a uploadManager object
    private static UploadManager uploadManager = new UploadManager();

    private static UploadManager getUploadManager(){
        return uploadManager;
    }

    public static void put(
            Prefix prefix, String filePath,
            UpCompletionHandler completionHandler,
            final UploadOptions options
    ){
        getUploadManager().put(
                filePath, createNetPath(prefix,filePath), Storage.getQNToken(),
                completionHandler,options
        );

    }

    public static void put(
            Prefix prefix, String filePath,
            UpCompletionHandler completionHandler
    ){
        getUploadManager().put(
                filePath, createNetPath(prefix,filePath), Storage.getQNToken(),
                completionHandler,null
        );

    }


    private static final String QiNiuUrl="http://123qgljoem2k.hn-bkt.clouddn.com/";
    public static String getNetRealPath(String path){
        return QiNiuUrl+path;
    }


    /**
     *management
     *
     * */
    private static final String ACCESS_KET ="QcI-ap9BXmJdJfJJYCNGBk6alODAk8YCeG-O0TZb";
    private static final String SECRET_KRY ="GScVtgj9j-mRpqqeMYgmStfrymHrel76nVOkzNAF";
    private static final String BUCKET="recipecook";


    public static String getToken(){
        QiNiuUtil tokenUtil = QiNiuUtil.create(QiNiuUtil.ACCESS_KET, QiNiuUtil.SECRET_KRY);
        String token = tokenUtil.uploadTokenWithDeadline(QiNiuUtil.BUCKET, "", new HashMap(){{
            put("isPrefixalScope", 1);
        }});
        Log.e("token====",token);
        return token;
    }

    /**
     * update
     * reference：<a href="https://developer.qiniu.com/kodo/manual/put-policy"></a>
     */
    private static final String[] policyFields = new String[]{
            "callbackUrl", "callbackBody", "callbackHost", "callbackBodyType", "callbackFetchKey",
            "returnUrl", "returnBody",
            "endUser", "saveKey", "insertOnly", "isPrefixalScope",
            "detectMime", "mimeLimit", "fsizeLimit", "fsizeMin",
            "persistentOps", "persistentNotifyUrl", "persistentPipeline",
            "deleteAfterDays", "fileType",
    };
    private final String accessKey;
    private final SecretKeySpec secretKey;

    private QiNiuUtil(String accessKey, SecretKeySpec secretKeySpec) {
        this.accessKey = accessKey;
        this.secretKey = secretKeySpec;
    }

    private static QiNiuUtil create(String accessKey, String secretKey) {
        if ((accessKey == null || "".equals(accessKey)) || (secretKey == null || "".equals(secretKey)) ){
            throw new IllegalArgumentException("empty key");
        }
        byte[] sk = secretKey.getBytes(Charset.forName("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
        return new QiNiuUtil(accessKey, secretKeySpec);
    }

    private String uploadTokenWithDeadline(String bucket, String key, Map<String,Object> originPolicyMap) {

        Map<String,Object> policyMap = new HashMap<>();
        policyMap.put("scope", key==null ? bucket : bucket+":"+key);
        policyMap.put("deadline", System.currentTimeMillis() / 1000 + 3600);
        if (originPolicyMap != null) {
            for (Map.Entry<String, Object> i : originPolicyMap.entrySet()) {
                if (!inStringArray(i.getKey(), new String[]{"asyncOps",})&&inStringArray(i.getKey(), policyFields)) {
                    policyMap.put(i.getKey(), i.getValue());
                }
            }
        }

        String policyJson =new Gson().toJson(policyMap);

        String encodedPutPolicy = Base64.encodeToString(
                policyJson.getBytes(Charset.forName("UTF-8")),
                Base64.URL_SAFE | Base64.NO_WRAP);

        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
        byte[] signByte=mac.doFinal(encodedPutPolicy.getBytes(Charset.forName("UTF-8")));

        String encodedSign = Base64.encodeToString(
                signByte,Base64.URL_SAFE | Base64.NO_WRAP);

        String uploadToken=this.accessKey + ":" + encodedSign+ ":" + encodedPutPolicy;
        return uploadToken;
    }

    private static boolean inStringArray(String s, String[] array) {
        for (String x : array) {
            if (x.equals(s)) {
                return true;
            }
        }
        return false;
    }


}
