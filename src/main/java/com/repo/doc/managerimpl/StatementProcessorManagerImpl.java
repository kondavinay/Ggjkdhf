package com.repo.doc.managerimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repo.doc.manager.StatementProcessorManager;
import com.repo.doc.model.Record;
import com.repo.doc.service.StatementProcessorService;

/**
 * The Class StatementProcessorManagerImpl.
 */
@Component			
public class StatementProcessorManagerImpl implements StatementProcessorManager {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(StatementProcessorManagerImpl.class);
	
	/** The statement processor service. */
	@Autowired
	private StatementProcessorService statementProcessorService;

	/* (non-Javadoc)
	 * @see com.repo.doc.manager.StatementProcessorManager#getDocumentDetails(java.lang.String)
	 */
	@Override
	public List<Record> getDocumentDetails(String fileName) throws Exception {
		logger.info("StatementProcessorManagerImpl :: getDocumentDetails start", fileName);
		fileName = fileName == null ? "csv" : fileName;
		if (fileName.equalsIgnoreCase("csv")) {
			logger.info("Invoke the csv report details");
			return statementProcessorService.getCsvReportDetails();
		} else if(fileName.equalsIgnoreCase("xml")){
			logger.info("Invoke the xml report details");
			return statementProcessorService.getxmlReportDetails();
		}else {
			return statementProcessorService.getFailedTransactionDetails();
		}
	}
}
