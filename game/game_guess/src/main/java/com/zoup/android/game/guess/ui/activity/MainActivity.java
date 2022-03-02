package com.zoup.android.game.guess.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zoup.android.game.guess.R;
import com.zoup.android.game.guess.ui.adapter.CommonAdapter;
import com.zoup.android.game.guess.ui.adapter.ViewHolder;
import com.zoup.android.game.guess.utils.GuessLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private CommonAdapter<GuessLogic.GuessResult> adapter;
    private List<GuessLogic.GuessResult> resultList;
    // ToolBar
    Toolbar toolbar;
    // 4个猜数字的按钮
    Button firstWheel;
    Button secondWheel;
    Button thirdWheel;
    Button fourthWheel;
    private Button[] wheelButtons = new Button[4];
    private Set<Button> selectedWheels = new HashSet<>();
    private Button selectedWheel;
    // 10个数字键盘
    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    private Button[] numberButtons = new Button[10];
    private Button selectedNumber;
    ListView resultListView;
    // 游戏的逻辑类
    private GuessLogic guessLogic;
    // 猜测的次数
    private int attemptCounts = 0;
    // 游戏是否结束的flag
    private Boolean isWon;
    private static final int N = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        addListener();
        initViews();
    }

    private void initComponents() {
        firstWheel=findViewById(R.id.first_wheel);
        secondWheel=findViewById(R.id.second_wheel);
        thirdWheel=findViewById(R.id.third_wheel);
        fourthWheel=findViewById(R.id.fourth_wheel);
        wheelButtons[0] = firstWheel;
        wheelButtons[1] = secondWheel;
        wheelButtons[2] = thirdWheel;
        wheelButtons[3] = fourthWheel;

        num0=findViewById(R.id.button_0);
        num1=findViewById(R.id.button_1);
        num2=findViewById(R.id.button_2);
        num3=findViewById(R.id.button_3);
        num4=findViewById(R.id.button_4);
        num5=findViewById(R.id.button_5);
        num6=findViewById(R.id.button_6);
        num7=findViewById(R.id.button_7);
        num8=findViewById(R.id.button_8);
        num9=findViewById(R.id.button_9);
        numberButtons[0] = num0;
        numberButtons[1] = num1;
        numberButtons[2] = num2;
        numberButtons[3] = num3;
        numberButtons[4] = num4;
        numberButtons[5] = num5;
        numberButtons[6] = num6;
        numberButtons[7] = num7;
        numberButtons[8] = num8;
        numberButtons[9] = num9;

        resultListView=findViewById(R.id.result_listview);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void addListener() {
        for (int i = 0; i < wheelButtons.length; i++) {
            wheelButtons[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != selectedWheel) {
                        selectedWheel.setSelected(false);
                    }
                    selectedWheel = (Button) v;
                    selectedWheel.setSelected(true);
                    selectedWheels.add(selectedWheel);
                }
            });
        }
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedNumber = (Button) v;
                    if (null != selectedWheel) {
                        selectedWheel.setText(selectedNumber.getText().toString());
                    }
                    if (selectedWheels.size() == N) {
                        checkGuess();
                    }
                }
            });
        }
    }

    private void initViews() {
        resultList = new ArrayList<>();
        adapter = new CommonAdapter<GuessLogic.GuessResult>(this, resultList, R.layout.list_item) {
            @Override
            public void convert(ViewHolder helper, GuessLogic.GuessResult guessResult) {
                helper.setTextColor(R.id.content, "#80ff0000");
                helper.setText(R.id.content, "第 " + guessResult.attemptCounts + " 次:" + guessResult.guessStr + " A=" + guessResult.correctNumber + " B=" + guessResult.existingNumber);
            }
        };
        resultListView.setAdapter(adapter);
        newGame();
    }

    private void newGame() {
        for (Button button : wheelButtons) {
            button.setSelected(false);
            button.setText("?");
        }
        MyTimeCounter timeCounter = new MyTimeCounter(2600, 83);
        timeCounter.start();
        guessLogic = new GuessLogic();
        resultList.clear();
        adapter.notifyDataSetChanged();
        isWon = false;
        attemptCounts = 0;
    }

    private void checkGuess() {
        selectedWheel.setSelected(false);
        attemptCounts++;
        int[] guessAttempt = new int[4];
        for (int i = 0; i < 4; i++) {
            guessAttempt[i] = Integer.parseInt(wheelButtons[i].getText().toString());
        }
        GuessLogic.GuessResult guessResult = guessLogic.guess(guessAttempt);
        guessResult.attemptCounts = attemptCounts;
        resultList.add(guessResult);
        adapter.notifyDataSetChanged();
        if (!guessResult.isWon) {
            selectedWheels.clear();
            for (Button button : wheelButtons) {
                button.setText("?");
            }
        } else {
            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            for (Button button : wheelButtons) {
                button.setText("?");
            }
        }
    }

    private class MyTimeCounter extends CountDownTimer {
        MyTimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            for (Button button : wheelButtons) {
                button.setSelected(false);
                button.setText(String.valueOf((int) (Math.random() * 10)));
            }
        }

        @Override
        public void onFinish() {
            for (Button button : wheelButtons) {
                button.setSelected(false);
                button.setText("?");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                newGame();
                break;
            case R.id.action_share:
                break;
            case R.id.action_about:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
