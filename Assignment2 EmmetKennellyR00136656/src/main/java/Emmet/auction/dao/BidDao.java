package Emmet.auction.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Emmet.auction.domain.Bid;

public interface BidDao extends JpaRepository<Bid, Integer>
{

	List<Bid> getBidsByUserId(Integer userId);
	// Bid findById(int bidId);
}
