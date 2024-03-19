package Operations;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import Entity.Bus;
import Entity.Passenger;
import Entity.Ticket;
import java.util.Scanner;

public class CancelTicket {
	private SessionFactory factory;
	private Scanner in;
	private Bus bus;
	private Ticket ticket;
	private Passenger result;
	public CancelTicket() {
		super();
	}

	public CancelTicket( SessionFactory factory ) {
		super();

		this.factory=factory;
		this.in=new Scanner(System.in);
		
	}


	public void cancelBooking() {
		
		System.out.println();
		System.out.println();	
		System.out.println("------------------------ Welcome To The Booking Cancellation Panel ----------------------------");
		System.out.println("---------------------------------- Enter Your Details -----------------------------------------");
		System.out.println();
		System.out.print("Enter Your Ticket Id      : ");
		int ticketid = in.nextInt();
		
		System.out.println();
		
		System.out.println("Cancelling Your Booking For Ticket Id - "+ticketid);
		System.out.println();
		System.out.print("Hold On");
		for(int i =1;i<=5;i++) {
			System.out.print(".");
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}						
		}
		
		System.out.println();
		
	try {	
		
		Session session =factory.openSession();
		
		Query query = session.createQuery(" FROM Passenger_details p  WHERE p.ticket.ticketId = :ticketId");
		query.setParameter("ticketId", ticketid);

		result = (Passenger) query.uniqueResult();
		
		
		bus = result.getBus();
		ticket=result.getTicket();
		boolean tcs = result.getTicket().isTicketStatus();

		result.setPassengerSeatNo(0);
		session.beginTransaction();
		session.update(result);
		session.getTransaction().commit();
		session.close();
		
		if(tcs==true) {
		
		ticket.setTicketStatus(false);
		Session session2 = factory.openSession();
		session2.beginTransaction();
		session2.update(ticket);
		session2.getTransaction().commit();
		
		boolean row=ticket.isTicketStatus();
		session2.close();
		
		Session session3 = factory.openSession();
		
		if(row==false) {
			 bus.setAvailableCapacity(bus.getAvailableCapacity() + 1);
		        session3.beginTransaction();
		        session3.update(bus); // Update the bus entity
		        session3.getTransaction().commit();
			
			
			// close the session
			session3.close();
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.println("------------------------------ Booking Cancelled Succesfully ----------------------------------");
			System.out.println("-------------------------------------- Thank You ----------------------------------------------");
		}else {
			System.out.println("Your Booking Is Already Cancelled For Ticket Id - "+ticketid);
		}
		
		    System.out.println("-----------------------------------------------------------------------------------------------");
		
		}else {
			System.out.println("------------------------------ Your Booking Is Already Cancelled ------------------------------");
			System.out.println("-----------------------------------------------------------------------------------------------");
		}
			
			
	}catch(Exception e) {
		System.out.println("Error - "+e.getMessage());
	}
			
			
	}
	
	
}


