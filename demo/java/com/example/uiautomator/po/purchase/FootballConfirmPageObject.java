package com.example.uiautomator.po.purchase;



import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zch12 on 2017/10/26.
 */

public class FootballConfirmPageObject extends CommonPo {
    public  final BySelector numEtSelector =  By.res(packageName+":id/numEt");
    public  final BySelector submitBtnSelector =  By.res(packageName+":id/submitBtn");
    public  final BySelector passBtnsLlSelector =  By.res(packageName+":id/passBtnsLl");
    public  final BySelector totalPriceTvSelector =  By.res(packageName+":id/totalPriceTv");
    public  final BySelector okBtnSelector =  By.res(packageName+":id/okBtn");

    public FootballConfirmPageObject(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }


//    //actions help
//    private void checkMix(List<UiObject2> mixs){
//        if(this.getMixTypes().isEmpty())
//            return;
//        for (UiObject2 mix : mixs) {
//            String mixPage = mix.getText().trim();
//            if(getMixTypes().contains(mixPage)){
//                ActionUtil.safeCheck(mix,className,"checkMix");
//                this.getMixTypes().remove(mixPage);
//            }
//        }
//    }
//    //actions
//
//    public FootballConfirmPageObject setMultiEdit(){
//        ActionUtil.safeEdit(uiAutomatorContext.device(),numEtSelector,multi,timeout,className,"setMultiEdit");
//        return this;
//    }
//    public String getTotalPriceTvText(){
//        return ActionUtil.safeFind(uiAutomatorContext.device(),totalPriceTvSelector,timeout,className,"setMultiEdit").getText();
//    }
//    public FootballConfirmPageObject setMix(){
//        UiObject2 mixHolder = ActionUtil.safeFind(uiAutomatorContext.device(),passBtnsLlSelector,timeout,className,"setMultiEdit");
//        List<UiObject2> mixs = ActionUtil.safeFind1(mixHolder,By.clazz("android.widget.ToggleButton"),timeout,className,"setMultiEdit");
//        checkMix(mixs);
//        if(this.getMixTypes().isEmpty())
//            return this;
//        else{
//            UiObject2 more =null;
//            for (UiObject2 mix : mixs) {
//                if(mix.getText().trim().equals("更多")){
//                    more = mix;
//                    break;
//                }
//            }
//            if(more == null)
//                throw new PageObjectException("要求的mixs没有全部被选中，但是找不到 更多按钮");
//
//            more.click();
//            //提前查找弹层的确定按钮，不但以后可以用到，而且可以为弹层弹出做隐式延时
//            UiObject2 okBtn = ActionUtil.safeFind(uiAutomatorContext.device(),okBtnSelector,timeout,className,"setMix");
//
//            Set<String> texts = new HashSet<>();
//            while (true){
//
//                //查找android.widget.ToggleButton
//                List<UiObject2> btns = ActionUtil.safeFind1(UIAutomatorContext.getInstance().device(),By.clazz("android.widget.ToggleButton"),3000,this.getClass().getName(),"tes");
//                int beforeSize = texts.size();
//                for (UiObject2 btn : btns) {
//                    texts.add(btn.getText());
//                }
//                int afterSize = texts.size();
//                if(afterSize == beforeSize)
//                    break;
//                checkMix(btns);
//                if(this.getMixTypes().isEmpty())
//                    break;
//                ActionUtil.swipe(uiAutomatorContext.device(),btns.get(btns.size() - 1),btns.get(0),btns.get(0),6, ActionUtil.START_POINT.CENTER, ActionUtil.START_POINT.CENTER);
//            }
//            if(!this.getMixTypes().isEmpty())
//                throw new PageObjectException("要求的mixs没有全部被选中，找不到需要的mix "+getMixTypes());
//
//            okBtn.click();
//
//        }
//
//        return this;
//    }
//    public FootballConfirmPageObject clickPassBtn(){
//        ActionUtil.safeClick(uiAutomatorContext.device(),submitBtnSelector,timeout,className,"clickPassBtn");
//        return this;
//    }
}
