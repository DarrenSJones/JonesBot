package ca.darrensjones.jonesbot.utilities;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.2 2021-01-13
 * @since 1.0.0 2020-11-24
 */
public class RequestUtils {

	public static String getResponseBody(String requestUrl) throws Exception {
		Reporter.info(String.format("getResponseBody Sending Request:[%s]", requestUrl));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(new URI(requestUrl));
		HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
		String response = IOUtils.toString(httpEntity.getContent(), StandardCharsets.UTF_8.name());
		Reporter.info(String.format("getResponseBody Received Response:[%s]", response));
		return response;
	}
}