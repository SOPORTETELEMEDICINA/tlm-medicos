package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String>, JpaSpecificationExecutor {
   Medico findByIdUsuario(@NotNull Long idUsuario) throws Exception;

   //@Query("SELECT m FROM Medico m WHERE m.userName = ':userName'")
   //Medico findByUserName(@Param("userName") String userName) throws Exception;
   Medico findByUserName(@NotNull String userName) throws Exception;

   Medico findByCurp(@NotNull String curp) throws Exception;

   Medico findByRfc(@NotNull String rfc) throws Exception;

   // Inicia GGR20200617
   @Query(value = "select med.id_usuario from medico med join medicos_grupos mg on mg.id_medico = med.id_medico where mg.id_group = :idGroup", nativeQuery = true)
   List<BigInteger> findIdByGroup(@Param("idGroup") Long idGroup) throws Exception;

   @Query(value = "SELECT m FROM Medico m WHERE idUsuario in(:usuarios) and lower(datosBusqueda) like :datosBusqueda")
   Page<Medico> findAllByGroup(@Param("usuarios") List<Long> usuarios, @Param("datosBusqueda") String datosBusqueda, Pageable pageable) throws Exception;

   @Query(value = "SELECT m FROM Medico m WHERE lower(datosBusqueda) like :datosBusqueda")
   Page<Medico> findAllByGroup(@Param("datosBusqueda") String datosBusqueda, Pageable pageable) throws Exception;
   // Fin GGR20200617
}
