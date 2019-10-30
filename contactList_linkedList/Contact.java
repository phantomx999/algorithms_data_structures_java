//written by phantomx999

public class Contact{	
	private String name;	//name of contact
	private long phone;		//phone number
	private String address;	//street addess
	private String comments;	//extra info
	
	//constructor
	public Contact(String na, long ph, String ad, String com){
		this.name = na;
		this.phone = ph;
		this.address = ad;
		this.comments = com;
	}
	
	//constructor
	public Contact(){
		this.name = null;
		this.phone = 0;
		this.address = null;
		this.comments = null;
	}
	
	//setter
	public void setName(String n){
		name = n;
	}
	
	//getter
	public String getName(){
		return name;
	}
	
	//setter
	public void setPhone(long p){
		phone = p;
	}
	
	//getter
	public long getPhone(){
		return phone;
	}
	//more setters and getters...
	public void setAddress(String a){
		address = a;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setComments(String c){
		comments = c;
	}
	
	public String getComments(){
		return comments;
	}
	
	//displays contact information
	public String toString(){
		return "Name: " + getName() + "  Phone: " + getPhone() + "  Address: " + getAddress() + " Comments: " + getComments();
	}
	
	//checks if contact is equal to another contact
	public boolean equals(Contact other){
		if (this.name.equals(other.getName()) && (this.phone == other.getPhone()) && this.address.equals(other.getAddress()) && this.comments.equals(other.getComments())){
			return true;
		}
		return false;
	}
}