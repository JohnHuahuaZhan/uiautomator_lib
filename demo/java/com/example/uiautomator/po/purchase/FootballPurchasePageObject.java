package com.example.uiautomator.po.purchase;
import android.graphics.Rect;

import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;
import com.uiautomator.lib.support.log.UIAutomatorLogger;

import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zch12 on 2017/10/18.
 */

public class FootballPurchasePageObject extends CommonPo {
    public FootballPurchasePageObject(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    public  BySelector gameNoTvSelector = By.res(packageName+":id/gameNoTv");
    public  BySelector leagueNameTvSelector = By.res(packageName+":id/leagueNameTv");
    public  BySelector LlLeagueNameSelector = By.res(packageName+":id/LlLeagueName");


    public  BySelector guestTeamNameTvSelector = By.res(packageName+":id/guestTeamNameTv");
    public  BySelector guestTeamTvSelector = By.res(packageName+":id/guestTeamTv");

    public  BySelector  homeTeamNameTvSelector = By.res(packageName+":id/homeTeamNameTv");
    public  BySelector homeTeamTvSelector = By.res(packageName+":id/homeTeamTv");



    public  BySelector concedeTvSelector = By.res(packageName+":id/concedeTv");
    public  BySelector stopTimeTvSelector = By.res(packageName+":id/stopTimeTv");

    public  BySelector spfLayoutSelector = By.res(packageName+":id/spfLayout");
    public  BySelector rqspfLayoutSelector = By.res(packageName+":id/rqspfLayout");
    public  BySelector jqsLayoutSelector = By.res(packageName+":id/jqsLayout");
    public  BySelector bfLayoutSelector = By.res(packageName+":id/bfLayout");
    public  BySelector bqcGvSelector = By.res(packageName+":id/bqcGv");

    public  BySelector groupTitleTvSelector = By.res(packageName+":id/groupTitleTv");
    public  BySelector groupArrowIvSelector = By.res(packageName+":id/groupArrowIv");


    public  BySelector danZiIvSelector = By.res(packageName+":id/danZiIv");

    public  BySelector roomElvSelector = By.res(packageName+":id/roomElv");

    public  BySelector layout_titleSelector = By.res(packageName+":id/layout_title");

    public  BySelector gridTbMoreSelector = By.res(packageName+":id/gridTbMore");

    public  BySelector okBtnSelector = By.res(packageName+":id/okBtn");

    public  BySelector shaixuanBtnSelector = By.res(packageName+":id/filterBtn");

    public  BySelector submitBtnSelector = By.res(packageName+":id/submitBtn");

    public void clickSureButton() {
        UiObject2 object = mustFindObject(submitBtnSelector, find_timeout, "选完比赛找不到'确定'按钮");
        object.click();
    }

    /**
     * 赛事信息类。
     */
    public static class GameFrameInfo{
        //主队名称
        public String homeTeam;
        //客队名称
        public String guestTeam;
        //赛事编号
        public String gameNo;
        //赛事类型
        public String gameType;
        //赛事截止时间
        public String gameTime;
        //rqspf盘口
        public String concede;
        //赛事唯一id，赛事编号@赛事名称@主队名称@客队名称
        public String id;

        //赛事每一个玩法每一个选项中的text文本
        public Map<Integer,List<String>> selectionsInfo;

        @Override
        public String toString() {
            return "GameFrameInfo{" +
                    "homeTeam='" + homeTeam + '\'' +
                    ", guestTeam='" + guestTeam + '\'' +
                    ", gameNo='" + gameNo + '\'' +
                    ", gameType='" + gameType + '\'' +
                    ", gameTime='" + gameTime + '\'' +
                    ", concede='" + concede + '\'' +
                    ", id='" + id + '\'' +
                    ", selectionsInfo=" + selectionsInfo +
                    '}';
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            GameFrameInfo other = (GameFrameInfo) obj;
            return other.id.equals(this.id);
        }
    }




    private Map<String,Map<Integer,List<Integer>>> selections;

    public void setSelections(Map<String,Map<Integer,List<Integer>>> selections) {
        this.selections = selections;
    }



    /**
     *  选择比赛，需要先设置setSelections(Map<String,Map<Integer,List<Integer>>> selections)
     *  key的组成是如 周三003@天皇杯@山形山神@枥木SC  即 赛事编号@赛事名称@主队名称@客队名称   ,这个key是用来唯一区别一场赛事用的
     *  内部的map的key表示一场比赛的几种不同玩法 spf是0,rqspf 是1 以此类推。
     *  List<Integer> 从左到右存贮了对于玩法的选项index，从左到右重上到下 依次加1，序号从0开始
     */
    public List<GameFrameInfo> chooseGame(){
        List<GameFrameInfo> result = new ArrayList<>();
        List<String> storedIteratedGames = new ArrayList<>();
        while (true){
            if(this.selections.isEmpty())
                break;
            List<UiObject2> games = getSelectableGames();
            Map<String,Pair<Boolean,GameFrameInfo>> pairMap = doSelection(games);
            List<String> iteratedGames = new ArrayList<>();
            for (Map.Entry<String,Pair<Boolean,GameFrameInfo>> entry : pairMap.entrySet()) {
                iteratedGames.add(entry.getKey());
            }
            if(storedIteratedGames.containsAll(iteratedGames))
                throw new UIAutomatorTestException("all game has been Iterated but just can not find one game fitted for"+this.selections);
            for (Map.Entry<String,Pair<Boolean,GameFrameInfo>> entry : pairMap.entrySet()) {
                if(entry.getValue().first)
                    result.add(entry.getValue().second);
                storedIteratedGames.add(entry.getKey());
            }
            doScroll(games);
        }
        return result;
    }

    /**
     * 展开所有赛事，以供chooseGame选择
     */
    public  void expandAllGames(){

        //先收起第一组赛事
        List<UiObject2> titles = mustFindObjects(Matchers.greaterThan(0), groupTitleTvSelector,find_timeout,"may be there is no game to choose!");
        titles.get(0).click();

        systemWait(1000);


        //title高度
        titles = mustFindObjects(Matchers.greaterThan(0), groupTitleTvSelector,find_timeout,"may be there is no game to choose!");
        int singlePixelHeight = titles.get(0).getParent().getVisibleBounds().height();
        int titleSize = titles.size();

        UIAutomatorLogger.d(titleSize + "");
        if(titleSize == 1){
            titles.get(0).click();
            return;
        }

        //记录所有已展开组的title
        List<String> expandedGroupTitles = new ArrayList<>();
        while(true){
            if(expandedGroupTitles.size() == titleSize)
                break;

            //找出排在最后的不在已展开列表中的title
            int unExpandTitleIndex = 0;
            for (int i = 0; i < titles.size(); i++) {
                if(expandedGroupTitles.contains(titles.get(i).getText())){
                    unExpandTitleIndex = i - 1;
                    break;
                }
                unExpandTitleIndex = i;
            }

            //展开赛事，并记录
            expandedGroupTitles.add(titles.get(unExpandTitleIndex).getText());
            titles.get(unExpandTitleIndex).click();

            systemWait(1000);
//向上swipe总unExpandTitleIndex长度
            if(0 != unExpandTitleIndex)
                swipe( Direction.DOWN,singlePixelHeight * unExpandTitleIndex,singlePixelHeight * titleSize );


            //更新
            titles = mustFindObjects(Matchers.greaterThan(0), groupTitleTvSelector,find_timeout,"may be there is no game to choose!");

        }
    }

    /**
     * 获取赛事。并移除显示不完整的赛事
     * @return
     */
    private  List<UiObject2> getSelectableGames(){
        UiObject2 parent = mustFindObject(roomElvSelector, find_timeout, "找不到赛事容器 roomElv");
        List<UiObject2> games = mustFindObjects(parent, Matchers.greaterThan(0),By.clazz("android.widget.FrameLayout").hasDescendant(LlLeagueNameSelector), find_timeout, "赛事容器中找不到赛事");
        UiObject2 center = games.get(games.size() / 2);
        Rect centerRect = center.getVisibleBounds();
        int  completeHeight = centerRect.height();
        List<UiObject2> incompleteGames = new ArrayList<>();

        for (UiObject2 game : games) {
            if(game.getVisibleBounds().height() < completeHeight)
                incompleteGames.add(game);
        }
        games.removeAll(incompleteGames);
        return games;
    }
    private  List<UiObject2> getSelections(UiObject2 parentGame,BySelector parent){
        UiObject2 parentObject = mustFindObject(parentGame, parent, find_timeout, "找不到选项容器"+parent);
        List<UiObject2> selections =mustFindObjects(parentObject, Matchers.greaterThan(0),By.clazz("android.widget.ToggleButton"), find_timeout, "选项容器中找不到赛事");
        return selections;
    }
    private  List<UiObject2> getExtraSelections(BySelector parent){
        UiObject2 parentObject = mustFindObject(parent, find_timeout, "找不到扩展选项容器"+parent);
        List<UiObject2> selections =mustFindObjects(parentObject, Matchers.greaterThan(0),By.clazz("android.widget.ToggleButton"), find_timeout, "扩展选项容器中找不到赛事");
        return selections;
    }
    private List<String> doClickSelections(List<UiObject2> selections,List<Integer> index){
        List<String> texts = new ArrayList<>();
        for (Integer integer : index) {
            UiObject2 selection = selections.get(integer);
            texts.add(selection.getText());
            mustChecked(selection, "投注选项不能被选中");
        }
        return texts;
    }
    private GameFrameInfo doCollectGameInfo(UiObject2 game){
        GameFrameInfo gameFrameInfo = new GameFrameInfo();
        String  homeTeamNameTvText = mustFindObject(game, homeTeamNameTvSelector, find_timeout,  "找不到 homeTeamNameTv 主队名称").getText();
        String  guestTeamNameTvText = mustFindObject(game, guestTeamNameTvSelector, find_timeout,  "找不到 guestTeamNameTv 客队名称").getText();
        String gameNoTvText = mustFindObject(game, gameNoTvSelector, find_timeout,  "找不到gameno 编号").getText();
        String leagueNameTvText = mustFindObject(game, leagueNameTvSelector, find_timeout,  "找不到leagueName 赛事名称").getText();
        String concedeTvText = mustFindObject(game, concedeTvSelector, find_timeout,  "找不到concedeTv 赛事让球盘口").getText();
        String stopTomeTvText = mustFindObject(game, stopTimeTvSelector, find_timeout,  "找不到stopTimeTv 赛事截止时间").getText();
        String id = String.format("%s@%s@%s@%s", gameNoTvText, leagueNameTvText, homeTeamNameTvText, guestTeamNameTvText);
        gameFrameInfo.gameNo = gameNoTvText;
        gameFrameInfo.gameType = leagueNameTvText;
        gameFrameInfo.homeTeam = homeTeamNameTvText;
        gameFrameInfo.guestTeam = guestTeamNameTvText;
        gameFrameInfo.concede = concedeTvText;
        gameFrameInfo.gameTime = stopTomeTvText;
        gameFrameInfo.id = id;
        return gameFrameInfo;
    }
    private List<String> doCollectSelections(List<UiObject2> selections){
        List<String> texts = new ArrayList<>();
        for (UiObject2 selection : selections) {
            texts.add(selection.getText());
        }
        return texts;
    }
    private Map<String,Pair<Boolean,GameFrameInfo>> doSelection(List<UiObject2> games) {
        Map<String, Pair<Boolean, GameFrameInfo>> gameFrameInfos = new HashMap<>();
        for (UiObject2 game : games) {
            if (selections.isEmpty())
                break;

            //收集被选赛事信息
            GameFrameInfo gameFrameInfo = doCollectGameInfo(game);
            String id = gameFrameInfo.id;
            Set<String> keySet = this.selections.keySet();
            boolean selected = false;
            for (String s : keySet) {
                if (s.equals(id)) {
                    selected = true;
                    Map<Integer,List<String>> gameSelectionsInfo = new HashMap<>();
                    Map<Integer,List<Integer>> gameSelections = this.selections.get(s);
                    Set<Integer> gameSelectionsKeySet = gameSelections.keySet();
                    if(gameSelectionsKeySet.contains(0)){
                        List<UiObject2> spfToggleButtons = getSelections(game, spfLayoutSelector);
                        gameSelectionsInfo.put(0,doClickSelections(spfToggleButtons,gameSelections.get(0)));
                    }
                    if(gameSelectionsKeySet.contains(1)){
                        List<UiObject2> rqspfToggleButtons = getSelections(game, rqspfLayoutSelector);
                        gameSelectionsInfo.put(1,doClickSelections(rqspfToggleButtons,gameSelections.get(1)));
                    }
                    if(gameSelectionsKeySet.contains(2) || gameSelectionsKeySet.contains(3) || gameSelectionsKeySet.contains(4)){
                        mustFindObject(game, gridTbMoreSelector, find_timeout, "赛事找不到更多按钮").click();

                        if(gameSelectionsKeySet.contains(2)){
                            List<UiObject2> jqsToggleButtons = getExtraSelections(jqsLayoutSelector);
                            gameSelectionsInfo.put(2,doClickSelections(jqsToggleButtons,gameSelections.get(2)));
                        }
                        UiObject2 parentObject = mustFindObject(jqsLayoutSelector, find_timeout, "找不到扩展选项容器"+jqsLayoutSelector);
                        swipe(Direction.UP, parentObject.getVisibleBounds().height(), getDevice().getDisplayHeight());
                        if(gameSelectionsKeySet.contains(3)){
                            List<UiObject2> bfToggleButtons = getExtraSelections(bfLayoutSelector);
                            gameSelectionsInfo.put(3,doClickSelections(bfToggleButtons,gameSelections.get(3)));
                        }
                        if(gameSelectionsKeySet.contains(4)){
                            List<UiObject2> bqcToggleButtons = getExtraSelections(bqcGvSelector);
                            gameSelectionsInfo.put(4,doClickSelections(bqcToggleButtons,gameSelections.get(4)));
                        }
                        mustFindObject(okBtnSelector, find_timeout, "更多赛事选项找不到确定按钮").click();
                        //通过查找投注页面某个固定元素保证弹层关闭完全
                        mustFindObject(shaixuanBtnSelector, find_timeout, "投注页找不到筛选按钮");
                    }
                    this.selections.remove(s);
                    gameFrameInfo.selectionsInfo = gameSelectionsInfo;
                    break;
                }
            }

            gameFrameInfos.put(id, new Pair(selected, gameFrameInfo));
        }
        return gameFrameInfos;
    }
    private void doScroll(List<UiObject2> games){
        if(games.isEmpty())
            return;
        UiObject2 titlePnl = mustFindObject(layout_titleSelector,find_timeout,"doScroll");
        games.get(games.size() - 1).click();
        games.get(games.size() - 1).click();
        swipe(games.get(games.size() - 1),titlePnl,games.get(games.size() - 1),3, START_POINT.CENTER, START_POINT.H_CENTER_BOTTOM);
    }

    /**
     * 滚动将所有完整显示的赛事向上滚出视图，最后一个完整的赛事在滚动后将只显示一半，即不完整显示
     */
    public void scrollFullVisibleGames(){
        List<UiObject2> games = getSelectableGames();
        doScroll(games);
    }

    /**
     * 手机当前所有完整赛事的信息并返回
     * @return
     */
    public List<GameFrameInfo> fullVisibleGamesAndCollect(){
        List<UiObject2> games = getSelectableGames();
        List<GameFrameInfo> result = new ArrayList<>();
        for (UiObject2 game : games) {
            GameFrameInfo info = collectInfo(game);
            result.add(info);
        }
        return result;
    }

    private GameFrameInfo collectInfo(UiObject2 game){
        GameFrameInfo result = doCollectGameInfo(game);
        Map<Integer,List<String>> gameSelectionsInfo = new HashMap<>();
        List<UiObject2> spfToggleButtons = getSelections(game, spfLayoutSelector);
        gameSelectionsInfo.put(0,doCollectSelections(spfToggleButtons));

        List<UiObject2> rqspfToggleButtons = getSelections(game, rqspfLayoutSelector);
        gameSelectionsInfo.put(1,doCollectSelections(rqspfToggleButtons));
        mustFindObject(game, gridTbMoreSelector, find_timeout, "赛事找不到更多按钮").click();

        List<UiObject2> jqsToggleButtons = getExtraSelections(jqsLayoutSelector);
        gameSelectionsInfo.put(2,doCollectSelections(jqsToggleButtons));

        UiObject2 parentObject = mustFindObject(jqsLayoutSelector, find_timeout, "找不到扩展选项容器"+jqsLayoutSelector);
        swipe(Direction.UP, parentObject.getVisibleBounds().height(), getDevice().getDisplayHeight());

        List<UiObject2> bfToggleButtons = getExtraSelections(bfLayoutSelector);
        gameSelectionsInfo.put(3,doCollectSelections(bfToggleButtons));

        List<UiObject2> bqcToggleButtons = getExtraSelections(bqcGvSelector);
        gameSelectionsInfo.put(4,doCollectSelections(bqcToggleButtons));


        mustFindObject(okBtnSelector, find_timeout, "更多赛事选项找不到确定按钮").click();
        //通过查找投注页面某个固定元素保证弹层关闭完全
        mustFindObject(shaixuanBtnSelector, find_timeout, "投注页找不到筛选按钮");

        result.selectionsInfo = gameSelectionsInfo;
        return result;
    }
}
