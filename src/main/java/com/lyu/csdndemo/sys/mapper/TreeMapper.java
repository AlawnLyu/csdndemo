package com.lyu.csdndemo.sys.mapper;


import com.lyu.csdndemo.sys.dto.TreeDTO;
import com.lyu.csdndemo.sys.entity.Tree;
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
