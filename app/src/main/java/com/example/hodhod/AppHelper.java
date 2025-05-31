package com.example.hodhod;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.Group;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppHelper {
    private String userInputDate ;
    private String userInputTime ;
    private List<TaskGroup> groups;
    private Context context;
    private int groupIndex;
    private int taskIndex;

    AppHelper(Context context){
        userInputDate = "";
        userInputTime = "";
        this.context = context;
        try {
            (new File(context.getFilesDir(), "groups.json")).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        groups = new ArrayList<>();
        loadGroups();
    }

    public int getGroupIndex() {
        return groupIndex;
    }
    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }
    public int getTaskIndex() {
        return taskIndex;
    }
    public void setTaskIndex(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    public List<TaskGroup> getGroups() {
        return groups;
    }
    public void setGroups(List<TaskGroup> groups) {
        this.groups = groups;
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

    public FrameLayout getAppButton(String content , int x , int y){
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
    public FrameLayout getAppButton(String content , int y){
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
    public void loadGroups(){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<TaskGroup>>() {}.getType();
        try(BufferedReader reader = new BufferedReader(new FileReader(context.getFilesDir()+"\\groups.json"))){
            groups = gson.fromJson(reader , listType);
            if(groups==null)
                groups = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveGroups(){
        Gson gson = new Gson();
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(context.getFilesDir()+"\\groups.json"))){
            writer.write(gson.toJson(groups));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public FrameLayout getTaskButton(String content ,boolean checkBoxState , int y){
        FrameLayout body;
        Button button;
        CheckBox checkBox;
        LinearLayout linearLayout;
        ImageView image;
        y =(int) (y * context.getResources().getDisplayMetrics().density);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, y);
        //
        body=new FrameLayout(context);
        body.setLayoutParams(params);
        //
        image=new ImageView(context);
        image.setLayoutParams(params);
        image.setImageDrawable(AppCompatResources.getDrawable(context , R.drawable.border));
        //
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        //
        checkBox = new CheckBox(context);
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{-android.R.attr.state_checked}
                },
                new int[]{
                        Color.parseColor("#03DAC6"), // Color when checked
                        Color.parseColor("#03DAC6")  // Color when unchecked
                }
        );
        checkBox.setButtonTintList(colorStateList);
        checkBox.setChecked(checkBoxState);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.0f
        );
        checkBox.setLayoutParams(params2);
        checkBox.setText("");
        //
        button=new Button(context);
        button.setText(content);
        button.setTextSize((int)(10*context.getResources().getDisplayMetrics().density));
        button.setTextColor(Color.parseColor("#E0E0E0"));
        button.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        button.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );
        button.setLayoutParams(params3);
        //
        body.addView(image);
        linearLayout.addView(checkBox);
        linearLayout.addView(button);
        body.addView(linearLayout);
        return body;
    }
}
