package com.pk.chemhelp;

public class Utils {
	public static boolean isBelowHoneyComb(){
		int sdk = android.os.Build.VERSION.SDK_INT;
		return (sdk < android.os.Build.VERSION_CODES.HONEYCOMB);
	}
}
