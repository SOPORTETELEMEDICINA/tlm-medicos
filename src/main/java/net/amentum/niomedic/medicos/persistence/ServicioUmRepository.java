package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.ServicioUm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioUmRepository extends JpaRepository<ServicioUm, Long>, JpaSpecificationExecutor {
}
