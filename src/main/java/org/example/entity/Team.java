package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipo")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "fecha_creacion")
    private Timestamp creationDate;
    @Column(name = "pais")
    private String country;
    @OneToMany(mappedBy = "team")
    private List<Athlete> athletes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deporte")
    private Sport sport;
    @ManyToMany(mappedBy = "teams")
    private List<User> user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "equipo_partido",
            joinColumns = {@JoinColumn(name = "equipo")},
            inverseJoinColumns = {@JoinColumn(name = "partido")})
    private List<EventSport> events;

}
