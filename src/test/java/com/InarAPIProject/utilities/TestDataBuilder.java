package com.InarAPIProject.utilities;

import com.InarAPIProject.pojo.AddPlace;
import com.InarAPIProject.pojo.Location;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    public AddPlace addPlacePayLoad (String name, String language, String address) {
        AddPlace p = new AddPlace();

        p.setName(name);
        p.setLanguage(language);
        p.setAddress(address);
        p.setAccuracy(50);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId) {
        return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";
    }
}