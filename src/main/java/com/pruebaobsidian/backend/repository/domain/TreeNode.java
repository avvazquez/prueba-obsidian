package com.pruebaobsidian.backend.repository.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TreeNode<T> {

    private T self;
    private List<T> children;

}