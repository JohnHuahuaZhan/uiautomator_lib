package com.example.uiautomator.service.gamePurchase.footballGame;

import android.util.Log;

import com.example.uiautomator.po.basic.BranchInfoDetailPO;
import com.example.uiautomator.po.gamePurchase.GameBoughtDetailPO;
import com.example.uiautomator.po.gamePurchase.GameFollowPO;
import com.example.uiautomator.po.gamePurchase.GameMethodPO;
import com.example.uiautomator.po.myshop.MyShopPO;
import com.example.uiautomator.po.purchase.FootballPurchasePageObject;
import com.example.uiautomator.service.CommonService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootballGameService extends CommonService {
    private MyShopPO myShopPO;
    private BranchInfoDetailPO branchInfoDetailPO;
    private GameMethodPO gameMethodPO;
    private GameFollowPO gameFollowPO;
    private GameBoughtDetailPO gameBoughtDetailPO;
    private FootballPurchasePageObject footballPurchasePageObject;
    private String shopName;
    private String gameList;
    private String setKey;
    private String methodList;
    private String times;
    private String odds;

    public FootballGameService(String shopName, String gameList, String setKey, String methodList, String times, String odds) {
        this.shopName = shopName;
        this.gameList = gameList;
        this.setKey = setKey;
        this.methodList = methodList;
        this.times = times;
        this.odds = odds;

        this.myShopPO = new MyShopPO(packageName, find_timeout, pollingEvery);
        this.branchInfoDetailPO = new BranchInfoDetailPO(packageName, find_timeout, pollingEvery);
        this.gameMethodPO = new GameMethodPO(packageName, find_timeout, pollingEvery);
        this.gameFollowPO = new GameFollowPO(packageName, find_timeout, pollingEvery);
        this.gameBoughtDetailPO = new GameBoughtDetailPO(packageName, find_timeout, pollingEvery);
        footballPurchasePageObject = new FootballPurchasePageObject(packageName, find_timeout, pollingEvery);
    }

    public void enter() {
        myShopPO.clickShopByShopName(shopName);
        systemWait(1000);
        branchInfoDetailPO.clickFootballGameButton();
        systemWait(1000);
    }

    private void chooseKey() {
        if (!setKey.equals("")) {
            for (String key : setKey.split(",")) {
                gameMethodPO.clickKeyButton(key);
            }
        }
    }

    private void chooseMethod() {
        gameMethodPO.clickMethodButton();
        systemWait(1000);
        gameMethodPO.clickMethodSelectedButton();
        for (String m : methodList.split(",")) {
            gameMethodPO.clickMethodListButton(m);
        }
    }

    private void chooseTimes() {
        gameMethodPO.inputTimes(times);
    }

    private void goOn() {
        gameMethodPO.clickGoOnButton();
    }

    private void chooseOdds() {
        if (!odds.equals("")) {
            gameFollowPO.clickOddsButton(odds);
        } else {
            gameFollowPO.clickOddsCloseButton();
        }
    }

    private void pay() {
        gameFollowPO.clickPayButton();
    }

    private void keyMethodPage() {
        chooseKey();
        systemWait(1000);
        chooseMethod();
        systemWait(1000);
        chooseTimes();
        systemWait(1000);
        goOn();
        systemWait(1000);
    }

    private void oddsPage() {
        chooseOdds();
        systemWait(1000);
        pay();
        systemWait(1000);
    }

    private List<String> getGameList(int length) {
        List<String> list = new ArrayList<>();
        String g1Key = "周五003@挪超@奥德@莫尔德";
        String g2Key = "周五008@挪超@维京@海于格松";
        String g3Key = "周五009@非洲杯@乌干达@塞内加尔";
        String g4Key = "周六013@韩职@大邱FC@庆南FC";
        list.add(g1Key);
        list.add(g2Key);
        list.add(g3Key);
        list.add(g4Key);
        for (int i = list.size(); list.size() > length; i--) {
            list.remove(i - 1);
        }
        return list;
    }

    private void setMapByItem(String itemStr, Map<Integer, List<Integer>> itemMap) {
        int number1 = 0;
        int number2 = 0;
        switch (itemStr) {
            case "胜":
                number1 = 0;
                number2 = 0;
                break;
            case "平":
                number1 = 0;
                number2 = 1;
                break;
            case "负":
                number1 = 0;
                number2 = 2;
                break;
            case "让胜":
                number1 = 1;
                number2 = 0;
                break;
            case "让平":
                number1 = 1;
                number2 = 1;
                break;
            case "让负":
                number1 = 1;
                number2 = 2;
                break;
            case "0球":
                number1 = 2;
                number2 = 0;
                break;
            case "1球":
                number1 = 2;
                number2 = 1;
                break;
            case "2球":
                number1 = 2;
                number2 = 2;
                break;
            case "3球":
                number1 = 2;
                number2 = 3;
                break;
            case "4球":
                number1 = 2;
                number2 = 4;
                break;
            case "5球":
                number1 = 2;
                number2 = 5;
                break;
            case "6球":
                number1 = 2;
                number2 = 6;
                break;
            case "7+球":
                number1 = 2;
                number2 = 7;
                break;
            case "1:0":
                number1 = 3;
                number2 = 0;
                break;
            case "2:0":
                number1 = 3;
                number2 = 1;
                break;
            case "2:1":
                number1 = 3;
                number2 = 2;
                break;
            case "3:0":
                number1 = 3;
                number2 = 3;
                break;
            case "3:1":
                number1 = 3;
                number2 = 4;
                break;
            case "3:2":
                number1 = 3;
                number2 = 5;
                break;
            case "4:0":
                number1 = 3;
                number2 = 6;
                break;
            case "4:1":
                number1 = 3;
                number2 = 7;
                break;
            case "4:2":
                number1 = 3;
                number2 = 8;
                break;
            case "5:0":
                number1 = 3;
                number2 = 9;
                break;
            case "5:1":
                number1 = 3;
                number2 = 10;
                break;
            case "5:2":
                number1 = 3;
                number2 = 11;
                break;
            case "胜其他":
                number1 = 3;
                number2 = 12;
                break;
            case "0:0":
                number1 = 3;
                number2 = 13;
                break;
            case "1:1":
                number1 = 3;
                number2 = 14;
                break;
            case "2:2":
                number1 = 3;
                number2 = 15;
                break;
            case "3:3":
                number1 = 3;
                number2 = 16;
                break;
            case "平其他":
                number1 = 3;
                number2 = 17;
                break;
            case "0:1":
                number1 = 3;
                number2 = 18;
                break;
            case "0:2":
                number1 = 3;
                number2 = 19;
                break;
            case "1:2":
                number1 = 3;
                number2 = 20;
                break;
            case "0:3":
                number1 = 3;
                number2 = 21;
                break;
            case "1:3":
                number1 = 3;
                number2 = 22;
                break;
            case "2:3":
                number1 = 3;
                number2 = 23;
                break;
            case "0:4":
                number1 = 3;
                number2 = 24;
                break;
            case "1:4":
                number1 = 3;
                number2 = 25;
                break;
            case "2:4":
                number1 = 3;
                number2 = 26;
                break;
            case "0:5":
                number1 = 3;
                number2 = 27;
                break;
            case "1:5":
                number1 = 3;
                number2 = 28;
                break;
            case "2:5":
                number1 = 3;
                number2 = 29;
                break;
            case "负其他":
                number1 = 3;
                number2 = 30;
                break;
            case "胜胜":
                number1 = 4;
                number2 = 0;
                break;
            case "胜平":
                number1 = 4;
                number2 = 1;
                break;
            case "胜负":
                number1 = 4;
                number2 = 2;
                break;
            case "平胜":
                number1 = 4;
                number2 = 3;
                break;
            case "平平":
                number1 = 4;
                number2 = 4;
                break;
            case "平负":
                number1 = 4;
                number2 = 5;
                break;
            case "负胜":
                number1 = 4;
                number2 = 6;
                break;
            case "负平":
                number1 = 4;
                number2 = 7;
                break;
            case "负负":
                number1 = 4;
                number2 = 8;
                break;
        }
        List<Integer> item = itemMap.get(number1);
        if (item == null) {
            item = new ArrayList<>();
            item.add(number2);
            itemMap.put(number1, item);
        } else {
            item.add(number2);
            itemMap.put(number1, item);
        }
    }

    private Map<String, Map<Integer, List<Integer>>> getGames() {
        Map<String, Map<Integer, List<Integer>>> selections = new HashMap<>();
        List<Map<Integer, List<Integer>>> allList = new ArrayList<>();
        Map<Integer, List<Integer>> itemMap;
        String[] arr = gameList.split("@");

        List<String> listGame = getGameList(arr.length);

        List<String> game = Arrays.asList(arr);
        for (int i = 0; i < game.size(); i++) {
            itemMap = new HashMap<>();
            if (game.get(i).contains(",")) {
                for (String type : game.get(i).split(",")) {
                    setMapByItem(type, itemMap);
                }
            } else {
                setMapByItem(game.get(i), itemMap);
            }
            allList.add(itemMap);
        }

        for (int i = 0; i < arr.length; i++) {
            selections.put(listGame.get(i), allList.get(i));
        }
        return selections;
    }

    public void chooseGame() {
        Map<String, Map<Integer, List<Integer>>> selections = getGames();
        footballPurchasePageObject.setSelections(selections);
        footballPurchasePageObject.expandAllGames();
        footballPurchasePageObject.chooseGame();
        footballPurchasePageObject.clickSureButton();
        systemWait(1500);
    }

    public void buyGame() {
        keyMethodPage();
        oddsPage();
        gameBoughtDetailPO.findTitleButton();
    }
}
