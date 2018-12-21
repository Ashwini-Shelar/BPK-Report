package com.ln.bpk.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ln.bpk.model.BPKReportModel;
import com.ln.bpk.model.StatusAudit;
import com.ln.bpk.service.BPKReportService;
import com.ln.bpk.service.BPKReportServiceImpl;
import com.ln.bpk.utils.CommonConstants;
import com.ln.bpk.utils.SendMail;

//@Component
@RestController
@RequestMapping("/api")
public class GenerateReport {

	@Autowired
	private BPKReportService reportService;

	@Autowired
	private BPKReportServiceImpl reportServiceImpl;

	@Value("${serverEnvironment}")
	private String serverEnvironmentProp;

	@Value("${folderPath}")
	private String folderPathProp;

	@Value("${bukalapakRepotFileName}")
	private String bukalapakRepotFileNameProp;

	private static final Logger logger = LoggerFactory.getLogger(GenerateReport.class);

	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public void getNowCreatedOrder() {
		List<List> orderList = null;
		Map<Integer, BPKReportModel> sheetData = new HashMap<Integer, BPKReportModel>();

		BPKReportModel header = new BPKReportModel();
		header.setOrderNo("OrderNo");
		header.setSttNumber("STT Number");
		header.setLastWebhookStatus("Webhook Request Body");
		header.setWebhookRequest("Last Weebhook Status");
		header.setTimestamp("Last status timestamp");

		sheetData.put(1, header);
		Integer rowCount = 1;
		BPKReportModel reportDataRecord = null;

		orderList = reportService.getTodaysCreatedOrderNumbers();

		logger.info("orderList={}", orderList.size());
		Iterator<List> iterator = orderList.iterator();
		/*
		 * while (iterator.hasNext()) { logger.info("order no={}", iterator.next()); }
		 */

		List<StatusAudit> historyRecord = null;
		while (iterator.hasNext()) {
			historyRecord = this.getOrderHistoryByOrderNo(iterator.next().get(0).toString());
			reportDataRecord = this.getLastWebHookRequest(historyRecord);
			sheetData.put(++rowCount, reportDataRecord);
		}

		generateBukalapakWebhookDailyReport(sheetData);

	}

	public void generateBukalapakWebhookDailyReport(Map<Integer, BPKReportModel> sheetData) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("BPK Report");

