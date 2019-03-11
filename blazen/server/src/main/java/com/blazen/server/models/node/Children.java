package com.blazen.server.models.node;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nodes")
public class Children extends Directory {
    private Directory[] children;

    public Children() {
    }

    public Children(String name, String parent, Directory[] children) {
        super(name, parent);
        this.children = children;
    }

    public Directory[] getChildren() {
        return children;
    }

    public void setChildren(Directory[] children) {
        this.children = children;
    }
}