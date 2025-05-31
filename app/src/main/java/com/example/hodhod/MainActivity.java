package com.example.hodhod;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button hamMenuB;
    private Button addNewGroup;
    private Button uncType;
    private Button cType;
    private Button addNewReminder;
    private Button hideHamMenu;
    private ScrollView hamMenu;
    private LinearLayout groupList;
    private LinearLayout groupNameBox;
    private EditText groupNameInput;
    private Button groupNameOk;
    private ConstraintLayout constraintLayout;
    private boolean isHamMenuOpen;
    private Button taskOk;
    private Button timeChoose;
    private Button dateChoose;
    private EditText taskTitle;
    private EditText taskDetail;
    private LinearLayout newTaskBox;
    private boolean isGroupNameBoxOpen;
    private boolean isNewTaskBoxOpen;
    private AppHelper abdoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.t), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH) + 1;
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
        int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
        int minuteNow = calendar.get(Calendar.MINUTE);
        //
        abdoll = new AppHelper();
        hamMenuB = findViewById(R.id.button4);
        addNewGroup = findViewById(R.id.addgroupB);
        uncType = findViewById(R.id.buttonLeft);
        cType = findViewById(R.id.buttonRight);
        addNewReminder = findViewById(R.id.addNewReminder);
        hamMenu = findViewById(R.id.hamMenu);
        hideHamMenu = findViewById(R.id.hideHamMenu);
        constraintLayout = findViewById(R.id.t);
        groupList = findViewById(R.id.groupListLayout);
        groupNameBox = findViewById(R.id.groupNameBox);
        groupNameInput = findViewById(R.id.groupNameInput);
        groupNameOk = findViewById(R.id.groupNameOk);
        isHamMenuOpen = false;
        taskOk = findViewById(R.id.taskOk);
        taskTitle = findViewById(R.id.taskTitle);
        taskDetail = findViewById(R.id.taskDetail);
        timeChoose = findViewById(R.id.timeChoose);
        dateChoose = findViewById(R.id.dateChoose);
        newTaskBox = findViewById(R.id.newTaskBox);
        isGroupNameBoxOpen = false;
        isNewTaskBoxOpen = false;
        //
        hamMenuB.setOnClickListener(event -> {
            if (!isGroupNameBoxOpen && !isHamMenuOpen && !isNewTaskBoxOpen) {
                isHamMenuOpen = true;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                ValueAnimator animator = ValueAnimator.ofInt(-280, 0);
                animator.setDuration(300);
                animator.addUpdateListener(animation -> {
                    int animatedValue = (int) animation.getAnimatedValue();
                    constraintSet.connect(hamMenu.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int) (animatedValue * getResources().getDisplayMetrics().density));
                    constraintSet.applyTo(constraintLayout);
                });
                animator.start();
            }
        });
        hideHamMenu.setOnClickListener(event -> {
            isHamMenuOpen = false;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(0, -280);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(hamMenu.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int) (animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
        });
        addNewGroup.setOnClickListener(event -> {
            if (!isGroupNameBoxOpen && !isHamMenuOpen && !isNewTaskBoxOpen) {
                isGroupNameBoxOpen = true;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                ValueAnimator animator = ValueAnimator.ofInt(-300, 250);
                animator.setDuration(300);
                animator.addUpdateListener(animation -> {
                    int animatedValue = (int) animation.getAnimatedValue();
                    constraintSet.connect(groupNameBox.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, (int) (animatedValue * getResources().getDisplayMetrics().density));
                    constraintSet.applyTo(constraintLayout);
                });
                animator.start();
            }
        });
        groupNameOk.setOnClickListener(event -> {
            if (!groupNameInput.getText().toString().isEmpty()) {
                isGroupNameBoxOpen = false;
                groupList.addView(abdoll.getAppButton(this, groupNameInput.getText().toString(), 72));
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                ValueAnimator animator = ValueAnimator.ofInt(250, -300);
                animator.setDuration(300);
                animator.addUpdateListener(animation -> {
                    int animatedValue = (int) animation.getAnimatedValue();
                    constraintSet.connect(groupNameBox.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, (int) (animatedValue * getResources().getDisplayMetrics().density));
                    constraintSet.applyTo(constraintLayout);
                    groupNameInput.setText("");
                });
                animator.start();
            } else {
                Toast.makeText(this, "please enter a name", Toast.LENGTH_SHORT).show();
            }

        });
        dateChoose.setOnClickListener(event -> {
            DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                abdoll.setUserInputDate(year + "_" + (month + 1) + "_" + dayOfMonth);
                Toast.makeText(this, "Selected Date: " + abdoll.getUserInputDate(), Toast.LENGTH_SHORT).show();
            }, yearNow, monthNow, dayNow);
            datePicker.show();
        });
        timeChoose.setOnClickListener(event -> {
            TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                abdoll.setUserInputTime(hourOfDay + "_" + minute);
                Toast.makeText(this, "Selected Time: " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
            }, 12, 0, true);
            timePicker.show();
        });
        addNewReminder.setOnClickListener(event -> {
            if (!isGroupNameBoxOpen && !isHamMenuOpen && !isNewTaskBoxOpen) {
                isNewTaskBoxOpen = true;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                ValueAnimator animator = ValueAnimator.ofInt(-400, 200);
                animator.setDuration(300);
                animator.addUpdateListener(animation -> {
                    int animatedValue = (int) animation.getAnimatedValue();
                    constraintSet.connect(newTaskBox.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, (int) (animatedValue * getResources().getDisplayMetrics().density));
                    constraintSet.applyTo(constraintLayout);
                });
                animator.start();
            }
        });
        taskOk.setOnClickListener(event -> {
            if (!taskTitle.getText().toString().isEmpty() && !abdoll.getUserInputTime().isEmpty() && abdoll.getUserInputDate().isEmpty()) {
                Toast.makeText(this, "please enter a date", Toast.LENGTH_SHORT).show();
            } else if (taskTitle.getText().toString().isEmpty()) {
                Toast.makeText(this, "please enter a task title", Toast.LENGTH_SHORT).show();
            }else{
                isNewTaskBoxOpen = false;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                ValueAnimator animator = ValueAnimator.ofInt(200, -400);
                animator.setDuration(300);
                animator.addUpdateListener(animation -> {
                    int animatedValue = (int) animation.getAnimatedValue();
                    constraintSet.connect(newTaskBox.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, (int) (animatedValue * getResources().getDisplayMetrics().density));
                    constraintSet.applyTo(constraintLayout);
                });
                animator.start();
                if(!taskTitle.getText().toString().isEmpty() && abdoll.getUserInputTime().isEmpty()&& abdoll.getUserInputDate().isEmpty()){
                    new Task(taskTitle.getText().toString(),taskDetail.getText().toString());
                }else{
                    new TimedTask(taskTitle.getText().toString(),taskDetail.getText().toString(),abdoll.getUserInputDate()+abdoll.getUserInputTime());
                }
                taskTitle.setText("");
                taskDetail.setText("");
                abdoll.resetUserDateAndTime();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isGroupNameBoxOpen) {
            isGroupNameBoxOpen = false;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(250, -300);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(groupNameBox.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, (int) (animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
        } else if (isHamMenuOpen) {
            isHamMenuOpen = false;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(0, -280);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(hamMenu.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int) (animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
        } else if (isNewTaskBoxOpen) {
            abdoll.resetUserDateAndTime();
            isNewTaskBoxOpen = false;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(200, -400);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(newTaskBox.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, (int) (animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
            taskTitle.setText("");
            taskDetail.setText("");
            abdoll.resetUserDateAndTime();
        } else {
            super.onBackPressed();
        }
    }
}