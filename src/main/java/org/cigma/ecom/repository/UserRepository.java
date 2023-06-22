package org.cigma.ecom.repository;

import org.cigma.ecom.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {
	
	   @Query("select case when count(u)> 0 then true else false end from User u where lower(u.username) like lower(:username)")
	    public boolean existsUsername(@Param("username") String username);

	    @Query("select case when count(u)> 0 then true else false end from User u where lower(u.email) like lower(:email)")
	    public boolean existsEmail(@Param("email") String email);

	    public User findUserByUsername(String username);

}
