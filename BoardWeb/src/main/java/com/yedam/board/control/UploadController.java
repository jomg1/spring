package com.yedam.board.control;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.board.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	//페이지 열어주기
	@GetMapping("/uploadForm")
	public void uploadFomr() {
		log.info("uploadForm...");
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax...");
	}
	
	//파일 업로드
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile) {
		String uploadFolder = "c:\\upload";
		for(MultipartFile multipartFile : uploadFile) {
			log.info("---------");
			log.info("Upload FileName: " + multipartFile.getOriginalFilename());
			log.info("file Size: " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//파일 업로드 Ajax
	@PostMapping(value="/uploadAjaxAction", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	//return 값 필요 없으므로 void
	@ResponseBody//반환되는 타입 정의
	public List<AttachFileDTO> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<AttachFileDTO> list = new ArrayList<>();
		
		String uploadFolder = "c:\\upload";
		String uploadFolderPath = getFolder();
		// folder setting
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info(uploadPath);
		
		if(uploadPath.exists() == false){ // 폴더가 존재하는지 체크 후 폴더 생성
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			attachDTO.setFileName(uploadFileName); // uuid 생성 전 파일 네임으로 attachDTO setting
			
			// 중복된 파일 이름을 방지 UUID
			UUID uuid = UUID.randomUUID(); // 문자열을 중복되지 않도록 랜덤 생성.
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info(uploadFileName);
			
			// File saveFile = new File(uploadFolder, uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString()); // attachDTO setting
				attachDTO.setUploadPath(uploadFolderPath); // attachDTO setting
				
				// 이미지 파일이면 썸네일 이미지 생성
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true); // attachDTO setting
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200);
					thumbnail.close();
				}
			}  catch (Exception e) {
				e.printStackTrace();
			}
			list.add(attachDTO); // attachDTO setting
		} // end of for
		return list;
	} // end of uploadAjaxPost
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		File file = new File("c:\\upload\\" + fileName);
		ResponseEntity<byte[]> result = null;
		
		byte[] files;
		try {
			files = FileCopyUtils.copyToByteArray(file);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", Files.probeContentType(file.toPath())); // image/jpg, image/png ...
		result = new ResponseEntity<byte[]>(files, headers, HttpStatus.OK);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 업로드 파일을 저장할 폴더를 날짜별로 생성해서 저장하기 위한 폴더네이밍
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator); // 윈도우 2023\05\23 , 리눅스 2023/05/23 등
	}
	
	// 이미지 파일 여부 체크
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType); // image/PNG
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
