package net.amentum.niomedic.medicos.model;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medico_firma")
public class MedicoFirma implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico_firma")
    private Integer idMedicoFirma;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
}