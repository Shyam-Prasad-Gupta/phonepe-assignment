package com.shyam.phonepe.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shyam.phonepe.entity.InningStatus;
import com.shyam.phonepe.entity.Player;
import com.shyam.phonepe.entity.PlayerScoreCard;
import com.shyam.phonepe.entity.Team;
import com.shyam.phonepe.enums.DeliveryType;
import com.shyam.phonepe.service.CricketScoreboardService;
import com.shyam.phonepe.service.OverProcessorService;
import com.shyam.phonepe.utility.FileReaderUtility;

public class MatchDriver {
	public static void main(final String... strings) throws IOException {

		// new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = FileReaderUtility.giveMeFileReader("input3.txt");// FileReaderUtility.giveMeFileReader("input2.txt");
		int totalNumberOfPlayersInEachTeam = Integer.parseInt(br.readLine());
		int totalNumberOfOvers = Integer.parseInt(br.readLine());

		// Note: assuming that team 1 will bat first without tossing a coin
		List<Player> playerList = new ArrayList<>();
		for (int i = 0; i < totalNumberOfPlayersInEachTeam; i++) {
			String playerName = br.readLine();
			playerList.add(new Player(playerName));
			// here we can also set player past data as well fetching from some external
			// source or from user provided input as well, adding name only for simplicity.
		}
		Team team1 = createTeam("Team1", playerList);

		// inning 1 starts here
		InningStatus firstInning = new InningStatus();

		// prepare empty score card map for players to be populated during the match
		firstInning.setPlayerScoreCardMap(prepareEmptyScorecardMapForPlayers(team1.getBattingOrder()));
		firstInning.setTotalOversInTheMatch(totalNumberOfOvers);
		firstInning.setBattingTeam(team1);
		firstInning.setBatsmanOnStrike(team1.getBattingOrder().get(0));
		firstInning.setBatsmanNotOnStrike(team1.getBattingOrder().get(1));

		for (int i = 0; (!firstInning.isInningOver());) {
			String ballStatus = br.readLine();

			printScoreCardForInning(firstInning);
			if (OverProcessorService.processOver(ballStatus, i + 1, firstInning)) {
				break;
			}
			if (!DeliveryType.isExtraBallTypes(ballStatus)) {
				i++;
			}
			// printScoreCardForInning(firstInning);
		}

		Team team2 = new Team("T2");
		team2.setTotalNumberOfPlayers(totalNumberOfPlayersInEachTeam);

		List<Player> team2PlayerList = new ArrayList<Player>();
		team2.setTotalNumberOfPlayers(totalNumberOfPlayersInEachTeam);
		team2.setBattingOrder(team2PlayerList);

		for (int i = 0; i < totalNumberOfPlayersInEachTeam; i++) {
			String playerName = br.readLine();
			team2PlayerList.add(new Player(playerName));
			// here we can also set player past data as well fetching from some external
			// source or from user provided input as well, adding name only for simplicity.
		}

		// inning 2 starts here
		InningStatus secondInning = new InningStatus();

		// prepare empty score card map for players
		secondInning.setPlayerScoreCardMap(prepareEmptyScorecardMapForPlayers(team2PlayerList));
		secondInning.setTotalOversInTheMatch(totalNumberOfOvers);
		secondInning.setBattingTeam(team2);
		secondInning.setBatsmanOnStrike(team2PlayerList.get(0));
		secondInning.setBatsmanNotOnStrike(team2PlayerList.get(1));

		for (int i = 0; (!secondInning.isInningOver());) {

			String ballStatus = br.readLine();

			printScoreCardForInning(firstInning);
			if (OverProcessorService.processOver(ballStatus, i + 1, secondInning)
					|| (secondInning.getTotalRuns() > firstInning.getTotalRuns())
					|| (secondInning.getTotalWickets() == (totalNumberOfPlayersInEachTeam - 1))
					|| (secondInning.getTotalBallDelivered() % 6 == 0)) {
				// CricketScoreboardService.generateScoreBoard(secondInning);
				break;
			}
			if (!DeliveryType.isExtraBallTypes(ballStatus)) {
				i++;
			}
			// printScoreCardForInning(secondInning);
		}

		// now here depending on the inning status choose the winner
		printMatchResult(firstInning, secondInning);
	}

	private static Team createTeam(final String teamName, final List<Player> totalPlayers) {

		Team team = new Team(teamName);

		team.setTotalNumberOfPlayers(totalPlayers.size());
		team.setBattingOrder(totalPlayers);
		return team;
	}

	private static void printMatchResult(final InningStatus firstInning, final InningStatus secondInning) {
		if (firstInning.getTotalRuns() > secondInning.getTotalRuns()) {
			System.out.printf("Team 1 won the match by %s runs.",
					(firstInning.getTotalRuns() - secondInning.getTotalRuns()));
		} else if (firstInning.getTotalRuns() < secondInning.getTotalRuns()) {
			System.out.printf("Team 2 won the match by %s runs.",
					(secondInning.getTotalRuns() - firstInning.getTotalRuns()));
		} else {
			System.out.println("Match is draw.");
		}

	}

	private static Map<Player, PlayerScoreCard> prepareEmptyScorecardMapForPlayers(final List<Player> team1PlayerList) {
		Map<Player, PlayerScoreCard> map = new HashMap<>();
		team1PlayerList.stream().forEach(player -> map.put(player, new PlayerScoreCard()));
		return map;
	}

	private static void printScoreCardForInning(final InningStatus inning) {
		// cases where we have to print score card
		// 1. all players are out
		// 2. over is completed
		// 3. inning is over
		if ((inning.getTotalWickets() == (inning.getBattingTeam().getTotalNumberOfPlayers() - 1))
				|| (inning.getTotalBallDelivered() % 6 == 0) || inning.isInningOver()) {
			CricketScoreboardService.generateScoreBoard(inning);
		}
	}
}
