package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.AgendaMedicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface AgendamedicosRepository extends JpaRepository<AgendaMedicos, Long>, JpaSpecificationExecutor {
    @Query(value ="SELECT  am.*  FROM  Agenda_medicos am  "
            + "WHERE am.idmedico =':idmedico' ",nativeQuery=true)
    List<AgendaMedicos> findByidmedico(@NotNull @Param("idmedico") String idmedico)throws Exception;

    AgendaMedicos findByIdagenda(Integer idagenda) throws Exception;


}
