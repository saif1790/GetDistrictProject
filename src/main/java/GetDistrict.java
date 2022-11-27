
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDistrict extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		/*
		 * System.out.println("getDistrict gets executed"); String UPCity[] = {
		 * "Ghazipur", "Varanasi", "Luckhnow", "Kanpur", "Noida", "Gaziabad", "Mau",
		 * "Jaunpur" }; String BiharCity[] = { "patna", "Shiwan", "Darbhanga", "Gaya",
		 * "Bhagalpur" };
		 */

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		String stateName = req.getParameter("state");
		String cityName = req.getParameter("city");

		System.out.println("=========="+stateName);

		CityDAO city = new CityDAO();

		if (stateName != null && cityName != null) {
			boolean insertCityName = false;
			try {
				insertCityName = city.insertCityName(stateName, cityName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (insertCityName == true) {
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);

				pw.println("<h3>City inserted successfully</h3>" + "<br>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);
				pw.println("<h3 BGCOLOR='RED'>" + "THERE ARE SOME ISSUE WHILE INSERTING CITY" + "</h3>" + "<br>");
			}
		} else {
			if (stateName == null) {
				pw.println("<h3 BGCOLOR='RED'>" + "FIELD CANT BE EMPTY" + "</h3>" + "<br>");
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);
				
			} else {
				int count=0;
				List<String> citiesByState = city.getCitiesByState(stateName);
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);

				for (String city1 : citiesByState) {
					pw.println(++count +" "+city1 + "<br>");
				}
			}

		}

	}

}
