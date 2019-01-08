package org.cch.demo;

public class DoubleByteCheckDemo {

	public static void main(String[] args) {
		String singleStr = "hello,chen.";
		String doubleStr = "0123456789abcde!@#$%^& 陈";
		singleOrDouble(singleStr);
		singleOrDouble(doubleStr);
		System.out.println("===A gorgeous partition line==");
		System.out.println("");

		System.out.println("singleStr: " + contianDoubleByteChar(singleStr));
		System.out.println("doubleStr: " + contianDoubleByteChar(doubleStr));
		System.out.println("===A gorgeous partition line==");
		System.out.println("");
		doubleStr = "０１２３４５６７８９ａｂｃｄｅ！＠＃＄％＾＆　陈";
		regularExpression(doubleStr);
		System.out.println("===A gorgeous partition line==");
		System.out.println("");

		doubleStr = "0123456789abcde!@#$%^_-& 陈０１２３４５６７８９ａｂｃｄｅ！＠＃＄％＾＿－＆　春";
		regularExpression(doubleStr);
		System.out.println("===A gorgeous partition line==");
		System.out.println("");

		removeChineseCharacter("we are 我们ｗｏｍｅｎ在这里");
		System.out.println("===A gorgeous partition line==");
		System.out.println("");

		checkOutChineseCharacter("we are 我们ｗｏｍｅｎ在这里");
		System.out.println("===A gorgeous partition line==");
		System.out.println("");
		
		unicodeCheckCharacter(doubleStr);
		System.out.println("===A gorgeous partition line==");
		System.out.println("");
	}

	public static void singleOrDouble(String text) {
		if (text.length() < text.getBytes().length) {
			System.out.println("some char are double btye: " + text);
		} else {
			System.out.println("all char are single btye: " + text);
		}
	}

	public static boolean contianDoubleByteChar(String str) {
		byte[] Char;
		for (int i = 0; i < str.length(); i++) {
			try {
				Char = (new Character(str.charAt(i)).toString()).getBytes();
			} catch (Exception e) {
				return false;
			}
			if (Char.length != 1) {
				System.out.println(new String(Char));
				return true;
			}
		}
		return false;
	}

	// regular Expression: [^\\x00-\\xff]
	public static void regularExpression(String text) {
		// Pure half corner, including numbers, letters,
		// special symbols, spaces, Chinese characters
		// String text = "0123456789abcde!@#$%^& 陈";
		char[] charArys = text.toCharArray();
		for (int i = 0; i < charArys.length; i++) {
			String temp = String.valueOf(charArys[i]);
			// character of double byte
			if (temp.matches("[^\\x00-\\xff]")) {
				System.out.println("doulble char: " + temp);
			}
			// character of single byte
			else {
				System.out.println("single char: " + temp);
			}
		}
	}

	// Remove Chinese characters from strings
	public static void removeChineseCharacter(String text) {
		System.out.println(text.replaceAll("[\u4e00-\u9fa5]", ""));
	}

	// Extracting Chinese Characters from Strings
	public static void checkOutChineseCharacter(String text) {
		char[] charArys = text.toCharArray();
		String chineseStr = "";
		for (int i = 0; i < charArys.length; i++) {
			String temp = String.valueOf(charArys[i]);
			// is chinese
			if (temp.matches("[\u4e00-\u9fa5]")) {
				chineseStr += temp;
			}
		}
		System.out.println(chineseStr);
	}

	public static void unicodeCheckCharacter(String text) {
		// Pure half corner, including numbers, letters, 
		// special symbols, spaces, Chinese characters
		//String text = "0123456789abcde!@#$%^& 陈";

		// remove chinese
		text = text.replaceAll("[\u4e00-\u9fa5]", "");

		char[] charArys = text.toCharArray();
		for (int i = 0; i < charArys.length; i++) {
			int charValue = (int) charArys[i];
			// double byte character
			if (charValue >= 65281 && charValue <= 65374 || charValue == 12288) {
				System.out.println("double byte " + (char) charValue);
			}
			// single byte character
			else if (charValue >= 33 && charValue <= 126 || charValue == 32) {
				System.out.println("single byte " + (char) charValue);
			}
		}
	}

}
