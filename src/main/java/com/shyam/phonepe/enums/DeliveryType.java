package com.shyam.phonepe.enums;

import java.util.Arrays;

public enum DeliveryType {

	Wd("wide ball"), W("wicket ball"), Nb("no ball");

	private String ballDescription;

	private DeliveryType(final String ballDescription) {
		this.ballDescription = ballDescription;
	}

	public static boolean isExtraBallTypes(final String delivery) {
		return Arrays.asList(Wd.toString(), Nb.toString()).contains(delivery);
	}

	public static boolean isWicketBall(final String delivery) {
		return W.toString().equals(delivery);
	}

}
