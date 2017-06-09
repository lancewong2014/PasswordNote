package com.whitekapok.passwordnote.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.whitekapok.passwordnote.MainApp;
import com.whitekapok.passwordnote.R;


/**
 * 用于显示Toast
 * 放在Application里面
 * Created by Lance on 2016/5/24.
 */
public class ToastUtil {
    private Toast toast;
    private Context context;

    public ToastUtil(Context context) {
        this.context=context;
    }

    /**
     * 显示一个Toast
     * @param strRes  需要显示的字符串
     * @param time  需要显示的时间
     */
    public void showToast(String strRes, int time) {
        showCustomToast(strRes,time);
    }

    public void showToast(String strRes){
        showCustomToast(strRes);
    }

    public void showToast(int strRes) throws Resources.NotFoundException{
        showCustomToast(strRes);
    }
    /**
     * 显示一个Toast
     * @param strRes 需要显示的文本资源
     * @throws Resources.NotFoundException
     */
    public void showToast(int strRes,int time) throws Resources.NotFoundException{
        showCustomToast(strRes,time);
    }

    private void showCustomToast(@StringRes int message, int time){
        if(toast!=null){
            toast.cancel();
        }
        toast=new Toast(MainApp.getInstance());
        View contentView= LayoutInflater.from(MainApp.getInstance()).inflate(R.layout.view_toast,null);
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(time);
        TextView msgText=(TextView)contentView.findViewById(R.id.tv_msg);
        msgText.setText(message);
        toast.show();
    }
    private void showCustomToast(@StringRes int message){
        showCustomToast(message, Toast.LENGTH_SHORT);
    }

    private void showCustomToast(String message){
        showCustomToast(message, Toast.LENGTH_SHORT);
    }

    private void showCustomToast(String message, int time){
        if(toast!=null){
            toast.cancel();
        }
        toast=new Toast(MainApp.getInstance());
        View contentView= LayoutInflater.from(MainApp.getInstance()).inflate(R.layout.view_toast,null);
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(time);
        TextView msgText=(TextView)contentView.findViewById(R.id.tv_msg);
        msgText.setText(message);
        toast.show();
    }


}
