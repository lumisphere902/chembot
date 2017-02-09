private String toWolfram(String str) {
		StringTokenizer st = new StringTokenizer(str);
		String ans = "";
		while (st.hasMoreTokens()) {
			String temp = st.nextToken();
			temp.toLowerCase();
			char[] arr = temp.toCharArray();
			arr[0] = Character.toUpperCase(arr[0]);
			ans += new String(arr);
		}
		return ans;
	}
