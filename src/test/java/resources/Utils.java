package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification reqspec;

	public RequestSpecification requestSpecification() throws FileNotFoundException, IOException {
		if (reqspec == null) {
			PrintStream log = new PrintStream(new FileOutputStream(new File("logging.txt")));
			reqspec = new RequestSpecBuilder().setBaseUri(getProp("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return reqspec;
		}
		return reqspec;
	}

	public String getProp(String key) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/config.properties"));
		return prop.getProperty(key);
	}

	public JsonPath parseToJson(Response response) {
		JsonPath json = new JsonPath(response.asString());
		return json;
	}
}
