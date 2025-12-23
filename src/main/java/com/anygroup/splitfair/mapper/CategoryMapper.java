package com.anygroup.splitfair.mapper;

import com.anygroup.splitfair.dto.CategoryDTO;
import com.anygroup.splitfair.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(source = "categoryName", target = "name")
    CategoryDTO toDTO(Category entity);


    @Mapping(source = "name", target = "categoryName")
    Category toEntity(CategoryDTO dto);
}
