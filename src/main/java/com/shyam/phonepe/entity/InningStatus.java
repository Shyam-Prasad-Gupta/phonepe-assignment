package com.shyam.phonepe.entity;

import java.util.HashMap;
import java.util.Map;

public class InningStatus {

	private Team battingTeam;
	private Team bowlingTeam;
	private int totalRuns;
	private int totalWickets;
	private int extras;
	private int totalBallDelivered;
	private Player batsmanOnStrike;
	private Player batsmanNotOnStrike;
	private int totalOversInTheMatch;
	private boolean isInningOver = false;
	private Map<Player, PlayerScoreCard> playerScoreCardMap = new HashMap<>();

	public InningStatus() {

	}

	public Team getBattingTeam() {
		return battingTeam;
	}

	public void setBattingTeam(final Team battingTeam) {
		this.battingTeam = battingTeam;
	}

	public Team getBowlingTeam() {
		return bowlingTeam;
	}

	public void setBowlingTeam(final Team bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(final int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getTotalWickets() {
		return totalWickets;
	}

	public void setTotalWickets(final int totalWickets) {
		this.totalWickets = totalWickets;
	}

	public int getExtras() {
		return extras;
	}

	public void setExtras(final int extras) {
		this.extras = extras;
	}

	public int getTotalBallDelivered() {
		return totalBallDelivered;
	}

	public void setTotalBallDelivered(final int totalBallDelivered) {
		this.totalBallDelivered = totalBallDelivered;
	}

	public Player getBatsmanOnStrike() {
		return batsmanOnStrike;
	}

	public void setBatsmanOnStrike(final Player batsmanOnStrike) {
		this.batsmanOnStrike = batsmanOnStrike;
	}

	public Player getBatsmanNotOnStrike() {
		return batsmanNotOnStrike;
	}

	public void setBatsmanNotOnStrike(final Player batsmanNotOnStrike) {
		this.batsmanNotOnStrike = batsmanNotOnStrike;
	}

	public int getTotalOversInTheMatch() {
		return totalOversInTheMatch;
	}

	public void setTotalOversInTheMatch(final int totalOversInTheMatch) {
		this.totalOversInTheMatch = totalOversInTheMatch;
	}

	public boolean isInningOver() {
		return isInningOver;
	}

	public void setInningOver(final boolean isInningOver) {
		this.isInningOver = isInningOver;
	}

	public Map<Player, PlayerScoreCard> getPlayerScoreCardMap() {
		return playerScoreCardMap;
	}

	public void setPlayerScoreCardMap(final Map<Player, PlayerScoreCard> playerScoreCardMap) {
		this.playerScoreCardMap = playerScoreCardMap;
	}

	public String getTotalOverDeliveredToPring() {
		String printableOver = "";
		printableOver += totalBallDelivered / 6;

		printableOver += totalBallDelivered % 6 == 0 ? "" : ("." + totalBallDelivered % 6);
		return printableOver;
	}

}
