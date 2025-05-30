package com.example.hodhod;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    private TextView temp;
    private ScrollView hamMenu;
    private LinearLayout groupList;
    private LinearLayout groupNameBox;
    private EditText groupNameInput;
    private Button groupNameOk;
    private ConstraintLayout constraintLayout;
    private boolean isHamMenuOpen;

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
        AppHelper abdoll = new AppHelper();
        hamMenuB = findViewById(R.id.button4);
        addNewGroup = findViewById(R.id.addgroupB);
        uncType = findViewById(R.id.buttonLeft);
        cType = findViewById(R.id.buttonRight);
        addNewReminder = findViewById(R.id.addNewReminder);
        temp = findViewById(R.id.temp255);
        hamMenu = findViewById(R.id.hamMenu);
        hideHamMenu = findViewById(R.id.hideHamMenu);
        constraintLayout = findViewById(R.id.t);
        groupList = findViewById(R.id.groupListLayout);
        groupNameBox = findViewById(R.id.groupNameBox);
        groupNameInput = findViewById(R.id.groupNameInput);
        groupNameOk = findViewById(R.id.groupNameOk);
        isHamMenuOpen = false;
        //

        List<Button> buttonList = List.of(addNewReminder);
        buttonList.forEach(o -> o.setOnClickListener(event -> temp.setText(String.valueOf(Math.random() * 100))));

        hamMenuB.setOnClickListener(event -> {
            isHamMenuOpen = true;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(-280, 0);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(hamMenu.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int)(animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
        });
        hideHamMenu.setOnClickListener(event -> {
            isHamMenuOpen = false;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(0, -280);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(hamMenu.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int)(animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
        });
        addNewGroup.setOnClickListener(event->{
            if(groupNameBox.getY()<0) {
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
        groupNameOk.setOnClickListener(event->{
            if (!groupNameInput.getText().toString().isEmpty()) {
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
        uncType.setOnClickListener(event->{
            DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String userDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                Toast.makeText(this, "Selected Date: " + userDate, Toast.LENGTH_SHORT).show();
            }, yearNow, monthNow, dayNow);
            datePicker.show();
        });
        cType.setOnClickListener(event->{
            TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String userTime = hourOfDay + ":" + minute;
                Toast.makeText(this, "Selected Time: " + userTime, Toast.LENGTH_SHORT).show();
            }, 12, 0, true);
            timePicker.show();
        });
    }
    @Override
    public void onBackPressed() {
        if(groupNameBox.getY()>0) {
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
        }
        else if (isHamMenuOpen) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            ValueAnimator animator = ValueAnimator.ofInt(0, -280);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                int animatedValue = (int) animation.getAnimatedValue();
                constraintSet.connect(hamMenu.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, (int)(animatedValue * getResources().getDisplayMetrics().density));
                constraintSet.applyTo(constraintLayout);
            });
            animator.start();
        }
        else {
            super.onBackPressed();
        }
    }
}