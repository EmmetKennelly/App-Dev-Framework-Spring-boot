package Emmet.auction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Emmet.auction.dao.BidDao;
import Emmet.auction.dao.JobDao;
import Emmet.auction.dao.UserDao;
import Emmet.auction.domain.Bid;
import Emmet.auction.domain.Job;
import Emmet.auction.domain.User;

@Service
public class BidServiceImpl implements BidService{

	@Autowired
	BidDao biddao;
	@Autowired
	JobDao jobdao;
	@Autowired
	UserDao userdao;


	@Override
	public void addBid(Bid bid) {
		// TODO Auto-generated method stub
		biddao.save(bid);
	}



	//public Job findBid(int bidId) {
		// TODO Auto-generated method stub

        //return jobdao.findById(bidId);
		//return jobdao;
	//}Job


//	@Override
//	public List<Bid> getAllBid(String email ) {
//		 TODO Auto-generated method stub
//		 List<Job> jobs = new ArrayList<>();
//	        userdao.findUserByEmail(email);
//	        .forEach(jobs::add);
//	        return biddao.getBidsByUserId();
//	    }
	@Override
	public List<Bid> getAllBid( ) {
		return biddao.findAll();
	}

	class JobAndItsBids {
		Job job;


	}
	//TODO: For having bids alongside jobs
//	@Override
//	public List<Bid>[] getBidsByJobs() {
//
//		List<Bid> allBids = biddao.findAll();
//		List<Job> jobs = allBids.stream().map(Bid::getJob).distinct().collect(Collectors.toList());
//		jobs.stream().map(job -> {job, allBids.stream().filter(bid -> bid.getJob() == job)});
//	}

	public List<Bid> getBidsByUserId(Integer userId) {
		List<Bid> bids = new ArrayList<>();
		 bids = biddao.getBidsByUserId(userId);
		 return bids;
	}


	@Override
	public void addBid(Job job, String email, int amount) {
		// TODO Auto-generated method stub
		Optional<Job> njob = jobdao.findById(job.getId());
        User user = userdao.findUserByEmail(email);
    //    Bid.setPrice(amount);

        jobdao.save(job);

	}

	}










