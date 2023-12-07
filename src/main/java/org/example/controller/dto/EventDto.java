package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.EventSportEntity;
import org.example.entity.SportEntity;
import org.example.entity.TeamEntity;

import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventDto {
    @Positive
    private Integer id;
    @JsonProperty("result")
    private String result;
    @JsonProperty("location")
    private String location;
    private Timestamp date;
    private SportEntity sport;
    private List<TeamEntity> teams;


    public static EventSportEntity toEntity(EventDto dto) {
        return new EventSportEntity(
                dto.getId(),
                dto.getResult(),
                dto.getLocation(),
                dto.getDate(),
                dto.getSport(),
                dto.getTeams()
        );
    }

    public static EventDto toDto(EventSportEntity entity) {
        return new EventDto(
                entity.getId(),
                entity.getResult(),
                entity.getLocation(),
                entity.getDate(),
                entity.getSport(),
                entity.getTeams()
        );
    }
}
