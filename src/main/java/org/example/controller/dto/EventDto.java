package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.EventSport;
import org.example.entity.Sport;
import org.example.entity.Team;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    @Positive
    private Integer id;
    @NotBlank
    private String resultado;

    private String ubicacion;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp date;
    @NotBlank
    private Sport sport;
    @NotEmpty
    private List<Team> teams;


    public static EventSport toEntity(EventDto dto) {
        return new EventSport(
                dto.getId(),
                dto.getResultado(),
                dto.getUbicacion(),
                dto.getDate(),
                dto.getSport(),
                dto.getTeams()
        );
    }

    public static EventDto toDto(EventSport entity) {
        return new EventDto(
                entity.getId(),
                entity.getResultado(),
                entity.getUbicacion(),
                entity.getDate(),
                entity.getSport(),
                entity.getTeams()
        );
    }
}
