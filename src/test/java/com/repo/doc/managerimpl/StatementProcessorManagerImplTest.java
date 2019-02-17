package com.repo.doc.managerimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.repo.doc.model.Record;
import com.repo.doc.service.StatementProcessorService;

@RunWith(MockitoJUnitRunner.class)
public class StatementProcessorManagerImplTest  {

	@Mock
	StatementProcessorService statementProcessorService;
    @InjectMocks
    StatementProcessorManagerImpl statementProcessorManager;
    

    
    @Test
    public void test_csv_valid_report() throws Exception {
    	Record record =new Record();
    	record.setAccountNumber("100");
    	record.setReference(12433L);
    	record.setDescription("Dress");
    	record.setEndBalance(12.12);
    	record.setStartBalance(1.1);
    	record.setMutation(12.11);
    	List<Record> mockResponse =new ArrayList<Record>();
    	mockResponse.add(record);
    	when(statementProcessorService.getCsvReportDetails()).thenReturn(mockResponse);
    	 List<Record> finalResponse= statementProcessorManager.getDocumentDetails("csv");
         assertEquals("100", finalResponse.get(0).getAccountNumber());
         assertNotNull(finalResponse);
    	
    }
    
    
    @Test
    public void test_xml_valid_report() throws Exception {
    	Record record =new Record();
    	record.setAccountNumber("100");
    	record.setReference(12433L);
    	record.setDescription("Dress");
    	record.setEndBalance(12.12);
    	record.setStartBalance(1.1);
    	record.setMutation(12.11);
    	List<Record> mockResponse =new ArrayList<Record>();
    	mockResponse.add(record);
    	when(statementProcessorService.getxmlReportDetails()).thenReturn(mockResponse);
    	 List<Record> finalResponse= statementProcessorManager.getDocumentDetails("xml");
         assertEquals("100", finalResponse.get(0).getAccountNumber());
         assertNotNull(finalResponse);
    	
    }
	
}
