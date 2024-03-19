package Operations;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;
import Entity.User;

public class LoginModule {
	
	SessionFactory factory;
	String userpassword ;
	String email;
	Scanner in;
	User user;
	


	public LoginModule() {
		super();
	}



	public LoginModule (User user, SessionFactory factory) {
		super();
		this.user =user;
		this.factory=factory;
		this.in = new Scanner(System.in);
	}



	public String Login() {
		
		
		
		
		
		System.out.println("-------------------------- Enter Login Details -------------------------------");
		System.out.println();
		System.out.print("Email  : ");
		email= in.nextLine();
		System.out.print("Password : ");
		String password= in.nextLine();

		// Open a new session 
				Session session = factory.openSession();
		Query query = session.createQuery("from User_details u where u.userEmail=:i and u.password=:j");
		query.setParameter("i", email);
		query.setParameter("j", password);
		
		
		List<User> result = query.getResultList();
		session.close();
		
			    if(!result.isEmpty()) {	
			    	User user = result.get(0);
			        return user.getUserEmail();
			    }else {
			    	return null;
			    }

}
}