import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

	   List<String> citiesName = new ArrayList<>();
	 
	public List<String> getCitiesByState(String stateName) {

		 

		String queryString = stateName + "_CITYNAME";
		try {
			// get Connection

			Connection con = DBConnection.createConnection();
			 
			//String query=	"SELECT DISTINCT * FROM"+" "+ queryString +" "+ "LIMIT 5";
		 
			
			String query = "select * from" + " " + queryString+ " "+"order by city_name asc";
			

			System.out.println(query);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int cityId = rs.getInt(1);
				String cityName = rs.getString(2);
				
				citiesName.add(cityName);
				
				System.out.println("SID :" + cityId);
				System.out.println("City Name :" + cityName);
			  System.out.println("============================");

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return citiesName;

	}
	
	public boolean insertCityName(String stateName,String cityName) 
	{
		Boolean status =false;
		
		String queryString = stateName+"_CITYNAME";
		
		try {
			// get Connection

			Connection con = DBConnection.createConnection();
			
			//insert into BIHAR_cityname(id,city_name) values (2,'GAYA');

			String query = "insert into" + " " + queryString+"(city_name)"+" "+ "VALUES" + "(?)";

			System.out.println(query);

			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1,cityName);
			psmt.executeUpdate();
        
			status=true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
		
	}

}