		// Iterate over data and write to sheet
		Set<Integer> keyset = sheetData.keySet();
		int rownum = 0;
		int dataColumsForSheet = 5;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			BPKReportModel rowData = sheetData.get(key);
			ArrayList<String> list = new ArrayList<>();
			list.add(rowData.getOrderNo());
			list.add(rowData.getSttNumber());
			list.add(rowData.getWebhookRequest());
			list.add(rowData.getLastWebhookStatus());
			list.add(rowData.getTimestamp());
			int cellnum = 0;
			for (int i = 0; i < list.size(); i++) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(list.get(i));
			}

		}
		try {
			// Write the workbook in file system
			// String fileName = "BukalapakWebhookHistory"+new Date();
			String env = serverEnvironmentProp;
			String folderPath = null;
			if (env.equalsIgnoreCase("UAT") || env.equalsIgnoreCase("DEV") || env.equalsIgnoreCase("Production")) {
				// folderPath = PropertyReader.getProperty("folderPath");
				folderPath = folderPathProp;
			} else {
				folderPath = folderPathProp;
				File directory = new File(folderPathProp);
				if (!directory.exists()) {
					directory.mkdir();
				}
			}

			String jakartaDate = "";

			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("WIB"));
			dateTimeInGMT.applyPattern("yyyy");
			jakartaDate = jakartaDate + dateTimeInGMT.format(new Date()) + "_";

			dateTimeInGMT.applyPattern("MM");
			jakartaDate = jakartaDate + dateTimeInGMT.format(new Date()) + "_";

			dateTimeInGMT.applyPattern("dd");
			jakartaDate = jakartaDate + dateTimeInGMT.format(new Date());

			dateTimeInGMT.applyPattern("HH");
			String hh = dateTimeInGMT.format(new Date());
			int hour = Integer.parseInt(hh) + 7;
			// jakartaDate = jakartaDate + "_" + hour;
			jakartaDate = jakartaDate + "_" + "00";

			dateTimeInGMT.applyPattern("mm");
			// jakartaDate = jakartaDate + "_" + dateTimeInGMT.format(new Date());
			jakartaDate = jakartaDate + "_" + "00";

			dateTimeInGMT.applyPattern("ss");
			// jakartaDate = jakartaDate + "_" + dateTimeInGMT.format(new Date());
			jakartaDate = jakartaDate + "_" + "00";

			// localhost
			String fileName = folderPath + bukalapakRepotFileNameProp + jakartaDate + ".xlsx";
			// Server
			// String fileName = PropertyReader.getProperty("bukalapakRepotFileName")+
			// jakartaDate + ".xlsx";
			File generateFile = new File(fileName);
			if (!generateFile.exists()) {
				generateFile.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(generateFile);
			workbook.write(out);
			out.close();
			// code to send mail
			new SendMail().mail(generateFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BPKReportModel getLastWebHookRequest(List<StatusAudit> historyData) {
		BPKReportModel webhookRequestRecord = new BPKReportModel();
		int size = -1;
		if (historyData != null) {
			size = historyData.size();
		}

		ListIterator<StatusAudit> itr = historyData.listIterator(size);
		StatusAudit row = null;
		boolean lastRecord = false;
		boolean sttCreatedFlag = false;
		boolean sttDeliverFlag = false;

		while (itr.hasPrevious()) {
			row = itr.previous();
			String status = row.getStatus();

			// Check last webhook status for order
			String webhookRequest = reportService.getWebhookRequestPerStatus(row.getOrderNo(), status);
			if (webhookRequest != null) {
				webhookRequestRecord.setWebhookRequest(webhookRequest);
			}

			if ((lastRecord == false) && (status.equalsIgnoreCase(CommonConstants.LP001)
					|| status.equalsIgnoreCase(CommonConstants.LP002) || status.equalsIgnoreCase(CommonConstants.LP003)
					|| status.equalsIgnoreCase(CommonConstants.LP011) || status.equalsIgnoreCase(CommonConstants.LP005)
					|| status.equalsIgnoreCase(CommonConstants.LP009) || status.equalsIgnoreCase(CommonConstants.LP006)
					|| status.equalsIgnoreCase(CommonConstants.LP004)
					|| status.equalsIgnoreCase(CommonConstants.LP007))) {

				String lpStatus = row.getStatus();
				switch (lpStatus) {
				case CommonConstants.LP001:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._101);
					break;
				case CommonConstants.LP002:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._103);
					break;
				case CommonConstants.LP003:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._105);
					break;
				case CommonConstants.LP004:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._150);
					break;
				case CommonConstants.LP005:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._100);
					break;
				case CommonConstants.LP006:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._200);
					break;
				case CommonConstants.LP007:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._152);
					break;
				case CommonConstants.LP009:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._104);
					break;
				case CommonConstants.LP011:
					webhookRequestRecord.setLastWebhookStatus(CommonConstants._NA);
					break;

				}
				webhookRequestRecord.setTimestamp(row.getModified_date().toString());
				// webhookRequestRecord.setLastWebhookStatus(row.getStatus());
				lastRecord = true;
			}
			// Set OrderNo
			if (status.equalsIgnoreCase(CommonConstants.LP001)) {
				webhookRequestRecord.setOrderNo(row.getOrderNo());
			}
			// STT Number
			if (status.equalsIgnoreCase(CommonConstants.LP005) && sttCreatedFlag == false) {
				sttCreatedFlag = true;
				webhookRequestRecord.setSttNumber(row.getOrderNo());
				webhookRequestRecord.setOrderNo(row.getAwbNumber());
			}
			if (status.equalsIgnoreCase(CommonConstants.LP006) && sttDeliverFlag == false) {
				sttDeliverFlag = true;
				webhookRequestRecord.setSttNumber(row.getOrderNo());
				webhookRequestRecord.setOrderNo(row.getAwbNumber());
			}
		}

		return webhookRequestRecord;
	}

	// Function to get orderHistory by orderNo
	public List<StatusAudit> getOrderHistoryByOrderNo(String orderNo) {

		List<StatusAudit> orderHistory = null;
		logger.debug("getOrderHistoryByOrderNo = {}", orderNo);
		try {
			orderHistory = reportService.getHistoryByOrderNo(orderNo);
			if (orderHistory != null) {
				/*
				 * Iterator<StatusAudit> iterator = orderHistory.iterator(); while
				 * (iterator.hasNext()) { logger.trace("STATUS AUDIT={}",
				 * iterator.next().toString()); }
				 */

				logger.debug("Order history found.");
				return orderHistory;
			} else {
				return null;
			}
		} catch (Exception ex) {
			logger.error("Exception: {}", ex.getMessage());
			return null;
		}

	}

}
