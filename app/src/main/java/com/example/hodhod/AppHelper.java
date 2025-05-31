package com.example.hodhod;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class AppHelper {
    private String userInputDate ;
    private String userInputTime ;

    AppHelper(){
        userInputDate = "";
        userInputTime = "";
    }


    public String getUserInputDate() {
        return userInputDate;
    }
    public void setUserInputDate(String userInputDate) {
        this.userInputDate = userInputDate;
    }
    public String getUserInputTime() {
        return userInputTime;
    }
    public void setUserInputTime(String userInputTime) {
        this.userInputTime = userInputTime;
    }

    public FrameLayout getAppButton(Context context, String content , int x , int y){
        FrameLayout body;
        Button button;
        ImageView image;
        x =(int) (x * context.getResources().getDisplayMetrics().density);
        y =(int) (y * context.getResources().getDisplayMetrics().density);
        ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(x, y);
        //
        body=new FrameLayout(context);
        body.setLayoutParams(params);
        //
        button=new Button(context);
        button.setText(content);
        button.setTextSize((int)(10*context.getResources().getDisplayMetrics().density));
        button.setTextColor(Color.parseColor("#E0E0E0"));
        button.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        button.setLayoutParams(params);
        //
        image=new ImageView(context);
        image.setLayoutParams(params);
        image.setBackgroundResource(R.drawable.border);
        //
        body.addView(image);
        body.addView(button);
        return body;
    }
    public FrameLayout getAppButton(Context context, String content , int y){
        FrameLayout body;
        Button button;
        ImageView image;
        y =(int) (y * context.getResources().getDisplayMetrics().density);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, y);
        //
        button=new Button(context);
        button.setText(content);
        button.setTextSize((int)(10*context.getResources().getDisplayMetrics().density));
        button.setTextColor(Color.parseColor("#E0E0E0"));
        button.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        button.setLayoutParams(params);
        //
        image=new ImageView(context);
        image.setLayoutParams(params);
        image.setBackgroundResource(R.drawable.border);
        //
        body=new FrameLayout(context);
        body.setLayoutParams(params);
        //
        body.addView(image);
        body.addView(button);
        return body;
    }
    public void resetUserDateAndTime(){
        userInputDate = "";
        userInputTime = "";
    }
}
