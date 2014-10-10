package com.wolfbeisz.web.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wolfbeisz.model.Comment;
import com.wolfbeisz.model.Document;
import com.wolfbeisz.model.File;
import com.wolfbeisz.repository.CommentRepository;
import com.wolfbeisz.repository.DocumentRepository;
import com.wolfbeisz.repository.FileRepository;
import com.wolfbeisz.web.service.FileManager;

@Controller
public class DocumentRequestController {

	@Autowired
	private DocumentRepository documentRepo_;
	
	@Autowired
	private CommentRepository commentRepo_;
	
	@Autowired
	private FileManager fileMgr_;
	
	@Autowired
	private FileRepository fileRepo_;
	
	@RequestMapping(method=RequestMethod.GET,value="/document/{id}")
	public String showDocument(Model model, @PathVariable String id)
	{
		Document document = documentRepo_.findOne(Long.parseLong(id));
		model.addAttribute("document", document);
		
		Collection<Comment> comments = commentRepo_.findByDocument(document);
		model.addAttribute("comments", comments);
		
//		model.addAttribute("title", document.getTitle());
//		model.addAttribute("author", document.getUser().getName());
//		model.addAttribute("tags", document.getTags());
		
		return "displayDocument";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/file/{id}/download")
	public void downloadDocument(@PathVariable String id, HttpServletResponse response) throws IOException
	{
		File file = fileRepo_.findOne(Long.parseLong(id));
		
		if (file != null)
		{
			//TODO: return the actual content-type of the file? maybe save it in the db when the file is uploaded. security! 
			//TODO: better file name (e.g. based on the name of the document)
			//TODO: http://stackoverflow.com/questions/20508788/do-i-need-content-type-application-octet-stream-for-file-download
			response.setContentType("application/octet-stream");
			
			ServletOutputStream responseBody = response.getOutputStream();
			fileMgr_.retrieveFile(file.getPath(), responseBody);
		} else {
			response.sendError(404);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/documents")
	public String listAllDocuments(Model model)
	{
		model.addAttribute("documents", documentRepo_.findAll());
		return "listDocuments";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	public String root()
	{
		return "redirect:/documents";
	}
}
