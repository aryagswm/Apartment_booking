import java.io.*;
import java.util.*;
class Inventory extends Balancer{
        public void addBranch(String loc){
        for(int k=0;k<Balancer.i;k++){
            if(Balancer.info[k].location.equals(loc)){
                System.out.println("Location already exists ,Please procees to add type of apartment and name");
                return;
            }
        }
        Balancer.info[Balancer.i]=new ApartmentInfo();
        Balancer.info[Balancer.i].location=loc;
        Balancer.i++;
    }

    public void allocatePrice(String loc,String apartmentType,float cost){
        int index=-1;
        for(int k=0;k<Balancer.i;k++){
            if(Balancer.info[k].location.equals(loc)){
                index=k;
                break;
            }
        }
        if((index!=-1 && Balancer.info[index].type!=null)|| index==-1){
            Balancer.info[Balancer.i]=new ApartmentInfo();
            Balancer.info[Balancer.i].location=loc;
            Balancer.info[Balancer.i].type=apartmentType;
            Balancer.info[Balancer.i].price=cost;
            Balancer.info[Balancer.i].id=loc+","+apartmentType;
            Balancer.i++;
            
        }else{
            Balancer.info[index].type=apartmentType;
            Balancer.info[index].price=cost;
            Balancer.info[index].id=loc+","+apartmentType;

        }
       

    }

    public void addApartment(String apartmentName,String apartmentType,String loc){
        String findId=null;
        for(int it=0;it<Balancer.i;it++){
            if(Balancer.info[it].id.equals(loc+","+apartmentType)){
                findId=Balancer.info[it].id;
            }
        }
        if(findId!=null){
            Balancer.apartments[Balancer.n]=new Apartment();
            Balancer.apartments[Balancer.n].name=apartmentName;
            Balancer.apartments[Balancer.n].id=findId;
            Balancer.n++;
        }else{
            System.out.println("Price for "+apartmentType+" not allocated for the "+loc+" .Hence cannot allocate apartment");
        }
    }

    
        
        
        
    

    public void printAllBookings(){
        System.out.println("=================Bookings===================");
        for(int k=0;k<Balancer.n;k++){
            String [] locationAndType=Balancer.apartments[k].id.split(",");
            System.out.println(locationAndType[1]+" at "+Balancer.apartments[k].name +" located at "+locationAndType[0]+" is booked for the following dates");
            for (Map.Entry<Date, Date> entry : Balancer.apartments[k].bookings.entrySet()) {
                Date startDate = entry.getKey();
                Date endDate = entry.getValue();
                
                System.out.println("StartDate "+startDate+" End Date "+endDate);
                
            }
            System.out.println("***************************************");
            
        }
    }

    public void printInventory(Date start,Date end){
        System.out.println("=================Inventory===================");
        HashMap<String,List<String>> map=new HashMap<String,List<String>>();

         for(int k=0;k<Balancer.n;k++){
                    
                String [] locationAndType=Balancer.apartments[k].id.split(",");
                boolean bookingCheck=false;
                for (Map.Entry<Date, Date> entry : Balancer.apartments[k].bookings.entrySet()) {
                        Date startDate = entry.getKey();
                        Date endDate = entry.getValue();
                        if(start.compareTo(startDate)>=0 && end.compareTo(endDate)>=0){
                        bookingCheck=true;
                        break;
                        }
                        
                }
                if(bookingCheck){
                //       String [] locationAndType=Balancer.apartments[k].id.split(",");
                      if(map.get(locationAndType[0])==null){
                              map.put(locationAndType[0], new ArrayList<>());
                              map.get(locationAndType[0]).add(Balancer.apartments[k].name +" Booked");
                      }else{
                              map.get(locationAndType[0]).add(Balancer.apartments[k].name +" Booked");
                      }
                       
                      
                }else{
                        if(map.get(locationAndType[0])==null){
                                map.put(locationAndType[0], new ArrayList<>());
                                map.get(locationAndType[0]).add(Balancer.apartments[k].name +" Available");
                        }else{
                                map.get(locationAndType[0]).add(Balancer.apartments[k].name +" Available");
                        } 
                }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String loc = entry.getKey();
                List<String> apartmentList = entry.getValue();
                
                System.out.println("Location "+loc);
                for(int t=0;t<apartmentList.size();t++){
                        System.out.println(apartmentList.get(t));
                }
                
            }
    }
}
