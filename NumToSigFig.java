public static String numToString(double num, int sf) {
		String raw = "" + num;
		boolean decimalFound = false;
		int count = 0;
		for (int i = raw.charAt(0) == '0' ? 1 : 0; i < raw.length(); i++) {
			char cur = raw.charAt(i);
			if (cur == '.') {
				decimalFound = true;
			} else {
				count++;
				if (count > sf) {
					boolean roundUp = false;
					if (i < raw.length() - 1) {
						char next = raw.charAt(i + 1);
						if (next == '.') {
							if (i < raw.length() - 2 && raw.charAt(i + 2) >= '5')
								roundUp = true;
						} else {
							if (next >= '5')
								roundUp = true;
						}
					}
					String refined;
					if (roundUp) {
						refined = raw.substring(0, i - 1) + (char) (raw.charAt(i) + 1);
					} else {
						refined = raw.substring(0, i);
					}
					if (!decimalFound) {
						for (int j = i; raw.charAt(j) != '.'; j++) {
							refined += '0';
						}
					} else if (refined.charAt(refined.length() - 1) == '.') {
						refined = refined.substring(0, refined.length() - 1);
					}
					return refined;
				}
			}
		}
		return raw;
	}
