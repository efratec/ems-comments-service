package com.algaworks.algacomments.device.comments.common.mappers;

import com.algaworks.algacomments.device.comments.api.model.SensorInput;
import com.algaworks.algacomments.device.comments.api.model.SensorOutput;
import com.algaworks.algacomments.device.comments.domain.model.Sensor;
import com.algaworks.algacomments.device.comments.domain.model.SensorId;
import io.hypersistence.tsid.TSID;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-13T13:54:39-0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Ubuntu)"
)
@Component
public class SensorMapperImpl implements SensorMapper {

    @Override
    public void updateSensorFromInput(SensorInput input, Sensor sensor) {
        if ( input == null ) {
            return;
        }

        sensor.setName( input.getName() );
        sensor.setIp( input.getIp() );
        sensor.setLocation( input.getLocation() );
        sensor.setProtocol( input.getProtocol() );
        sensor.setModel( input.getModel() );
    }

    @Override
    public Sensor toEntity(SensorInput input) {
        if ( input == null ) {
            return null;
        }

        Sensor.SensorBuilder sensor = Sensor.builder();

        sensor.name( input.getName() );
        sensor.ip( input.getIp() );
        sensor.location( input.getLocation() );
        sensor.protocol( input.getProtocol() );
        sensor.model( input.getModel() );

        return sensor.build();
    }

    @Override
    public SensorOutput toModel(Sensor sensor) {
        if ( sensor == null ) {
            return null;
        }

        SensorOutput.SensorOutputBuilder sensorOutput = SensorOutput.builder();

        sensorOutput.id( sensorIdValue( sensor ) );
        sensorOutput.name( sensor.getName() );
        sensorOutput.ip( sensor.getIp() );
        sensorOutput.location( sensor.getLocation() );
        sensorOutput.protocol( sensor.getProtocol() );
        sensorOutput.model( sensor.getModel() );
        sensorOutput.enabled( sensor.getEnabled() );

        return sensorOutput.build();
    }

    @Override
    public List<SensorOutput> toModel(List<Sensor> sensors) {
        if ( sensors == null ) {
            return null;
        }

        List<SensorOutput> list = new ArrayList<SensorOutput>( sensors.size() );
        for ( Sensor sensor : sensors ) {
            list.add( toModel( sensor ) );
        }

        return list;
    }

    private TSID sensorIdValue(Sensor sensor) {
        SensorId id = sensor.getId();
        if ( id == null ) {
            return null;
        }
        return id.getValue();
    }
}
