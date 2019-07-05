package com.example.uiautomator.common.dbTool.interface_test_data;

import android.util.Log;

import com.example.uiautomator.common.core.RegexTool;
import com.example.uiautomator.common.dbTool.CommonDbTool;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.sql.SQLException;


public class CellSectionUseDb extends CommonDbTool {

    //获取数据库最新手机号
    public static String getMaxNumber(){
        String sql = "SELECT * FROM cell_section_use WHERE sectionId = (SELECT ID FROM cell_section WHERE appCode = 'ch') ORDER BY id desc LIMIT 1";
        String response = getDateBaseResponse(MYSQL_NAME,MYSQL_PSW,MYSQL_URL,MYSQL_DRIVER_CLASS_NAME, sql,TIME_OUT,false);
        return response;
    }

    //插入注册手机号记录
    public static void updateNumber(Integer sectionId) throws SQLException {
        Long maxCell = Long.valueOf(RegexTool.getNumber(getMaxNumber())) + 1 ;
        String sql = "INSERT INTO cell_section_use (number, sectionId) VALUES ("+ maxCell +" ,"+ sectionId + ")";
        String response = getDateBaseResponse(MYSQL_NAME,MYSQL_PSW,MYSQL_URL,MYSQL_DRIVER_CLASS_NAME, sql,TIME_OUT,true);
        System.out.println("手机返回"+ response);
        Log.d("ok", "手机返回"+ response);
    }


}
