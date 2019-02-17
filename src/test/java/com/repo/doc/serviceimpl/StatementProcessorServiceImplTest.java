package com.repo.doc.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.repo.doc.service.StatementProcessorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.repo.doc.model.Record;

@RunWith(MockitoJUnitRunner.class)
@Configuration
@Profile("dev")
public class StatementProcessorServiceImplTest {
	
	@InjectMocks
	StatementProcessorServiceImpl statementProcessorServiceImpl;
	@Mock 
	ClassLoader classLoader;

	@Test
	public void test_csv_valid_report() throws Exception {
	
		List<Record> successTranaction =statementProcessorServiceImpl.getCsvReportDetails();
		assertNotNull(successTranaction);
		assertEquals(10,successTranaction.size());
	}

	@Test(expected=Exception.class)
	public void test_xml_valid_report() throws Exception {
		statementProcessorServiceImpl.getxmlReportDetails();
	}

}
