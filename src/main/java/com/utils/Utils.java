package com.utils;

import java.util.regex.Pattern;

public class Utils {

	public static boolean emailVerification(String email) {
		final String EMAIL_REGEX = "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$";
		if (Pattern.matches(EMAIL_REGEX, email)) return true;
		return false;
	}

	public static boolean passwordVerification(String password) {
		final String PASS_REGEX = "\\S{5,25}";
		if (Pattern.matches(PASS_REGEX, password)) return true;
		return false;
	}

	public static boolean isValidParameters(int age, double cost) {
		return cost > 0 && age > 0 ? false : true;
	}
}
