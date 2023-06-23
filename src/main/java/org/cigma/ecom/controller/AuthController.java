package org.cigma.ecom.controller;

import org.cigma.ecom.dto.UserDto;
import org.cigma.ecom.dto.UserLoginRequest;
import org.cigma.ecom.dto.UserLoginResponse;
import org.cigma.ecom.exceptions.UserException;
import org.cigma.ecom.model.User;
import org.cigma.ecom.service.IUserService;
import org.cigma.ecom.service.JwtUserDetailsService;
import org.cigma.ecom.service.SmtpService;
import org.cigma.ecom.util.CheckUser;
import org.cigma.ecom.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    IUserService userService;
    @Autowired
    SmtpService smtpService;
    @Autowired
    CheckUser checkUser;
    ModelMapper modelMapper = new ModelMapper();
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createAuthenticationToken(@RequestBody UserLoginRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Token : " + token);
        return token;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Authenticate Username : "+username);
        } catch (DisabledException e) {
            //throw new Exception("USER_DISABLED", e);
            throw new UserException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            //throw new Exception("INVALID_CREDENTIALS", e);
            throw new UserException("INVALID_CREDENTIALS");
        }
    }

    @PostMapping(value = "/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> clientSignUp(@RequestBody UserDto userDto) throws Exception {
        HttpStatus status; 		
        System.out.println("UserNAme"+userDto.getUsername());
		User user = modelMapper.map(userDto, User.class);
		System.out.println("user serv "+user.getUsername());
		user = userService.insertUser(user);
        if (user == null)
            status = HttpStatus.NOT_ACCEPTABLE;
        else {
            status = HttpStatus.OK;
            //send Mail
            //smtpService.SendMail(user);
            //Just for security the password don't return to client
            //user.setPassword("xxxxxx", user.getPassword());
        }
        
        return new ResponseEntity<>(modelMapper.map(user, UserLoginResponse.class),status);

    }
    /*
    @PostMapping(value = "/admin/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adminSignUp(@RequestBody Admin admin) throws Exception {
        HttpStatus status;
        admin = aService.insertAdmin(admin);
        if (admin == null)
            status = HttpStatus.NOT_ACCEPTABLE;
        else {
            status = HttpStatus.OK;
            //send Mail
            smtpService.SendMail(admin);
            //Just for security the password don't return to client
            admin.setPassword("xxxxxx", admin.getPassword());
        }
        return new ResponseEntity<>(admin, status);
    }
	*/
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUser(@RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return userDetailsService.loadUserByUsername(username).getAuthorities().toString();
    }
}
