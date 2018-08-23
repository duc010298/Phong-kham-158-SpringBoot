package com.phongkham.utils;

import com.phongkham.model.FormEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParseData {

    public List<FormEntity> parseJson(String json) {
        List<FormEntity> formEntities = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String class1 = jsonObject.getString("Class1");
            String class2 = jsonObject.getString("Class2");
            class1 = class1.equals("") ? null : class1;
            class2 = class2.equals("") ? null : class2;
            formEntities.add(new FormEntity(class1, class2));
        }
        return formEntities;
    }

}
