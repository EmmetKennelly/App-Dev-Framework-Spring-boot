package Emmet.auction.service;

import java.util.List;

import Emmet.auction.domain.Bid;
import Emmet.auction.domain.Job;
import Emmet.auction.domain.User;

public interface BidService {

	void addBid(Bid bid);

	//Job findBid(int bidId);

//	List<Bid> getAllBid(String email);
	List<Bid> getAllBid();
//	List<Bid>[] getBidsByJobs();

	void addBid(Job job, String username, int amount);

	static List<Bid> getBidsByUserId(User findUser) {
		// TODO Auto-generated method stub
		return null;
	}


}
