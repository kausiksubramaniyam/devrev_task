import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private Map<Integer,Flights> allFlightsMap = new HashMap<>();
    private Map<String,Users> allUsersMap = new HashMap<>();

    public void Main(String args[]){
        Scanner sc =  new Scanner(System.in);
        System.out.println("----------------------FLIGHT TICKETING SYSTEM--------------------------");
        System.out.println("Enter 1 To Login as USER");
        System.out.println("Enter 2 To Login as ADMIN");
        System.out.println("Enter 3 to SIGN UP if you are a NEW USER");
        int loginChoice = sc.nextInt();
        sc.nextLine();
        LoginModule userlogin = new LoginModule();
        switch (loginChoice){
            case 1:
                System.out.println("************* USER LOGIN *************");
                System.out.println("Enter User ID / Email ID : ");
                String userId = sc.nextLine();
                System.out.println("Enter Your Password :");
                String password = sc.nextLine();
                if(userlogin.login(userId,password)==1){

                }
                break;
            case 2:
                System.out.println("************* ADMIN LOGIN *************");
                System.out.println("Enter Admin Mail Id : ");
                String adminId = sc.nextLine();
                System.out.println("Enter Your Secure Admin PassWord :");
                String adminPassword = sc.nextLine();
                if(userlogin.login(adminId,adminPassword)==0){
                    System.out.println("=======WELCOME ADMIN=======");
                    System.out.print("Please Select the Action You would Like to Perform : ");
                    System.out.print("1. Add Flights");
                    System.out.print("2. Remove Flights");
                    System.out.print("3. View All Bookings Based on Flight Number");
                    System.out.print("4. View All Bookings Based on Departure Time");
                    int adminChoice = sc.nextInt();
                    sc.nextLine();
                    switch (adminChoice){
                        case 1:
                            System.out.print("Enter flight number: ");
                            int flightNumber = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter flight model: ");
                            String flightModel = sc.nextLine();
                            System.out.print("Enter airline name: ");
                            String airlineName = sc.nextLine();
                            System.out.print("Enter departure from: ");
                            String fromDestination = sc.nextLine();
                            System.out.print("Enter arrival to: ");
                            String toDestination = sc.nextLine();
                            System.out.print("Enter next departure date and time (YYYY-MM-DD HH:MM): ");
                            String departureDateTimeString = sc.nextLine();
                            LocalDateTime nextDeparture = LocalDateTime.parse(departureDateTimeString);
                            System.out.print("Enter seat count: ");
                            int seatCount = sc.nextInt();
                            sc.nextLine();

                            Flights flight = new Flights();
                            flight.createFlight(flightNumber,flightModel,airlineName,fromDestination,toDestination,nextDeparture,seatCount);
                            this.allFlightsMap.put(flightNumber,flight);
                            break;

                        case 2:
                            System.out.print("Enter Flight Number to Remove Flight: ");
                            int flightNum = sc.nextInt();
                            sc.nextLine();
                            if(this.allFlightsMap.containsKey(flightNum)){
                                this.allFlightsMap.remove(flightNum);
                                System.out.println("Flight "+ flightNum + " Removed Successfully");
                            }
                            else{
                                System.out.println("Flight Does NOT Exists, Enter the correct Flight Number");
                            }
                            break;

                        case 3:
                            System.out.print("Enter Flight Number to View Bookings :");
                            int flightNoForBookings = sc.nextInt();
                            sc.nextLine();
                            break;

                    }

                }
                break;
            case 3:
                System.out.println("************* SIGN UP *************");
                System.out.print("Enter user ID: ");
                String newUserId = sc.nextLine();
                System.out.print("Enter user name: ");
                String userName = sc.nextLine();
                System.out.print("Enter user age: ");
                int userAge = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter user type: ");
                int userType = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Aadhar ID: ");
                int aadharId = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter password: ");
                String userPassword = sc.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = sc.nextLine();

                Users user = new Users();
                user.createUser(newUserId,userName,userType,userAge,aadharId,userPassword,phoneNumber);
                this.allUsersMap.put(newUserId,user);
        }
    }
}
