package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partido")
public class EventSportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "resultado")
    private String result;
    @Column(name = "ubicacion")
    private String location;
    @Column(name = "fecha")
    private Timestamp date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deporte")
    private SportEntity sport;

    @ManyToMany(mappedBy = "events")
    private List<TeamEntity> teams;

}
