package com.ln.bpk.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//@Component
public class CommonConstants {

	public static final String BLANK = "";

	public static final String COMMA = ",";

	public static final String ERP = "ERP";

	public static final String CONFIG = "config";

	public static final String DOT = ".";

	public static final String SPACE = " ";

	public static final String LOG4J = "log4j.properties";

	public static final int BATCH_SIZE = 20;

	public static final int MAX_API_COUNTER = 5;

	public static final int PAUSE = 30000;

	public static final int PAUSE_API_DIFF = 1000;

	public static final int RETRY_COUNTER = 3;

	public static final String baseURI = "baseURI";

	public static final String POST = "POST";

	public static final String GET = "GET";

	public static final String CONTENT_TYPE = "Content-Type";

	public static final String APPLICATION_JSON = "application/json";

	public static final String ACCEPT = "Accept";

	public static final String authURI = "authURI";

	public static final String WWW_AUTHENTICATE = "WWW-Authenticate";

	public static final String CONTENT_LENGTH = "Content-Length";

	public static final String RESPONSE_CODE = "RESPONSE_CODE";

	public static final String RESPONSE = "RESPONSE";

	public static final String JSON_RESPONSE = "JSON_RESPONSE";

	public static final String LEFT_BRACKET = "[";

	public static final String RIGHT_BRACKET = "]";

	public static final String CLIENT_SECRET_KEY = "CLIENT_SECRET_KEY";

	public static final String invalidateURI = "invalidateURI";

	public static final int MAX_RETRY = 3;

	public static final String ORDER_DELIVERED_QUERY = "orderDeliveredQuery";

	public static final String ORDER_NOT_DELIVERED_QUERY = "orderNotDeliveredQuery";

	public static final String ORDER_CANCELED_QUERY = "orderCanceledQuery";

	public static final String CREATE_ORDER_QUERY = "createOrderQuery";

	public static final String CREATE_CONFIRM_ORDER_QUERY = "createConfirmOrderQuery";

	public static final int STATUS_401 = 401;

	public static final String START_TRIP_QUERY = "startTripQuery";

	public static final String JNDI = "jndi";

	// LP
	public static final String LION_PARCEL_APP = "LPAPP";

	public static final String LION_PARCEL_TKP = "LPTKP";

	public static final String LION_PARCEL_BPK = "LPBPK";

	public static final String UPDATE_WAIT_WEBHOOK = "tp_update_weight_wh";

	public static final String LNCANCEL_URL = "cancelLNURL";

	public static final String LN_GET_ORDER_URL = "LN_GET_ORDER_URL";

	public static final String OK = "OK";

	public static final String DATEFORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String REQUEST = "REQUEST";

	public static final String SMS_URL = "smsUrl";

	public static final String SMS_SUCCESS = "000";

	public static final String TOKO_TRACKING_WEBHOOK = "TrackingWebHoolURL";

	public static final String APP = "APP";

	public static final String TKP = "TKP";

	public static final String BPK = "BPK";

	public static final String LP = "LP";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String HYPHEN = "-";

	public static final String PICKUP = "PICKUP";

	public static final String FORWARD = "FORWARD";

	public static final Integer INT_VALUE = 0;

	public static final int OTP_LENGTH = 6;

	public static final String Authorization = "Authorization";

	public static final String MIDTRANS_SERVER_KEY = "midtrans_server_key";

	public static final String DATE_yyyyMMddhhmmss = "yyyyMMddhhmmss";

	public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd hh:mm:ss";

	public static final String DATE_YYYY_MM_DD_ONLY = "yyyy-MM-dd";

	public static final Character Y = Character.valueOf('Y');

	public static final String N = "N";

	public static final String YES = "Y";

	public static final String ACCOUNT_APP = "Customer_App";

	public static final String ACCOUNT_TKP = "CGK-0001-C-174";

	public static final String ACCOUNT_BPK = "CGK-0001-C-212";

	public static final String LP_BRANCH = "branchName";

	public static final String PREPAID = "PREPAID";

	public static final Integer FIVE = Integer.valueOf(5);

	public static final String IDN = "IDN";

	public static final String MIDTRANS_ENABLED_PAYMENTS = "enabled_payments";

	public static final String DATE_DDMMMYYYY = "dd MMM yyyy";

	public static final String PUT = "PUT";

	public static final String FAILED = "​Failed or Not Allowed";
	public static final String NOT_FOUND = "No Data found";

	public static final String _152 = "152";
	public static final String _200 = "200";
	public static final String _104 = "104";
	public static final String _103 = "103";
	public static final String _105 = "105";
	public static final String _150 = "150";
	public static final String _151 = "151";
	public static final String _100 = "100";
	public static final String _101 = "101";
	public static final String _NA = "NA";

	public static final String Q_GET_STT_STATUS_DETAIL = "getSttStatusDetail";

	public static final String Q_GET_HISTORY_DETAIL = "getHistoryDetail";

