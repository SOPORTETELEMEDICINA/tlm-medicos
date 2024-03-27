package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.RegionSanitaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionSanitariaRepository extends JpaRepository<RegionSanitaria, Integer>, JpaSpecificationExecutor {

    @Query(value = "select case when count(rs)>0 then true else false end from cat_dir_reg_sanitaria rs where rs.rs_edo = :idEstado", nativeQuery = true)
    boolean existEstado(@Param("idEstado") Integer idEstado);

    @Query(value = "select rs.* from cat_dir_reg_sanitaria rs where rs.rs_edo = :idEstado", nativeQuery = true)
    List<RegionSanitaria> getRegionByEstado(@Param("idEstado") Integer idEstado);

}
