package go4code.zadatak1.services;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileHandlerImpl implements FileHandler{

	@Override
	public File getLogs() {
		String path = "C:\\Users\\Stefan\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\go4code-zadatak1\\logs";
		
		File log = new File(path);
		
		return log;
	}

}
