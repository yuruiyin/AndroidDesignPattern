package com.yuruiyin.androiddesignpattern.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/25
 */
public class DialogUtil {

    public static void showAlertDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("title");
        builder.setMessage("message");
        builder.setPositiveButton("确定", (dialog, which) -> {
            Toast.makeText(context, "点击了确定按钮", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("取消", (dialog, which) -> {
            Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
        });

        builder.show();
    }

}
