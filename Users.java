import java.util.Scanner;

public class Users {
    private String userId;
    private String userName;
    private Integer userAge;
    private Integer userType;
    private Integer aadharId;
    private String password;
    private String phoneNumber;


    public void createUser(String userId,String userName,Integer userType,Integer userAge,Integer aadharId, String password, String phoneNumber){
        this.userId= userId;
        this.userName=userName;
        this.userAge=userAge;
        this.userType=userType;
        this.aadharId=aadharId;
        System.out.println("Please Re-Enter Your Password :");
        Scanner sc= new Scanner(System.in);
        String reEnteredPW = sc.nextLine();
        if(reEnteredPW.equals(password)){
            this.password=password;
            System.out.println("Account Creation Successfull.");
            if(this.userType==0){
                System.out.println("Welcome ADMIN - " + userName);
            }
            else if(this.userType==1){
                System.out.println("Welcome CUSTOMER - " + userName);
            }
            else{
                System.out.println("INCORRECT USERTYPE");
                System.exit(0);
            }
        }
        else{
            System.out.println("PASSWORDS DOESNOT MATCH TRY AGAIN");
            System.exit(0);
        }
        this.phoneNumber=phoneNumber;
    }

}
