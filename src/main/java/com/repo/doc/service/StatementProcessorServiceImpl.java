package com.repo.doc.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.repo.doc.error.ProcessorException;
import com.repo.doc.model.Record;
import com.repo.doc.model.Records;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The Class StatementProcessorServiceImpl.
 */
@Service
public class StatementProcessorServiceImpl implements StatementProcessorService {

	/** The Constant CSV_FILE_NAME. */
	private static final String CSV_FILE_NAME = "records.csv";
	
	/** The Constant SAMPLE_XML_FILE_PATH. */
	private static final String SAMPLE_XML_FILE_PATH = "records.xml";
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(StatementProcessorServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.repo.doc.service.StatementProcessorService#getCsvReportDetails()
	 */
	@Override
	public List<Record> getCsvReportDetails() throws Exception {
		List<Record> ListStatement = null;
		try {

			String filePath = getClass().getClassLoader().getResource(CSV_FILE_NAME).getFile();
			File file = new File(filePath);
			Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
			logger.info("processing the csv file reader");
			CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader).withType(Record.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			logger.info("end the csv file reader");
			ListStatement = csvToBean != null ? csvToBean.parse() : null;
			if (ListStatement == null) {
				logger.error("Empty record ");
				throw new ProcessorException(400, "All transaction references Number should be unique");
			} else if (!isReferencesUnique(ListStatement)) {
				logger.error("Duplicate transaction references Number ");
				throw new ProcessorException(400, "All transaction references Number should be unique");
			} else if (isEndBalanceValid(ListStatement)) {
				logger.error("Invalid end Balance ");
				throw new ProcessorException(400, "Invalid end balance");
			}

		} catch (Exception e) {
			throw new ProcessorException(400, e.getMessage());
		}
		return ListStatement;

	}

	/* (non-Javadoc)
	 * @see com.repo.doc.service.StatementProcessorService#getxmlReportDetails()
	 */
	@Override
	public List<Record> getxmlReportDetails() throws Exception {
		List<Record> listStatement = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(SAMPLE_XML_FILE_PATH).getFile());
		if (!file.exists()) {
			logger.error("File is not present ");
			throw new ProcessorException(400, "File is not present");
		}
		String fileContent = FileUtils.readFileToString(file);
		JAXBContext jaxb = JAXBContext.newInstance(Records.class);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		StringReader stringReader = new StringReader(fileContent);
		Records records = (Records) unmarshaller.unmarshal(stringReader);
		listStatement = records != null ? records.getRecords() : null;

		if (listStatement == null) {
			logger.error("Empty record ");
			throw new ProcessorException(400, "All transaction references Number should be unique");
		} else if (!isReferencesUnique(listStatement)) {
			throw new ProcessorException(400, "All transaction references Number should be unique");
		} else if (isEndBalanceValid(listStatement)) {
			throw new ProcessorException(400, "Invalid End Balance");
		}
		return listStatement;
	}

	/**
	 * Distinct by key.
	 *
	 * @param <T> the generic type
	 * @param keyExtractor the key extractor
	 * @return the predicate
	 */
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	/**
	 * Checks if is references unique.
	 *
	 * @param csvUsers the csv users
	 * @return true, if is references unique
	 */
	private boolean isReferencesUnique(List<Record> csvUsers) {
		return csvUsers.stream().allMatch(distinctByKey(p -> p.getReference()));

	}

	/**
	 * Checks if is end balance valid.
	 *
	 * @param csvUsers the csv users
	 * @return true, if is end balance valid
	 */
	private boolean isEndBalanceValid(List<Record> csvUsers) {
		long count = csvUsers.stream()
				.filter(x -> x.getEndBalance() != (Double
						.parseDouble(new DecimalFormat("##.##").format((x.getStartBalance() + x.getMutation())))))
				.count();
		return count > 0 ? true : false;
	}
	
	

	/**
	 * get the all failure transaction details.
	 *
	 *
	 * 
	 */
	public List<Record> getFailedTransactionDetails() throws IOException {
		List<Record> listStatement = null;
		String filePath = getClass().getClassLoader().getResource(CSV_FILE_NAME).getFile();
		File file = new File(filePath);
		Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
		logger.info("processing the csv file reader");
		CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader).withType(Record.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		logger.info("end the csv file reader");

		listStatement = csvToBean != null ? csvToBean.parse(): null;
		
		Set<Long> nameSet = new HashSet<>();
		List<Record> failureTransaction = listStatement.stream()
		            .filter(e -> !nameSet.add(e.getReference()))
		            .collect(Collectors.toList());
		// invalid end balance consider as failure tranaction
//		List<Record> failureTransactio=listStatement.stream()
//				.filter(x -> x.getEndBalance() != (Double
//						.parseDouble(new DecimalFormat("##.##").format((x.getStartBalance() + x.getMutation())))))
//				.collect(Collectors.toList());

		
		return failureTransaction;
	}

}
