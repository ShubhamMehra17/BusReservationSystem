package Operations;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Entity.Bus;
import Entity.Passenger;
import Entity.Ticket;
import Entity.User;

public class Reservation {
	private SessionFactory factory;
	private Passenger passenger;
	private Scanner	in;
	private Date date;
	private Bus bus1;
	private User user;
	
	public Reservation(Passenger passenger , Bus bus ,SessionFactory factory,User user) {
		super();
		this.passenger = passenger;
		this.factory=factory;
		this.in=new Scanner(System.in);
		this.bus1=bus;
		this.user=user;
	}
	public Reservation() {
		super();
	}
	
	
	public void reserve_seat(String Email) {
				System.out.println();
				System.out.println();
				System.out.println("--------------------------- Welcome To Reservation Corner -------------------------------------");
				System.out.println("---------------------------------- Enter Your Details -----------------------------------------");
				System.out.println();
		
		
					String email=Email;
		        Session s4 = factory.openSession();
		
			 	Query q4 = s4.createQuery("from User_details where userEmail =: usermail" );
				q4.setParameter("usermail", email);
			 	User user = (User)q4.uniqueResult();
			 	s4.close();
					
				// Open a new session
				Session session = factory.openSession();
		try {
			 	Query query = session.createQuery("from Bus_details" );
			 	List<Bus> resultList  = query.getResultList();
		
				
				for(int i = 0;i<Math.min(8,resultList.size());i++) {
				
				System.out.println("---------------------------------------------------------------");
				System.out.println("Starting Point     : "+resultList.get(i).getStartingPoint());
				System.out.println("Destination Point  : "+resultList.get(i).getDestinationPoint());
				System.out.println("Bus No.            : "+resultList.get(i).getBusNo());
				System.out.println("Bus Id             : "+resultList.get(i).getBusId());
				System.out.println("Bus Route No.      : "+resultList.get(i).getRouteNo());
				System.out.println("Total seats        : "+resultList.get(i).getTotalCapacity());
				System.out.println("Departure Time     : "+resultList.get(i).getDepartureTime());
				System.out.println();
				System.out.println("---------------------------------------------------------------");	
		 }		
				session.close();
		}		catch(Exception e) {
				System.out.println("Error - "+e.getMessage());
	}
				System.out.print("Choose A Route :  ");		
				int routeChoice = in.nextInt();		
				switch(routeChoice) {
				case (1) : 					
							setBusDetilsInPassenger(1);
							break;					
				case (2) : 
							setBusDetilsInPassenger(2);
							break;
				case (3) : 
							setBusDetilsInPassenger(3);
							break;
				case (4) :
							setBusDetilsInPassenger(4);
							break;
				case (5) : 
							setBusDetilsInPassenger(5);
							break;
				case (6) : 
							setBusDetilsInPassenger(6);
							break;
				case (7) : 
							setBusDetilsInPassenger(7);
							break;
				case (8) : 
							setBusDetilsInPassenger(8);
							break;
				default : System.out.println("Please Enter A Valid Route");
				}		
				
			try	{
				Session s = factory.openSession();
				Query q3 = s.createQuery("from Bus_details where routeNo = : route" );
				q3.setParameter("route", routeChoice);
				Bus bus = (Bus)q3.uniqueResult();
				s.close();
			
				bus1.setBusId(bus.getBusId());
				bus1.setBusNo(bus.getBusNo());
				bus1.setDepartureTime(bus.getDepartureTime());
				bus1.setDestinationPoint(bus.getDestinationPoint());
				bus1.setStartingPoint(bus.getStartingPoint());
				bus1.setTotalCapacity(bus.getTotalCapacity());
			}catch(Exception e) {
				System.out.println("Error - "+e.getMessage());
			}
				
				in.nextLine();
				
				System.out.print("Enter Full Name : ");
				String passenger_name = in.nextLine();	
				
				
				System.out.print("Age : ");
				int passenger_age = in.nextInt();
				
				
				in.nextLine();
				System.out.print("Gender : ");
				String passenger_gender = in.nextLine();
				
			
				System.out.print("Contact no. : ");	
				String passenger_contact = in.nextLine();
				
				
				System.out.print("Email : ");
				String passenger_email = in.nextLine();
				
				
				System.out.print("Date Booked(yy-mm-dd): ");
				String paasenger_bookingdate = in.nextLine();	
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
		            // Parsing the user input into a Date object
				date = sdf.parse(paasenger_bookingdate);
		            
		        } catch (ParseException e) {
		            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
		        }		
				
				
				 System.out.println("----------------------------------------------------------------------");
				 System.out.println(" Please Select Your Seat Number");
				 System.out.println("1   2   3   4   5   6   7   8   9   10");
				 System.out.println("11  12  13  14  15  16  17  18  19  20");
				 System.out.println("21  22  23  24  25  26  27  28 ");
				 System.out.println("----------------------------------------------------------------------");
				 
				
			