	public static final String Q_GET_PROGRESS_DETAIL = "getProgressDetail_query";

	public static final String LP003 = "LP003";

	public static final String LP001 = "LP001";

	public static final String LP005 = "LP005";

	public static final String LP006 = "LP006";

	public static final String LP009 = "LP009";

	public static final String LP010 = "LP010";

	public static final String LP011 = "LP011";

	public static final String LP012 = "LP012";

	public static final String LP015 = "LP015";

	public static final String LP016 = "LP016";

	public static final String LP017 = "LP017";

	public static final String POD = "POD";

	public static final String DEX = "DEX";

	public static final String LP007 = "LP007";

	public static final String LP002 = "LP002";

	public static final String LP004 = "LP004";

	public static final Object LP014 = "LP014";

	public static final Object LP018 = "LP018";

	public static final String PICKEDUP = "PICKEDUP";

	public static final String TIME_ZONE_HOUR = "timeZoneHour";

	public static final String ORDER_DELIVER = "orderDeliver";

	public static final String TKP_TRACKINGWB_URL = "trackingDeliveryURI";

	public static final String IFRAME_URL = "iframeURL";

	public static final String AID = "aid";

	public static final String KEY = "key";

	public static final String ORDER_NO = "ordno=";

	public static final String Q_UPDATE_WEIGHT = "updateWeightQuery";

	public static final String PAYABLE = "payableQuery";

	public static final String UPDATE_PAYMENT_IN_STT = "updatePaymentInStt";

	public static final String DD_MM_YYYY = "dd-mm-yyyy​ ​ ​​hh:mm:ss";

	public static final String Q_DEST_CITY_LIST = "destinationcitylist";

	public static final String CHECK_TARIFF_URL = "checkTarifURL";

	public static final String STI = "STI";

	public static final String ORIGIN_HUB = "ORIGIN_HUB";

	public static final String ECARGO_TRUCK = "E-CARGO";

	public static final String TRUCK = "TRUCK";

	public static final String STI_DEST = "STI-DEST";

	public static final String DEL = "DEL";

	public static final String ON_DELIVERY = "ON_DELIVERY";

	public static final String CNX = "CNX";

	public static final String CANCEL = "CANCEL";

	public static final String IN_TRANSIT = "INTRANSIT";

	public static final String DATE_DD_MMM_YYYY = "dd-MM-YYYY";

	public static final String REACHED_ORIGIN_HUB = "REACHED AT ORIGIN HUB";

	public static final String DATE_DD_MMM_YY = "dd-mm-yyyy";

	public static final String Q_GET_STATUS = "statusQuery";

	public static final String DELIVERED = "Delivered";

	public static final String bkDel = "Shipment on the way destination";

	public static final String FAILED_DELIVERY = "FAILED_DELIVERY";

	public static final String ACCEPTED = "ACCEPTED";

	public static final String DELIVER = "DELIVER";

	public static final String TRACK_LOCATION = "track_location";

	public static final String UPDATE_TSATUS = "UPDATE_STATUS";

	public static final String UPDATEORDER_NEWSTATUS = "updateOrder_NewStatus";

	public static final String NOT_DISPATCHED = "not_dispatched";

	public static final String STT = "STT";

	public static final String UCNL = "UCNL";

	public static final String CONTACT_CS = "contactcs";

	public static final String Q_DRIVER_NOT_FOUND = "driverNotFoundQuery";

	public static final String Q_STATUS_DETAILS = "getStatus_query";

	public static final String Q_CANCEL_ORDER = "updateCancelOrderQuery";

	public static String Q_GET_STT_DETAIL = "getSttDetail";

	public static String _404 = "404";

	public static final String FCM_SCOPE = "FCM_Scope";

	public static final String FCM_TOKEN_FILE = "fcm.service-account-file";

	public static final String FCM_BACKGROUND = "FCM_Background";

	public static final String FCM_AUTHORIZATION_KEY = "FCM_Authorization_key";

	public static final String FCM_FOREGROUND = "FCM_Foreground";

	public static final String FCM_FOREGROUND_AUTHORIZATION_KEY = "FCM_Foreground_Authorization_key";

	public static final String DF_CHECK = "DF-CHECK";

	public static final String AUTHERIZATION = "Authorization";

	public static final String DRIVER_FOUND = "DRIVER FOUND";

	public static final String DRIVER_NOT_FOUND = "DRIVER NOT FOUND";

	public static final String SCRAP = "SCRAP";

	public static final String BKD = "BKD";

	public static final String Q_STT_COUNR = "stt_count_query";

	public static final String DUPLICATE_MSG = "duplicate_message";

	public static String UPDATEASSIGNORDERURI = "updateAssignOrderURI";

	public static final String FCM_ACCOUNT_FILE = "fcm.service-account-file";

	public static final String DEX_POD_RESPONSE = "dex_pod_response";

	public static final String DEL_SUCCESS = "del_success";

	public static final String DEL_EXIST = "del_exist";

