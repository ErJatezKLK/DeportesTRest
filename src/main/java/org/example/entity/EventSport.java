package org.example.entity;

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
@Table(name = "partido")
public class EventSport implements Serializable {
    @Id
    private Integer id;
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "fecha")
    private Timestamp date;
    @ManyToMany(mappedBy = "events")
    private List<Team> teams;




}
