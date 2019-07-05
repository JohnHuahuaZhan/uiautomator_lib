package com.example.uiautomator.common.dbTool;

import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.util.HttpRequestUtil;

import java.util.HashMap;
import java.util.Map;

public class CommonDbTool {

    public static String MYSQL_NAME = "root";    //test_tool服务器mysql的userName
    public static String MYSQL_PSW = "123456";   //test_tool测试服务器mysql的password
    public static String MYSQL_URL = "jdbc:mysql://192.168.16.2:3306/interface_test_data?useSSL=false&serverTimezone=Asia/Shanghai";    //test_tool测试服务器mysql的url
    public static String TIME_OUT = "6000";      //连接超时为6s
    public static String ORACLE_DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";    //oralce jar包名
    public static String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";     //mysql jar包名


    //test_tool测试服务器mysql的url
    /**
     * @description
     * @param userName   数据库名称
     * @param password   数据库密码
     * @param url   数据库连接地址
     * @param driverClassName   数据库jar包名
     * @param sql    sql语句
     * @param timeOut   超时时间
     * @param isUpdate 是否操作数据库  true 为操作数据库, false 为查询
     * @return   Json字符串
     */
    public static String getDateBaseResponse(String userName, String password, String url, String driverClassName, String sql, String timeOut, Boolean isUpdate){
        try {
            Map pramsMaps = new HashMap();
            pramsMaps.put("username",userName);
            pramsMaps.put("password",password);
            pramsMaps.put("url",url);
            pramsMaps.put("driverClassName",driverClassName);
            pramsMaps.put("sql",sql);
            pramsMaps.put("timeout",timeOut);
            pramsMaps.put("change", isUpdate.toString());
            MyRequest myRequest = HttpRequestUtil.buildPostRequest("http", "192.168.16.2", "/tool/common/commonSql", "8080", "utf-8", pramsMaps);
            byte[] response = HttpRequestUtil.request(myRequest);
            return new String(response);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage(), throwable);
        }

    }








}
