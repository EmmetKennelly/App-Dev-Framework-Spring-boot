package Emmet.auction.service;


import java.util.List;

import Emmet.auction.domain.User;

public interface UserService {

	void saveUser(User user);

    User findUser(String email);

    User updateUser(String email, User user);

    List<User> findAll();
}

