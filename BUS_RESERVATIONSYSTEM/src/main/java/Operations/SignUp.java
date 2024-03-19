package Operations;



import Entity.User;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class SignUp {
				private SessionFactory factory;
				private	User user ;
				private	boolean ifexists;
				Scanner in;
				public SignUp(User user, SessionFactory factory) {
					super();
					
					this.factory=factory;
					this.user = new User();
					this.in=new Scanner(System.in);
					
				
				}
				
			
			
				public SignUp() {
					super();
					this.user = new User();
				}



	public void signUp() {
										
						System.out.println("------------------------- Welcome To Sign-Up Corner -----------------------------");	
						System.out.println();
						System.out.print("Full Name : ");
						String full_name = in.nextLine();//Taking user-input for user-name
						System.out.println();
						System.out.print("Email : ");
						String email = in.nextLine();//Taking user-input for user-email
						System.out.println();
						System.out.print("Password : ");
						String password= in.nextLine();//Taking user-input for user-password
								
						ifexists=user_exists(email);
						
						System.out.println("Please wait...");
						if(ifexists==false) {
						System.out.print("User Already Have Account on email : "+email);
						System.out.println();
						}else {
							
						user.setUserName(full_name);
						user.setUserEmail(email);
						user.setPassword(password);
						
						// Open a new session 
						Session session = factory.openSession();
						// Begin a transaction
						Transaction transaction = session.beginTransaction();
						
						session.save(user);
						//commit the transaction
						transaction.commit();	
						// close the session
						session.close();
						//}
						
						System.out.println();
						System.out.println("--------------------------- Sign-Up Successfully --------------------------------");
						System.out.println();
						System.out.println();
						
					
					}
	
}
	
	
	
	
	

	public boolean user_exists(String email) {
		String insertedemail = email;
		
		// Open a new session
		Session session = factory.openSession();
		Query q=session.createQuery("Select u.userEmail FROM User_details u where u.userEmail = :email");
		  q.setParameter("email",insertedemail );
	        
		  User result = (User)q.uniqueResult();
		  
	  
	        session.close();
	        
	        if(result!=null) {
	        	
	        	System.out.println(result.getUserEmail());
	        	return false;
	        	
	        // Check if the result is not null
	        }else {
	        	return true;
	        }
			
		}
	
}
