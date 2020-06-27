import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;




class ApartmentInfo{
    String location=null;
    String type=null;
    float price=0;
    String id=null;

    
}

class Apartment{
    String name;
    String id;
    HashMap<Date,Date> bookings=new HashMap<Date,Date>();
}

class Balancer{
        public static  ApartmentInfo info[] =new ApartmentInfo[20];
        public static Apartment apartments[]=new Apartment[50];
        public static int i=0;//Number of different locations and type and cost
        public static int n=0;//Number of apartments
}

public class Main {
    public static void main(String[] args){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Booking booking =new Booking();
        Inventory inventory=new Inventory();
        
        inventory.addBranch("Perungudi");
        inventory.addBranch("Thoraipakkam");


        inventory.allocatePrice("Perungudi","1BHK",800);
        inventory.allocatePrice("Perungudi","2BHK",1000);
        inventory.allocatePrice("Thoraipakkam","Studio",400);
        inventory.allocatePrice("Thoraipakkam","2BHK",800);

        inventory.addApartment("tweens", "2BHK", "Perungudi");
        inventory.addApartment("VST", "2BHK", "Thoraipakkam");
        inventory.addApartment("PRIYA", "Studio", "Thoraipakkam");
        inventory.addApartment("ceebros", "1BHK", "Perungudi");

        Date start,end;
        try{
                start=dateFormat.parse("16-05-2020");
                end= dateFormat.parse("19-05-2020");
        }catch(ParseException e){
                System.out.println("Could not parse date");
                return;
        }

        booking.bookApartment("2BHK",start,end );
        

        
        
        booking.bookApartment("2BHK",start,end);
        booking.bookApartment("Studio", start, end);
        //inventory.bookApartment("1BHK", start, end);
        
        
        
        inventory.printAllBookings();
        inventory.printInventory(start,end);
    }
    
}
