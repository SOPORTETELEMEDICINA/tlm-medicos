package net.amentum.niomedic.medicos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medico")
public class Medico implements Serializable {

    private static final long serialVersionUID = 7504220664222030541L;

    @Id
    @Column(name = "id_medico")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String idMedico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private String estadoCivil;
    private String sexo;
    private String curp;
    private String rfc;

    private String email;
    private String telefonoFijo;
    private String telefonoMovil;
    private String id_cat_nacionalidades;
    private String id_cat_entidades;
    private String id_institucion;
    private String per_id;
    private String act_id;
    private String atr_id;
    private String id_cat_clues;
    private String jor_id;
    private String id_cat_especialidades;
    private String con_id;
    private String pla_id;

    private Integer id_medico_firma;

    @Column(unique = true, nullable = false)
    private Long idUsuario;
    private Boolean activo;
    private String userName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    //   campo compuesto de busqueda
    private String datosBusqueda;
    private String idUsuarioZoom;
    private Integer idUnidadMedica;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "medico")
    private Collection<Domicilio> domicilioList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "medico")
    private Collection<Especialidad> especialidadList = new ArrayList<>();

   /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
   @JoinTable(
      name = "medico_unidad_medica",
      joinColumns = {@JoinColumn(name = "id_medico", referencedColumnName = "id_medico", nullable = false)},
      inverseJoinColumns = {@JoinColumn(name = "id_unidad_medica", referencedColumnName = "id_unidad_medica", nullable = false)}
   )
   private Set<UnidadMedica> unidadMedica = new HashSet<>();*/

    @Override
    public int hashCode() {
        return Objects.hash(idMedico);
    }

    @Override
    public String toString() {
        return "Medico {"+
                "idMedico=" + idMedico +
                ", nombre=" + nombre +
                ", apellidoPaterno=" + apellidoPaterno +
                ", apellidoMaterno=" + apellidoMaterno +
                ", fechaNacimiento=" + fechaNacimiento +
                ", lugarNacimiento=" + lugarNacimiento +
                ", estadoCivil=" + estadoCivil +
                ", sexo=" + sexo +
                ", curp=" + curp +
                ", rfc=" + rfc +
                ", email=" + email +
                ", telefonoFijo=" + telefonoFijo +
                ", telefonoMovil=" + telefonoMovil +
                ", id_cat_nacionalidades=" + id_cat_nacionalidades +
                ", id_cat_entidades=" + id_cat_entidades +
                ", id_institucion=" + id_institucion +
                ", per_id=" + per_id +
                ", act_id=" + act_id +
                ", atr_id=" + atr_id +
                ", id_cat_clues=" + id_cat_clues +
                ", jor_id=" + jor_id +
                ", id_cat_especialidades=" + id_cat_especialidades +
                ", con_id=" + con_id +
                ", pla_id=" + pla_id +
                ", id_medico_firma=" + id_medico_firma +
                ", idUsuario=" + idUsuario +
                ", activo=" + activo +
                ", userName=" + userName +
                ", fechaCreacion=" + fechaCreacion +
                ", datosBusqueda=" + datosBusqueda +
                ", idUsuarioZoom=" + idUsuarioZoom +
                "}";
    }


}