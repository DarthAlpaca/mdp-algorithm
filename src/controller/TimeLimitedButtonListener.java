package controller;

import model.entity.Grid;
import model.entity.Robot;
import view.Simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by koallen on 26/8/17.
 */
public class TimeLimitedButtonListener implements ActionListener {

    private Simulator mView;
    private Grid mGrid;
    private Robot mRobot;

    public TimeLimitedButtonListener(Simulator view, Grid grid, Robot robot) {
        mView = view;
        mGrid = grid;
        mRobot = robot;
        mView.addTimeLimitedButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Time limited button pressed");
    }

}
