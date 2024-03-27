package net.amentum.niomedic.medicos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidad_um")
public class EspecialidadUm implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_especialidad_um")
   private Long idEspecialidadUm;
   @Size(max = 150)
   private String nombre;
   //relaciones
   /*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "servicioUm")
   private Set<UnidadMedica> unidadMedicaList;*/

   @Override
   public int hashCode() {
      return Objects.hash(idEspecialidadUm);
   }

   @Override
   public String toString() {
      return "EspecialidadUm{" +
         "idEspecialidadUm=" + idEspecialidadUm +
         ", nombre='" + nombre + '\'' +
         '}';
   }
}
