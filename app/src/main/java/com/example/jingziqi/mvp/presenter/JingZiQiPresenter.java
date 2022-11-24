package com.example.jingziqi.mvp.presenter;

import com.example.jingziqi.mvp.model.Board;
import com.example.jingziqi.mvp.model.Player;
import com.example.jingziqi.mvp.view.JingZiQiView;

public class JingZiQiPresenter {
    private JingZiQiView view;
    private Board model;

    public JingZiQiPresenter(JingZiQiView view) {
        this.view = view;
        this.model = new Board();
    }

    public void onButtonSelected(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            }
        }
    }


    public void onResetSelected() {
        model.restart();
        view.clearWinnerDisplay();
        view.clearButtons();
    }
}
