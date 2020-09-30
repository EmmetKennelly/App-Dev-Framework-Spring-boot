package Emmet.auction.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Emmet.auction.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {
	 User findUserByPhoneNumber(int phoneNumber);
	 User findUserByEmail(String email);

}

