package com.datx.repository;

import com.datx.mapper.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findOneByUsername(String username);

	@Query("select count(a) > 0 from Account a where a.username = :username")
	boolean exists(@Param("username") String username);

}