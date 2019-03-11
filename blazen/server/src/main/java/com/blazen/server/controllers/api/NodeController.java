package com.blazen.server.controllers.api;

import com.blazen.server.models.node.Node;
import com.blazen.server.services.node.interfaces.INodeCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/nodes")
public class NodeController {
    private final INodeCRUDService service;

    @Autowired
    public NodeController(INodeCRUDService service) {
        this.service = service;
    }

    @PostMapping()
    ResponseEntity<?> newNode(@RequestBody Node node) {
        var saved = service.createNode(node);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(saved.get_id().toString())
                                .toUri()
                )
                .body(saved);
    }

    @GetMapping("/{id}/children")
    ResponseEntity<?> children(@PathVariable String id) {
        return ResponseEntity
                .accepted()
                .body(service.findNodeChildren(id));
    }
}