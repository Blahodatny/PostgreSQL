package com.blazen.server.models.node;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nodes")
public class Directory extends Node {
    private ObjectId parent;

    public Directory() {
    }

    public Directory(String name, String parent) {
        super(name);
        this.parent = new ObjectId(parent);
    }

    public ObjectId getParent() {
        return parent;
    }

    public void setParent(ObjectId parent) {
        this.parent = parent;
    }
}