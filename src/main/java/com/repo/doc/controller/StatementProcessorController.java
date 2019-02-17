package com.repo.doc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.repo.doc.service.StatementProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.repo.doc.manager.StatementProcessorManager;
import com.repo.doc.model.InputRequest;
import com.repo.doc.model.Record;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author vinayKonda
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class StatementProcessorController {

	@Autowired
	private StatementProcessorManager statementProcessorManager;




	private final Logger logger = LoggerFactory.getLogger(StatementProcessorController.class);

	/**
	 * Gets the customer statement.
	 *
	 * @param inputRequest the input request
	 * @return the customer statement
	 * @throws Exception the exception
	 */


	@PostMapping("/stament/processor")
	List<Record> getCustomerStatement(@RequestBody InputRequest inputRequest) throws Exception {

		logger.info("controller start",inputRequest.getFileName());
		return statementProcessorManager.getDocumentDetails(inputRequest.getFileName());
	}


}
