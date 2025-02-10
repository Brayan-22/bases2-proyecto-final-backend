package dev.alejandro.sedeservice.event;

import dev.alejandro.sedeservice.dto.CreatePregradoRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PregradoEvent implements Event{
    private UUID id = UUID.randomUUID();
    private Date date = new Date();

    private CreatePregradoRequestDto createPregradoRequestDto;
    private PregradoOperation operation;

    @Override
    public UUID getEventId() {
        return id;
    }

    @Override
    public Date getEventDate() {
        return date;
    }

    public PregradoEvent(CreatePregradoRequestDto createPregradoRequestDto, PregradoOperation operation) {
        this.createPregradoRequestDto = createPregradoRequestDto;
        this.operation = operation;
    }

}
