package Emmet.auction;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import Emmet.auction.dao.BidDao;
import Emmet.auction.dao.JobDao;
import Emmet.auction.dao.RoleDao;
import Emmet.auction.dao.UserDao;
import Emmet.auction.domain.Bid;
import Emmet.auction.domain.Job;
import Emmet.auction.domain.Role;
import Emmet.auction.domain.User;
@EnableScheduling
@SpringBootApplication
public class EmmetProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmmetProjectApplication.class, args);
	}

	@Autowired
	BidDao bidDao;
	@Autowired
	JobDao jobDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roledao;
    @Autowired
    PasswordEncoder pw;

    public static Role STANDARD_USER_ROLE;

	@PostConstruct
	public void loadData() {

		STANDARD_USER_ROLE = new Role("all registered users");
		roledao.save(STANDARD_USER_ROLE);


		User  newUser = new User("Dave", "david@efm", pw.encode("1"), "99090", STANDARD_USER_ROLE);
		userDao.save(newUser);

		Job newJob = new Job( "Roof", "Build a roof", newUser,true);
		jobDao.save(newJob);

		User newUser2 = new User("John", "john@efm",pw.encode("2"), "569874", STANDARD_USER_ROLE);
		userDao.save(newUser2);

		Job newJob2 = new Job( "IKEA Kitchen", "Fit a kitchen", newUser2,true);
		jobDao.save(newJob2);
		Job roof_tilingJob = new Job("Roof tiling", "Tile my roof before it's cold please", newUser, false);
		jobDao.save(roof_tilingJob);
		bidDao.save(new Bid(3, roof_tilingJob, newUser));
		bidDao.save(new Bid(10, roof_tilingJob, newUser2));

		Bid newBid = new Bid( 100, newJob, newUser2);
		bidDao.save(newBid);
		bidDao.save(new Bid(45454, newJob2, newUser));

	}

}
