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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return this.userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAadharId() {
        return this.aadharId;
    }

    public void setAadharId(Integer aadharId) {
        this.aadharId = aadharId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
