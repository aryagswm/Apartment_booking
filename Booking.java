import java.io.*;
import java.util.*;
class Booking extends Balancer{
        public void bookApartment(String apartmentType,Date start,Date end){
        float cost=Float.MAX_VALUE;
        int infoIndex=-1,apartmentIndex=-1;
        for(int it=0;it<Balancer.i;it++){
            
            if(Balancer.info[it].type.equals(apartmentType)){
                String tempId=Balancer.info[it].location+","+Balancer.info[it].type;
               
                for(int k=0;k<Balancer.n;k++){
                    if(tempId.equals(Balancer.apartments[k].id)){
                            
                        boolean bookingCheck=true;
                        for (Map.Entry<Date, Date> entry : Balancer.apartments[k].bookings.entrySet()) {
                            Date startDate = entry.getKey();
                            Date endDate = entry.getValue();
                            if(start.compareTo(startDate)>=0 && end.compareTo(endDate)<=0){
                                bookingCheck=false;
                                break;
                            }
                            
                        }
                        if(bookingCheck && Balancer.info[it].price<cost){
                            cost=Balancer.info[it].price;
                            infoIndex=it;
                            apartmentIndex=k;

                        }
                       
                        
                    }
                }
            }
        }

        //save the booking
        if(infoIndex!=-1 && apartmentIndex!=-1){
            Balancer.apartments[apartmentIndex].bookings.put(start, end);
            System.out.println(Balancer.apartments[apartmentIndex].name+" from "+Balancer.info[infoIndex].location+" has been booked");

        }else{
            System.out.println("No apartments available");
        }
}
}
