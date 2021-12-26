package com.shyam.phonepe.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shyam.phonepe.entity.InningStatus;
import com.shyam.phonepe.entity.Player;
import com.shyam.phonepe.entity.PlayerScoreCard;
import com.shyam.phonepe.entity.Team;
import com.shyam.phonepe.enums.DeliveryType;
import com.shyam.phonepe.service.OverProcessorService;

public class MatchDriver {
	public static void main(final String... strings) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int totalNumberOfPlayersInEachTeam = Integer.parseInt(br.readLine());
		int totalNumberOfOvers = Integer.parseInt(br.readLine());

		// Note: assuming that team 1 will bat first without tossing a coin
		Team team1 = new Team("T1");
		List<Player> team1PlayerList = new ArrayList<Player>();
		team1.setTotalNumberOfPlayers(totalNumberOfPlayersInEachTeam);
		team1.setBattingOrder(team1PlayerList);

		for (int i = 0; i < totalNumberOfPlayersInEachTeam; i++) {
			String playerName = br.readLine();
			team1PlayerList.add(new Player(playerName));
			// here we can also set player past data as well fetching from some external
			// source or from user provided input as well, adding name only for simplicity.
		}

		// inning 1 starts here
		InningStatus firstInning = new InningStatus();

		// prepare empty score card map for players to be populated during the match
		firstInning.setPlayerScoreCardMap(prepareEmptyScorecardMapForPlayers(team1PlayerList));
		firstInning.setTotalOversInTheMatch(totalNumberOfOvers);
		firstInning.setBattingTeam(team1);
		firstInning.setBatsmanOnStrike(team1PlayerList.get(0));
		firstInning.setBatsmanNotOnStrike(team1PlayerList.get(1));

		for (int i = 0; (!firstInning.isInningOver());) {
			String ballStatus = br.readLine();

			if (OverProcessorService.processOver(ballStatus, i + 1, firstInning)) {
				break;
			}
			if (!DeliveryType.isExtraBallTypes(ballStatus)) {
				i++;
			}
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

			if (OverProcessorService.processOver(ballStatus, i + 1, secondInning)
					|| (secondInning.getTotalRuns() > firstInning.getTotalRuns())) {
				break;
			}
			if (!DeliveryType.isExtraBallTypes(ballStatus)) {
				i++;
			}
		}

		// now here depending on the inning status choose the winner
		printMatchResult(firstInning, secondInning);
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
}
