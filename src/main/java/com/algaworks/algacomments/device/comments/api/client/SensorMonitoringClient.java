package com.algaworks.algacomments.device.comments.api.client;

import com.algaworks.algacomments.device.comments.api.model.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange("/api/sensors/{sensorId}/monitoring")
public interface SensorMonitoringClient {

    @PutExchange("/enable")
    void enabledMonitoring(@PathVariable TSID sensorId);

    @DeleteExchange("/enable")
    void disabledMonitoring(@PathVariable TSID sensorId);

    @GetExchange
    SensorMonitoringOutput getDetail(@PathVariable TSID sensorId);

}
