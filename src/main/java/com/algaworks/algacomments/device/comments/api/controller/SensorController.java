package com.algaworks.algacomments.device.comments.api.controller;

import com.algaworks.algacomments.device.comments.api.client.SensorMonitoringClient;
import com.algaworks.algacomments.device.comments.api.model.SensorDetailOutput;
import com.algaworks.algacomments.device.comments.api.model.SensorInput;
import com.algaworks.algacomments.device.comments.api.model.SensorOutput;
import com.algaworks.algacomments.device.comments.common.mappers.SensorMapper;
import com.algaworks.algacomments.device.comments.domain.model.Sensor;
import com.algaworks.algacomments.device.comments.domain.model.SensorId;
import com.algaworks.algacomments.device.comments.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static com.algaworks.algacomments.device.comments.common.GeneratorID.generateTSID;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private static final SensorMapper sensorMapper = SensorMapper.INSTANCE;

    private final SensorRepository sensorRepository;
    private final SensorMonitoringClient sensorMonitoringClient;

    @PostMapping
    public ResponseEntity<SensorOutput> create(@RequestBody SensorInput input) {
        var sensor = sensorMapper.toEntity(input);
        sensor.setId(SensorId.of(generateTSID()));
        sensor.setEnabled(false);
        sensor = sensorRepository.saveAndFlush(sensor);

        var location = URI.create("/api/sensors/" + sensor.getId());
        return ResponseEntity.created(location).body(sensorMapper.toModel(sensor));
    }

    @GetMapping("{sensorId}")
    public SensorOutput getOne(@PathVariable @Valid TSID sensorId) {
        return sensorMapper.toModel((getSensor(sensorId)));
    }

    @GetMapping("{sensorId}/detail")
    public SensorDetailOutput getOneWithDetails(@PathVariable @Valid TSID sensorId) {
        var sensor = getSensor(sensorId);
        return SensorDetailOutput.builder()
                .monitoring(sensorMonitoringClient.getDetail(sensorId))
                .sensor(sensorMapper.toModel(sensor))
                .build();
    }

    @GetMapping
    public Page<SensorOutput> search(@PageableDefault  Pageable pageable) {
        var sensors = sensorRepository.findAll(pageable);
        return sensorMapper.toModel(sensors);
    }

    @PutMapping("{sensorId}")
    public ResponseEntity<SensorOutput> update(@PathVariable @Valid TSID sensorId,
                                               @RequestBody @Valid SensorInput input) {
        var sensor = getSensor(sensorId);
        sensorMapper.updateSensorFromInput(input, sensor);
        sensor = sensorRepository.saveAndFlush(sensor);
        return ResponseEntity.ok(sensorMapper.toModel(sensor));
    }

    @DeleteMapping("{sensorId}")
    public ResponseEntity<Void> delete(@PathVariable TSID sensorId) {
        var sensor = getSensor(sensorId);
        sensorRepository.delete(sensor);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{sensorId}/enable")
    public ResponseEntity<Void> enable(@PathVariable TSID sensorId) {
        var sensor = getSensor(sensorId);
        sensor.setEnabled(true);
        sensorRepository.saveAndFlush(sensor);
        sensorMonitoringClient.enabledMonitoring(sensorId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{sensorId}/enable")
    public ResponseEntity<Void> disable(@PathVariable TSID sensorId) {
        var sensor = getSensor(sensorId);
        sensor.setEnabled(false);
        sensorRepository.saveAndFlush(sensor);
        sensorMonitoringClient.disabledMonitoring(sensorId);
        return ResponseEntity.noContent().build();
    }

    private Sensor getSensor(TSID sensorId) {
        return sensorRepository.findById(SensorId.of(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
