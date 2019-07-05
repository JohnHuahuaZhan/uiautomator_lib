package com.example.uiautomator.common.dbTool.gotone_core_base_ch;

import com.example.uiautomator.common.dbTool.CommonDbTool;

public class GtcSmsOutHisDb extends CommonDbTool {

    //短信历史记录
    public static String getCellSmsHisDb(String cell){
        String sql = "SELECT * FROM (SELECT * FROM GTC_SMS_OUT_HIS WHERE RECEIVER = '"+ cell +"' order by GMT_CREATE DESC) WHERE ROWNUM = 1";
        String response = getDateBaseResponse("gotone_core_base_ch","gotone_core_base_ch","jdbc:oracle:thin:@//10.0.0.91/dev",ORACLE_DRIVER_CLASS_NAME,sql,"6000",false);
        return response;
    }


}
