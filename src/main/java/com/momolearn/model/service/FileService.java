package com.momolearn.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Value("${upload.path}")
    private String uploadPath; // "src/main/webapp/img"

	//유저 프로필 파일 저장
	//필요한 정보: 사진 파일, 유저Id(저장할이름)
	public String getProfile(String memId, MultipartFile file) throws IOException {
		//fileName: 원래 파일명
		String fileName = file.getOriginalFilename();
		
		//fileExtension: 확장자명
	    //String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
	    
	    //savedFileName: 디렉토리에 저장될 파일명[유저id.확장자]
	    String savedFileName = memId + ".jpg" ;// + fileExtension;
	    
	    try {
	        //프로필 사진 저장할 경로
	        String savePath = uploadPath + "/profile/"; // src/main/webapp/img/profile/
	        
	        //file.getBytes(): 파일을 다운받기 위해 byte배열로 변환함
	        byte[] bytes = file.getBytes();
	        
	        //src/main/webapp/img/profile/[유저id.확장자]
	        Path path = Paths.get(savePath + savedFileName);
	        
	        //path에 파일(bytes)을 저장함
	        Files.write(path, bytes);
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        e.getMessage();
	    }
	    //DB에 저장할 image명 리턴
		return savedFileName;
	}
	
	
	//강의 대표사진 저장
	//필요한 정보: 사진 파일, 강의명(저장할이름)
	public String getLectureImage(String lectureName, MultipartFile file) throws IOException {
		//fileName: 원래 파일명
		String fileName = file.getOriginalFilename();
		//fileExtension: 확장자명
	    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
	    //savedFileName: 저장될 파일명[유저id.확장자]
	    String savedFileName = lectureName + "." + fileExtension;
	    
	    try {
	        //프로필 사진 저장할 경로
	        String savePath = uploadPath + "/lecture/"; // src/main/webapp/img/lecture/
	        //file.getBytes(): 파일을 다운받기 위해 byte배열로 변환함
	        byte[] bytes = file.getBytes();
	        //src/main/webapp/img/profile/[유저id.확장자]
	        Path path = Paths.get(savePath + savedFileName);
	        //path에 파일(bytes)을 저장함
	        Files.write(path, bytes);
	    } catch (IOException e) {
	        e.printStackTrace();
	        e.getMessage();
	    }
	    //DB에 저장할 image명 리턴
		return savedFileName;
	}
}
