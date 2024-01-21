package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.addPlace;
import pojo.location;

public class TestBuildData {

	public addPlace addPlacePayLoad(String name, String language, String address) {
		addPlace addplace = new addPlace();
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		addplace.setLocation(loc);

		addplace.setAccuracy(50);
		// addplace.setName("Frontline house");
		addplace.setName(name);
		addplace.setPhoneNumber("(+91) 983 893 3937");
		addplace.setAddress("29, side layout, cohen 09");
		addplace.setAddress(address);

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		addplace.setTypes(list);

		addplace.setWebsite("http://google.com");
		// addplace.setLanguage("French-IN");
		addplace.setLanguage(language);
		return addplace;
	}

	public String deletePlacePayLoad(String placeID) {
		return "{\n" + "\n" + "    \"place_id\":\"" + placeID + "\"\n" + "}";
	}

}
