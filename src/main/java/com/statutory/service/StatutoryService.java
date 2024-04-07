

package com.statutory.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.statutory.model.StatutoryModel;
import com.statutory.repository.StatutoryRepository;


import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StatutoryService {
	
    @Autowired
    private  StatutoryRepository statutoryRepository;
    
    
    private final String uploadDir = "C:\\Users\\piyush_kumar\\Desktop\\StatutoryDetails"; 

   
    public StatutoryModel getByempId(Long empid) {
        Optional<StatutoryModel> statutory = statutoryRepository.findByempid(empid);
        return statutory.orElse(null);
    }
    
    
    

//    public List<StatutoryModel> getAll() {
//        return statutoryRepository.findAll();
//    }

    
    
 public void insert(Long empid, String panNumber, Long aadharNumber, String nameAadhar, String Uan,
            String fatherHusbandName, String relation, String memberPF, String intlWorker, String spAbled,
            String pfbankName, String pfbankAccount, String pfbankIfsc, String prevCompany, MultipartFile file1,
            MultipartFile file2,String folder) throws IOException {

         
   
        StatutoryModel statutory = new StatutoryModel();
        statutory.setEmpid(empid);
        statutory.setPanNumber(panNumber);
        
        
        statutory.setAadharNumber(aadharNumber);
        
        String AadharFile=file1.getOriginalFilename();
        String PanFile=file2.getOriginalFilename();
        
        statutory.setAadaharCopy(AadharFile);
        statutory.setPanCopy(PanFile);
        
        statutory.setNameAadhar(nameAadhar);
        statutory.setUan(Uan);
        statutory.setFatherHusbandName(fatherHusbandName);
        statutory.setRelation(relation);
        statutory.setMemberPF(memberPF);
        statutory.setIntlWorker(intlWorker);
        statutory.setSpAbled(spAbled);
        statutory.setPfbankName(pfbankName);
        statutory.setPfbankAccount(pfbankAccount);
        statutory.setPfbankIfsc(pfbankIfsc);
        statutory.setPrevCompany(prevCompany);
        statutory.setFolder(folder);
 

        statutoryRepository.save(statutory);

        
        Path aadharFilePath = Path.of(uploadDir, folder, AadharFile);
        Path panFilePath = Path.of(uploadDir, folder, PanFile);

        Files.createDirectories(aadharFilePath.getParent());
        Files.createDirectories(panFilePath.getParent());

        Files.copy(file1.getInputStream(), aadharFilePath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(file2.getInputStream(), panFilePath, StandardCopyOption.REPLACE_EXISTING);

    }
    
    
    
    
    
    
    // getByfolderandFileName
    
    public File getFile(String folder, String fileName) {
        return Path.of(uploadDir, folder, fileName).toFile();
    }
}









