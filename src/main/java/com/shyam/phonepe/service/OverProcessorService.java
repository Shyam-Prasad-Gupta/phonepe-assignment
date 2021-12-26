package com.shyam.phonepe.service;

import java.util.Map;

import com.shyam.phonepe.entity.InningStatus;
import com.shyam.phonepe.entity.Player;
import com.shyam.phonepe.entity.PlayerScoreCard;
import com.shyam.phonepe.enums.DeliveryType;

public class OverProcessorService {

	private final static int FOUR = 4;
	private final static int SIX = 6;

	public static boolean processOver(final String overDelivery, final int deliveryNum,
			final InningStatus inningStatus) {
		int runScoredOnThisBall = 0;
		Map<Player, PlayerScoreCard> playerScoreCardMap = inningStatus.getPlayerScoreCardMap();
		PlayerScoreCard scoreCard = null;

		scoreCard = playerScoreCardMap.get(inningStatus.getBatsmanOnStrike());
		if (DeliveryType.isExtraBallTypes(overDelivery)) {
			// if the delivery type is no ball or wide ball then we update the extra and
			// total runs by 1
			// Note: assuming that extra balls is not counted under players played ball
			inningStatus.setExtras(inningStatus.getExtras() + 1);
			inningStatus.setTotalRuns(inningStatus.getTotalRuns() + 1);
			return false;
		} else if (DeliveryType.isWicketBall(overDelivery)) {
			// if it is wicket ball then there will be following changes:
			// 1. current striker will be set as out,
			// 2. next player will come at the strike/non strike depends on the over
			// 3. as the players journey in this inning is ending update his/her score card
			runScoredOnThisBall = 0;
			scoreCard.setOut(true);
			inningStatus.setTotalWickets(inningStatus.getTotalWickets() + 1);
			// update the strike appropriately
			// checking if total players -1 are out.
			if (inningStatus.getTotalWickets() == (inningStatus.getBattingTeam().getTotalNumberOfPlayers() - 1)) {
				// inning ends here as there are no more player left
				inningStatus.setInningOver(true);
			} else {
				Player newBatsmanOnPitch = inningStatus.getBattingTeam().getBattingOrder()
						.get(inningStatus.getTotalWickets() + 1);
				inningStatus.setBatsmanOnStrike(newBatsmanOnPitch);
			}
		} else {
			runScoredOnThisBall = Integer.parseInt(overDelivery);
			if (runScoredOnThisBall == FOUR) {
				// means it is either 4 or 6
				scoreCard.setTotalFours(scoreCard.getTotalFours() + 1);
			} else if (runScoredOnThisBall == SIX) {
				scoreCard.setToalSixes(scoreCard.getToalSixes() + 1);
			}

		}
		// check here if there is a need to change the strike
		if (runScoredOnThisBall % 2 != 0)
			changeStrike(inningStatus);

		// here update the total balls played by the player and total runs
		scoreCard.setTotalRuns(scoreCard.getTotalRuns() + runScoredOnThisBall);
		scoreCard.setTotalBallsPlayed(scoreCard.getTotalBallsPlayed() + 1);

		// update totalRuns, totalBallsDelivered
		inningStatus.setTotalRuns(inningStatus.getTotalRuns() + runScoredOnThisBall);
		inningStatus.setTotalBallDelivered(inningStatus.getTotalBallDelivered() + 1);

		// here generate and print the score board and also check if innings is over or
		// not
		if (deliveryNum % 6 == 0 || inningStatus.isInningOver()) {
			CricketScoreboardService.generateScoreBoard(inningStatus);

			// change the strike if the over is completed
			if (deliveryNum % 6 == 0)
				changeStrike(inningStatus);
		}
		if ((inningStatus.getTotalBallDelivered() / 6 == inningStatus.getTotalOversInTheMatch())
				|| (inningStatus.getTotalWickets() == (inningStatus.getBattingTeam().getTotalNumberOfPlayers() - 1))) {
			inningStatus.setInningOver(true);
		}
		return inningStatus.isInningOver();
	}

	private static void changeStrike(final InningStatus inningStatus) {
		Player currentPlayerOnStrike = inningStatus.getBatsmanOnStrike();
		inningStatus.setBatsmanOnStrike(inningStatus.getBatsmanNotOnStrike());
		inningStatus.setBatsmanNotOnStrike(currentPlayerOnStrike);
	}
}
