
import java.util.Scanner;

class BankAccount{

    String name;
    String username;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the Name");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username");
        this.username = sc.nextLine();
        System.out.println("\nEnter your Password");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Login to your Bank Account");
    }

    public boolean login(){
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while(!isLogin){
            System.out.println("\nEnter your Username");
            String inputUsername = sc.nextLine();
            if(inputUsername.equals(username)){
                while(!isLogin){
                    System.out.println("\nEnter your Password");
                    String inputPassword = sc.nextLine();
                    if(inputPassword.equals(password)){
                        System.out.println("\nLogin Successful");
                        isLogin = true;
                    }
                    else{
                        System.out.println("\nIncorrect Password");
                    }
                }
            }
            else {
                System.out.println("\nUsername not Found");
            }
        }
        return isLogin;
    }

    public void withdraw(){
        System.out.println("\nEnter the Amount to Withdraw");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if(balance >= amount){
                transactions++;
                balance -= amount;
                System.out.println("\n Withdraw Successful");
                String str = amount + "Rs Withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            }else{
                System.out.println("Insufficient Balance.");
            }
        } catch(Exception e){
            System.out.println("An error occurred.");
        }
    }

    public void deposit(){
        System.out.println("\nEnter the Amount to Deposit");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if(amount <= 10000){
                transactions++;
                balance += amount;
                System.out.println("\nDeposit Successful");
                String str = amount + "Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            }else{
                System.out.println("\nSorry. The limit is 10000");
            }
        } catch(Exception e){
            System.out.println("An error occurred.");
        }
    }

    public void transfer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Recipient's Name:");
        String recipient = sc.nextLine();
        System.out.println("\nEnter Amount to transfer: ");
        float amount = sc.nextFloat();
        try {
            if(balance >= amount){
                if(amount <= 50000f){
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully Transferred to " + recipient);
                    String str = amount + "Rs transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                }else{
                    System.out.println("\nSorry. The limit is 50000");
                }
            }else{
                System.out.println("\nInsufficient Balance");
            }
        } catch(Exception e){
            System.out.println("An error occurred.");
        }
    }

    public void checkBalance(){
        System.out.println("\n" + balance + "Rs" );
    }

    public void transHistory(){
        if(transactions == 0){
            System.out.println("No Transaction Happened ");
        }else{
            System.out.println("\n" + transactionHistory);
        }
    }
}


public class ATMInterface {

    public static int takeInteger(int limit) {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (flag && (input > limit || input < 1)) {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value.");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n ********** Welcome to the ATM **********");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option");
        int choose = takeInteger(2);

        if (choose == 1) {
            BankAccount account = new BankAccount();
            account.register();
            while (true) {
                System.out.println("1.Login \n2.Exit");
                System.out.println("Enter your choice");
                int choice = takeInteger(2);
                if (account.login()) {
                    System.out.println("\n********** Welcome to the ATM **********");
                    boolean isFinished = false;
                    while (!isFinished) {
                        System.out.println(
                                "\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                        System.out.println("Enter your choice");
                        int c = takeInteger(6);
                        switch (c) {
                            case 1:
                                account.withdraw();
                                break;
                            case 2:
                                account.deposit();
                                break;
                            case 3:
                                account.transfer();
                                break;
                            case 4:
                                account.checkBalance();
                                break;
                            case 5:
                                account.transHistory();
                                break;
                            case 6:
                                isFinished = true;
                                break;
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}