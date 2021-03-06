package com.qsboy.antirecall.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Printer;
import android.view.accessibility.AccessibilityNodeInfo;

import org.jetbrains.annotations.Contract;

/**
 * Created by JasonQS
 */

public class GetNodes {

    static String TAG = "get nodes";

    public static void show(AccessibilityNodeInfo node, int num) {
        Log.i(TAG, "\t");
        Log.i(TAG, "\t");
        iter(node, num);
        Log.i(TAG, "\t");
    }

    private static void iter(AccessibilityNodeInfo node, int num) {
        if (node == null) return;
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Log.i(TAG, "\n\t" + addPadding(num) + i + addPadding(num, 6) + print(node.getChild(i)));
            iter(node.getChild(i), num + 1);
        }
    }

    @NonNull
    private static String addPadding(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append("\t");
        }
        return builder.toString();
    }

    @NonNull
    private static String addPadding(int num, int amount) {
        if (amount < num)
            return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < amount - num; i++) {
            builder.append("\t");
        }
        return builder.toString();
    }

    @Contract("null -> !null")
    private static String print(AccessibilityNodeInfo nodeInfo) {

        if (nodeInfo == null)
            return "";

        CharSequence text = nodeInfo.getText();
        CharSequence description = nodeInfo.getContentDescription();
        CharSequence packageName = nodeInfo.getPackageName();
        CharSequence className = nodeInfo.getClassName();
        boolean focusable = nodeInfo.isFocusable();
        boolean clickable = nodeInfo.isClickable();
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        String viewId = nodeInfo.getViewIdResourceName();

        return "| " +
                "text: " + text + " \t" +
                "description: " + description + " \t" +
                "ID: " + viewId + " \t" +
                "class: " + className + " \t" +
                "location: " + rect + " \t" +
//                "package: " + packageName + " \t" +
                "focusable: " + focusable + " \t" +
                "clickable: " + clickable + " \t" +
                '\n';

    }
}
