package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DomicilioRepository extends JpaRepository<Domicilio, String>, JpaSpecificationExecutor {
}
