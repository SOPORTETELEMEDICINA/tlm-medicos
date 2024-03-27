package net.amentum.niomedic.medicos.service;

import net.amentum.niomedic.medicos.exception.UnidadMedicaException;
import net.amentum.niomedic.medicos.model.ServicioUm;
import net.amentum.niomedic.medicos.model.UnidadMedica;
import net.amentum.niomedic.medicos.views.EspecialidadUmView;
import net.amentum.niomedic.medicos.views.ServicioUmView;
import net.amentum.niomedic.medicos.views.UnidadMedicaPageView;
import net.amentum.niomedic.medicos.views.UnidadMedicaView;

import java.util.List;

import org.springframework.data.domain.Page;

public interface UnidadMedicaService {
	
   UnidadMedicaView createUnidadMedica(UnidadMedicaView unidadMedicaView) throws UnidadMedicaException;
	
   UnidadMedicaView updateUnidadMedica(Long idUnidadMedica, UnidadMedicaView unidadMedicaView) throws UnidadMedicaException;
	
   Page<UnidadMedicaPageView>  searchUnidadMedica(String datosBusqueda, Boolean activo, Integer page, Integer size, String orderColumn, String orderType) throws UnidadMedicaException;
   
   UnidadMedicaView getUnidadMedicaById(Long idUnidadMedica) throws UnidadMedicaException;

   List<UnidadMedicaView> getUnidadMedicaByRegion(Integer idRegionSanitaria) throws UnidadMedicaException;

   List<UnidadMedicaView> getUnidadMedicaByEntidad(Integer idEntidad) throws UnidadMedicaException;

   List<ServicioUmView> getServiciosByIdUnidadMedica(Long idUnidadMedica) throws UnidadMedicaException;

   ServicioUmView getServicioByIdUnidadMedicaByIdServicio(Long idUnidadMedica, Long idServicioUm) throws UnidadMedicaException;

   List<EspecialidadUmView> getEspecialidadesByIdUnidadMedica(Long idUnidadMedica) throws UnidadMedicaException;

   EspecialidadUmView getEspecialidadByIdUnidadMedicaByIdEspecialidad(Long idUnidadMedica, Long idEspecialidadUm) throws UnidadMedicaException;

}
