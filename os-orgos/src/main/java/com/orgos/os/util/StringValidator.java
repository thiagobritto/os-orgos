package com.orgos.os.util;

import java.util.Objects;

public class StringValidator {

	public static boolean isEmpty(String text) {
		return Objects.isNull(text) || text.trim().isEmpty();
	}

}