			try {
				

					Session s = factory.openSession();
					Query q = s.createQuery("SELECT p.passengerSeatNo from Passenger_details p INNER JOIN p.bus b WHERE b.routeNo = :route AND DATE(b.date) = :date");
					q.setParameter("route",routeChoice );
					q.setParameter("date",date );
					
					List<Integer> seatNumbers = q.list();
					
					System.out.print("Booked Seats : ");
				int count = 0; // Initializing count variable for counting repetitons of loop 
				
				for (Integer psno : seatNumbers) {
							if(psno!=0) {
							System.out.print(psno + " "); // Printing the booked seat numbers
		        				count+=1; // counting repetition for adding new line
			            // Check if 10 elements have been printed or if it's the last element
			            if (count==10 || count ==20) {
			                System.out.println(); // Move to the next line
			                System.out.print("Booked Seats : ");      
			            }    
					} 
				}
					}catch(Exception e ) {
						System.out.println("Error - "+e.getMessage());
					}
				System.out.println();
				System.out.println();
				
				boolean seatAlreadyBooked = true; // Initialize to true to enter the loop
				int seatno = 0;
				try {
		        while (seatAlreadyBooked) {
		            System.out.print("Enter Your Seat No. : ");
		           int  seat = in.nextInt();
		          
		          // Open a new session
						Session session2 = factory.openSession();
			
						Query query2 = session2.createQuery("from Passenger_details p where p.passengerSeatNo=:i and p.bookingDate =: j and p.bus.routeNo =: k and p.bus.date=: l"); 			// query for checking whether the user-entered seat number on user-entered date is already booked
						query2.setParameter("i", seat);																								// Passing the parameters for check seat no
						query2.setParameter("j", date); 																								// Passing the parameters for check date
						query2.setParameter("k",routeChoice);
						query2.setParameter("l", date);
						Passenger passenger1 = (Passenger) query2.uniqueResult();
						session2.close();
		          
		            if (passenger1 != null) {
		            	System.out.println();
		                System.out.println("Seat Is Already booked. Please Choose A Different Seat no.");
		                System.out.println();
		            } else {
		            	seatno+=seat;
		                seatAlreadyBooked = false; // Set to false to exit the loop
		            }
		        }
				}catch(Exception e) {
		        	  System.out.println("Error - "+e.getMessage());
		          }
				System.out.println();
				
