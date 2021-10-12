package com.evalshell.utils;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class QiniuUtil {

    public static String accessKey = "xTtB76QCbVVS0kqEqcoxLIWtcVbsPpWuncc_2UPd";
    public static String secretKey = "4xke8WcGziKDArFkRnHVWEyqf5gwLycShMc_KL67";

//    @Value("${water.path}")
    public static String path = "/Users/fengxuan/Downloads/";

    public static void main(String[] args) {
        try {
            uploadFile(path+"out.png", "image/calendar/test.png");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void uploadFile(String filePath, String fileName) throws Exception {
        String bucket = "fengxuanmusic";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, fileName);
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
}
