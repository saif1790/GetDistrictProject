import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

	static Connection con;

	public static Connection createConnection() {

		try {

			//used because when this project moved to another machine/server then location of project may be change
			//String projectPath = System.getProperty("user.dir");
			//System.out.println(projectPath);
			//D:\Study\OnceAgain2020\project2022\GetDistrict\src\main\java\connection.properties
			FileInputStream fis = new FileInputStream("D://study//OnceAgain2020//project2022//GetDistrict//src//main//java//connection.properties");
			
			Properties prop = new Properties();
			
			prop.load(fis);

			String getDriver = prop.getProperty("driver");

			String getURL = prop.getProperty("url");

			String getUser = prop.getProperty("user");

			String getpassword = prop.getProperty("password");

			// load driver
			Class.forName(getDriver);

			// cretae connection
			String url = getURL;
			System.out.println(url);
			String user = getUser;
			
			String password = getpassword;

			con = DriverManager.getConnection(url, user, password);

			if (con.isClosed())
			{
				
				System.out.println("Connection is closed");
			} else {
				System.out.println("Connection established........");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

}
