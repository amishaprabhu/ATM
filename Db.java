package src.Atm1;
import java.sql.*;
import java.sql.DriverManager;
public class Db
{
	public static Connection dbconnect() 
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm?useSSL=false&allowPublicKeyRetrieval=true", "root", "Gayatri17#");
			
			return con;               
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		                     
	}

}
