package com.shyam.phonepe.service;

import java.util.List;
import java.util.Map;

import com.shyam.phonepe.entity.InningStatus;
import com.shyam.phonepe.entity.Player;
import com.shyam.phonepe.entity.PlayerScoreCard;

/**
 * This service generate the score board of all the players given their
 * individual score card.
 *
 * @author shyamprasadgupta
 *
 */
public class CricketScoreboardService {

	public static void generateScoreBoard(final InningStatus inningStatus) {

		StringBuilder scorecardOutput = new StringBuilder();
		// generate and print the score board for the batting team
		final Map<Player, PlayerScoreCard> playerScoreCards = inningStatus.getPlayerScoreCardMap();
		List<Player> battingOrder = inningStatus.getBattingTeam().getBattingOrder();
		scorecardOutput.append("Player Name    Score    4s    6s    Balls     ");
		String templateString = "%s             %d         %d     %d     %d     ";
		for (Player player : battingOrder) {
			PlayerScoreCard scoreCard = playerScoreCards.get(player);
			scorecardOutput.append("\n");
			scorecardOutput.append(String.format(templateString,
					(player.getName() + ((!scoreCard.isOut() && scoreCard.getTotalBallsPlayed() > 0) ? "*" : "")),
					scoreCard.getTotalRuns(), scoreCard.getTotalFours(), scoreCard.getToalSixes(),
					scoreCard.getTotalBallsPlayed()));
		}
		scorecardOutput.append("\nTotal: " + inningStatus.getTotalRuns() + "/" + inningStatus.getTotalWickets());

		scorecardOutput.append("\nOvers: " + inningStatus.getTotalOverDeliveredToPring());
		System.out.println(scorecardOutput.toString());
	}
}
