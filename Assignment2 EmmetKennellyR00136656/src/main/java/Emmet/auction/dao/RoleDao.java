package Emmet.auction.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Emmet.auction.domain.Role;

public interface RoleDao extends JpaRepository<Role, Long>{
}