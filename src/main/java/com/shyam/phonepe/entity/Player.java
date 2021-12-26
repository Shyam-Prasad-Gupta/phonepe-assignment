package com.shyam.phonepe.entity;

/*
 * This shows the players past records.
 */
public class Player {

	private String name;
	private int age;
	private int totalRuns;
	private int totalFours;
	private int totalSixes;
	private int totalMatchesPlayed;

	public Player() {
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + totalFours;
		result = prime * result + totalMatchesPlayed;
		result = prime * result + totalRuns;
		result = prime * result + totalSixes;
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
		Player other = (Player) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalFours != other.totalFours)
			return false;
		if (totalMatchesPlayed != other.totalMatchesPlayed)
			return false;
		if (totalRuns != other.totalRuns)
			return false;
		if (totalSixes != other.totalSixes)
			return false;
		return true;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(final int age) {
		this.age = age;
	}

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

	public int getTotalSixes() {
		return totalSixes;
	}

	public void setTotalSixes(final int totalSixes) {
		this.totalSixes = totalSixes;
	}

	public int getTotalMatchesPlayed() {
		return totalMatchesPlayed;
	}

	public void setTotalMatchesPlayed(final int totalMatchesPlayed) {
		this.totalMatchesPlayed = totalMatchesPlayed;
	}

	public Player(final String name) {
		this.name = name;
		this.totalRuns = 0;
		this.totalFours = 0;
		this.totalSixes = 0;
		this.totalMatchesPlayed = 0;
		this.age = 0;
	}

}
