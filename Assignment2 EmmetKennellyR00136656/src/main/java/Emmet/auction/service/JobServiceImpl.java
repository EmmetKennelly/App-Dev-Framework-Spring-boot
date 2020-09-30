package Emmet.auction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Emmet.auction.dao.BidDao;
import Emmet.auction.dao.JobDao;
import Emmet.auction.dao.UserDao;
import Emmet.auction.domain.Job;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	 JobDao jobdao;
	@Autowired
	UserDao userdao;
	public List<Job> findAll() {
		List<Job> jobs = new ArrayList<>();
        jobs = jobdao.findAll();
        return jobs;
	}

        public void addJob(Job job, String email) {
            job.setUser(userdao.findUserByEmail(email));
            jobdao.save(job);
        }

    @Override
    public Job getAJob(int id) {
        return jobdao.findById(id);
    }

    public Job findjobForUpdate(Integer id){
           return null;  // jobdao.findById(id);
       }

       public Job updateAuction(Integer id, Job job) {
           Job JobForUpdate = findjobForUpdate(id);
           JobForUpdate.setDescription(job.getDescription());
           JobForUpdate.setBids(job.getBids());
           return jobdao.save(JobForUpdate);
       }
       @Override
   	public List<Job> findByisOpen(boolean isOpen) {
   		List<Job> jobs = new ArrayList<>();
   		jobs = jobdao.findByIsOpen(isOpen);


   		return jobs.stream().filter(job -> job.isOpen() == isOpen).collect(Collectors.toList());

   	}
}
