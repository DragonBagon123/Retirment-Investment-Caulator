package retirement_caulator;
import java.util.Scanner;
public class retirement_caulator {
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Welcome to Retirement Investment Caulator: Answering the questions below to get a year by year table with a final summary");
		
		do {

			int age = readIntInRange(sc, "\nEnter your current age: ", 18, 100);			
			int retirementAge = readIntInRange(sc, "Enter the age you plan to retire: ", age+1, 100);
			double currentBalance = readDoubleInRange(sc, "Enter your current balance: ", 0, Integer.MAX_VALUE);
			double annualContribution = readDoubleInRange(sc, "Enter your Annual Contribution: ", 0, Integer.MAX_VALUE);
			double annualInterstRate = readDoubleInRange(sc, "Enter your APR(Annual Interest Rate): ", 0, 30);
			int compundingFrequency = readCompoundingChoice(sc, "Enter your compounding frequency, Type 1(Annually), 2(Monthly), 3 (Daily(365)): ");			
			double annualContributoionIncrease = readDoubleInRange(sc, "Enter your Annual Contribution increase: ", 0, 20);
			
			System.out.println();
			
			runSimulation(age, retirementAge, currentBalance,annualContribution,annualInterstRate,compundingFrequency, annualContributoionIncrease);

		}
		while(askRunAgain(sc));
	}

	public static boolean askRunAgain(Scanner sc) 
	{	
		Boolean runProgramAgain = null;
		String runAgain = null;
		do {
		    runAgain = getUserInput(sc, "Would you like to run the program again (Y/N): ").toUpperCase();

		    if(!runAgain.equals("Y") && !runAgain.equals("N"))
		        System.out.println("Please enter Y or N.");

		} while(!runAgain.equals("Y") && !runAgain.equals("N"));
		
		if(runAgain.equals("Y")) {
			
			runProgramAgain = true;
		}
		else {
			
			runProgramAgain = false;
			
		}
			
		return runProgramAgain;
	}
	
	public static void runSimulation(int age, int retirementAge, double currentBalance,double annualContribution, double annualIntrentsRate, int compundingFrequency, double annualContributoionIncrease) {
		
		String[] compoundOptionsWordForm = {"Annually", "Monthly", "Daily"};
		int[] compoundOptionsNumForm = {1,12,365};
		double balance = currentBalance;
		double totalContributions = 0;
		double totalInterest = 0;
		
		System.out.println("\nYour Info");
		for(int i = 0; i < 80; i++) {
		    System.out.print("-");
		}
		
		System.out.printf("Current Age: %d%n", age);
		System.out.printf("Retirement Age: %d%n", retirementAge);
		System.out.printf("Annual Rate: %.2f%%%n", annualIntrentsRate);
		System.out.printf("Compounding: %s%n", compoundOptionsWordForm[compundingFrequency-1]);
		System.out.printf("Annual Contribution (Year 1): $%.2f%n", annualContribution);
		System.out.printf("Annual Contribution Increase: %.2f%%%n", annualContributoionIncrease);

		System.out.println("Year by Year Projection");
		for(int i = 0; i < 80; i++) {
		    System.out.print("-");
		}
		
		System.out.println("\nAge | Start Balance | Contributions | Interest Earned | End Balance");
	
		for(int i = age+1; retirementAge >= i; i++) {
			
			double startBalance = balance;
			double r_period = (annualIntrentsRate/100)/compoundOptionsNumForm[compundingFrequency-1];
			double contribution = annualContribution/compoundOptionsNumForm[compundingFrequency-1];
			for(int j = 0; j < compoundOptionsNumForm[compundingFrequency-1]; j++ ) {
				
				balance += contribution;
				balance = balance * (1 + r_period);
			
			}
			
			double interestEarned = balance- startBalance-annualContribution;
			
			System.out.printf("%3d | $%12.2f | $%12.2f | $%12.2f | $%12.2f%n", i, startBalance, annualContribution, interestEarned, balance);
			annualContribution *= (1+ annualContributoionIncrease/100);
			totalContributions += annualContribution;
			totalInterest += interestEarned;
			
		}
		
		for(int i = 0; i < 80; i++) {
		    System.out.print("-");
		}
		System.out.println("");
		
		System.out.println("Summary");
		
		System.out.printf("Total Contributions: $%.2f%n", totalContributions);
		System.out.printf("Total Interest Earned: $%.2f%n", totalInterest);
		System.out.printf("Ending Balance at Age %d: $%.2f%n", retirementAge, balance);
	
		for(int i = 0; i < 80; i++) {
		    System.out.print("-");
		}
		System.out.println("");
		
	}
	
	public static int readCompoundingChoice(Scanner sc, String prompt) {
		
		Integer value = null;
		
		do {
			try {
				String userInput = getUserInput(sc, prompt);
				value = Integer.parseInt(userInput);
				if(value < 1 || value > 3) {
					value = null;
					throw new OutOfRangeException( 
							"Error: Value is out of range.");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid Input: Please enter a numeric value.");
			}
			catch(OutOfRangeException e) {
				value = null;
				System.out.println(e.getMessage());
			}
			
		
			}while(value == null);	
			
			return value;
	}
	
	public static String getUserInput(Scanner sc, String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}
	
	public static double readDoubleInRange (Scanner sc, String prompt,double min,double max) {
		
		Double value = null;
		
	do {
		try {
			String userInput = getUserInput(sc, prompt);
			value = Double.parseDouble(userInput);
			if(value < min || value > max) {
				value = null;
				throw new OutOfRangeException( 
						"Error: Value is out of range.");
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid Input: Please enter a numeric value.");
		}
		catch(OutOfRangeException e) {
			value = null;
			System.out.println(e.getMessage());
		}
		
		}while(value == null);	
	
		return value;
	
	}

	public static int readIntInRange (Scanner sc, String prompt,int min,int max) {
			
		Integer value = null;
		
	do {
		try {
			String userInput = getUserInput(sc, prompt);
			value = Integer.parseInt(userInput);
			if(value < min || value > max) {
				value = null;
				throw new OutOfRangeException( 
						"Error: Value is out of range.");
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid Input: Please enter a numeric value.");
		}
		catch(OutOfRangeException e) {
			value = null;
			System.out.println(e.getMessage());
		}
		
	
		}while(value == null);	
	
		return value;
	
	}

	static class OutOfRangeException extends Exception {
		private static final long serialVersionUID = 1L;
		public OutOfRangeException() {
			super();
		}
		public OutOfRangeException(String message) {
			super(message);
		}
		public OutOfRangeException(Throwable cause) {
			super(cause);
		}
		public OutOfRangeException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}