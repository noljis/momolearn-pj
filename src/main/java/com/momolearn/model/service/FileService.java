package com.momolearn.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.momolearn.exception.MessageException;

@Service
public class FileService {
	
	@Value("${upload.path}")
    private String uploadPath;

	public String getProfile(String memId, MultipartFile file) throws IOException {
	    
	    String savedFileName = memId + ".jpg" ;
	    
	    try {
	    	
	        String savePath = uploadPath + "/profile/";
	        
	        byte[] bytes = file.getBytes();
	        
	        Path path = Paths.get(savePath + savedFileName);
	        
	        Files.write(path, bytes);
	        
	    } catch (IOException e) {
	    	
	        e.printStackTrace();
	        e.getMessage();
	    }
	    
		return savedFileName;
	}
	
	
	public String getLectureImage(String lectureName, MultipartFile file) throws MessageException, IOException {

		String savedFileName = null;
	    	
	    try {
	        
	    	String fileName = file.getOriginalFilename();
	    	String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
	    	savedFileName = lectureName + "." + fileExtension;
	    	
	        String savePath = uploadPath + "/lecture/";
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get(savePath + savedFileName);
	        Files.write(path, bytes);
	        
	    } catch (IOException e) {
	    	
	        throw new MessageException("썸네일 업로드 중 오류가 발생하였습니다.");
	        
	    }
	        
		return savedFileName;
	}
	
}
