package Emmet.auction.domain;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Role {

	@OneToMany(mappedBy = "role")
	List<User> users;
	@Id

	 @Column(unique = true)
	 private String description;

	public Role() {
		super();
	}

	public Role(String description) {
		super();
		this.description = description;
	}
}