	public static final String Q_PAYMENT_PENDING = "payment_pending_query";

	public static final Object PENDING = "pending";

	public static final String Q_STT_LATLONG = "get_lat_long_query";

	public static final String PAYMENT_SETTLEMENT_HISTORY = "paymentSettlementHistory";

	public static final String PAYMENT_EXPIRE_HISTORY = "paymentExpireHistory";

	public static final String GET_VA_NUMBER = "get_vaNumber_query";

	public static final String BANK_TRANSFER = "BANK TRANSFER";

	public static final String DI_BOOKING = "DI BOOKING";

	public static final String T_DI_BOOKING = "di_booking";

	public static final String EXPIRE = "expire";

	public static final String SETTLEMENT = "settlement";

	public static final String Q_PAYMENT_HISTORY = "get_payment_history_query";

	public static final String DRIVER_IMAGE = "driver_image";

	public static final String MENCARI_KURIR_FCMMSG = "Mohon tunggu, kami sedang mencarikan kurir untuk Anda.";

	public static final String MENUNGGU_PENJEMPUTAN_FCMMSG = "Mohon tunggu, kurir kami sedang menuju lokasi penjemputan.";

	public static final String PENJEMPUTAN_SELESAI_FCMMSG = "Penjemputan sudah selesai, kami akan segera pulang untuk menginput paket Anda.";

	public static final String PAKET_DIBATALKAN_FCMMSG = "Paket dibatalkan.";

	public static final String PAKET_SUDAH_DIINPUT_FCMMSG = "Paket Anda sudah diinput ke dalam sistem kami, nomor STT (Resi): <<STT_NO>>";

	public static final String PAKET_TERKIRIM_FCMMSG = "Paket Anda sudah terkirim, nomor STT (Resi): <<STT_NO>>";

	public static final String PENGANTARAN_GAGALGAGAL_FCMMSG = "Paket Anda gagal terkirim, nomor STT (Resi): <<STT_NO>>";

	public static final String PAKET_DALAM_PENGANTARAN_FCMMSG = "Paket Anda dalam pengantaran oleh kurir kami, nomor STT (Resi): <<STT_NO>>";

	public static final String PAKET_SAMPAI_DI_HUB_FCMMSG = "Paket Anda sudah sampai di hub kami, nomor STT (Resi): <<STT_NO>>";

	public static final String PAKET_SAMPAI_DI_STATION_ORIGIN_FCMMSG = "Paket Anda sudah sampai di station kota origin kami, nomor STT (Resi): <<STT_NO>>";

	public static final String PAKET_INTRANSIT_FCMMSG = "Paket Anda sudah dalam perjalanan ke kota tujuan, nomor STT (Resi): <<STT_NO>>";

	public static final String PAKET_SAMPAI_DI_STATION_TUJUAN_FCMMSG = "Paket Anda sudah sampai di station kota tujuan kami, nomor STT (Resi): <<STT_NO>>";

	public static final String MENCARI_KURIR = "Mencari Kurir";

	public static final String MENUNGGU_PENJEMPUTAN = "Menunggu penjemputan";

	public static final String PENJEMPUTAN_SELESAI = "Penjemputan selesai";

	public static final String PAKET_DIBATALKAN = "Paket dibatalkan";

	public static final String PAKET_SUDAH_DIINPUT = "Paket sudah diinput";

	public static final String PAKET_TERKIRIM = "Paket terkirim";

	public static final String PENGANTARAN_GAGAL = "Pengantaran gagal";

	public static final String PAKET_DALAM_PENGANTARAN = "Paket dalam pengantaran";

	public static final String PAKET_SAMPAI_DI_HUB = "Paket sampai di hub";

	public static final String PAKET_SAMPAI_DI_STATION_ORIGIN = "Paket sampai di station origin";

	public static final String PAKET_INTRANSIT = "Paket in-transit";

	public static final String PAKET_SAMPAI_DI_STATION_TUJUAN = "Paket sampai di station tujuan";

	public static final String FCM_INTEGRATION_QUERY = "fcm_integeration_query";

	public static final String Q_DELETE_DELDEXPOD = "delete_deldexpod";

	public static final String Q_RETRY_CREATEORDER = "retry_createorder";

	public static final String Q_FCM_INTEGRATIONMILESTONE = "fcm_integeration_milestone_query";

	public static final String LION_PARCEL = "LION PARCEL";

	public static final String VERSION_CODE = "2.0";

	public static final String ON_PROCESS = "On Process";

	public static final String cancelTypeAuto = "cancelTypeAuto";

	public static final String doNotSend = "DNS";

	public static final String intransitStatus = "orderIntransit";

	public static final String MID_TRANS_URL = "midtrans_transaction_url";

	public static final String INVALIDSTATUS = "Invalid status code";
	public static final List<String> sstOrderStatus = new ArrayList<String>() {
		{
			add(LP009);
			add(LP007);
			add(LP006);

		}
	};
}
