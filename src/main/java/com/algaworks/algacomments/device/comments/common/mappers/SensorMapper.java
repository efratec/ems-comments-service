package com.algaworks.algacomments.device.comments.common.mappers;

import com.algaworks.algacomments.device.comments.api.model.SensorInput;
import com.algaworks.algacomments.device.comments.api.model.SensorOutput;
import com.algaworks.algacomments.device.comments.domain.model.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    void updateSensorFromInput(SensorInput input, @MappingTarget Sensor sensor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Sensor toEntity(SensorInput input);

    @Mapping(target = "id", source = "id.value")
    SensorOutput toModel(Sensor sensor);

    List<SensorOutput> toModel(List<Sensor> sensors);

    default Page<SensorOutput> toModel(Page<Sensor> sensors) {
        return sensors.map(this::toModel);
    }

}
