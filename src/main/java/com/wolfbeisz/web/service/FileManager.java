package com.wolfbeisz.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public interface FileManager {
	public void retrieveFile(String key, OutputStream destination) throws IOException;
	public void storeFile(String key, InputStream fileContent) throws IOException;
	public boolean containsFileAssociatedWith(String key) throws IOException;
	public Collection<String> keys() throws IOException;
}
