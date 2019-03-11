package com.blazen.server.models.node;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nodes")
public class File extends Directory {
    private String link;

    public File() {
    }

    public File(String name, String parent, String link) {
        super(name, parent);
        setLink(link);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        try {
            this.link = new java.net.URL(link).toURI().toString();
        } catch (java.net.MalformedURLException
                | java.net.URISyntaxException e) {
            e.printStackTrace();
        }
    }
}