package com.experthub.smartlock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.experthub.smartlock.models.Password;
import com.shuhart.stepview.StepView;

import java.util.List;

public class AppLockPattern extends AppCompatActivity {
    private StepView stepView;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private Password mPassword;
    private String userPassword;
    private TextView stateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_lock_pattern);



        initializeViews();

        mPassword = new Password(this);
        stateText = findViewById(R.id.state_text);
        stateText.setText(mPassword.FIRST_USE);

        if (mPassword.getPASSWORD_KEY() == null){
            linearLayout.setVisibility(View.GONE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0, true);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.GONE);

            int backgroundColour = ResourcesCompat.getColor(getResources(), R.color.colorBlue, null);
            relativeLayout.setBackgroundColor(backgroundColour);
            stateText.setTextColor(Color.WHITE);
        }
        setPatternListener();
    }

    private void initializeViews() {
        stepView = findViewById(R.id.stepView);
        linearLayout = findViewById(R.id.linearLayout);
        relativeLayout = findViewById(R.id.main_pattern_layout);
    }

    private void setPatternListener() {
        final PatternLockView patternLockView = findViewById(R.id.patternLockView);
        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                String password = PatternLockUtils.patternToString(patternLockView, pattern);
                if (password.length() < 4){
                    stateText.setText(mPassword.SCHEMA_FAILED);
                    patternLockView.clearPattern();
                    return;
                }if (mPassword.getPASSWORD_KEY() == null){
                    if (mPassword.isFirst){
                        userPassword = password;
                        mPassword.isFirst = false;
                        mPassword.setFirst(false);
                        stateText.setText(mPassword.CONFIRM_PATTERN);
                        stepView.go(1, true);
                    }else {
                        if (userPassword.equals(password)){
                            mPassword.setPASSWORD_KEY(password);
                            stateText.setText(mPassword.PATTERN_SET);
                            stepView.done(true);
                            goToMainActivity();
                        }else {
                            stateText.setText(mPassword.PATTERN_SET);
                        }
                    }
                }else {
                    if (mPassword.isCorrect(password)){
                        stateText.setText(mPassword.PATTERN_SET);
                        goToMainActivity();
                    }else {
                        stateText.setText(mPassword.INCREMENT_PATTERN);
                    }

                }
                patternLockView.clearPattern();
            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(AppLockPattern.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mPassword.getPASSWORD_KEY() == null && !mPassword.isFirst){
            stepView.go(0, true);
            mPassword.setFirst(true);
            stateText.setText(mPassword.FIRST_USE);
        }else {
            finish();
            super.onBackPressed();
        }
    }
}