package com.repo.doc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.repo.doc.manager.StatementProcessorManager;
import com.repo.doc.model.InputRequest;
import com.repo.doc.model.Record;

@RunWith(MockitoJUnitRunner.class)
public class StatementProcessorControllerTest {
	
	@Mock
	StatementProcessorManager statementProcessorManager;


	@InjectMocks
    StatementProcessorController statementProcessorController;
    
    @Test
    public void testReportValidRecord() throws Exception {
    	Record record =new Record();
    	record.setAccountNumber("100");
    	record.setReference(12433L);
    	record.setDescription("Dress");
    	record.setEndBalance(12.12);
    	record.setStartBalance(1.1);
    	record.setMutation(12.11);
    	List<Record> response =new ArrayList<Record>();
    	response.add(record);
    	InputRequest inputRequest =new InputRequest();
    	inputRequest.setFileName("csv");
    	
        when(statementProcessorManager.getDocumentDetails(Mockito.anyString())).thenReturn(response);
        List<Record> finalResponse= statementProcessorController.getCustomerStatement(inputRequest);
        assertEquals("100", finalResponse.get(0).getAccountNumber());
        assertNotNull(finalResponse);

    }
}
