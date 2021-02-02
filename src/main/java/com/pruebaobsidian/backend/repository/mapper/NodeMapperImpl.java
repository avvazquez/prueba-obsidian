package com.pruebaobsidian.backend.repository.mapper;

import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.repository.domain.NodeRoot;
import com.pruebaobsidian.backend.service.domain.NodeDTO;

public class NodeMapperImpl implements NodeMapper {

    @Override
    public NodeDTO visit(final NodeRoot node) {
        return NodeDTO.builder()
                .id(node.getId())
                .descripcion(node.getDescripcion())
                .build();
    }

    @Override
    public NodeDTO visit(final NodeDesc node) {
        return NodeDTO.builder()
                .id(node.getId())
                .descripcion(node.getDescripcion())
                .parentId(node.getParentId())
                .build();
    }
}
