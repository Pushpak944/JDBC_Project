package jdbc_maven_eb9_4;
import java.util.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class UserCRUD {
    public Connection getconnection() throws Exception
    {
    	Driver driver=new Driver();
    	DriverManager.registerDriver(driver);
    	
    	FileInputStream fileInputStream=new FileInputStream("config.properties");
    	Properties properties=new Properties();
    	properties.load(fileInputStream);
    	Connection connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
    	return connection;
    }
    
    //Insert
    public void signupUser(User user) throws Exception
    {
    	Connection connection=getconnection();
    	PreparedStatement preparedStatement=connection.prepareStatement("insert into user2 (id,name,email,password,address) values(?,?,?,?,?)");
    	
    	preparedStatement.setInt(1, user.getId());
    	preparedStatement.setString(2, user.getUsername());
    	preparedStatement.setString(3, user.getEmail());
    	preparedStatement.setString(4, user.getPassword());
    	preparedStatement.setString(5, user.getAddress());
    	
    	preparedStatement.execute();
    	System.out.println("Imserted");
    	connection.close();
    	
    }
    //login
    public boolean loginuser(User user) throws Exception
    {
    	Connection connection = getconnection();
		String query = "select * from user2 where name= ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, user.getUsername());
		ResultSet resultSet = preparedStatement.executeQuery();
		String password = null;
		while (resultSet.next()) {
			password = resultSet.getString("password");

		}
		if (password.equals(user.getPassword())) {
			return true;
		}
		return false;
    }
   
    public void createPassword(User user) throws Exception {
		Connection connection = getconnection();
		String query = "update 	user2	set facebook_password=?,insta_password=?,snapchat_password=?,whatsapp_password=?,twitter_password=? where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(6, user.getId());
		preparedStatement.setString(1, user.getFacebook_pass());
		preparedStatement.setString(2, user.getInsta_pass());
		preparedStatement.setString(3,user.getPassword());
		preparedStatement.setString(4,user.getPassword());
		preparedStatement.setString(5,user.getTwitter_pass());
		
		preparedStatement.execute();
		connection.close();
		System.out.println(" All the Password Created Successfully");
	}
    
    //Create Password
    
    public void updatePassword(User user) throws Exception {
		Connection connection = getconnection();
		Scanner scanner=new Scanner(System.in);
		System.out.println(
				"Enter \n 1.Update Facebook Passswod \n 2.Update Insta Password \n 3.Update Twitter Password \n 4.Update Whatsapp Password \n 5.Update SnapChat Password ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1: {
			
				System.out.println("Enter id to Update Password");
				int id = scanner.nextInt();
				System.out.println("Enter New Password");
				String f1_pass = scanner.next();
				user.setFacebook_pass(f1_pass);
				user.setId(id);
				String query1 = "update user2 set facebook_password=? where id=?";
				PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setString(1, user.getFacebook_pass());
				preparedStatement1.setInt(2, user.getId());
				preparedStatement1.execute();
				connection.close();
				System.out.println("Updated Successfully");
			}

			break;
    

		
    case 2: {
		
			System.out.println("Enter id to Update Passwod");
			int id = scanner.nextInt();
			System.out.println("Enter New Password");
			String insta_pass = scanner.next();
			user.setInsta_pass(insta_pass);
			user.setId(id);
			String query1 = "update user2 set insta_password=? where id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			preparedStatement1.setString(1, user.getInsta_pass());
			preparedStatement1.setInt(2, user.getId());
			preparedStatement1.execute();
			connection.close();
			System.out.println("Updated Successfully");
		} 
    break;
    case 3: {
		
			System.out.println("Enter id to update password");
			int id = scanner.nextInt();
			System.out.println("Enter New Password");
			String tw_pass = scanner.next();
			user.setTwitter_pass(tw_pass);
			user.setId(id);
			String query1 = "update user2 set twitter_password=?, where id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			preparedStatement1.setString(1, user.getTwitter_pass());
			preparedStatement1.setInt(2, user.getId());
			preparedStatement1.execute();
			connection.close();
			System.out.println("Updated Successfully");
		}
		break;
		
    case 4: {
		
			System.out.println("Update Passwod");
			int id = scanner.nextInt();
			System.out.println("New Password");
			String wh_pass = scanner.next();
			user.setWhatsapp_pass(wh_pass);
			user.setId(id);
			String query1 = "update user2	set whatsapp_password=? where id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			preparedStatement1.setString(1, user.getPassword());
			preparedStatement1.setInt(2, user.getId());
			preparedStatement1.execute();
			connection.close();
			System.out.println("Updated Successfully");
		} 
		break;
		
    case 5: {
		
			System.out.println("Enter id to Update Passwod");
			int id = scanner.nextInt();
			System.out.println("Enter New Password");
			String sn_pass = scanner.next();
			user.setInsta_pass(sn_pass);
			user.setId(id);
			String query1 = "update user2	set snapchat_password=? where id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			preparedStatement1.setString(1, user.getPassword());
			preparedStatement1.setInt(2, user.getId());
			preparedStatement1.execute();
			connection.close();
			System.out.println("Updated Successfully");
		} 
		break;
	default:
		System.out.println("Invalid Choice");

		}
}
}
