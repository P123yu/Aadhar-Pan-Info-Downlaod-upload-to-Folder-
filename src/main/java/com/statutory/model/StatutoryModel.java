package com.statutory.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="statutory")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StatutoryModel {
	@Id
	@GeneratedValue
	private Long id;
	private Long empid;
	private String panNumber;
	private String panCopy;
	private Long aadharNumber;
	private String aadaharCopy;
	private String nameAadhar;
	private String Uan;
	private String fatherHusbandName;
	private String relation;
	private String memberPF;
	private String intlWorker;
	private String spAbled;
	private String pfbankName;
	private String pfbankAccount;
	private String pfbankIfsc;
	private String prevCompany;
	private String folder;
	
	
//	@Lob
//	@Column(columnDefinition="LONGBLOB")
//	private byte[] panData;
//	@Lob
//	@Column(columnDefinition="LONGBLOB")
//	private byte[] aadharData;
	
	
    }
