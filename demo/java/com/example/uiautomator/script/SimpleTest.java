package com.example.uiautomator.script;

import android.Manifest;
import android.util.Pair;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.chshop.R;
import com.example.uiautomator.po.purchase.FootballPurchasePageObject;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.rule.TestWatcherRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(AndroidJUnit4.class)//调试用
@SdkSuppress(minSdkVersion = 21)
public class SimpleTest extends CommonTest {
    public String packageName = getContext().getStringResources(R.string.packageName);
    public String mainActivityName = getContext().getStringResources(R.string.mainActivityName);
    public long pollingEvery = Long.valueOf(getContext().getStringResources(R.string.pollingEvery));
    public long find_timeout = Long.valueOf(getContext().getStringResources(R.string.match_timeout));
    @Rule
    public TestWatcherRule testWatcherRule = new TestWatcherRule(getDevice());
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);


    //以下的选项提供参考
    public static class SelectionType{
        public static final String  SPF_WIN = "SPF_WIN";
        public static final String  SPF_DRAW = "SPF_DRAW";
        public static final String  SPF_LOST = "SPF_LOST";
        public static final String  RQSPF_WIN = "RQSPF_WIN";
        public static final String  RQSPF_DRAW = "RQSPF_DRAW";
        public static final String  RQSPF_LOST = "RQSPF_LOST";
        public static final String  JQS_G0 = "JQS_G0";
        public static final String  JQS_G1 = "JQS_G1";
        public static final String  JQS_G2 = "JQS_G2";
        public static final String  JQS_G3 = "JQS_G3";
        public static final String  JQS_G4 = "JQS_G4";
        public static final String  JQS_G5 = "JQS_G5";
        public static final String  JQS_G6 = "JQS_G6";
        public static final String  JQS_G7 = "JQS_G7";
        public static final String  BF_S_1_0 = "BF_S_1_0";
        public static final String  BF_S_2_0 = "BF_S_2_0";
        public static final String  BF_S_2_1 = "BF_S_2_1";
        public static final String  BF_S_3_0 = "BF_S_3_0";
        public static final String  BF_S_3_1 = "BF_S_3_1";
        public static final String  BF_S_3_2 = "BF_S_3_2";
        public static final String  BF_S_4_0 = "BF_S_4_0";
        public static final String  BF_S_4_1 = "BF_S_4_1";
        public static final String  BF_S_4_2 = "BF_S_4_2";
        public static final String  BF_S_5_0 = "BF_S_5_0";
        public static final String  BF_S_5_1 = "BF_S_5_1";
        public static final String  BF_S_5_2 = "BF_S_5_2";
        public static final String  BF_S_W_O = "BF_S_W_O";
        public static final String  BF_S_0_0 = "BF_S_0_0";
        public static final String  BF_S_1_1 = "BF_S_1_1";
        public static final String  BF_S_2_2 = "BF_S_2_2";
        public static final String  BF_S_3_3 = "BF_S_3_3";
        public static final String  BF_S_D_O = "BF_S_D_O";
        public static final String  BF_S_0_1 = "BF_S_0_1";
        public static final String  BF_S_0_2 = "BF_S_0_2";
        public static final String  BF_S_1_2 = "BF_S_1_2";
        public static final String  BF_S_0_3 = "BF_S_0_3";
        public static final String  BF_S_1_3 = "BF_S_1_3";
        public static final String  BF_S_2_3 = "BF_S_2_3";
        public static final String  BF_S_0_4 = "BF_S_0_4";
        public static final String  BF_S_1_4 = "BF_S_1_4";
        public static final String  BF_S_2_4 = "BF_S_2_4";
        public static final String  BF_S_0_5 = "BF_S_0_5";
        public static final String  BF_S_1_5 = "BF_S_1_5";
        public static final String  BF_S_2_5 = "BF_S_2_5";
        public static final String  BF_S_L_O = "BF_S_L_O";
        public static final String  BQC_WW = "BQC_WW";
        public static final String  BQC_WD = "BQC_WD";
        public static final String  BQC_WL = "BQC_WL";
        public static final String  BQC_DW = "BQC_DW";
        public static final String  BQC_DD = "BQC_DD";
        public static final String  BQC_DL = "BQC_DL";
        public static final String  BQC_LW = "BQC_LW";
        public static final String  BQC_LD = "BQC_LD";
        public static final String  BQC_LL = "BQC_LL";
    }
    public static class MixType{
        public static final String M_1_1 = "1串1";
        public static final String M_2_1 = "2串1";
        public static final String M_3_1 = "3串1";
        public static final String M_4_1 = "4串1";
        public static final String M_5_1 = "5串1";
        public static final String M_6_1 = "6串1";
        public static final String M_7_1 = "7串1";
        public static final String M_8_1 = "8串1";
        public static final String M_3_3 = "3串3";
        public static final String M_3_4 = "3串4";
        public static final String M_4_4 = "4串1";
        public static final String M_4_5 = "4串5";
        public static final String M_4_6 = "4串6";
        public static final String M_4_11 = "4串11";
        public static final String M_5_5 = "5串5";
        public static final String M_5_6 = "5串6";
        public static final String M_5_10 = "5串10";
        public static final String M_5_16 = "5串16";
        public static final String M_5_20 = "5串20";
        public static final String M_5_26 = "5串26";
        public static final String M_6_6 = "6串6";
        public static final String M_6_7 = "6串7";
        public static final String M_6_15 = "6串15";
        public static final String M_6_20 = "6串20";
        public static final String M_6_22 = "6串22";
        public static final String M_6_35 = "6串35";
        public static final String M_6_42 = "6串42";
        public static final String M_6_50 = "6串50";
        public static final String M_6_57 = "6串57";
        public static final String M_7_7 = "7串7";
        public static final String M_7_8 = "7串8";
        public static final String M_7_21 = "7串21";
        public static final String M_7_35 = "7串35";
        public static final String M_7_120 = "7串120";
        public static final String M_8_8 = "8串8";
        public static final String M_8_9 = "8串9";
        public static final String M_8_28 = "8串28";
        public static final String M_8_56 = "8串56";
        public static final String M_8_70 = "8串70";
        public static final String M_8_247 = "8串247";

    }

    //玩法与选项映射
    private static Map<String,Pair<Integer,Integer>> map;
    static{
        map = new HashMap<>();
        map.put(SelectionType.SPF_WIN,new Pair<Integer, Integer>(0,0));
        map.put(SelectionType.SPF_DRAW,new Pair<Integer, Integer>(0,1));
        map.put(SelectionType.SPF_LOST,new Pair<Integer, Integer>(0,2));
        map.put(SelectionType.RQSPF_WIN,new Pair<Integer, Integer>(1,0));
        map.put(SelectionType.RQSPF_DRAW,new Pair<Integer, Integer>(1,1));
        map.put(SelectionType.RQSPF_LOST,new Pair<Integer, Integer>(1,2));
        map.put(SelectionType.JQS_G0,new Pair<Integer, Integer>(2,0));
        map.put(SelectionType.JQS_G1,new Pair<Integer, Integer>(2,1));
        map.put(SelectionType.JQS_G2,new Pair<Integer, Integer>(2,2));
        map.put(SelectionType.JQS_G3,new Pair<Integer, Integer>(2,3));
        map.put(SelectionType.JQS_G4,new Pair<Integer, Integer>(2,4));
        map.put(SelectionType.JQS_G5,new Pair<Integer, Integer>(2,5));
        map.put(SelectionType.JQS_G6,new Pair<Integer, Integer>(2,6));
        map.put(SelectionType.JQS_G7,new Pair<Integer, Integer>(2,7));
        map.put(SelectionType.BF_S_1_0,new Pair<Integer, Integer>(3,0));
        map.put(SelectionType.BF_S_2_0,new Pair<Integer, Integer>(3,1));
        map.put(SelectionType.BF_S_2_1,new Pair<Integer, Integer>(3,2));
        map.put(SelectionType.BF_S_3_0,new Pair<Integer, Integer>(3,3));
        map.put(SelectionType.BF_S_3_1,new Pair<Integer, Integer>(3,4));
        map.put(SelectionType.BF_S_3_2,new Pair<Integer, Integer>(3,5));
        map.put(SelectionType.BF_S_4_0,new Pair<Integer, Integer>(3,6));
        map.put(SelectionType.BF_S_4_1,new Pair<Integer, Integer>(3,7));
        map.put(SelectionType.BF_S_4_2,new Pair<Integer, Integer>(3,8));
        map.put(SelectionType.BF_S_5_0,new Pair<Integer, Integer>(3,9));
        map.put(SelectionType.BF_S_5_1,new Pair<Integer, Integer>(3,10));
        map.put(SelectionType.BF_S_5_2,new Pair<Integer, Integer>(3,11));
        map.put(SelectionType.BF_S_W_O,new Pair<Integer, Integer>(3,12));
        map.put(SelectionType.BF_S_0_0,new Pair<Integer, Integer>(3,13));
        map.put(SelectionType.BF_S_1_1,new Pair<Integer, Integer>(3,14));
        map.put(SelectionType.BF_S_2_2,new Pair<Integer, Integer>(3,15));
        map.put(SelectionType.BF_S_3_3,new Pair<Integer, Integer>(3,16));
        map.put(SelectionType.BF_S_D_O,new Pair<Integer, Integer>(3,17));
        map.put(SelectionType.BF_S_0_1,new Pair<Integer, Integer>(3,18));
        map.put(SelectionType.BF_S_0_2,new Pair<Integer, Integer>(3,19));
        map.put(SelectionType.BF_S_1_2,new Pair<Integer, Integer>(3,20));
        map.put(SelectionType.BF_S_0_3,new Pair<Integer, Integer>(3,21));
        map.put(SelectionType.BF_S_1_3,new Pair<Integer, Integer>(3,22));
        map.put(SelectionType.BF_S_2_3,new Pair<Integer, Integer>(3,23));
        map.put(SelectionType.BF_S_0_4,new Pair<Integer, Integer>(3,24));
        map.put(SelectionType.BF_S_1_4,new Pair<Integer, Integer>(3,25));
        map.put(SelectionType.BF_S_2_4,new Pair<Integer, Integer>(3,26));
        map.put(SelectionType.BF_S_0_5,new Pair<Integer, Integer>(3,27));
        map.put(SelectionType.BF_S_1_5,new Pair<Integer, Integer>(3,28));
        map.put(SelectionType.BF_S_2_5,new Pair<Integer, Integer>(3,29));
        map.put(SelectionType.BF_S_L_O,new Pair<Integer, Integer>(3,30));
        map.put(SelectionType.BQC_WW,new Pair<Integer, Integer>(4,0));
        map.put(SelectionType.BQC_WD,new Pair<Integer, Integer>(4,1));
        map.put(SelectionType.BQC_WL,new Pair<Integer, Integer>(4,2));
        map.put(SelectionType.BQC_DW,new Pair<Integer, Integer>(4,3));
        map.put(SelectionType.BQC_DD,new Pair<Integer, Integer>(4,4));
        map.put(SelectionType.BQC_DL,new Pair<Integer, Integer>(4,5));
        map.put(SelectionType.BQC_LW,new Pair<Integer, Integer>(4,6));
        map.put(SelectionType.BQC_LD,new Pair<Integer, Integer>(4,7));
        map.put(SelectionType.BQC_LL,new Pair<Integer, Integer>(4,8));
    }
    /**
     * 测试选择赛事
     */
    @Test
    public void  testChooseGame(){
        Map<String,Map<Integer, List<Integer>>>  selections = new HashMap<>();

        String g1Key = "周三001@天皇杯@千叶市原@冈山绿雉";
        Map<Integer, List<Integer>> g1Map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        g1Map.put(0, list);


        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        g1Map.put(1, list);
        selections.put(g1Key, g1Map);

        String g2Key = "周三003@天皇杯@山形山神@枥木SC";
        Map<Integer, List<Integer>> g2Map = new HashMap<>();
        list = new ArrayList<>();
        list.add(0);
        list.add(6);
        g2Map.put(2, list);
        list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(6);
        list.add(8);
        list.add(23);
        list.add(26);
        g2Map.put(3, list);
        list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(6);
        g2Map.put(4, list);
        selections.put(g2Key, g2Map);

        FootballPurchasePageObject footballPurchasePageObject = new FootballPurchasePageObject(packageName, find_timeout, pollingEvery);
        footballPurchasePageObject.setSelections(selections);
        footballPurchasePageObject.expandAllGames();
        footballPurchasePageObject.chooseGame();


    }

    /**
     * 测试赛事滚动和获取赛事信息
     */
    @Test
    public void  testScrollAndGetGameInfo(){

        FootballPurchasePageObject footballPurchasePageObject = new FootballPurchasePageObject(packageName, find_timeout, pollingEvery);
        List<FootballPurchasePageObject.GameFrameInfo> infos = footballPurchasePageObject.fullVisibleGamesAndCollect();
        UIAutomatorLogger.d(infos.toString());
        infos = footballPurchasePageObject.fullVisibleGamesAndCollect();
        UIAutomatorLogger.d(infos.toString());

    }

}
