package com.example.uiautomator.common.core;

import com.example.uiautomator.common.dbTool.gotone_core_base_ch.GtcSmsOutHisDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTool {

    /**
     * @description
     * @param cell 查询的手机号
     * @return 手机验证码
     */
    public static String getCellVerifyCode(String cell){
        String responses = GtcSmsOutHisDb.getCellSmsHisDb(cell);
        String verifyCode = null;
        String rex = "(?<=(校验码)).*?(?=(，))";     //匹配的正则
        Pattern s = Pattern.compile(rex);
        Matcher p = s.matcher(responses);
        while(p.find()){
            verifyCode = p.group();
        }
        if(verifyCode == null){
            Assert.fail("没有找到手机验证码");
        }
        return verifyCode;
    }


    /**
     * @description 解析json
     * @param json
     * @return  手机号
     */
    public static String getNumber(String json){
        try {
            JSONArray jsonArray=new JSONObject(json).getJSONArray("data");
            String number = "";
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                number=jsonObject.getString("number");
                String id=jsonObject.getString("id");
                Integer sectionId=jsonObject.getInt("sectionId");
            }
            return number;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage(), throwable);
        }

    }





}
