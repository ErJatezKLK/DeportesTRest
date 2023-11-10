package org.example.entity;

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
@Table(name = "partido")
public class EventSport implements Serializable {
    @Id
    private Integer id;
    @ManyToMany(mappedBy = "events")
    private List<Team> teams;
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "ubicacion")
    private String ubicacion;
}
