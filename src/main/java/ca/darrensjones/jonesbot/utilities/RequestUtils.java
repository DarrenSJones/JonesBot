package ca.darrensjones.jonesbot.utilities;

import ca.darrensjones.jonesbot.log.Reporter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-22
 * @since   1.0.0 2020-11-24
 */
public class RequestUtils {

	/**
	 * Runs a simple GET request and returns the response body.
	 * 
	 * @param  requestUrl URL the request is sent to.
	 * @return            ResponseBody retrieved from the request.
	 * @throws Exception  Error of any kind.
	 */
	public static String getResponseBody(String requestUrl) throws Exception {
		Reporter.debug(String.format("RequestUtils sending request:[%s]", requestUrl));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(new URI(requestUrl));
		HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
		String responseBody = IOUtils.toString(httpEntity.getContent(),
				StandardCharsets.UTF_8.name());
		Reporter.debug(String.format("RequestUtils received responseBody:[%s]", responseBody));
		return responseBody;
	}
}