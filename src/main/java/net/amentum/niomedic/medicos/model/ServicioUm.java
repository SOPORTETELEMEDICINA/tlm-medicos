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
@Table(name = "servicio_um")
public class ServicioUm implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_servicio_um")
   private Long idServicioUm;
   @Size(max = 150)
   private String nombre;
   //relaciones
   /*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "servicioUm")
   private Set<UnidadMedica> unidadMedicaList;*/

   @Override
   public int hashCode() {
      return Objects.hash(idServicioUm);
   }

   @Override
   public String toString() {
      return "ServicioUm{" +
         "idServicioUm=" + idServicioUm +
         ", nombre='" + nombre + '\'' +
         '}';
   }
}
