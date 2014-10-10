package com.wolfbeisz.web.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;

public class LocalFileManager implements FileManager {

	private Path targetDir_;
	
	private LocalFileManager(String directoryPath) throws IOException{
		targetDir_ = Paths.get(directoryPath);
		
		if(!Files.exists(targetDir_)){
			Files.createDirectories(targetDir_);
		}
	}
	

	/**
	 * This static factory method creates and returns a 
	 * VideoFileManager object to the caller. Feel free to customize
	 * this method to take parameters, etc. if you want.
	 * 
	 * @return
	 * @throws IOException
	 */
	public static LocalFileManager get(String path) throws IOException {
		Preconditions.checkNotNull(path);
		return new LocalFileManager(path);
	}
	
	private boolean validateKey(String key)
	{
		return !key.contains("/");
	}
	


	@Override
	public void storeFile(String key, InputStream fileContent) throws IOException {
		// TODO Auto-generated method stub
		if (!validateKey(key))
		{
			throw new IllegalArgumentException("key is invalid");
		}
		
		Path targetFilePath  = targetDir_.resolve(key);
		Files.copy(fileContent, targetFilePath);
	}

	@Override
	public boolean containsFileAssociatedWith(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<String> keys() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void retrieveFile(String key, OutputStream destination) throws IOException
			 {
		// TODO Auto-generated method stub
		if (!validateKey(key))
		{
			throw new IllegalArgumentException("key is invalid");
		}
		
		Path targetFilePath  = targetDir_.resolve(key);
		InputStream file = null;
		try {
			file = Files.newInputStream(targetFilePath, StandardOpenOption.READ);
			ByteStreams.copy(file, destination);
		} catch (IOException e) {
			try {
				if (file != null)
				{
					file.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw e;
		}
	}

}
