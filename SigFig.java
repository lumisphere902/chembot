public static int sigFig(String str) {
		boolean decimalFound = false;
		boolean prevNumExists = false;
		int count = 0;
		int zeroInARow = 0;
		int i = 0;
		for (; str.charAt(i) == '0' && i < str.length(); i++)
			;
		for (; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (cur == '.') {
				if (decimalFound) {
					// throw error here i guess?
				}
				count += zeroInARow;
				zeroInARow = 0;
				decimalFound = true;
				prevNumExists = false;
			} else if (cur == '0') {
				zeroInARow++;
			} else {
				count++;
				if (!decimalFound || prevNumExists) {
					count += zeroInARow;
				}
				prevNumExists = true;
				zeroInARow = 0;
			}
		}
		if (decimalFound) {
			count += zeroInARow;
		}
		return count;
	}
