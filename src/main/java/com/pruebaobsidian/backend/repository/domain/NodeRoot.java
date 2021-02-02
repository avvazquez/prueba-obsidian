package com.pruebaobsidian.backend.repository.domain;

import com.pruebaobsidian.backend.repository.mapper.NodeMapper;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "node")
@TypeAlias("root")
@ToString
public class NodeRoot extends Node {

    public NodeRoot(final String id, final String descripcion) {
        super(id, descripcion);
    }

    @Override
    public NodeDTO mapper(final NodeMapper visitor) {
        return visitor.visit(this);
    }
}
