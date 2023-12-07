package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.CommentEntity;
import org.example.entity.SportEntity;
import org.example.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommentDto {
    @Positive
    private Integer id;
    @NotBlank
    private String content;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp date;
    @NotEmpty
    private UserEntity user;
    @NotEmpty
    private SportEntity sport;

    public static CommentEntity toEntity(CommentDto dto) {
        return new CommentEntity(
                dto.getId(),
                dto.getContent(),
                dto.getDate(),
                new UserEntity(),
                new SportEntity()
        );
    }

    public static CommentDto toDto(CommentEntity entity) {
        return new CommentDto(
                entity.getId(),
                entity.getContent(),
                entity.getDate(),
                entity.getUser(),
                entity.getSport()
        );
    }
}
