package ru.dmitriyt.testproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Document {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("link")
    private String link;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public String getFullName() {
        return String.format(Locale.getDefault(), "%s.%s", getName(), getType());
    }
}
