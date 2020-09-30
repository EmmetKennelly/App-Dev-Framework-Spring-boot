package Emmet.auction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bidId;

	private double price;

	@ManyToOne
	Job job;

	@ManyToOne
	User user;

	public Bid(double price, Job job,User user) {
		super();
		this.price = price;
		this.job = job;
		this.user=user;
	}



	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public User getUser() {
		return user;
	}

	public Bid() {
		super();
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Bid [bidId=" + bidId + ", price=" + price + ", job=" + job + "]";
	}


}

