package org.cigma.ecom.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cigma.ecom.model.User;
import org.cigma.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImp implements IUserService {

	@Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User insertUser(User u) {
    	//u.setId(null);
    	
        if (repository.existsUsername(u.getUsername()) || repository.existsEmail(u.getEmail())){
            System.out.println("Signup refused !!!");
            System.out.println("ID = " + u.getId() + " ~ Username = " + u.getUsername() + " ~ Email = " + u.getEmail());
            return null;
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()),u.getPassword());
        u.setRole("USER");
        return repository.save(u);
    }

    @Override
    public User updateUser(User u, String username) {
        if (username.equals(u.getUsername())){
            User old = repository.findById(u.getId()).get();
            old.setVille(u.getVille());
            old.setTele(u.getTele());
            old.setPrenom(u.getPrenom());
            old.setNom(old.getNom());
            if (!repository.existsEmail(u.getEmail()))
                old.setEmail(u.getEmail());
            return repository.save(old);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id, String username) {
        User u = repository.findById(id).get();
        if (u.getUsername() == username)
            repository.deleteById(id);
    }

    @Override
    public Optional<User> selectUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> selectAll() {
        return (List<User>) repository.findAll();
    }
}
