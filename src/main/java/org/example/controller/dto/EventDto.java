package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventDto {
    @Positive
    private Integer id;
    @JsonProperty("result")
    private String result;
    @JsonProperty("location")
    private String location;
    private Timestamp date;
    private Sport sport;
    private List<Team> teams;


    public static EventSport toEntity(EventDto dto) {
        return new EventSport(
                dto.getId(),
                dto.getResult(),
                dto.getLocation(),
                dto.getDate(),
                dto.getSport(),
                dto.getTeams()
        );
    }

    public static EventDto toDto(EventSport entity) {
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
