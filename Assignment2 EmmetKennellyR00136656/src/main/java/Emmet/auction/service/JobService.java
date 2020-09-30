package Emmet.auction.service;

import java.util.List;

import Emmet.auction.domain.Job;
import Emmet.auction.domain.User;

public interface JobService {

	 List<Job> findAll();
	 List<Job> findByisOpen(boolean isOpen);
     public void addJob(Job job, String email);
     public Job getAJob(int id);
}
