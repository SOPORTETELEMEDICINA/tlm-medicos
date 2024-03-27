package net.amentum.niomedic.medicos.converter;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.medicos.model.UnidadMedica;
import net.amentum.niomedic.medicos.views.UnidadMedicaPageView;
import net.amentum.niomedic.medicos.views.UnidadMedicaView;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UnidadMedicaConverter {

	public UnidadMedica toEntity(UnidadMedicaView view, UnidadMedica entity, Boolean update) {
		if(update) {
		entity.setIdUnidadMedica(view.getIdUnidadMedica());
		}
		entity.setClues(view.getClues());
		entity.setFkEntidad(view.getFkEntidad());
		entity.setFkCveMunicipio(view.getFkCveMunicipio());
		entity.setFkCveLocalidad(view.getFkCveLocalidad());
		entity.setNombreJurisdiccion(view.getNombreJurisdiccion());
		entity.setClaveJurisdiccion(view.getClaveJurisdiccion());
		entity.setNombreInstitucion(view.getNombreInstitucion());
		entity.setClaveInstitucion(view.getClaveInstitucion());
		entity.setClaveCortaInstitucion(view.getClaveCortaInstitucion());
		entity.setNombreTipoEstablecimiento(view.getNombreTipoEstablecimiento());
		entity.setClaveTipoEstablecimiento(view.getClaveTipoEstablecimiento());
		entity.setNombreTipologia(view.getNombreTipologia());
		entity.setClaveTipologia(view.getClaveTipologia());
		entity.setNombreSubtipologia(view.getNombreSubtipologia());
		entity.setClaveSubtipologia(view.getClaveSubtipologia());
		entity.setClaveScian(view.getClaveScian());
		entity.setDescripcionClaveScian(view.getDescripcionClaveScian());
		entity.setConsultoriosMedGral(view.getConsultoriosMedGral());
		entity.setConsultoriosOtrasAreas(view.getConsultoriosOtrasAreas());
		entity.setTotalConsultorios(view.getTotalConsultorios());
		entity.setCamasAreaHos(view.getCamasAreaHos());
		entity.setCamasOtrasAreas(view.getCamasOtrasAreas());
		entity.setTotalCamas(view.getTotalCamas());
		entity.setNombreUnidad(view.getNombreUnidad());
		entity.setClaveVialidad(view.getClaveVialidad());
		entity.setTipoVialidad(view.getTipoVialidad());
		entity.setVialidad(view.getVialidad());
		entity.setNumeroExterior(view.getNumeroExterior());
		entity.setNumeroInterior(view.getNumeroInterior());
		entity.setClaveTipoAsentamiento(view.getClaveTipoAsentamiento());
		entity.setTipoAsentamiento(view.getTipoAsentamiento());
		entity.setAsentamiento(view.getAsentamiento());
		entity.setEntreTipoVialidad1(view.getEntreTipoVialidad1());
		entity.setEntreVialidad1(view.getEntreVialidad1());
		entity.setEntreTipoVialidad2(view.getEntreTipoVialidad2());
		entity.setEntreVialidad2(view.getEntreVialidad2());
		entity.setObservacionesDireccion(view.getObservacionesDireccion());
		entity.setCodigoPostal(view.getCodigoPostal());
		entity.setEstatusOperacion(view.getEstatusOperacion());
		entity.setClaveEstatusOperacion(view.getClaveEstatusOperacion());
		entity.setTieneLicenciaSanitaria(view.getTieneLicenciaSanitaria());
		entity.setNumeroLicenciaSanitaria(view.getNumeroLicenciaSanitaria());
		entity.setTieneAvisoFuncionamiento(view.getTieneAvisoFuncionamiento());
		entity.setFechaEmisionAvFun(view.getFechaEmisionAvFun());
		entity.setRfcEstablecimiento(view.getRfcEstablecimiento());
		entity.setFechaConstruccion(view.getFechaConstruccion());
		entity.setFechaInicioOperacion(view.getFechaInicioOperacion());
		entity.setUnidadMovilMarca(view.getUnidadMovilMarca());
		entity.setUnidadMovilModelo(view.getUnidadMovilModelo());
		entity.setUnidadMovilCapacidad(view.getUnidadMovilCapacidad());
		entity.setUnidadMovilPrograma(view.getUnidadMovilPrograma());
		entity.setUnidadMovilClavePrograma(view.getUnidadMovilClavePrograma());
		entity.setUnidadMovilTipo(view.getUnidadMovilTipo());
		entity.setUnidadMovilTipologia(view.getUnidadMovilTipologia());
		entity.setUnidadMovilClaveTipo(view.getUnidadMovilClaveTipo());
		entity.setUnidadMovilClaveTipologia(view.getUnidadMovilClaveTipologia());
		entity.setLongitud(view.getLongitud());
		entity.setLatitud(view.getLatitud());
		entity.setNombreInsAdm(view.getNombreInsAdm());
		entity.setClaveInsAdm(view.getClaveInsAdm());
		entity.setNivelAtencion(view.getNivelAtencion());
		entity.setClaveNivelAtencion(view.getClaveNivelAtencion());
		entity.setEstatusAcreditacion(view.getEstatusAcreditacion());
		entity.setClaveEstatusAcreditacion(view.getClaveEstatusAcreditacion());
		entity.setAcreditaciones(view.getAcreditaciones());
		entity.setSubacreditacion(view.getSubacreditacion());
		entity.setEstratoUnidad(view.getEstratoUnidad());
		entity.setClaveEstratoUnidad(view.getClaveEstratoUnidad());
		entity.setTipoObra(view.getTipoObra());
		entity.setClaveTipoObra(view.getClaveTipoObra());
		entity.setHorarioAtencion(view.getHorarioAtencion());
		entity.setAreasServicios(view.getAreasServicios());
		entity.setUltimoMovimiento(view.getUltimoMovimiento());
		entity.setFechaUltimoMovimiento(view.getFechaUltimoMovimiento());
		entity.setCertificacionCsg(view.getCertificacionCsg());
		entity.setTipoCertificacion(view.getTipoCertificacion());
		entity.setVigenciaCertificacion(view.getVigenciaCertificacion());
		entity.setNombreEntidad(view.getNombreEntidad());
		entity.setNombreMunicipio(view.getNombreMunicipio());
		entity.setNombreLocalidad(view.getNombreLocalidad());
		entity.setMotivoBaja(view.getMotivoBaja());
		entity.setFechaEfectivaBaja(view.getFechaEfectivaBaja());
		entity.setActivo(view.getActivo());
		log.debug("convetir unidadMedicaView to entity: {}", view);
		log.info("Convirtiendo el modelo unidadMedicaView a un model entidad");
		return entity;
	}
	
	public UnidadMedicaView toView(UnidadMedica entity, Boolean completeConversion) {
		UnidadMedicaView view = new UnidadMedicaView();
		view.setIdUnidadMedica(entity.getIdUnidadMedica());
		view.setClues(entity.getClues());
		view.setFkEntidad(entity.getFkEntidad());
		view.setFkCveMunicipio(entity.getFkCveMunicipio());
		view.setFkCveLocalidad(entity.getFkCveLocalidad());
		view.setNombreJurisdiccion(entity.getNombreJurisdiccion());
		view.setClaveJurisdiccion(entity.getClaveJurisdiccion());
		view.setNombreInstitucion(entity.getNombreInstitucion());
		view.setClaveInstitucion(entity.getClaveInstitucion());
		view.setClaveCortaInstitucion(entity.getClaveCortaInstitucion());
		view.setNombreTipoEstablecimiento(entity.getNombreTipoEstablecimiento());
		view.setClaveTipoEstablecimiento(entity.getClaveTipoEstablecimiento());
		view.setNombreTipologia(entity.getNombreTipologia());
		view.setClaveTipologia(entity.getClaveTipologia());
		view.setNombreSubtipologia(entity.getNombreSubtipologia());
		view.setClaveSubtipologia(entity.getClaveSubtipologia());
		view.setClaveScian(entity.getClaveScian());
		view.setDescripcionClaveScian(entity.getDescripcionClaveScian());
		view.setConsultoriosMedGral(entity.getConsultoriosMedGral());
		view.setConsultoriosOtrasAreas(entity.getConsultoriosOtrasAreas());
		view.setTotalConsultorios(entity.getTotalConsultorios());
		view.setCamasAreaHos(entity.getCamasAreaHos());
		view.setCamasOtrasAreas(entity.getCamasOtrasAreas());
		view.setTotalCamas(entity.getTotalCamas());
		view.setNombreUnidad(entity.getNombreUnidad());
		view.setClaveVialidad(entity.getClaveVialidad());
		view.setTipoVialidad(entity.getTipoVialidad());
		view.setVialidad(entity.getVialidad());
		view.setNumeroExterior(entity.getNumeroExterior());
		view.setNumeroInterior(entity.getNumeroInterior());
		view.setClaveTipoAsentamiento(entity.getClaveTipoAsentamiento());
		view.setTipoAsentamiento(entity.getTipoAsentamiento());
		view.setAsentamiento(entity.getAsentamiento());
		view.setEntreTipoVialidad1(entity.getEntreTipoVialidad1());
		view.setEntreVialidad1(entity.getEntreVialidad1());
		view.setEntreTipoVialidad2(entity.getEntreTipoVialidad2());
		view.setEntreVialidad1(entity.getEntreVialidad2());
		view.setEntreVialidad2(entity.getEntreVialidad2());
		view.setObservacionesDireccion(entity.getObservacionesDireccion());
		view.setCodigoPostal(entity.getCodigoPostal());
		view.setEstatusOperacion(entity.getEstatusOperacion());
		view.setClaveEstatusOperacion(entity.getClaveEstatusOperacion());
		view.setTieneLicenciaSanitaria(entity.getTieneLicenciaSanitaria());
		view.setNumeroLicenciaSanitaria(entity.getNumeroLicenciaSanitaria());
		view.setTieneAvisoFuncionamiento(entity.getTieneAvisoFuncionamiento());
		view.setFechaEmisionAvFun(entity.getFechaEmisionAvFun());
		view.setRfcEstablecimiento(entity.getRfcEstablecimiento());
		view.setFechaConstruccion(entity.getFechaConstruccion());
		view.setFechaInicioOperacion(entity.getFechaInicioOperacion());
		view.setUnidadMovilMarca(entity.getUnidadMovilMarca());
		view.setUnidadMovilModelo(entity.getUnidadMovilModelo());
		view.setUnidadMovilCapacidad(entity.getUnidadMovilCapacidad());
		view.setUnidadMovilPrograma(entity.getUnidadMovilPrograma());
		view.setUnidadMovilClavePrograma(entity.getUnidadMovilClavePrograma());
		view.setUnidadMovilTipo(entity.getUnidadMovilTipo());
		view.setUnidadMovilTipologia(entity.getUnidadMovilTipologia());
		view.setUnidadMovilClaveTipo(entity.getUnidadMovilClaveTipo());
		view.setUnidadMovilClaveTipologia(entity.getUnidadMovilClaveTipologia());
		view.setLongitud(entity.getLongitud());
		view.setLatitud(entity.getLatitud());
		view.setNombreInsAdm(entity.getNombreInsAdm());
		view.setClaveInsAdm(entity.getClaveInsAdm());
		view.setNivelAtencion(entity.getNivelAtencion());
		view.setClaveNivelAtencion(entity.getClaveNivelAtencion());
		view.setEstatusAcreditacion(entity.getEstatusAcreditacion());
		view.setClaveEstatusAcreditacion(entity.getClaveEstatusAcreditacion());
		view.setAcreditaciones(entity.getAcreditaciones());
		view.setSubacreditacion(entity.getSubacreditacion());
		view.setEstratoUnidad(entity.getEstratoUnidad());
		view.setClaveEstratoUnidad(entity.getClaveEstratoUnidad());
		view.setTipoObra(entity.getTipoObra());
		view.setClaveTipoObra(entity.getClaveTipoObra());
		view.setHorarioAtencion(entity.getHorarioAtencion());
		view.setAreasServicios(entity.getAreasServicios());
		view.setUltimoMovimiento(entity.getUltimoMovimiento());
		view.setFechaUltimoMovimiento(entity.getFechaUltimoMovimiento());
		view.setCertificacionCsg(entity.getCertificacionCsg());
		view.setTipoCertificacion(entity.getTipoCertificacion());
		view.setVigenciaCertificacion(entity.getVigenciaCertificacion());
		view.setNombreEntidad(entity.getNombreEntidad());
		view.setNombreMunicipio(entity.getNombreMunicipio());
		view.setNombreLocalidad(entity.getNombreLocalidad());
		view.setMotivoBaja(entity.getMotivoBaja());
		view.setFechaEfectivaBaja(entity.getFechaEfectivaBaja());
		view.setActivo(entity.getActivo());
		log.debug("convertir unidadMedica to View: {}", view);
		return view;
	}
	
	public UnidadMedicaPageView toPage(UnidadMedica entity) {
		UnidadMedicaPageView view = new UnidadMedicaPageView();
		view.setIdUnidadMedica(entity.getIdUnidadMedica());
		view.setClues(entity.getClues());
		view.setNombreUnidad(entity.getNombreUnidad());
		view.setActivo(entity.getActivo());
		view.setCodigoPostal(entity.getCodigoPostal());
		return view;
	}
}


