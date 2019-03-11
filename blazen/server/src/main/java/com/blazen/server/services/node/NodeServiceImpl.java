package com.blazen.server.services.node;

import com.blazen.server.models.node.Children;
import com.blazen.server.models.node.Node;
import com.blazen.server.repositories.INodeRepository;
import com.blazen.server.services.node.interfaces.INodeCRUDService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements INodeCRUDService {
    private final MongoTemplate template;
    private final INodeRepository repository;

    @Autowired
    public NodeServiceImpl(MongoTemplate template, INodeRepository repository) {
        this.template = template;
        this.repository = repository;
    }

    public Node createNode(Node node) {
        return repository.save(node);
    }

    public void deleteNode(String _id) {

    }

    public List<Children> findNodeChildren(String parent) {
        return template.aggregate(Aggregation.newAggregation(
                Children.class,
                Aggregation.match(Criteria.where("_id").is(new ObjectId(parent))),
//                Aggregation
//                        .graphLookup("nodes")
//                        .startWith("$_id")
//                        .connectFrom("_id")
//                        .connectTo("parent")
//                        .maxDepth(0)
//                        .as("children")
//                ),
                Aggregation.lookup("nodes", "_id", "parent", "children")
                ),
                Children.class
        ).getMappedResults();
    }
}