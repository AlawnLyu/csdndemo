package com.lyu.tech.sys.mapper;


import com.lyu.tech.sys.dto.TreeDTO;
import com.lyu.tech.sys.entity.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreeMapper {

    @Mapping(source = "id", target = "id")
    TreeDTO treeToTreeDTO(Tree entity);

    List<TreeDTO> treeToTreeDTOs(List<Tree> treeList);

    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);
}
