package customerDB;

import java.sql.*;
import java.util.Scanner;

public class CustMain{  
	public static void main(String [] args){  
		try{  


			String Response, query, FName, LName, Street, City, State, Duty, FullName, Title, Email, Company;
			int ZIP, choise;

			Scanner input = new Scanner(System.in); 
			Scanner intScan = new Scanner (System.in);
			System.out.println("What Would you like to do? add, update, delete or search a record.") ;
			Response = input.nextLine(); 


			Class.forName("com.mysql.jdbc.Driver");  
			//System.out.println("Test connection");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Customer","root","password");  
			PreparedStatement preparedStmt = null;

			switch(Response){

			case "add" :

				System.out.println("Enter Title (Mr, Mrs...): ");
				Title = input.nextLine();
				System.out.println("Enter Customer Name: ");
				FName = input.nextLine();
				System.out.println("Enter Last Name: ");
				LName = input.nextLine();
				System.out.println("Enter Street Name: ");
				Street = input.nextLine();
				System.out.println("Enter City Name: ");
				City = input.nextLine();
				System.out.println("Enter State Name: ");
				State = input.nextLine();
				System.out.println("Enter ZIP Code: ");
				ZIP = intScan.nextInt();
				System.out.println("Enter Email Address: ");
				Email = input.nextLine();
				System.out.println("Enter Position: ");
				Duty = input.nextLine();
				System.out.println("Enter Company Name: ");
				Company = input.nextLine();

				FullName = new StringBuilder().append(FName).append(" ").append(LName).toString();

				query = ("INSERT into `Customer`.`CustInfo`(`FullName`, `Title`,`FirstName`, `LastName`, `StreetAddress`, `City`, `State`, `ZipCode`, `EmailAddress`, `Position`, `Company`) "
						+ "VALUES (?,?,?,?,?, ?, ?,?,?,?,?)");
				preparedStmt = con.prepareStatement(query);


				preparedStmt.setString(1, FullName);
				preparedStmt.setString(2, Title);
				preparedStmt.setString(3, FName);
				preparedStmt.setString(4, LName);
				preparedStmt.setString(5, Street);
				preparedStmt.setString(6, City);
				preparedStmt.setString(7, State);
				preparedStmt.setInt(8, ZIP);
				preparedStmt.setString(9, Email);
				preparedStmt.setString(10, Duty);
				preparedStmt.setString(11, Company);
				preparedStmt.executeUpdate();
				System.out.println("Inserted ");
				break;

			case "delete":  

				System.out.println("What is your Name?");
				String name = input.nextLine(); 

				String Deletequery = (" Delete FROM CustInfo WHERE FirstName = ?");
				preparedStmt= con.prepareStatement(Deletequery); 

				preparedStmt.setString(1, name);

				preparedStmt.executeUpdate();
				System.out.println("The Customer has been removed!"); 

				break;

			case "search": 
				do{
					System.out.println("Enter Employee First Name:  ");
					String SearchName = input.nextLine();

					String Searchquery = ("SELECT * FROM CustInfo WHERE FirstName = '"+SearchName+"'"); 

					Statement stmt=con.createStatement();  // to provide methods to execute different SQL queries

					ResultSet rs=stmt.executeQuery(Searchquery);  //cursor pointer to the row of the table

					//executeQuery: to execute the select query
					System.out.println();
					while(rs.next()){  // from the current pointer till the end of the table
						System.out.println(rs.getString(2)+"\t"+rs.getString(1) + "\n"	
								+rs.getString(5) + "\t" + rs.getString(6) +"\n" 
								+ rs.getString(7) +rs.getInt(8) + "\n"
								+ rs.getString(9) + "\n"
								+ rs.getString(10) + "  "+ rs.getString(11)); } 
					System.out.println();
					System.out.println("Press 1 to continue: ");
					choise = input.nextInt();
					input.nextLine();
					if(choise==2){continue;}
				}while(choise==1);

			case "update" :
				System.out.println("Enter Your Name to ");
				String EMPID = input.nextLine(); 

				System.out.println("What is the your new name?");
				String update_cname = input.nextLine(); 

				query = ("UPDATE CustInfo SET FirstName  = ? WHERE FirstName = ? ");
				preparedStmt= con.prepareStatement(query); 
				preparedStmt.setString(1, update_cname); 
				preparedStmt.setString(2, EMPID); 

				preparedStmt.executeUpdate();
				System.out.println("Your New name  " + update_cname + " has updated");
				break;	

			 default: 
				 System.out.println("ERROR INPUT. If you want to do again, press 3");
				 int numCheck = intScan.nextInt();
				 if(numCheck==3){
				 main(args);}
			}
			intScan.close();
			input.close();
			con.close(); 
		}catch(Exception e){ System.out.println(e);}  

	}  
}  