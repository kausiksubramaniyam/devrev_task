

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


        private Map<Integer,Object> seatTicketMap = new HashMap<>();


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


        public void removeFlight(Integer flightNumber){


        }




    }

