package dev.alejandro.sedeservice.event;

import dev.alejandro.sedeservice.dto.CreateProfesorRequestDto;
import dev.alejandro.sedeservice.event.Event;
import dev.alejandro.sedeservice.event.ProfesorOperation;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ProfesorEvent implements Event {
    private UUID id = UUID.randomUUID();
    private Date date = new Date();

    private CreateProfesorRequestDto profesor;
    private ProfesorOperation operation;
    @Override
    public UUID getEventId() {
        return id;
    }

    @Override
    public Date getEventDate() {
        return date;
    }

    public ProfesorEvent (CreateProfesorRequestDto profesor, ProfesorOperation operation) {
        this.profesor = profesor;
        this.operation = operation;
    }
}
