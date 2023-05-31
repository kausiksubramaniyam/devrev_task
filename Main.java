import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private Map<Integer,Flights> allFlightsMap = new HashMap<>();
    private Map<String,Users> allUsersMap = new HashMap<>();

    public void Main(String args[]) throws SQLException {
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
                    System.out.println("=======WELCOME USER=======");
                    System.out.println("Please Select the Action You would Like to Perform : ");
                    System.out.println("1. Search Flights On Date");
                    System.out.println("2. Search Flights Between a Start Date and End Date");
                    System.out.println("3. Book Tickets");
                    System.out.println("4. My Bookings - View All Your Bookings");
                    System.out.println("5. Logout");
                    int userChoice = sc.nextInt();
                    sc.nextLine();
                    FlightsCRUD allFlights = new FlightsCRUD();
                    switch (userChoice){
                        case 1:
                            System.out.println("Enter Date to Search Flights :");
                            String departureDateTimeString = sc.nextLine();
                            LocalDateTime departureTimeToCheck = LocalDateTime.parse(departureDateTimeString);
                            allFlights.searchFlightsByDate(departureTimeToCheck);
                            break;

                        case 2:
                            System.out.println("Enter Start Date to Search Flights From:");
                            String startDateTimeString = sc.nextLine();
                            LocalDateTime startTimeToCheck = LocalDateTime.parse(startDateTimeString);
                            System.out.println("Enter End Date to Search Flights From:");
                            String endDateTimeString = sc.nextLine();
                            LocalDateTime endTimeToCheck = LocalDateTime.parse(startDateTimeString);
                            allFlights.searchFlightsBetweenStartAndEndDate(startTimeToCheck,endTimeToCheck);
                            break;

                        case 3:
                            System.out.println("Enter Flight Number to Book Tickets:");
                            int flightNumber = sc.nextInt();
                            sc.nextLine();
                            Flights flightObj = this.allFlightsMap.get(flightNumber);

                            flightObj.showAllUnBookedSeats();

                            System.out.println("Enter Seat Number to Book Ticket:");
                            int seatNumber = sc.nextInt();
                            sc.nextLine();

                            flightObj.BookTicket(seatNumber,userId);

                            System.out.println("Ticket Booked Successfully , You will get an Auto Generated Email to your User Id /Mail Id, with all teh ticket details:");
                            break;

                        case 4:
                            System.out.println(" ------------------ MY BOOKINGS ---------------------");
                            TicketsCRUD alltickets = new TicketsCRUD();
                            List<Ticket> allBookedTicketsInFlight = alltickets.getAllTicketsBookedByUserId(userId);
                            for(Ticket tckt : allBookedTicketsInFlight){
                                System.out.print("Ticket ID :" + tckt.getTicketId() + " -- ");
                                System.out.print("Flight Number:" + tckt.getFlightNumber() + " -- ");
                                System.out.print("Seat Number :" + tckt.getSeatNumber() + " -- ");
                                System.out.println(" ------------------------------------------------------------- ");
                            }
                            break;

                        case 5:
                            System.out.println("THANK YOU. LOGGING OUT ......");
                            System.exit(0);
                    }
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
                    System.out.println("Please Select the Action You would Like to Perform : ");
                    System.out.println("1. Add Flights");
                    System.out.println("2. Remove Flights");
                    System.out.println("3. View All Bookings Based on Flight Number");
                    System.out.println("4. View All Bookings Based on Departure Time");
                    int adminChoice = sc.nextInt();
                    sc.nextLine();
                    switch (adminChoice){
                        case 1:
                            System.out.println("Enter flight number: ");
                            int flightNumber = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter flight model: ");
                            String flightModel = sc.nextLine();
                            System.out.println("Enter airline name: ");
                            String airlineName = sc.nextLine();
                            System.out.println("Enter departure from: ");
                            String fromDestination = sc.nextLine();
                            System.out.println("Enter arrival to: ");
                            String toDestination = sc.nextLine();
                            System.out.println("Enter next departure date and time (YYYY-MM-DD HH:MM): ");
                            String departureDateTimeString = sc.nextLine();
                            LocalDateTime nextDeparture = LocalDateTime.parse(departureDateTimeString);
                            System.out.println("Enter seat count: ");
                            int seatCount = sc.nextInt();
                            sc.nextLine();

                            Flights flight = new Flights();
                            flight.createFlight(flightNumber,flightModel,airlineName,fromDestination,toDestination,nextDeparture,seatCount);
                            this.allFlightsMap.put(flightNumber,flight);
                            break;

                        case 2:
                            System.out.println("Enter Flight Number to Remove Flight: ");
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
                            System.out.println("Enter Flight Number to View Bookings :");
                            int flightNoToGetBookings = sc.nextInt();
                            sc.nextLine();
                            TicketsCRUD alltickets = new TicketsCRUD();
                            System.out.println("Next Departure of Flight :" + this.allFlightsMap.get(flightNoToGetBookings).getNextDeparture());
                            List<Ticket> allBookedTicketsInFlight = alltickets.getAllBookedTicketsByFlightId(flightNoToGetBookings);
                            for(Ticket tckt : allBookedTicketsInFlight){
                                System.out.print("Ticket ID :" + tckt.getTicketId() + " -- ");
                                System.out.print("Ticket Booked By (User ID) :" + tckt.getUserId() + " -- ");
                                System.out.print("Seat Number :" + tckt.getSeatNumber() + " -- ");
                                System.out.println(" ------------------------------------------------------------- ");
                            }
                            break;
                    }
                }
                break;
            case 3:
                System.out.println("************* SIGN UP *************");
                System.out.println("Enter user ID: ");
                String newUserId = sc.nextLine();
                System.out.println("Enter user name: ");
                String userName = sc.nextLine();
                System.out.println("Enter user age: ");
                int userAge = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter user type: ");
                int userType = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Aadhar ID: ");
                int aadharId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter password: ");
                String userPassword = sc.nextLine();
                System.out.println("Enter phone number: ");
                String phoneNumber = sc.nextLine();

                Users user = new Users();
                user.createUser(newUserId,userName,userType,userAge,aadharId,userPassword,phoneNumber);
                this.allUsersMap.put(newUserId,user);
        }
    }
}
