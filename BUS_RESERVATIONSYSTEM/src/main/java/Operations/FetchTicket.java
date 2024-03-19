package Operations;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.Scanner;

import Entity.Bus;
import Entity.Passenger;
import Entity.Ticket;

public class FetchTicket {
	private SessionFactory factory;
	Passenger passenger;
	Ticket ticket;
	Session session;
	private Scanner in;
	
	public FetchTicket(Passenger passenger, Ticket ticket, SessionFactory factory) {
		super();
		this.passenger = passenger;
		this.ticket = ticket;
		this.factory = factory;
		this.in=new Scanner(System.in);
	}

	

public FetchTicket() {
		super();
		// TODO Auto-generated constructor stub
	}



public void displayBookingDetails() {
	
		
			
        System.out.println();
		System.out.println();
		System.out.println("---------------------------- Welcome To Bus Ticket Corner -------------------------------------");
		System.out.println("---------------------------------- Enter Your Details -----------------------------------------");
		System.out.println();
		
		System.out.print("Ticket Id    : ");
		int ticketid = in.nextInt();
	
		in.nextLine();
		System.out.println("");
		System.out.print("Please Wait");
		 for(int i =1;i<=5;i++) {
				System.out.print(".");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		 System.out.println();
		
		 
		 Session session = factory.openSession();

		Query query = session.createQuery(" FROM Passenger_details p  WHERE p.ticket.ticketId = :ticketId");
		query.setParameter("ticketId", ticketid);

		Passenger result = (Passenger) query.uniqueResult();
		
		session.close();	  
		
			boolean ticketstatus=result.getTicket().isTicketStatus();
			String bookingStatus ;
			if(ticketstatus==true) {
				bookingStatus="Booked";
			}
			else {
				bookingStatus="Cancelled";
			}
			 System.out.println();
			 System.out.println("------------------------------------- Trip Details -------------------------------------------");
			 System.out.println();
			 System.out.println("      	    Passenger Name    : "+result.getPassengerName());
			 System.out.println("			Passenger Id      : "+result.getPassengerId());
			 System.out.println("			Starting point    : "+result.getPassengerStartingPoint());
			 System.out.println("			Destination point : "+result.getPassengerDestinationPoint());
			 System.out.println("			Booking Date      : "+result.getBookingDate());
			 System.out.println("			Seat No.          : "+result.getPassengerSeatNo());
			 System.out.println("			Ticket Id         : "+result.getTicket().getTicketId());
			 System.out.println("			Ticket Number     : "+result.getTicket().getTicketNumber());
			 System.out.println("			Ticket Status     : "+bookingStatus);
			 System.out.println();
			 System.out.println("-----------------------------------------------------------------------------------------------");
			 System.out.println();		
			
}
}
