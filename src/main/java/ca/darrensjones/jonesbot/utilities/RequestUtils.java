package ca.darrensjones.jonesbot.utilities;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-28
 * @since 1.0.0 2020-11-24
 */
public class RequestUtils {

	public static String getResponseBody(String requestUrl) throws Exception {
		return getResponseBody(requestUrl, false);
	}

	public static String getResponseBody(String requestUrl, boolean log) throws Exception {
		if (log) Reporter.info(String.format("Sending Request:[%s]", requestUrl));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(new URI(requestUrl));
		HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
		String response = IOUtils.toString(httpEntity.getContent(), StandardCharsets.UTF_8.name());
		if (log) Reporter.info(String.format("Received Response:[%s]", response));
		return response;
	}

	public static String getResponseBodyField(String requestUrl, String field) throws Exception {
		String response = getResponseBody(requestUrl);
		JSONObject json = (JSONObject) new JSONParser().parse(response);
		return json.get(field).toString();
	}
}