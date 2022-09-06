package com.example.invoice_maker_app.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ConverterTax {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Tax> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Tax>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<Tax> someObjects) {
        return gson.toJson(someObjects);
    }
}
