package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EspecialidadRepository extends JpaRepository<Especialidad, String>, JpaSpecificationExecutor {
}
