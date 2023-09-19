package com.eontecnologia.producers.services;

import lombok.RequiredArgsConstructor;
import models.dtos.MachineEventDTO;
import models.requests.CreateMachineRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final KafkaTemplate<String, Object> kaTemplate;

    public void createEvent(CreateMachineRequest request){
        kaTemplate.send("topic-" + request.machineId(), new MachineEventDTO(request));
    }
}
