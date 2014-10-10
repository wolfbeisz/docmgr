package com.wolfbeisz.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wolfbeisz.model.Document;
import com.wolfbeisz.repository.DocumentRepository;

@Controller
public class SearchRequestController {
	@Autowired
	private DocumentRepository documentRepo_;
	
	@RequestMapping(method=RequestMethod.GET,value="/documents/findByTitle")
	public String searchByName(Model model, @RequestParam("title") String title)
	{
		Collection<Document> documents = documentRepo_.findByTitle(title);
		
		model.addAttribute("documents", documents);
		model.addAttribute("title", title);
		return "listSearchResults"; 
	}
}
