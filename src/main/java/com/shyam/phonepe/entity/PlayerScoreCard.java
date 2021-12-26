package com.shyam.phonepe.entity;

public class PlayerScoreCard {

	private int totalRuns;
	private int totalFours;
	private int toalSixes;
	private int totalBallsPlayed;
	private boolean isOut = false;

	// attributes to track the balling performance in the match
	private float totalOverThrown;
	private int wicketsTaken;
	private int extras;

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(final int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getTotalFours() {
		return totalFours;
	}

	public void setTotalFours(final int totalFours) {
		this.totalFours = totalFours;
	}

	public int getToalSixes() {
		return toalSixes;
	}

	public void setToalSixes(final int toalSixes) {
		this.toalSixes = toalSixes;
	}

	public int getTotalBallsPlayed() {
		return totalBallsPlayed;
	}

	public void setTotalBallsPlayed(final int totalBallsPlayed) {
		this.totalBallsPlayed = totalBallsPlayed;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(final boolean isOut) {
		this.isOut = isOut;
	}

	public float getTotalOverThrown() {
		return totalOverThrown;
	}

	public void setTotalOverThrown(final float totalOverThrown) {
		this.totalOverThrown = totalOverThrown;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(final int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public int getExtras() {
		return extras;
	}

	public void setExtras(final int extras) {
		this.extras = extras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + extras;
		result = prime * result + (isOut ? 1231 : 1237);
		result = prime * result + toalSixes;
		result = prime * result + totalBallsPlayed;
		result = prime * result + totalFours;
		result = prime * result + Float.floatToIntBits(totalOverThrown);
		result = prime * result + totalRuns;
		result = prime * result + wicketsTaken;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerScoreCard other = (PlayerScoreCard) obj;
		if (extras != other.extras)
			return false;
		if (isOut != other.isOut)
			return false;
		if (toalSixes != other.toalSixes)
			return false;
		if (totalBallsPlayed != other.totalBallsPlayed)
			return false;
		if (totalFours != other.totalFours)
			return false;
		if (Float.floatToIntBits(totalOverThrown) != Float.floatToIntBits(other.totalOverThrown))
			return false;
		if (totalRuns != other.totalRuns)
			return false;
		if (wicketsTaken != other.wicketsTaken)
			return false;
		return true;
	}
}
