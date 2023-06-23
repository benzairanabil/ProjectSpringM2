package org.cigma.ecom.service;

import org.cigma.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //ADMIN ROLE
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList( "ROLE_ADMIN");
        //USER ROLE
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        //GUEST ROLE
        List<GrantedAuthority> authorityListGuest = AuthorityUtils.createAuthorityList("ROLE_GUEST");
        //Account
        org.cigma.ecom.model.User u;
        //Authority
        List<GrantedAuthority> comptAuthority;

        if (userRepo.existsUsername(username)){
            u = userRepo.findUserByUsername(username);
            if(u.getRole().equalsIgnoreCase("ADMIN"))
            	comptAuthority = authorityListAdmin;
            else 
            	comptAuthority = authorityListUser;
        }else {
            System.out.println("Compt NOT-FOUND");
            //throw new UsernameNotFoundException("Compt NOT-FOUND");
            u = new org.cigma.ecom.model.User();
            comptAuthority = authorityListGuest;
        }

        User user = new User(u.getUsername(), u.getPassword(), comptAuthority);
        System.out.println("User  Username "+user.getUsername()+" Role: "+user.getAuthorities());
        return user;

//        if ("cigma".equals(username)) {
//
//            return new User("cigma", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }

}
