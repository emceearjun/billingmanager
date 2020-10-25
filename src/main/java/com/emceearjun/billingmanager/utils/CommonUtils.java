package com.emceearjun.billingmanager.utils;

import java.util.UUID;

public class CommonUtils {

	public static String generateProductId() {
		return UUID.randomUUID().toString();
	}

}
