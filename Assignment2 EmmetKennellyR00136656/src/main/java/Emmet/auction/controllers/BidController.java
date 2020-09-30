package Emmet.auction.controllers;

import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import Emmet.auction.domain.BidForm;
import Emmet.auction.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import Emmet.auction.dao.JobDao;
import Emmet.auction.dao.UserDao;
import Emmet.auction.domain.Bid;
import Emmet.auction.domain.Job;
import Emmet.auction.domain.JobForm;
import Emmet.auction.domain.User;
import Emmet.auction.service.BidService;
import Emmet.auction.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BidController {
    @Autowired
    private BidService bidService;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/bid/add/{id}")
    public String bidWasAdded(BidForm theirBid, Principal principal, @PathVariable(name = "id") int id,
                              RedirectAttributes redirectAttributes){

        User bidder = userService.findUser(principal.getName());

        double currentLowestPrice = bidService.getAllBid().stream()
                .filter(bid -> bid.getJob().getId() == id) 
                .map(Bid::getPrice) 
                .sorted() 
                .collect(Collectors.toList()).get(0); 
        double theirPrice = theirBid.getPrice();
        if(theirPrice > currentLowestPrice) {
//            throw new Exception("");
            redirectAttributes.addFlashAttribute("notLowest", true);
            redirectAttributes.addFlashAttribute("currentLowest", currentLowestPrice);
            return "redirect:/jobs";
        }
        Bid newBid= new Bid(theirPrice, jobService.getAJob(id), bidder);
        bidService.addBid(newBid);


        return "index";
    }

    	// principal allows you to find out who they are
    	// id allows you to know the job on which the user is bidding so you can find the job

    	// 1. Add the job to the model
    	// 2. Show the user details of the job in HTML
    	// 2. Add a form to the HTML that reads the amount of money from the user, creating a form
    	// class for it as well for form binding
    	// 3. The method in the form is POST
    	// 4. The action might be "/bid/add/{id}"
    	// 5. Create a post controller for the endpoint that finds the job and the user and creates a bid
    	// from the job, user and amount of money
    	// 6. Save this bid to the database
       //.addBid(job, user, amount);




    @GetMapping("/getBids")
    public String getAllUserBids( Model model) {
    //	String email = userService.findUser(email) ;
//        bidService.getAllBid()
        List<Object[]> jobsAndTheirLowestBid = jobService.findByisOpen(false).stream().map(job ->
                new Object[]{
                        job,
                        job.getBids().stream()
                                .min((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())).orElseGet(() -> null)
                }).collect(Collectors.toList());

    	 model.addAttribute("jobsAndTheirLowestBid", jobsAndTheirLowestBid);
    	 model.addAttribute("jobs", jobService.findByisOpen(false));
    	 return "ShowBids";
    }

    @GetMapping("/users/{username}/bid/{id}")
    public Job getBid(@PathVariable String id) {
        return null;
        //  return bidService.getOneBid(id);
    }

    @GetMapping("/users/{username}/bids/{id}/{amount}")
    public void updateLot(@RequestBody Job job, @PathVariable String username, @PathVariable String id, @PathVariable double amount) {
        // bidService.updateBid(job,amount);
    }

}
