package com.server.dao.cache;

public class HttpReturnCodes {


	public static int CONTINUE = 100;
	public static int SWITCHING_PROTOCOL = 101;
	public static int PROCESSING_WEBDAV = 103;
	public static int URI_TOO_LONG = 122;

	public static int OK = 200;
	public static int CREATED = 201;
	public static int ACCEPTED = 202;
	public static int NON_AUTHORITATIVE_INFO = 203;
	public static int NO_CONTENT = 204;
	public static int RESET_CONTENT = 205;
	public static int PARTIAL_CONTENT = 206;
	public static int MULTI_STATUS_WEBDEV = 207;
	public static int INTERNET_MESSENGER_USED = 208;

	public static int MULTIPLE_CHOICES = 300;
	public static int URI_MOVED_PERMANENTLY = 301;
	public static int MOVED_TEMPORARILY = 302;
	public static int SEE_OTHER_URI = 303;
	public static int NOT_MODIFIED = 304;
	public static int USE_PROXY = 305;
	public static int SWITCH_PROXY = 306;
	public static int TEMPORARY_REDIRECT = 307;

	public static int BAD_REQUEST = 400;
	public static int UNAUTHORIZED = 401;
	public static int PAYMENT_REQUIRED = 402;
	public static int FORBIDDEN = 403;
	public static int NOT_FOUND = 404;
	public static int METHOD_NOT_ALLOWED = 405;
	public static int NOT_ACCEPTABLE = 406;
	public static int PROXY_AUTH_REQUIRED = 407;
	public static int REQUEST_TIMEOUT = 408;
	public static int CONFLICT = 409;
	public static int RESOURCE_GONE = 410;
	public static int LENGTH_REQUIRED = 411;
	public static int PRECONDITION_FAILURE = 412;
	public static int REQUEST_ENTITY_TOO_LONG = 413;
	public static int REQUEST_URI_TOO_LONG = 414;
	public static int MEDIA_TYPE_NOT_SUPPORTED = 415;
	public static int RANGE_CANT_BE_MET = 416;
	public static int EXPECTATION_FAILED = 417;
	public static int IM_A_TEAPOT = 418;
	public static int UNPROCESSABLE_ENTITY = 422;
	public static int RESOURCE_LOCKED = 423;
	public static int FAILED_DEPENDENCY = 424;
	public static int UNORDERED_COLLECTION = 425;
	public static int UPGRADE_REQUIRED = 426;
	public static int NO_RESPONSE = 444;
	public static int RETRY_WITH = 449;
	public static int BLOCKED_BY_WPC = 450;
	public static int CLIENT_CLOSED_REQUEST = 499;

	public static int INTERNAL_SERVER_ERROR = 500;
	public static int NOT_IMPLEMENTED = 501;
	public static int BAD_GATEWAY = 502;
	public static int SERVICE_UNAVAILABLE = 503;
	public static int GATEWAY_TIMEOUT = 504;
	public static int HTTP_VERSION_NOT_SUPPORTED = 505;
	public static int VARIANT_NEGOTIATES = 506;
	public static int INSUFFICIANT_STORAGE = 507;
	public static int BANDWIDTH_LIMIT_EXCEED = 509;
	public static int NOT_EXTENDED = 510;
	public static String SUCCESS = "Success";
	public static String FAILED = "Failed";
	public static String SESSION_EXPIRED = "Session Expired";
	public static String LICENSE_EXPIRED = "License Expired";
	public static String TOKEN_EMPTY="Authentication token is empty";
	public static String TOKEN_NOT_VALID="Authentication token is not valid";
	public static String TOKEN_REVALIDATE="Authentication token is expired";

	
}
