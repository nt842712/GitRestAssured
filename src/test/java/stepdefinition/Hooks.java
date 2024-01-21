package stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws FileNotFoundException, IOException {
		StepDefinition step = new StepDefinition();
		if (StepDefinition.placeID == null) {
			step.add_place_payload_with("Naresh", "English", "US");
			step.user_calls_with_http_request("addPlaceAPI", "POST");
			step.verify_place_id_created_maps_to_using("Naresh", "getPlaceAPI");
		}
	}
}
