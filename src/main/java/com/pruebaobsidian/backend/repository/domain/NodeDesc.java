package com.pruebaobsidian.backend.repository.domain;

import com.pruebaobsidian.backend.repository.mapper.NodeMapper;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "node")
@TypeAlias("desc")
@Getter
@ToString
public class NodeDesc extends Node {

    private final String parentId;

    public NodeDesc(final String id, final String descripcion, final String parentId) {
        super(id, descripcion);
        this.parentId = parentId;
    }

    @Override
    public NodeDTO mapper(final NodeMapper visitor) {
        return visitor.visit(this);
    }
}
