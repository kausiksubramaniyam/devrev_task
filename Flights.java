

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Flights {
        private Integer flightNumber;
        private String flightModel;
        private String airlineName;
        private String fromDestination;
        private String toDestination;


        private LocalDateTime nextDeparture;
        private Integer seatCount = 60;

        private Integer ticketCost = 1000;


        private Map<Integer,Ticket> seatTicketMap = new HashMap<>();


        public void createFlight(Integer flightNumber, String flightModel,String airlineName, String fromDestination, String toDestination, LocalDateTime nextDeparture, Integer seatCount){
            this.flightNumber=flightNumber;
            this.flightModel=flightModel;
            this.airlineName=airlineName;
            this.fromDestination=fromDestination;
            this.toDestination=toDestination;
            this.nextDeparture=nextDeparture;
            if(seatCount!=null){
                this.seatCount=seatCount;
                for(int i=0;i<seatCount;i++){
                    this.seatTicketMap.put(i,null);
                }
            }
            else{
                for(int i=0;i<60;i++){
                    this.seatTicketMap.put(i,null);
                }
            }
            System.out.println("Flight Created and Added to List :" + flightNumber + "Carrier :" + airlineName);
        }

        public void showAllUnBookedSeats(){
            for(Integer i : this.seatTicketMap.keySet()){
                if(this.seatTicketMap.get(i)==null){
                    System.out.print(i + " , ");
                }
            }
        }

        public void BookTicket(Integer seatNumber, String userId){
            Ticket ticket = new Ticket();
            ticket.createTicketAutoNumber(this.flightNumber, userId, this.ticketCost, seatNumber);
            this.seatTicketMap.put(seatNumber,ticket);
            TicketsCRUD ticketCRUD = new TicketsCRUD();
            ticketCRUD.bookTicket(ticket);
            this.seatTicketMap.put(seatNumber,ticket);
        }

    public Integer getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightModel() {
        return this.flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getAirlineName() {
        return this.airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFromDestination() {
        return this.fromDestination;
    }

    public void setFromDestination(String fromDestination) {
        this.fromDestination = fromDestination;
    }

    public String getToDestination() {
        return this.toDestination;
    }

    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }

    public LocalDateTime getNextDeparture() {
        return this.nextDeparture;
    }

    public void setNextDeparture(LocalDateTime nextDeparture) {
        this.nextDeparture = nextDeparture;
    }

    public Integer getSeatCount() {
        return this.seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getTicketCost() {
        return this.ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }
    public Map<Integer, Ticket> getSeatTicketMap() {
        return this.seatTicketMap;
    }
}




