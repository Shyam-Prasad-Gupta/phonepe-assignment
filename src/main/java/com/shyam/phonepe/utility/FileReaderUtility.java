package com.shyam.phonepe.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * This simple program takes the input as file name and returns the Reader
 * object to read that file.
 *
 * @author shyamprasadgupta
 *
 */
public class FileReaderUtility {

	public static BufferedReader giveMeFileReader(final String fileName) {

		BufferedReader br = null;
		// Paths.get("").toAbsolutePath(); --> this works well as well
		File file = new File(System.getProperty("user.dir") + "/src/main/resources/static/" + fileName);
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}

}
