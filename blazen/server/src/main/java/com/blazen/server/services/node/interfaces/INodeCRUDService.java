package com.blazen.server.services.node.interfaces;

import com.blazen.server.models.node.Children;
import com.blazen.server.models.node.Node;

import java.util.List;

public interface INodeCRUDService {
    Node createNode(Node node);

    void deleteNode(String _id);

    List<Children> findNodeChildren(String parent);
}