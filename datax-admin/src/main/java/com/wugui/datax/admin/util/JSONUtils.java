package com.wugui.datax.admin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataX JSON 用户名密码解密
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName JSONUtils
 * @Version 1.0
 * @since 2019/7/31 14:54
 */
public class JSONUtils {

    /**
     * decrypt 解密
     */
    public static Integer decrypt = 0;
    /**
     * decrypt 加密
     */
    public static Integer encrypt = 1;

    /**
     * @param content
     * @param key
     * @param changeType 0加密 or 1解密
     * @return
     */
    public static JSONObject change(String content, String key, Integer changeType) {
        JSONObject keyObj = JSONObject.parseObject(JSONObject.parseObject(content).getString(key));
        JSONObject params = JSONObject.parseObject(keyObj.getString("parameter"));
        String dUsername = null, dPassword = null;
        if (decrypt.equals(changeType)) { //解密
            dUsername = AESUtil.decrypt(params.getString("username"));
            dPassword = AESUtil.decrypt(params.getString("password"));

        } else if (encrypt.equals(changeType)) {//加密

            dUsername = AESUtil.encrypt(params.getString("username"));
            dPassword = AESUtil.encrypt(params.getString("password"));
        }
        String username = dUsername == null ? params.getString("username") : dUsername;
        String password = dPassword == null ? params.getString("password") : dPassword;
        params.put("username", username);
        params.put("password", password);
        keyObj.put("parameter", params);
        return keyObj;
    }


    /**
     * @param jsonStr
     * @param changeType 0加密 or 1解密
     * @return jsonStr
     */
    public static String changeJson(String jsonStr, Integer changeType) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        JSONObject job = json.getJSONObject("job");
        JSONArray contents = job.getJSONArray("content");
        for (int i = 0; i < contents.size(); i++) {
            String contentStr = contents.getString(i);
            Object obj = contents.get(i);
            if (decrypt.equals(changeType)) { //解密
                ((JSONObject) obj).put("reader", change(contentStr, "reader", decrypt));
                ((JSONObject) obj).put("writer", change(contentStr, "writer", decrypt));
            } else if (encrypt.equals(changeType)) {//加密
                ((JSONObject) obj).put("reader", change(contentStr, "reader", encrypt));
                ((JSONObject) obj).put("writer", change(contentStr, "writer", encrypt));
            }
        }
        job.put("content", contents);
        json.put("job", job);
        return json.toJSONString();
    }
    /**
     * @param jsonStr
     * @param type source target
     * @return jsonStr
     */
    public static String changeJsonPwd(String jsonStr, String type, String passwd) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        JSONObject job = json.getJSONObject("job");
        JSONArray contents = job.getJSONArray("content");
        for (int i = 0; i < contents.size(); i++) {
            String contentStr = contents.getString(i);
            Object obj = contents.get(i);
            if (StringUtils.equals("source",type)) { //解密
                ((JSONObject) obj).put("reader", changePwd(contentStr, "reader", passwd));

            } else {//加密
                ((JSONObject) obj).put("writer", changePwd(contentStr, "writer", passwd));
            }
        }
        job.put("content", contents);
        json.put("job", job);
        return json.toJSONString();
    }

    /**
     * @param content
     * @param key
     * @return
     */
    public static JSONObject changePwd(String content, String key, String pwd) {
        JSONObject keyObj = JSONObject.parseObject(JSONObject.parseObject(content).getString(key));
        JSONObject params = JSONObject.parseObject(keyObj.getString("parameter"));
        String dUsername = null, dPassword = null;
        //替换密码
        dPassword = pwd;
        String password = dPassword == null ? params.getString("password") : dPassword;
        params.put("password", password);
        keyObj.put("parameter", params);
        return keyObj;
    }


    //TODO.. 增加方法 传入数据源
     /**
      * @author: bahsk
      * @date: 2021-10-14 16:49
      * @description: 批量更换数据源
      * @params:
      * @return:
      */
    public static String changeJsonDSs(String jsonStr, List<Map<String,String>> dsList) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        JSONObject job = json.getJSONObject("job");
        JSONArray contents = job.getJSONArray("content");

        //替换reader和writer的数据源
        String contentStr = contents.getString(0);
        Object obj = contents.get(0);
        ((JSONObject) obj).put("reader", changeDS(contentStr, "reader", (HashMap<String, String>) dsList.get(0)));
        ((JSONObject) obj).put("writer", changeDS(contentStr, "writer", (HashMap<String, String>) dsList.get(1)));

        job.put("content", contents);
        json.put("job", job);
        return json.toJSONString();
    }


     /**
      * @author: bahsk
      * @date: 2021-10-14 15:44
      * @description: 替换数据源
      * @params:
      * @return:
      */
    public static JSONObject changeDS(String content, String key, Map<String,String> dbParams) {
        JSONObject keyObj = JSONObject.parseObject(JSONObject.parseObject(content).getString(key));
        JSONObject params = JSONObject.parseObject(keyObj.getString("parameter"));

        String dUsername = dbParams.get("jdbcUsername");
        String dPassword = dbParams.get("jdbcPassword");
        String dJdbcUrl = dbParams.get("jdbcUrl");

        JSONArray connection = params.getJSONArray("connection");
        JSONObject object = (JSONObject) connection.get(0);

        String username = dUsername == null ? params.getString("username") : dUsername;
        String password = dPassword == null ? params.getString("password") : dPassword;
        String jdbcUrl = dJdbcUrl == null ? object.getString("jdbcUrl") : dJdbcUrl;

        String[] arr = new String[1];
        arr[0] = jdbcUrl;

        if("reader".equals(key)) {
            ((JSONObject) connection.get(0)).put("jdbcUrl",arr);
        }else {
            ((JSONObject) connection.get(0)).put("jdbcUrl",jdbcUrl);
        }


        params.put("username", username);
        params.put("password", password);
        params.put("connection", connection);
        keyObj.put("parameter", params);
        return keyObj;
    }

     /**
      * @author: bahsk
      * @date: 2021-11-02 11:39
      * @description: TODO 获取指定reader/writer的username,password,jdbcUrl
      * @params: String source,String jsonStr
      * @return: Map<String,String>
      */
     public static Map<String,String> getDBParameter(String source, String jsonStr) {
         Map<String,String> params = new HashMap<>();

         //调用getParams 获取json串中"parameter"
         JSONObject parameters = JSONUtils.getParams(source,jsonStr);

         String dUsername = parameters.getString("username");
         String dPassword = parameters.getString("password");

         JSONArray connection = parameters.getJSONArray("connection");
         JSONObject object = (JSONObject) connection.get(0);
         String jdbcUrl = "";
         if(StringUtils.equals(source,"reader")) {
             JSONArray dJdbcUrl = object.getJSONArray("jdbcUrl");
             jdbcUrl = (String)dJdbcUrl.get(0);
         } else {
             jdbcUrl = (String) object.get("jdbcUrl");
         }


         params.put("username", dUsername);
         params.put("jdbcUrl", jdbcUrl);

         return params;
     }

      /**
       * @author: bahsk
       * @date: 2021-11-02 11:52
       * @description: TODO 替换密码,只换密码即可
       * @params: String jsonStr,String passwd
       * @return:
       */
