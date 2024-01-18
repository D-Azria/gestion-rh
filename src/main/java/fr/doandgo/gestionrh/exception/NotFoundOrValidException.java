package fr.doandgo.gestionrh.exception;

import fr.doandgo.gestionrh.dto.MessageDto;
import lombok.Getter;

@Getter
public class NotFoundOrValidException extends RuntimeException {
    private MessageDto messageDto;

    public NotFoundOrValidException(MessageDto messageDto) {
        this.messageDto = messageDto;
    }

}