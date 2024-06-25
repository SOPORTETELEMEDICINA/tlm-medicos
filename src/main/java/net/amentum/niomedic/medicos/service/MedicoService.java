package net.amentum.niomedic.medicos.service;

import java.util.List; // Sre19062020 Nuevo
import net.amentum.niomedic.medicos.exception.MedicoException;
import net.amentum.niomedic.medicos.model.MedicoFirma;
import net.amentum.niomedic.medicos.views.MedicoPageView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.springframework.data.domain.Page;

public interface MedicoService {
	MedicoView createMedico(MedicoView medicoView, String direccion) throws MedicoException;

	MedicoView updateMedico(MedicoView medicoView, String direccion) throws MedicoException;

   MedicoView getDetailsByIdMedico(String idMedico) throws MedicoException;

   MedicoView getDetailsByIdUsuario(Long idUsuario) throws MedicoException;

   //    Page<MedicoView> getMedicoPage(Boolean active, String name, Integer page, Integer size, String orderColumn, String orderType) throws MedicoException;
//    Page<MedicoPageView> getMedicoPage(Boolean active, String name, Integer page, Integer size, String orderColumn, String orderType) throws MedicoException;
    // GGR20200617 Agrego selectGroup
   Page<MedicoPageView> getMedicoPage(String datosBusqueda, Boolean active, Integer page, Integer size, String orderColumn, String orderType, Long selectGroup) throws MedicoException;

   MedicoView getDetailsByUserName(String userName) throws MedicoException;

    // Sre19062020 Nuevo servicio para actualizar grupos de medico
    void updateMedicoGroups(Long idUsuario, List<Long> medicoGroups) throws MedicoException;

    MedicoFirma getSignatureById(Integer idFirma) throws MedicoException;
}
