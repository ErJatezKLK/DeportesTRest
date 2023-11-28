package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Athlete;
import org.example.entity.Sport;
import org.example.entity.Team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamDto {
    @Positive
    private Integer id;
    private String name;
    private Timestamp creationDate;
    private String country;

    public static Team toEntity(TeamDto dto) {
        return new Team(
                dto.getId(),
                dto.getName(),
                dto.getCreationDate(),
                dto.getCountry(),
                new ArrayList<>(),
                new Sport(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
    public static TeamDto toDto(Team entity) {
        return new TeamDto(
                entity.getId(),
                entity.getName(),
                entity.getCreationDate(),
                entity.getCountry()
        );
    }
}
