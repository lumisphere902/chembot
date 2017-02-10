
import java.io.IOException;
import java.io.PrintWriter;
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
		ArrayList<ChemicalCompoundable> reactants = new ArrayList<ChemicalCompoundable>();
		ArrayList<ChemicalCompoundable> products = new ArrayList<ChemicalCompoundable>();
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
