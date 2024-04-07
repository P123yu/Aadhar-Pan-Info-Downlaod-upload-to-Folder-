package com.statutory.controller;

import java.io.File;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.statutory.model.StatutoryModel;
import com.statutory.service.StatutoryService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class StatutoryController {
	
	@Autowired
	private StatutoryService statutoryService;
	
	@GetMapping("/getByempId/{empId}")
	public ResponseEntity<?>getByempId(@PathVariable Long empId){
		StatutoryModel statutory=statutoryService.getByempId(empId);
		if(statutory!=null) {
			return ResponseEntity.ok(statutory);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
		}
	}
	
	
//	@GetMapping("/getAll")
//	public ResponseEntity<?>getAll(){
//		List<StatutoryModel> statutory=statutoryService.getAll();
//		if(statutory!=null) {
//			return ResponseEntity.ok(statutory);
//		}
//		else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
//		}
//	}
//	
//	
	
	
	@PostMapping("/post")
	public ResponseEntity<?>insert(@RequestParam Long empid,@RequestParam String panNumber,
			@RequestParam Long aadharNumber,@RequestParam String nameAadhar,@RequestParam String Uan,
			@RequestParam String fatherHusbandName,@RequestParam String relation,@RequestParam String memberPF,@RequestParam String intlWorker,
			@RequestParam String spAbled,@RequestParam String pfbankName,@RequestParam String pfbankAccount,
			@RequestParam String pfbankIfsc,@RequestParam String prevCompany,@RequestParam MultipartFile file1, @RequestParam MultipartFile file2,@RequestParam String folder) throws IOException{
		
		try{
			statutoryService.insert(empid,  panNumber,aadharNumber,nameAadhar, Uan, fatherHusbandName,relation,memberPF, intlWorker,spAbled, pfbankName, pfbankAccount,pfbankIfsc, prevCompany, file1,file2,folder);
			return ResponseEntity.ok("File uploaded successfully!");
		}
		catch(IOException e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
	
	}
	
	
	@GetMapping("/download")
	   public ResponseEntity<FileSystemResource> downloadPdf(@RequestParam("folder") String folder,
	                                               @RequestParam("fileName") String fileName) {
		   
		   System.out.print(folder+" "+fileName);
	       File file = statutoryService.getFile(folder, fileName);

	       HttpHeaders headers = new HttpHeaders();
	       headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

	       return ResponseEntity.ok()
	               .headers(headers)
	               .contentLength(file.length())
	               .contentType(MediaType.APPLICATION_PDF)
	               .body(new FileSystemResource(file));
	}	

}
