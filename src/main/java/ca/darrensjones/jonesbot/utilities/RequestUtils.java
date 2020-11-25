package ca.darrensjones.jonesbot.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-24
 */
public class RequestUtils {

	public static String getResponseBody(String requestUrl) throws Exception {
		String responseBody = "", line = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(new URL(requestUrl).openStream()));
		while ((line = in.readLine()) != null) responseBody += line;
		in.close();
		return responseBody;
	}
}