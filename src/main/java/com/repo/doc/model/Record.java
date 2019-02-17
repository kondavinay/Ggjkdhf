package com.repo.doc.model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.opencsv.bean.CsvBindByName;

/**
 * @author iyappan_b
 *
 */
@XmlRootElement(name="record")
public class Record {

	
	@CsvBindByName(column = "Reference")
	private Long reference;
	
	@CsvBindByName(column = "AccountNumber")
	private String accountNumber;

	@CsvBindByName(column = "Description")
	private String description;

	@CsvBindByName(column = "Start Balance")
	private Double startBalance;

	
	@CsvBindByName(column = "Mutation")
	private Double mutation;

	@CsvBindByName(column = "End Balance")
	private Double endBalance;

	public Long getReference() {
		return reference;
	}

	@XmlAttribute(name = "reference")
	public void setReference(Long reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	@XmlElement(name = "accountNumber")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getStartBalance() {
		return startBalance;
	}

	@XmlElement(name = "startBalance")
	public void setStartBalance(Double startBalance) {
		this.startBalance = startBalance;
	}

	public Double getMutation() {
		return mutation;
	}

	@XmlElement(name = "mutation")
	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	@XmlElement(name = "endBalance")
	public Double getEndBalance() {
		return endBalance;
	}

	
	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}



}
