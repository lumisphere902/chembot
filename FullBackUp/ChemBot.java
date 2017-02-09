
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/ChemBot")
public class ChemBot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChemBot() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageObj = request.getParameter("messageobj");

		String output = "nothing";
		System.out.println("query: " + request.getQueryString());
		System.out.println("url: " + request.getRequestURL());
		Enumeration<String> e = request.getAttributeNames();
		String params = "";
		while (e.hasMoreElements()) {
			params += e.nextElement();
		}
		// System.out.println("doesn't exist:
		// "+request.getParameterNames()==null);
		System.out.println("params: " + params);
		System.out.println("updated");
		response.setContentType("text/html");
		if (messageObj != null) {
			System.out.println(messageObj);
			String decoded = messageObj;
			String useful = returnUrl(decoded);
			// stored = "{\"type\":\"image\",\"originalUrl\":\"" + useful +
			// "\",\"previewUrl\":\"" + useful + "\"}";
			output = useful;
		} else {
			output = "no input";
		}
		output = ChemBot.getImgurContent("5075047008712e4", output);
		PrintWriter out = response.getWriter();
		System.out.println("output: " + output);
		out.print(output);
		out.flush();
		out.close();
	}

	public static String returnUrl(String str) {
		StringTokenizer st = new StringTokenizer(str);
		String typeS = st.nextToken();
		Number amount = new Number(st.nextToken());
		Chemical given = new Chemical(st.nextToken());
		String typeE = st.nextToken();
		Chemical target = new Chemical(st.nextToken());
		boolean isProduct = false;
		ArrayList<Chemical> reactants = new ArrayList<Chemical>();
		ArrayList<Chemical> products = new ArrayList<Chemical>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.equals("->")) {
				isProduct = true;
			} else if (!token.equals("+")) {
				if (isProduct) {
					products.add(new Chemical(token));
				} else {
					reactants.add(new Chemical(token));
				}
			}
		}
		Stoichiometry s = new Stoichiometry(new Equation(reactants, products));
	    String ans = s.evaluate(given, typeS, amount, target, typeE);
		String backSlashed = "";
		for(int i = 0;i<ans.length();i++){
			if(ans.charAt(i)=='\\'){
				backSlashed+='\\';
			}
			backSlashed+=ans.charAt(i);
		}
		
		return backSlashed;
	}
	
	public static String getImgurContent(String clientID, String img) {
		try {
			URL url;
			url = new URL("https://api.imgur.com/3/image");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			img = URLEncoder.encode(img, "UTF-8");
			System.out.println(img);
			String data = URLEncoder.encode("image", "UTF-8") + "=" + img;
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Client-ID " + clientID);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			conn.connect();
			StringBuilder stb = new StringBuilder();
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			System.out.println(conn);
			// Get the response
			if (conn.getResponseCode() > 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while (rd.ready()) {
					System.out.println(rd.readLine());
				}
				return "ERROR2";
			} else {
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					stb.append(line).append("\n");
				}
				wr.close();
				rd.close();

				return stb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
