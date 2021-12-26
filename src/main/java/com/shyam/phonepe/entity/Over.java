package com.shyam.phonepe.entity;

public class Over {

	private int currentOver;
	private int currentOverBalls;

	public int getTotalOvers() {
		return currentOver;
	}

	public void setTotalOvers(final int totalOvers) {
		this.currentOver = totalOvers;
	}

	public int getCurrentOverBalls() {
		return currentOverBalls;
	}

	public void setCurrentOverBalls(final int currentOverBalls) {
		this.currentOverBalls = currentOverBalls;
	}

}
