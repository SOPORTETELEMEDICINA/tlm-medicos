package net.amentum.niomedic.medicos.persistence;

import net.amentum.niomedic.medicos.model.MedicoFirma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicoFirmaRepository extends JpaRepository<MedicoFirma, Integer>, JpaSpecificationExecutor {
}