package Emmet.auction.controllers;

import Emmet.auction.domain.BidForm;
import Emmet.auction.domain.Job;
import Emmet.auction.domain.JobForm;
import Emmet.auction.service.BidService;
import Emmet.auction.service.JobService;
import Emmet.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class JobsController {

    @Autowired
    private BidService bidService;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @GetMapping(value = "/jobs/add")
    public String addJobsPage(Model model) {
        model.addAttribute("jobForm", new JobForm());
        return "AddJob";
    }
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


    @GetMapping("/jobs")
    public String showAllJobs(Model model)
    {
        model.addAttribute("jobs", jobService.findByisOpen(true));
        model.addAttribute("bidForm", new BidForm());
        model.addAttribute("bids", bidService.getAllBid());

        return "showjobs";
    }

//    @PostMapping("/jobs")
//    public String postJobBid(Model model, BidForm postedBidForm, Principal biddingUser) {
//        String biddingUserName = biddingUser.getName();
//        Bid bid = new Bid(postedBidForm.getPrice(), jobService.findByisOpen(), userService.findUser(biddingUserName));
//    }
}
