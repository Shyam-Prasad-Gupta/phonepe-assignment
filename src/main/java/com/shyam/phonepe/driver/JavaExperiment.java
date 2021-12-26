package com.shyam.phonepe.driver;

public class JavaExperiment {

	public static void main(final String... strings) {
		System.out.println(getTotalOverDeliveredToPrint(13));
		System.out.println(getTotalOverDeliveredToPrint(12));
		System.out.println(getTotalOverDeliveredToPrint(21));
	}

	public static String getTotalOverDeliveredToPrint(final int totalBallDelivered) {
		String printableOver = "";
		printableOver += totalBallDelivered / 6;

		printableOver += totalBallDelivered % 6 == 0 ? "" : ("." + totalBallDelivered % 6);
		return printableOver;
	}

}
