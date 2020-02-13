package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.entity.Grid;
import model.entity.Robot;
import test.CommTester;
import view.Simulator;

public class TestButtonListener implements ActionListener {
	private Simulator mView;
    private Grid mGrid;
    private Robot mRobot;
    private CommTester tester;

    public TestButtonListener(Simulator view, Grid grid, Robot robot) {
        mView = view;
        mGrid = grid;
        mRobot = robot;
        mView.addTestButtonListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Test button pressed");
		tester = new CommTester();
		boolean result = tester.test_general();
		if(!result) {
			JOptionPane.showMessageDialog(null, "General Test not passed", "Test Result", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "General Test Passed", "Test Result", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}

}
