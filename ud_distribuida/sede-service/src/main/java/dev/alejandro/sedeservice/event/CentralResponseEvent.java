package dev.alejandro.sedeservice.event;

import dev.alejandro.sedeservice.dto.CreateProfesorRequestDto;
import dev.alejandro.sedeservice.event.Event;
import dev.alejandro.sedeservice.event.OperationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CentralResponseEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private OperationStatus status;

    public CentralResponseEvent(OperationStatus status) {
        this.status = status;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getEventDate() {
        return eventDate;
    }
}
