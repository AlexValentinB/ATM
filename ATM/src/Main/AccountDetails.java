package Main;

public class AccountDetails {
	// Here we declare some variables that a typical bank account will have

		private String type;
		
		public AccountDetails(String type, double balance) {
			super();
			this.type = type;
			this.balance = balance;
		}

		private double balance;

		// The following methods are a combination of getter/setter methods as well
		// as two special deposit() and withdraw() methods

		
		public void deposit(double dep) {
			balance += dep; // Take the Account object's balance and add to it the current deposit
		}

		public void withdraw(double wit) {
			balance -= wit; // Take the Account object's balance and subtract from it the current withdrawal
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

}
