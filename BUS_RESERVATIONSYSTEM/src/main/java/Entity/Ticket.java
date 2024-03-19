package Entity;
import javax.persistence.*;

@Entity(name = "Ticket_details")
public class Ticket {
    @Id
    @Column(name = "Ticket_Id")
    private int ticketId;
    @Column(name = "Ticket_Number")
    private int ticketNumber;
    @Column(name="Booking_Status")
    private boolean ticketStatus;
    

    
    
	public Ticket() {
		super();
	}


	public Ticket(int ticketId, int ticketNumber, boolean ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.ticketNumber = ticketNumber;
		this.ticketStatus = ticketStatus;	
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public int getTicketNumber() {
		return ticketNumber;
	}


	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}


	public boolean isTicketStatus() {
		return ticketStatus;
	}


	public void setTicketStatus(boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}



	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketNumber=" + ticketNumber + ", ticketStatus=" + ticketStatus
				+ ", passenger=" +  "]";
	}
     
    
}