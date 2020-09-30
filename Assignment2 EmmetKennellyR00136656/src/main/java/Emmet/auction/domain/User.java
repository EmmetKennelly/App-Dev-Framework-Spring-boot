package Emmet.auction.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	String name;
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	String email;
	String password;

	String phoneNumber ;

	@OneToMany
	List<Bid> bids;

	@OneToMany(mappedBy = "user")
	List<Job> jobs;

	boolean enabled;
//	@OneToOne
//	@JoinTable(name = "user_role")
	@ManyToOne()
	@JoinColumn(name = "role")
	private Role role;

	public User() {
		super();
	}

	public User(String name, String email, String password, String phoneNumber, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.enabled = true;
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Bid> getBids() {
		return bids;
	}
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", bids=" + bids + ",jobs=" + jobs + "]";
	}

}
