package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deporte")
public class SportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "sport")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<AthleteEntity> athlete;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<TeamEntity> teams;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<EventSportEntity> eventSports;
}
