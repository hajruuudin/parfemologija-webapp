// src/main/java/ba/parfemologija/service/implementation/mapper/BrandMapper.java
package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.BrandEntity;
import ba.parfemologija.service.core.models.brand.BrandCreate;
import ba.parfemologija.service.core.models.brand.BrandModel;
import ba.parfemologija.service.core.models.brand.BrandUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    List<BrandModel>   entitiesToDtos(List<BrandEntity> entities);
    BrandModel         entityToDto(BrandEntity entity);
    BrandEntity        dtoToEntity(BrandCreate create);
    void               update(BrandUpdate update, @MappingTarget BrandEntity entity);
}
