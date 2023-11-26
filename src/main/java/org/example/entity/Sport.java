package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.parser.Cookie;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deporte")
public class Sport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "sport")
    private List<Comment> comments;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<Athlete> athlete;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<Team> teams;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<EventSport> eventSports;
}
