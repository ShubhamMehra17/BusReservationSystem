package Operations;

import java.util.Scanner;
import org.hibernate.SessionFactory;
import Entity.Bus;
import Entity.Passenger;
import Entity.Ticket;
import Entity.User;

public class MainInterface {
	
						SessionFactory factory;
						Passenger passenger;
						Ticket ticket;
						User user;
						Bus bus;
						Scanner in1;
					    private SignUp signup;
						private LoginModule login;
						private Reservation reservation;
						private FetchTicket fetchTicket;
						private CancelTicket cancelTicket;
						
					public MainInterface(  Passenger passenger, Bus bus, Ticket ticket, User user,
							  SessionFactory factory) {
						super();
						this.passenger = passenger;
						this.bus = bus;
						this.ticket = ticket;
						this.user = user;
						this.factory = factory;
						this.signup=new SignUp(user,factory);
						this.login = new LoginModule(user,factory);
						this.fetchTicket=new FetchTicket(passenger,ticket,factory);
						this.cancelTicket = new CancelTicket(factory);
						this.reservation= new Reservation(passenger,bus,factory,user);
						this.in1=new Scanner(System.in);
					}
					
	

	



	public MainInterface() {
						super();
	}
	
					   String email;
					   int choice1; 
					   Scanner in = new Scanner (System.in);
	
						public void StartApp(){
							
						try {
						while(true) {
						      System.out.println("Welcome To The Bus Travelling App ");
						      System.out.println("  We're Here To Serve You Best ");
						      System.out.println();
						      System.out.println("1. Sign Up ");
						      System.out.println("2. Log In  ");
						      System.out.println("3. Exit  ");
						      System.out.println();
						      System.out.print("Enter Your Choice :  ");
						    
						      choice1 = in.nextInt();
						      switch(choice1) {
						      case(1): signup.signUp();
									break;
						      case(2): email=login.Login();
						      if(email!=null){
								System.out.print("User Logging in");
								for(int i=1;i<=5;i++) {
									System.out.print(".");
								
									Thread.sleep(400);
								}
								System.out.println();
								System.out.println("---------------------------------------------");
								System.out.println();
								System.out.println("User Logged in");
							
								while(true) {
								System.out.println();
								System.out.println("1. Reserve seats");
								System.out.println("2. Show booked tickets");
								System.out.println("3. Cancle booked trip. ");
								System.out.println("4. Log out. ");
								System.out.println();
								System.out.print("Enter Your Choice :  ");
								int choice2 = in.nextInt();
								switch(choice2) {
								case(1):reservation.reserve_seat(email) ;
								
								break;
								
								case (2) : fetchTicket.displayBookingDetails();
								break;
								
								case (3) : cancelTicket.cancelBooking();
								break;
								
								case (4) : 
									System.out.print("Logging out ");
								
								for(int i=1;i<=5;i++) {
									System.out.print(".");
								
									Thread.sleep(400);
								}
								System.out.println();
								System.out.println();
								break;
								
								 default :
										System.out.println("Please Enter a valid choice.");
										System.out.println();
								}
								
								if (choice2 == 4) {
	                                break; // Break out of the inner loop and go back to the main menu
	                            }
								}
						      }else{
									System.out.print("User Logging in");
									for(int i=1;i<=5;i++) {
										System.out.print(".");
									
										Thread.sleep(400);
									}
									System.out.println();	
									System.out.println("-------------------------- Invalid User-Email Or Password -------------------------------");
									System.out.println();
						      }
						      
						      break;
						      
						      case(3): 
						    	  
						    	    System.out.println();
									System.out.println("---------------------------- Thankyou for using our services ---------------------------");
									
									System.exit(0);
									
						      default :
								System.out.println("Please Enter a valid choice.");
								System.out.println();
						      } 
						   }
							
						} catch(Exception e) {
							e.getMessage();
						}
						
   	
						}
						
						
					}
					
