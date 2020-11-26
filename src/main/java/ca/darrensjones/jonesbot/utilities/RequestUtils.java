package ca.darrensjones.jonesbot.utilities;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-26
 * @since 1.0.0 2020-11-24
 */
public class RequestUtils {

	public static String getResponseBody(String requestUrl) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(new URI(requestUrl));
		HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
		return IOUtils.toString(httpEntity.getContent(), StandardCharsets.UTF_8.name());
	}
}