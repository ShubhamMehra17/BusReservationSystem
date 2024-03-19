package BusReservationSystem.com.BusReservationSystem;
import org.hibernate.SessionFactory;
import Entity.Bus;
import Entity.Passenger;
import Entity.Ticket;
import Entity.User;
import Operations.MainInterface;

public class App 
{
	
	
    public static void main( String[] args )
    {
    	SessionFactory factory = HibernateUtil.getSessionFactory();
		Passenger passenger = new Passenger();
		Bus bus = new Bus();
		Ticket ticket = new Ticket();
		User user = new User();
    	MainInterface mainint =new MainInterface(passenger,bus,ticket,user, factory);
    	mainint.StartApp();
    	
    	
    	
    	
		
    }
}
