package org.cigma.ecom.service;

import java.util.List;
import java.util.Optional;

import org.cigma.ecom.model.User;

public interface IUserService {
	User insertUser(User u);
	User updateUser(User u,String username);
    void deleteUser(Long id, String username);
    Optional<User> selectUserById(Long id);
    List<User> selectAll();
}
