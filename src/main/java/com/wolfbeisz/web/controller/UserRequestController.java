package com.wolfbeisz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wolfbeisz.model.User;
import com.wolfbeisz.repository.UserRepository;

@Controller
public class UserRequestController {
	
	@Autowired
	private UserRepository userRepo_;
	
	@RequestMapping(method=RequestMethod.GET,value="/users")
	public String listAllUsers(Model model)
	{
		Iterable<User> allUsers = userRepo_.findAll();
		model.addAttribute("users", allUsers);
		
		return "listUsers";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/user/{id}")
	public String showUser(Model model, @PathVariable long id)
	{
		User user = userRepo_.findOne(id);
		model.addAttribute("user", user);
		
		return "userDetails";
	}
}
