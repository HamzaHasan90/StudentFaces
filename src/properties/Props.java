package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class Props {

	private static Properties properties = new Properties();
	private static Map<String, InputStream> inputStreams;

	public static String getProperty(InputStream inputStream, String key) throws IOException {

		properties.load(inputStream);

		String value = properties.getProperty(key).toString();

		return value;

	}

	public static InputStream getinputStreams(String string) throws FileNotFoundException {

		File file = new File("/messages.properties");
		File file_ar = new File("/messages.properties");

		inputStreams.put("en", Props.class.getResourceAsStream("/messages.properties"));
		inputStreams.put("ar", Props.class.getResourceAsStream("/messages_ar.properties"));

		return inputStreams.get(string);

	}

}
