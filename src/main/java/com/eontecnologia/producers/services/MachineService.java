package com.eontecnologia.producers.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import models.dtos.MachineEventDTO;
import models.requests.CreateMachineRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
@RequiredArgsConstructor
public class MachineService {
    private final KafkaTemplate<String, Object> kaTemplate;

    public void createEvent(CreateMachineRequest request){
        getResultSendMessage(request);
    }

    private CompletableFuture<SendResult<String, Object>> getResultSendMessage(CreateMachineRequest request) {
        return kaTemplate.send("topic-" + request.machineId(), new MachineEventDTO(request))
                .whenComplete((success, ex) -> {
                    if (ex != null) {
                        log.error("::: MACHINE_SERVICE | ERROR SEND MESSAGE :::");
                    } else {
                        log.info("::: MACHINE_SERVICE | SEND MESSAGE SUCCESSFULLY:::");
                    }
                });
    }
}
