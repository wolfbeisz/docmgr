package com.wolfbeisz.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Splitter;
import com.wolfbeisz.model.Comment;
import com.wolfbeisz.model.Document;
import com.wolfbeisz.model.File;
import com.wolfbeisz.model.Tag;
import com.wolfbeisz.model.User;
import com.wolfbeisz.repository.CommentRepository;
import com.wolfbeisz.repository.DocumentRepository;
import com.wolfbeisz.repository.FileRepository;
import com.wolfbeisz.repository.UserRepository;
import com.wolfbeisz.web.service.FileManager;

@Controller
public class DocumentCommandController {

	@Autowired
	private DocumentRepository documentRepo_;
	
	@Autowired
	private UserRepository userRepo_;
	
	@Autowired
	private CommentRepository commentRepo_;
	
	@Autowired
	private FileManager fileMgr_;
	
	@Autowired
	private FileRepository fileRepo_;
	
	@RequestMapping(method=RequestMethod.GET, value="/newDocument")
	public String showCreationDialogue()
	{
		return "newDocumentForm";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/newDocument")
	public String createDocument(Model model, @RequestParam(required=false) String title, @RequestParam(required=false) String author, @RequestParam(required=false) String tags, @RequestParam(required=false) MultipartFile document) throws IOException
	{
		try {
		User defaultUser = userRepo_.findOne(1L);
		
		Document d = new Document();
		d.setTitle(title);
		d.setCreatedon(new Timestamp((new Date()).getTime()));
		d.setUser(defaultUser);
		d.setTags(extractTags(tags));
		
		Document savedDocument = documentRepo_.save(d);
		
		//store file
		String fileIdentifier = String.valueOf(System.nanoTime());
		fileMgr_.storeFile(fileIdentifier, document.getInputStream());
		
		File file = new File();
		file.setPath(fileIdentifier);
		file.setDocument(savedDocument);
		fileRepo_.save(file);
		
		return "redirect:/document/"+savedDocument.getDocumentid();
		}
		catch (Exception e)
		{
			//error handling
			model.addAttribute("title", title);
			model.addAttribute("author", author);
			model.addAttribute("tags", tags);
			
			return "newDocumentForm";
		}
		
		//TODO: check whether the input values are correct and try to add the document to the db
		//if any of the steps fails => show an error message
		//else: redirect to the new document
	}
	
	private List<Tag> extractTags(String tagsEnteredByUser)
	{
		ArrayList<Tag> tags = new ArrayList<>();
		if (tags != null ){
			for (String tag : Splitter.on(' ').trimResults().omitEmptyStrings().split(tagsEnteredByUser)){
				Tag t = new Tag();
				t.setText(tag);
				tags.add(t);
			}
		}
		return tags;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/document/{id}/delete")
	public String deleteDocument(@PathVariable("id") long id)
	{
		documentRepo_.delete(documentRepo_.findOne(id));
		return "redirect:/documents";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/document/{id}/comment")
	public String addComment(@PathVariable("id") long id, @RequestParam("commentText") String commentText)
	{
		Document document = documentRepo_.findOne(id);
		
		User defaultUser = userRepo_.findOne(1L); //TODO
		
		Comment comment = new Comment();
		comment.setDocument(document);
		comment.setText(commentText);
		comment.setUser(defaultUser);
		commentRepo_.save(comment);
		
		return "redirect:/document/"+id;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/document/{id}/checkout")
	public String checkout(@PathVariable("id") long id)
	{
		
		return "redirect:/document/"+id;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/document/{id}/checkout")
	public String checkin(@PathVariable("id") long id)
	{
		return "redirect:/document/"+id;
	}
}
