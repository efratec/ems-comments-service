package com.algaworks.algacomments.device.comments.domain.repository;

import com.algaworks.algacomments.device.comments.domain.model.Sensor;
import com.algaworks.algacomments.device.comments.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {

}
