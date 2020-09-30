package Emmet.auction.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Emmet.auction.domain.Job;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("isAuthenticated()")
public interface JobDao extends JpaRepository<Job, Integer>
{


	    public List<Job> findByFinishDate (String endDate);
	    public List<Job> findByIsOpen (boolean isOpen);
		public Job findById(int id);

	//    public List<Job> findBySellerEmail (String name);
	 //   public List<Job> findByBuyerEmail(String name);
}

