package com.repo.doc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.repo.doc.model.Record;

@Service
public interface StatementProcessorService {

	public List<Record> getCsvReportDetails()throws Exception;
	public List<Record> getxmlReportDetails()throws Exception;
	public List<Record> getFailedTransactionDetails()throws Exception;
}

