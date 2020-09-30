package Emmet.auction.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import Emmet.auction.domain.*;

import Emmet.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Emmet.auction.dao.JobDao;
import Emmet.auction.dao.UserDao;
import Emmet.auction.service.JobService;
import Emmet.auction.service.UserService;

@RestController
@RequestMapping(value = "/apis")
public class RestUserControllers {

	@Autowired
    private UserService userService;
	@Autowired
    private JobService jobService;

	// add the findallbyisopen method to the sevice interface & impl
	@Autowired
	private JobDao jobDao;
	@Autowired
	private UserDao userdao;

	@Autowired
    private BidService bidService;

	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.findAll();
	}

	@GetMapping("/bids/{userEmail}")
    public List<Bid> getBidsByTheirUser(@PathVariable(name = "userEmail") String userEmail){
        return bidService.getAllBid().stream().filter(bid -> bid.getUser().getEmail().equals(userEmail)).collect(Collectors.toList());
    }

    @GetMapping("/user/{email}")
    public List<Job> updateUser(@PathVariable String email) {
    	User u = userService.findUser(email);
    	return u.getJobs();
    }

    @GetMapping("/jobs/{id}")
    public List<Job> getJobs() {
    	List<Job> jobs = jobService.findAll();

    	return  jobs;
    }

    @GetMapping("/jobs/open")
    public List<Job> getOpenJobs() {
    	List<Job> jobs = jobDao.findByIsOpen(true);
    	return jobs;
    }
    @PostMapping("/user/add")
    public ResponseEntity<User> addNewUserSave(@RequestBody String email, @RequestBody String password,@RequestBody String name,@RequestBody String phoneNumber,@RequestBody Role role)
    {
        User newUser = userdao.save(new User(email, password, name, phoneNumber, role));
        if ( newUser == null )
            return new ResponseEntity<>(newUser, HttpStatus.CONFLICT);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
//
    @PostMapping(value = "/jobs/add")
    public String addJob(JobForm postedJobForm, Principal user) {
        //TODO: Use actual logged in name
        String name = user.getName();

        Job newJob = new Job(
                postedJobForm.getName(),
                postedJobForm.getDescription(),
                userService.findUser(name),
                true);
        jobService.addJob(newJob, name);
        return "index";
    }

    //	@GetMapping("/auction/find/{id}")
//      public String findAuction(@PathVariable Integer id){
  //
//          auctionService.findByAuctionId(id);
  //
//          return "auction-list";                              // ??
//      }
}
