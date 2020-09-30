package Emmet.auction.service;


import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Emmet.auction.dao.JobDao;
import Emmet.auction.dao.UserDao;
import Emmet.auction.domain.User;


import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
	private PasswordEncoder passwordEncoder;
	 @Autowired
	    private UserDao userdao;
	    @Autowired
	    private JobDao jobdao;

	    @Override
	    public void saveUser(User user) {
	    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userdao.save(user);
	    }
	    @Override
	    // Input: email address
	    // returns the user found through findById()
	    public User findUser(String email) {
	        return userdao.findUserByEmail(email);
	    }

		@Override
		public User updateUser(String email, User user) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public List<User> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

	}

