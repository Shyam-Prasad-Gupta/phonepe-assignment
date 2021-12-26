package com.shyam.phonepe.entity;

import java.util.List;

public class Team {

	private String name;
	private int totalNumberOfPlayers;
	private List<Player> battingOrder;
	private List<Player> bowlingOrder;

	public Team() {
	}

	public Team(final String teamName) {
		this.name = teamName;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getTotalNumberOfPlayers() {
		return totalNumberOfPlayers;
	}

	public void setTotalNumberOfPlayers(final int totalNumberOfPlayers) {
		this.totalNumberOfPlayers = totalNumberOfPlayers;
	}

	public List<Player> getBattingOrder() {
		return battingOrder;
	}

	public void setBattingOrder(final List<Player> battingOrder) {
		this.battingOrder = battingOrder;
	}

	public List<Player> getBowlingOrder() {
		return bowlingOrder;
	}

	public void setBowlingOrder(final List<Player> bowlingOrder) {
		this.bowlingOrder = bowlingOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((battingOrder == null) ? 0 : battingOrder.hashCode());
		result = prime * result + ((bowlingOrder == null) ? 0 : bowlingOrder.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + totalNumberOfPlayers;
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
		Team other = (Team) obj;
		if (battingOrder == null) {
			if (other.battingOrder != null)
				return false;
		} else if (!battingOrder.equals(other.battingOrder))
			return false;
		if (bowlingOrder == null) {
			if (other.bowlingOrder != null)
				return false;
		} else if (!bowlingOrder.equals(other.bowlingOrder))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalNumberOfPlayers != other.totalNumberOfPlayers)
			return false;
		return true;
	}

}