				if(seatno<29) {
			         System.out.print("Your Booking Is About To Confirm. Please Wait For Some time");       
			         for(int i =1;i<5;i++) {
							System.out.print(".");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
				 }
				System.out.println();
				
				
			bus1.setDate(date);
			bus1.setRouteNo(routeChoice);
			Ticket generatedTicket =generatingTicket(routeChoice,seatno); 				//calling the generatingTicket method to generate ticket for passenger
			passenger.setPassengerName(passenger_name);			//Setting value of passenger name
			passenger.setPassengerAge(passenger_age);			//Setting value of passenger age
			passenger.setPassengerGender(passenger_gender);		//Setting value of passenger gender
			passenger.setPassengerEmail(passenger_contact);		//Setting value of passenger contact
			passenger.setPassengerEmail(passenger_email);		//Setting value of passenger email
			passenger.setBookingDate(date);						//Setting value of passenger 
			passenger.setPassengerSeatNo(seatno);				//Setting the seat number for passenger
			passenger.setPassengerContact(passenger_contact);
			passenger.setTicket(generatedTicket);
			passenger.setUser(user);
			
			
			try {
				Session s1=factory.openSession();
				Query que = s1.createQuery("from Bus_details where routeNo =: i and date =: j");
				que.setParameter("i", routeChoice);
				que.setParameter("j", date);
				Bus bus2 = (Bus)que.uniqueResult(); // Retriving bus for checking if a bus is present in database or not 
				
				if(bus2!=null) {
					  // If bus exists, update the available capacity
			        bus2.setAvailableCapacity(bus2.getAvailableCapacity() - 1);
			        s1.beginTransaction();
			        s1.update(bus2); // Update the bus entity
			        s1.getTransaction().commit();
			        passenger.setBus(bus2);
				}else {
					  // If bus doesn't exist, create a new bus entity and save it
			        bus1.setAvailableCapacity(27);
			        s1.beginTransaction();
			        s1.save(bus1); // Save the new bus entity
			        s1.getTransaction().commit();
			        passenger.setBus(bus1);
				}
				s1.close();
			}catch(Exception e) {
					System.out.println("Error - "+e.getMessage());
				}
				
		
			// Open a new session
			Session session4 = factory.openSession();
			Transaction transaction3 = session4.beginTransaction();
			session4.save(passenger);											// saving passenger object
				
			transaction3.commit();	
			//close the session
			session4.close();		
			
			
			
			System.out.println();
			System.out.println("Booking Confirmed on Date : "+paasenger_bookingdate);
			System.out.println("Seat No. is               : "+passenger.getPassengerSeatNo());
			System.out.println("Ticket Id is              : "+generatedTicket.getTicketId());
			System.out.println("Passenger Id No.          : "+passenger.getPassengerId());
			System.out.println();
			System.out.println("-------------------------------------- Happy Journey ------------------------------------------");
			System.out.println("--------------------------------- Thanks for Booking With Us ----------------------------------");
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
	
				}	
		
	
	public void setBusDetilsInPassenger(int routeid) {
				
			int srno = routeid;
		
			try {
			// Open a new session
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Bus busDetails=session.get(Bus.class, srno);
			tx.getClass();
			// close the session
			session.close();
			passenger.setPassengerStartingPoint(busDetails.getStartingPoint())  ;	
		    passenger.setPassengerDestinationPoint(busDetails.getDestinationPoint());
		    System.out.println("Enter Starting Point		 : "+busDetails.getStartingPoint());
		    System.out.println("Enter Destination Point 	 : "+busDetails.getDestinationPoint());
		    System.out.println("Bus No.			         : "+busDetails.getBusNo());			
			
			}catch(Exception e){
				System.out.println("Error - "+e.getMessage());
			}
		    
		    
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	public Ticket generatingTicket(int routeid, int seatnum) {
			int srno =routeid;
			int seatno = seatnum;
			Ticket ticket1 = new Ticket();
			
			// Open a new session
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			
			Bus result =session.get(Bus.class, srno);
			tx.getClass();
			// close the session
			session.close();
		
			int forTcG =result.getBusId();
		
			
			
			SecureRandom random = new SecureRandom();
			
			int random_n = random.nextInt(1000);
			int ticket_Id=forTcG+1+seatno+random_n;
			int tcNum = random.nextInt(Integer.MAX_VALUE);
			// Open a new session
			Session session1 = factory.openSession();					
			
			Query query4 = session1.createQuery("from Ticket_details ");
			List<Ticket> ticket2 = query4.getResultList();
			
			// close the session
			session1.close();
			for(Ticket tc : ticket2) {
				if(ticket_Id==tc.getTicketId()) {
					 random_n = random.nextInt(100);
					 ticket_Id = forTcG+1+seatno+random_n;
				}
				if(tcNum==tc.getTicketNumber()) {
					 tcNum = random.nextInt(Integer.MAX_VALUE);
				}	
			}			
			
				
			ticket1.setTicketId(ticket_Id);				      
	        ticket1.setTicketNumber(tcNum); 
	        ticket1.setTicketStatus(true);
	        Session session2 = factory.openSession();
	        Transaction transaction = session2.beginTransaction();
	        
	        session2.save(ticket1);
	        transaction.commit();
	        session2.close();
			return ticket1;
		
	}
	
	
	}
	

