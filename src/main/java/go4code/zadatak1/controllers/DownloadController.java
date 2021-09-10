package go4code.zadatak1.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import go4code.zadatak1.services.FileHandler;

@RestController
@RequestMapping(path = "/downloadFile")
public class DownloadController {

	@Autowired
	private FileHandler fileHandler;
	
	@PostMapping(path = "/download")
	public ResponseEntity<?> downloadLogs(){
		try {
			File file = fileHandler.getLogs();
			
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("content-disposition", "attachment; filename=" + "logs.txt");
	        
			
			return ResponseEntity.ok()
		            .headers(responseHeaders)
		            .contentLength(file.length())
		            .contentType(MediaType.parseMediaType("application/octet-stream"))
		            .body(resource);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}
}
