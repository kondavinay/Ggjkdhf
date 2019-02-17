package com.repo.doc.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.repo.doc.model.Record;

@Component
public interface StatementProcessorManager {

	public List<Record> getDocumentDetails(String fileName)throws Exception;
}
