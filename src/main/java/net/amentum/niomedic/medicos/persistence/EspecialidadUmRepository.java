package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.EspecialidadUm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadUmRepository extends JpaRepository<EspecialidadUm, Long>, JpaSpecificationExecutor {
}
