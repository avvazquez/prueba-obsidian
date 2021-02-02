package com.pruebaobsidian.backend.repository.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pruebaobsidian.backend.repository.mapper.NodeMapper;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "node")
@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonDeserialize(using = NodeDeserializer.class)
public abstract class Node {

    @Id
    private String id;
    private String descripcion;

    public abstract NodeDTO mapper(NodeMapper accept);
}
