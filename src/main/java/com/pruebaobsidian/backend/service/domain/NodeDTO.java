package com.pruebaobsidian.backend.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NodeDTO {
    @Id
    private String id;
    private String descripcion;
    private String parentId;

}
