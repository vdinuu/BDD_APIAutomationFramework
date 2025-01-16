package data_generation;

import net.datafaker.Faker;
import pojo.AddLocation;
import pojo.Location;

import java.util.HashMap;
import java.util.Map;

public class GeneratePayloadPojo {
    Faker faker = new Faker();

    public AddLocation generateAddPlaceApiPayload(String website, String language){
        return new AddLocation().toBuilder()
                .location(new Location().toBuilder().lat(-38.383494).lng(33.427362).build())
                .accuracy(50)
                .name(faker.location().building())
                .phone_number(faker.phoneNumber().cellPhone())
                .address(faker.address().streetAddress())
                .types(new String[] {faker.commerce().material(),
                        faker.commerce().material()})
                .website(website)
                .language(language)
                .build();
    }

    public Map<String, String> getDeletePlaceApiPayload(String placeId){
        Map<String, String> payload = new HashMap<>();
        payload.put("place_id", placeId);
        return payload;
    }
}
