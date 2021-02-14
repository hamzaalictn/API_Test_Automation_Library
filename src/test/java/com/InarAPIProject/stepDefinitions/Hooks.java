package com.InarAPIProject.stepDefinitions;


import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinitions stepdef = new StepDefinitions();
        if (StepDefinitions.place_id == null) {
            stepdef.add_Place_Payload("Hamza", "Belgium", "Europe");
            stepdef.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepdef.verify_place_Id_created_maps_to_using("Hamza", "getPlaceAPI");
        }
    }
}
