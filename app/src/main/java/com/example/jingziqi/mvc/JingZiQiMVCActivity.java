package com.example.jingziqi.mvc;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jingziqi.R;
import com.example.jingziqi.mvc.model.Board;
import com.example.jingziqi.mvc.model.Player;

/**
 * 一、MVC
 * M: Model           单独文件/package
 * V:  View           XML
 * C：Controller      Activity/Fragment
 * 优点：将Model进行了解耦
 * 缺点：1、静态：作为controller的activity里面包含了视图的代码；
 *      2、动态：随时需求的变化，由于activity能访问view和model，越来越多的代码放到activity，使得activity变得越来越臃肿；
 * 应用场景：设置/历史订单/交易记录/收藏
 */
public class JingZiQiMVCActivity extends AppCompatActivity {

    private static  String TAG = JingZiQiMVCActivity.class.getName();

    private Board model;

    /** View */
    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvc);
        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);
        model = new Board();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_jingziqi, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                model.restart();
                resetView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void resetView() {
        /*Reset View*/
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }



    public void onCellClicked(View v) {

        Button button = (Button) v;

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (model.getWinner() != null) {
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }
    }


}