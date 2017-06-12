package customerDB;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.println("Enter Customer Name: ");
		String FName = input.nextLine();
		
		System.out.println("Enter Last Name: ");
		String LName = input.nextLine();
		
		StringBuilder FullName = new StringBuilder().append(FName).append(" ").append(LName);
		System.out.println(FullName);
		
		System.out.println("Enter NUmber  ");
		int x= input.nextInt();
		System.out.println("Enter NUmber  ");
		int y= input.nextInt();
		int total = x+y;
		
		input.close();
	}
	

}
