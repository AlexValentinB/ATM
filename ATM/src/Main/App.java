package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat; // Helps with formatting doubles as currency
import java.util.*;

public class App {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int savingbalance = 0;
		int checkbalance = 0;
		String code = null;
		List<String> listcred = new ArrayList<String>(); 
		File credentialFile = new File("../test.txt");
		File balanceFile = new File("../balance.txt");
		Scanner scFile = new Scanner(credentialFile);
		
		 while(scFile.hasNextLine()){
			 
			 listcred.add(scFile.nextLine());

	        }      
		scFile.close();
		String acc_credentials = null;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(); // Creates the formatter object for currency
		Scanner sc = new Scanner(System.in); // Creates the sc object to read user input
		
		boolean session = true; // This variable will break the (while) loop when false
		while (session) {
			System.out.print("\nEnter Family Name:");
			acc_credentials += sc.next();
			acc_credentials += ";";
			System.out.print("\nEnter First Name:");
			acc_credentials += sc.next();
			acc_credentials += ";";
			System.out.print("\nEnter Pin:");
			acc_credentials += sc.next();
			acc_credentials += ";";
			System.out.print("\nEnter Account Code:");
			acc_credentials += sc.next();
			code = sc.next();
			
			for(String cred:listcred) {
				if(acc_credentials.contentEquals(cred)) {
					session=true;
					break;
				}
				else {
					session=false;
				}
			}
			
			
		}		
		

		while (session) {
			Scanner scFile2 = new Scanner(balanceFile);
			while(scFile2.hasNextLine()){
				 
				 String temp = scFile2.nextLine();
				 String[] tempSplit = temp.split(";");

			      if(tempSplit[1].contentEquals(code)) {
			    	  savingbalance= Integer.parseInt(tempSplit[2]);
			    	  checkbalance = Integer.parseInt(tempSplit[3]);
			      }

		        }
			AccountDetails savings = new AccountDetails("Savings",savingbalance);
			AccountDetails checking = new AccountDetails("Checks",checkbalance);
			// Present the user with a menu of 5 options
			scFile2.close();
			System.out.print("\nATM Menu: \n \n"
							 + "1. Deposit Money \n"
							 + "2. Withdraw Money \n"
							 + "3. Transfer Funds \n"
							 + "4. Check Account Balance\n"
							 + "5. End Session\n \n"
							 + "Enter selection: ");
	
			int selection = sc.nextInt(); // assign the user's input to the selection variable
	
			// This switch block will handle one of five selections and deal with them appropriately
	
			switch (selection) {
	
				// case 1 handles the depositing of money
	
				case 1:
					System.out.print("Enter (1) for Savings or (2) for Checking: ");
					int depAccount = sc.nextInt(); // Keeps track of which account to deposit money to
	
					if (depAccount == 1) {
	
						System.out.println("\nYour current Savings balance is: " + formatter.format(savings.getBalance()) + "\n");
	
						System.out.println("How much money would you like to deposit?");
						double deposit = sc.nextDouble();
	
						savings.deposit(deposit);
	
						System.out.println("\nYour Savings balance is now: " + formatter.format(savings.getBalance()) + "\n");
						
	
					}
	
					else if (depAccount == 2) {
						
						System.out.println("\nYour current Checking balance is: " + formatter.format(checking.getBalance()) + "\n");
	
						System.out.println("How much money would you like to deposit?");
						double deposit = sc.nextDouble();
	
						checking.deposit(deposit);
	
						System.out.println("\nChecking balance is now: " + formatter.format(checking.getBalance()) + "\n");
	
					}
	
					break;
	
				
	
				// case 2 handles the withdrawal of money	
	
				case 2:
					System.out.print("\nEnter (1) for Savings or (2) for Checking: ");
					int witAccount = sc.nextInt(); // Keeps track of which account to withdraw from
	
					if (witAccount == 1) {
	
						System.out.println("\nYour current Savings balance is: " + formatter.format(savings.getBalance()) + "\n");
	
						System.out.println("How much money would you like to withdraw?");
						double withdraw = sc.nextDouble();
	
						savings.withdraw(withdraw);
	
						System.out.println("\nYour Savings balance is now: " + formatter.format(savings.getBalance()) + "\n");
						
	
					}
	
					else if (witAccount == 2) {
						
						System.out.println("\nYour current Checking balance is: " + formatter.format(checking.getBalance()) + "\n");
	
						System.out.println("How much money would you like to withdraw?");
						double withdraw = sc.nextDouble();
	
						checking.withdraw(withdraw);
	
						System.out.println("\nYour Checking balance is now: " + formatter.format(checking.getBalance()) + "\n");
	
					}
	
					break;
	
				// case 3 handles the transfer of funds	
	
				case 3:
					System.out.print("\nFrom which account do you wish to transfer funds from?, (1) for Savings or (2) for Checking: ");
					int tranAccount = sc.nextInt();
	
					if (tranAccount == 1) {
	
						System.out.println("\nYour current Savings balance is: " + formatter.format(savings.getBalance()) + "\n");
	
						System.out.print("How much money do you wish to transfer from Savings to Checking?: ");
						double tranAmount = sc.nextDouble();
	
						savings.withdraw(tranAmount);
						checking.deposit(tranAmount);
	
						System.out.println("\nYou successfully transferred " + formatter.format(tranAmount) + " from Savings to Checking");
	
						System.out.println("\nChecking Balance: " + formatter.format(checking.getBalance()));
						System.out.println("Savings Balance: " + formatter.format(savings.getBalance()) + "\n");
	
					}
	
					else if (tranAccount == 2) {
	
						System.out.println("\nYour current Checking balance is: " + formatter.format(checking.getBalance()) + "\n");
	
						System.out.print("How much money do you wish to transfer from Checking to Saving?: ");
						double tranAmount = sc.nextDouble();
	
						checking.withdraw(tranAmount);
						savings.deposit(tranAmount);
	
						System.out.println("\nYou successfully transferred " + formatter.format(tranAmount) + " from Checking to Savings");
	
						System.out.println("\nChecking Balance: " + formatter.format(checking.getBalance()));
						System.out.println("Savings Balance: " + formatter.format(savings.getBalance()) + "\n");
						
					}
	
					break;
					
				// case 4 simply outputs the balances of both Checking and Savings accounts	
				
				case 4:
					System.out.println("\nChecking Balance: " + formatter.format(checking.getBalance()));
					System.out.println("Savings Balance: " + formatter.format(savings.getBalance()) + "\n");
					
					break;
	
				// case 5 breaks out of the (while) loop when the user is finished using the ATM
	
				case 5:
					session = false;
					
					break;
	
			}				 	
		

		}
		sc.close();
	}
}