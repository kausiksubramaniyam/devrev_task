public class Ticket {
    private Integer ticketId;
    private Integer flightNumber;
    private String userId;
    private Integer ticketCost;

    private Integer seatNumber;

    public void createTicket(int ticketId, int flightNumber, String userId, int ticketCost, int seatNumber) {
        this.ticketId=ticketId;
        this.flightNumber=flightNumber;
        this.userId=userId;
        this.ticketCost=ticketCost;
        this.seatNumber=seatNumber;
    }

    public void createTicketAutoNumber(int flightNumber, String userId, int ticketCost, int seatNumber) {
        this.flightNumber=flightNumber;
        this.userId=userId;
        this.ticketCost=ticketCost;
        this.seatNumber=seatNumber;
    }


    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTicketCost() {
        return this.ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    public Integer getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
}
