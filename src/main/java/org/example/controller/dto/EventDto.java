package org.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.EventSport;
import org.example.entity.Team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    @NotNull
    private Integer id;
    @NotEmpty
    private List<Team> teams;
    @NotBlank
    private String resultado;
    @NotBlank
    private String ubicacion;

    public static EventSport toEntity(EventDto dto) {
        return new EventSport(
                dto.getId(),
                dto.getTeams(),
                dto.getResultado(),
                dto.getUbicacion()
        );
    }

    public static EventDto toDto(EventSport entity) {
        return new EventDto(
                entity.getId(),
                entity.getTeams(),
                entity.getResultado(),
                entity.getUbicacion()
        );
    }
}
