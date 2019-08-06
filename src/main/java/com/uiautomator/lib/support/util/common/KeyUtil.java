package com.uiautomator.lib.support.util.common;

import android.view.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class KeyUtil {
    public static Map<Character, Integer> keyMap = new HashMap<>();
    static {
        keyMap.put('a', KeyEvent.KEYCODE_A);
        keyMap.put('b', KeyEvent.KEYCODE_B);
        keyMap.put('c', KeyEvent.KEYCODE_C);
        keyMap.put('d', KeyEvent.KEYCODE_D);
        keyMap.put('e', KeyEvent.KEYCODE_E);
        keyMap.put('f', KeyEvent.KEYCODE_F);
        keyMap.put('g', KeyEvent.KEYCODE_G);
        keyMap.put('h', KeyEvent.KEYCODE_H);
        keyMap.put('i', KeyEvent.KEYCODE_I);
        keyMap.put('j', KeyEvent.KEYCODE_J);
        keyMap.put('k', KeyEvent.KEYCODE_K);
        keyMap.put('l', KeyEvent.KEYCODE_L);
        keyMap.put('m', KeyEvent.KEYCODE_M);
        keyMap.put('n', KeyEvent.KEYCODE_N);
        keyMap.put('o', KeyEvent.KEYCODE_O);
        keyMap.put('p', KeyEvent.KEYCODE_P);
        keyMap.put('q', KeyEvent.KEYCODE_Q);
        keyMap.put('r', KeyEvent.KEYCODE_R);
        keyMap.put('s', KeyEvent.KEYCODE_S);
        keyMap.put('t', KeyEvent.KEYCODE_T);
        keyMap.put('u', KeyEvent.KEYCODE_U);
        keyMap.put('v', KeyEvent.KEYCODE_V);
        keyMap.put('w', KeyEvent.KEYCODE_W);
        keyMap.put('x', KeyEvent.KEYCODE_X);
        keyMap.put('y', KeyEvent.KEYCODE_Y);
        keyMap.put('z', KeyEvent.KEYCODE_Z);

        keyMap.put('0', KeyEvent.KEYCODE_0);
        keyMap.put('1', KeyEvent.KEYCODE_1);
        keyMap.put('2', KeyEvent.KEYCODE_2);
        keyMap.put('3', KeyEvent.KEYCODE_3);
        keyMap.put('4', KeyEvent.KEYCODE_4);
        keyMap.put('5', KeyEvent.KEYCODE_5);
        keyMap.put('6', KeyEvent.KEYCODE_6);
        keyMap.put('7', KeyEvent.KEYCODE_7);
        keyMap.put('8', KeyEvent.KEYCODE_8);
        keyMap.put('9', KeyEvent.KEYCODE_9);
    }
    public static int[] stringToKeyCodeArray(String message){
        int[] result = new int[message.length()];
        char[] charArray = message.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            result[i] = keyMap.get(charArray[i]);
        }
        return result;
    }
    public static int getCode(char c){
        return keyMap.get(c);
    }
}
