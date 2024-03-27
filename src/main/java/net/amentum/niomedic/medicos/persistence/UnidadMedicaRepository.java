package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.UnidadMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface UnidadMedicaRepository extends JpaRepository<UnidadMedica, Long>, JpaSpecificationExecutor {

   @Query(value = "select s.*" +
      "from unidad_medica_servicio_um ums " +
      "inner join servicio_um s " +
      "on ums.id_servicio_um = s.id_servicio_um " +
      "where ums.id_unidad_medica = :idUnidadMedica", nativeQuery = true)
   List<Object[]> getServiciosByIdUnidadMedica(@NotNull @Param("idUnidadMedica") Long idUnidadMedica) throws Exception;

   @Query(value = "select s.*" +
      "from unidad_medica_servicio_um ums " +
      "inner join servicio_um s " +
      "on ums.id_servicio_um = s.id_servicio_um " +
      "where ums.id_unidad_medica = :idUnidadMedica and ums.id_servicio_um = :idServicioUm", nativeQuery = true)
   List<Object[]> getServicioByIdUnidadMedicaByIdServicio(@NotNull @Param("idUnidadMedica") Long idUnidadMedica, @NotNull @Param("idServicioUm") Long idServicioUm) throws Exception;


   @Query(value = "select s.*" +
      "from unidad_medica_especialidad_um ums " +
      "inner join especialidad_um s " +
      "on ums.id_especialidad_um = s.id_especialidad_um " +
      "where ums.id_unidad_medica = :idUnidadMedica", nativeQuery = true)
   List<Object[]> getEspecialidadesByIdUnidadMedica(@NotNull @Param("idUnidadMedica") Long idUnidadMedica) throws Exception;

   @Query(value = "select s.*" +
      "from unidad_medica_especialidad_um ums " +
      "inner join especialidad_um s " +
      "on ums.id_especialidad_um = s.id_especialidad_um " +
      "where ums.id_unidad_medica = :idUnidadMedica and ums.id_especialidad_um = :idEspecialidadUm", nativeQuery = true)
   List<Object[]> getEspecialidadByIdUnidadMedicaByIdEspecialidad(@NotNull @Param("idUnidadMedica") Long idUnidadMedica, @NotNull @Param("idEspecialidadUm") Long idEspecialidadUm) throws Exception;

   @Query(value = "select case when count(um)>0 then true else false end from unidad_medica um where um.id_region_sanitaria = :idRegionSanitaria", nativeQuery = true)
   boolean existRegionMedica(@Param("idRegionSanitaria") Integer idRegionSanitaria);

   @Query(value = "select um.* from unidad_medica um where um.id_region_sanitaria = :idRegionSanitaria", nativeQuery = true)
   List<UnidadMedica> getUnidadMedicaByRegionMedica(@Param("idRegionSanitaria") Integer idRegionSanitaria);

   @Query(value = "select um.* from unidad_medica um where um.fk_entidad = :idEntidad", nativeQuery = true)
   List<UnidadMedica> getUnidadMedicaByEntidad(@Param("idEntidad") String idEntidad);
}
