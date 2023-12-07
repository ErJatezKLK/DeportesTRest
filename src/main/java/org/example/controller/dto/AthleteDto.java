package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.AthleteEntity;
import org.example.entity.SportEntity;
import org.example.entity.TeamEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AthleteDto {
    @Positive
    private Integer id;
    @NotBlank
    private String name;

    private String surname;
    @NotBlank
    private String position;
    @NotNull
    private Integer age;
    @NotBlank
    private String nacionality;

    private String nickName;

    private Integer titles;
    @NotEmpty
    private TeamEntity team;
    @NotEmpty
    private SportEntity sport;

    public static AthleteEntity toEntity(AthleteDto dto) {
        return new AthleteEntity(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                dto.getPosition(),
                dto.getAge(),
                dto.getNacionality(),
                dto.getNickName(),
                dto.getTitles(),
                new TeamEntity(),
                new SportEntity(),
                new ArrayList<>()
        );
    }

    public static AthleteDto toDto(AthleteEntity entity) {
        return new AthleteDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getPosition(),
                entity.getAge(),
                entity.getNacionality(),
                entity.getNickName(),
                entity.getTitles(),
                new TeamEntity(),
                new SportEntity()
        );
    }
}
