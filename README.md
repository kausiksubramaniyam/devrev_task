# FLIGHT TICKETING SYSTEM - BACKEND TASK
Task for devrev - Backend - Flight Ticketing System

# Flights.java 
Class for Flights containing Flight Details and a Map for seat Number - Vacancy details
IMPORTANT METHODS :
1. showAllUnBookedSeats() - To display all vacant seats available to User 
2. BookTicket(Integer seatNumber, String userId) - To Book a Ticket and update Seat - Ticket Map

# Ticket.java 
Class for Ticket Details containing Flight Number - Ticket Number - User Id mapping and other ticket related details

# Users.java 
Class for Storing User Details - Phone Number, bookings, etc

# DBConnector.java 
To connect to Local MySQL Database

# LoginModule.java 
For Authenticating users -  login() method - returns an Integer stating whether the logging in user is Admin / Customer

# FlightsCRUD.java 
For All CRUD operations wrt Flights
FUNCTIONS :
1. addFlight() - To Add Flights // Only Accessible by Admin
2. getFlightById(Integer flightNumber) - To Get flight Details from Flight Number // Accessible by both Admin & User
3. getAllFlights() - To get All the flight details as List<FLights>
4. searchFlightsByDate(LocalDateTime searchdate) - To search and Display all the flights on a given date // Only Accessible by User
5. searchFlightsBetweenStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) - To search and Display all the flights between 2 given dates (start and end date) // Only Accessible by User
6. createFlightFromResultSet(ResultSet resultSet) - To create a flight from a prepared ResultSet
  
# TicketsCRUD.java
  
