public static int findDig(String str) {
		str = additionRefine(str);
		boolean whole = str.charAt(str.length()-1)=='.';
		for(int i=str.length()-1-(whole?1:0);i>=0;i--){
			char cur = str.charAt(i);
			if(whole){
				if(cur!='0'){
					return str.length()-2-i;
				}
			}else{
				if(cur=='.'){
					return i-str.length()+1;
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	public static String additionRefine(String str) {
		String ans = "";
		int i = 0;
		boolean foundDecimal = false;
		for (; i < str.length() && str.charAt(i) == '0'; i++)
			;
		for (; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (cur == '.') {
				foundDecimal = true;
			}
			ans += cur;
			if (!foundDecimal) {
				ans += ".";
			}
		}
		return ans;
	}
