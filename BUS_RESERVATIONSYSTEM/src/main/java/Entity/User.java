package Entity;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "User_details")//Declaring class as Entity with @Entity annotation
public class User {											// Creating Class user

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private int userId;										//Initializing attribute userid for user

    @Column(name = "User_Name", length = 60)
    private String userName;								//Initializing attribute username for user

    @Column(name = "User_Email", length = 40)
    private String userEmail;								//Initializing attribute useremail for user

    @Column(name = "Password", length = 40)
    private String password;								//Initializing attribute userpassword for user
    
 // Constructors, getters, setters

	public User() {
		super();
		
	}

public User(int userId, String userName, String userEmail, String password) {
	super();
	this.userId = userId;
	this.userName = userName;
	this.userEmail = userEmail;
	this.password = password;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserEmail() {
	return userEmail;
}

public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

 
}