//     public String changePassword(String jsonStr,String source, String passwd) {
//         JSONObject parameters = JSONUtils.getParams(source,jsonStr);
//         parameters.put("password",passwd);
//         //替换密码
//         String json = JSONUtils.setParams(parameters.toJSONString(), source, jsonStr);
//         return json;
//     }

      /**
       * @author: bahsk
       * @date: 2021-11-02 15:13
       * @description: 获取params
       * @params: String source,String jsonStr
       * @return: JSONObject parameters
       */
     public static JSONObject getParams(String source,String jsonStr) {

         //解构传入的json串
         JSONObject json = JSONObject.parseObject(jsonStr);
         JSONObject job = json.getJSONObject("job");

         // 1.解析content
         JSONArray contents = job.getJSONArray("content");

         // 2.根据传入的source进行读取reader或者writer底下的配置项
         String contentStr = contents.getString(0);
         JSONObject keyObj = JSONObject.parseObject(JSONObject.parseObject(contentStr).getString(source));

         JSONObject parameters = JSONObject.parseObject(keyObj.getString("parameter"));
         return parameters;
     }

     /**
      * @author: bahsk
      * @date: 2021-11-02 15:14
      * @description: TODO 设置Parameter 要注意这个parameter 应该是完整的parameter而不是只有三个参数的param
      * @params: String parameters,String source,String jsonStr
      * @return:
      */
    public static String modifyPassword(Map<String,String> params,String source,String jsonStr) {

        //解构传入的json串
        JSONObject json = JSONObject.parseObject(jsonStr);
        JSONObject job = json.getJSONObject("job");

        // 1.解析content
        JSONArray contents = job.getJSONArray("content");

        // 2.根据传入的source进行读取reader或者writer底下的配置项
        String contentStr = contents.getString(0);
        JSONObject keyObj = JSONObject.parseObject(JSONObject.parseObject(contentStr).getString(source));
        JSONObject parameters = JSONObject.parseObject(keyObj.getString("parameter"));


        parameters.put("password",params.get("password"));

        keyObj.put("parameter", parameters);

        JSONObject contentObj = JSONObject.parseObject(contentStr);
        contentObj.put(source, keyObj);
        contents.set(0,contentObj);

        job.put("content", contents);
        json.put("job",job);
        jsonStr = json.toJSONString();

        return jsonStr;
    }

}
