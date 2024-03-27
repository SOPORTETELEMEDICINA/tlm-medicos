package net.amentum.niomedic.medicos.unidadMedica;

import net.amentum.niomedic.medicos.MedicoTestConfiguration;
import net.amentum.niomedic.medicos.views.UnidadMedicaView;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;


public class UnidadMedicaTest extends MedicoTestConfiguration {

	@Test
	public void createUnidadMedicaBadRequest() throws Exception{
		UnidadMedicaView view = new UnidadMedicaView();
		mockMvc.perform(MockMvcRequestBuilders.post("/unidad-medica/")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}
	Random r= new Random();
	@Test
	public void createUnidadMedicaCreated() throws Exception {
		UnidadMedicaView view = new UnidadMedicaView();
		Integer id = r.nextInt(9999999);

		view.setClues(UUID.randomUUID().toString());
		view.setFkEntidad(""+id);
		view.setFkCveMunicipio(""+id);
		view.setFkCveLocalidad(""+id);
		view.setNombreJurisdiccion("nombre jurisdiccion");
		view.setClaveJurisdiccion(""+id);
		view.setNombreInstitucion("nombre_institucion");
		view.setClaveInstitucion(""+id);
		view.setClaveCortaInstitucion(""+id);
		view.setNombreTipoEstablecimiento("Nombre_tipo_establecimiento");
		view.setClaveTipoEstablecimiento(""+id);
		view.setNombreTipologia("nombre_tipologia");
		view.setClaveTipologia(""+id);
		view.setNombreSubtipologia("nombre_subtipologia");
		view.setClaveSubtipologia(""+id);
		view.setClaveNivelAtencion(""+id);
		view.setClaveScian(""+id);
		view.setDescripcionClaveScian("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim, natoque accumsan euismod erat nec mauris ullamcorper, tempus lectus habitasse fusce eget magna fermentum. Consequat urna cursus sociosqu etiam quisque, varius augue gravida iaculis tincidunt, mattis lobortis in fusce. Quis nec curabitur class iaculis massa porta, accumsan vehicula convallis velit enim lacus, tristique tortor pretium ullamcorper posuere.\n" + 
				"\n" + 
				"Dapibus donec euismod iaculis pharetra nulla eu odio viverra mauris ad ullamcorper, non curae morbi ut cubilia blandit urna est litora nullam faucibus quisque, nibh sollicitudin aptent fringilla senectus vehicula egestas sapien consequat placerat. Gravida auctor integer nibh inceptos nostra facilisi euismod ridiculus convallis, turpis quis eget cras facilisis porttitor ultrices est, ut habitant et mollis vestibulum class primis ornare. Eros felis porta purus curabitur parturient at lobortis montes tincidunt suscipit, fusce justo nam etiam interdum fames viverra nascetur lacus, semper vitae mi orci enim aliquet praesent placerat tristique.");
		view.setConsultoriosMedGral(""+id);
		view.setConsultoriosOtrasAreas(""+id);
		view.setTotalConsultorios(""+id);
		view.setCamasAreaHos(""+id);
		view.setCamasOtrasAreas(""+id);
		view.setTotalCamas(""+id);
		view.setNombreUnidad("nombre_unidad");
		view.setClaveVialidad(""+id);
		view.setTipoVialidad("tipo_vialidad");
		view.setVialidad("vailidad");
		view.setNumeroExterior(""+id);
		view.setNumeroInterior(""+id);
		view.setTipoAsentamiento("Tipo_asentamiento");
		view.setClaveTipoAsentamiento(""+id);
		view.setAsentamiento("asentamiento");
		view.setEntreTipoVialidad1("entre_tipo_vialidad_1");
		view.setEntreVialidad1("entre_vialidad_1");
		view.setEntreTipoVialidad2("entre_tipo_vialidad_2");
		view.setEntreVialidad2("entre_vailidad2");
		view.setObservacionesDireccion("observacion_descripcion");
		view.setCodigoPostal(""+id);
		view.setEstatusOperacion("estatus_operaciones");
		view.setClaveEstatusOperacion(""+id);
		view.setTieneLicenciaSanitaria("tipo_licencia_sanitarias");
		view.setNumeroLicenciaSanitaria(""+id);
		view.setTieneAvisoFuncionamiento(""+id);
		view.setFechaEmisionAvFun("");
		view.setRfcEstablecimiento("rfc_establecimiento");
		Long tiempo = new Date().getTime()+12000;
		view.setFechaConstruccion("");
		tiempo = tiempo + 12000;
		view.setFechaInicioOperacion("");
		view.setUnidadMovilMarca("unidad_movil_marca");
		view.setUnidadMovilModelo(""+id);
		view.setUnidadMovilCapacidad(""+id);
		view.setUnidadMovilPrograma("unidad_movil_programa");
		view.setUnidadMovilClavePrograma(""+id);
		view.setUnidadMovilTipo("unidad_movil_tipo");
		view.setUnidadMovilTipologia("unidadMovilTipologia");
		view.setUnidadMovilClaveTipo("unidad_movil_clave");
		view.setUnidadMovilClaveTipologia(""+id);
		view.setLongitud("");
		view.setLatitud("");
		view.setNombreInsAdm("nombre_ins_adm");
		view.setClaveInsAdm(""+id);
		view.setNivelAtencion("nivel_de_atencion");
		view.setEstatusAcreditacion("estatus_acreditacion");
		view.setClaveEstatusAcreditacion(""+id);
		view.setAcreditaciones("acreditaciones");
		view.setSubacreditacion("subacreditacion");
		view.setEstratoUnidad("estrato_unidad");
		view.setClaveEstratoUnidad(""+id);
		view.setTipoObra("tipo_obra");
		view.setClaveTipoObra(""+id);
		view.setHorarioAtencion("horario de atencion");
		view.setAreasServicios("areas_servicios");
		view.setUltimoMovimiento("ultimo_movimiento");
		tiempo = tiempo + 12000;
		view.setFechaUltimoMovimiento("");
		view.setCertificacionCsg("certificacion_csg");
		view.setTipoCertificacion("tipo_certficacion");
		tiempo = tiempo + 12000;
		view.setVigenciaCertificacion("");

		mockMvc.perform(MockMvcRequestBuilders.post("/unidad-medica/")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateUnidadaMedicaBadRequest()throws Exception {
		UnidadMedicaView view = new UnidadMedicaView();
		mockMvc.perform(MockMvcRequestBuilders.put("/unidad-medica/{idUnidadMedica}",1)
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateUnidadMedicaOK() throws Exception {
		UnidadMedicaView view = createUnidadMedicaParaTest();
		Integer id = r.nextInt(9999999);
		view.setClues(view.getClues());
		view.setFkEntidad(""+id);
		view.setFkCveMunicipio(""+id);
		view.setFkCveLocalidad(""+id);
		view.setNombreJurisdiccion("u-nombre jurisdiccion");
		view.setClaveJurisdiccion(""+id);
		view.setNombreInstitucion("u-nombre_institucion");
		view.setClaveInstitucion(""+id);
		view.setClaveCortaInstitucion(""+id);
		view.setNombreTipoEstablecimiento("u-Nombre_tipo_establecimiento");
		view.setClaveTipoEstablecimiento(""+id);
		view.setNombreTipologia("u-nombre_tipologia");
		view.setClaveTipologia(""+id);
		view.setNombreSubtipologia("u-nombre_subtipologia");
		view.setClaveSubtipologia(""+id);
		view.setClaveNivelAtencion(""+id);
		view.setClaveScian(""+id);
		view.setDescripcionClaveScian("u-Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim, natoque accumsan euismod erat nec mauris ullamcorper, tempus lectus habitasse fusce eget magna fermentum. Consequat urna cursus sociosqu etiam quisque, varius augue gravida iaculis tincidunt, mattis lobortis in fusce. Quis nec curabitur class iaculis massa porta, accumsan vehicula convallis velit enim lacus, tristique tortor pretium ullamcorper posuere.\n" + 
				"\n" + 
				"Dapibus donec euismod iaculis pharetra nulla eu odio viverra mauris ad ullamcorper, non curae morbi ut cubilia blandit urna est litora nullam faucibus quisque, nibh sollicitudin aptent fringilla senectus vehicula egestas sapien consequat placerat. Gravida auctor integer nibh inceptos nostra facilisi euismod ridiculus convallis, turpis quis eget cras facilisis porttitor ultrices est, ut habitant et mollis vestibulum class primis ornare. Eros felis porta purus curabitur parturient at lobortis montes tincidunt suscipit, fusce justo nam etiam interdum fames viverra nascetur lacus, semper vitae mi orci enim aliquet praesent placerat tristique.");
		view.setConsultoriosMedGral(""+id);
		view.setConsultoriosOtrasAreas(""+id);
		view.setTotalConsultorios(""+id);
		view.setCamasAreaHos(""+id);
		view.setCamasOtrasAreas(""+id);
		view.setTotalCamas(""+id);
		view.setNombreUnidad("u-nombre_unidad");
		view.setClaveVialidad(""+id);
		view.setTipoVialidad("u-tipo_vialidad");
		view.setVialidad("u-vailidad");
		view.setNumeroExterior("u-"+id);
		view.setNumeroInterior("u-"+id);
		view.setTipoAsentamiento("u-Tipo_asentamiento");
		view.setClaveTipoAsentamiento(""+id);
		view.setAsentamiento("u-asentamiento");
		view.setEntreTipoVialidad1("u-entre_tipo_vialidad_1");
		view.setEntreVialidad1("u-entre_vialidad_1");
		view.setEntreTipoVialidad2("u-entre_tipo_vialidad_2");
		view.setEntreVialidad2("u-entre_vailidad2");
		view.setObservacionesDireccion("u-observacion_descripcion");
		view.setCodigoPostal("u-"+id);
		view.setEstatusOperacion("u-estatus_operaciones");
		view.setClaveEstatusOperacion(""+id);
		view.setTieneLicenciaSanitaria("u-tipo_licencia_sanitarias");
		view.setNumeroLicenciaSanitaria("u-"+id);
		view.setTieneAvisoFuncionamiento("");
		view.setFechaEmisionAvFun("");
		view.setRfcEstablecimiento("u-rfc_establecimiento");
		Long tiempo = new Date().getTime()+12000;
		view.setFechaConstruccion("");
		tiempo = tiempo + 12000;
		view.setFechaInicioOperacion("");
		view.setUnidadMovilMarca("u-unidad_movil_marca");
		view.setUnidadMovilModelo(""+id);
		view.setUnidadMovilCapacidad(""+id);
		view.setUnidadMovilPrograma("u-unidad_movil_programa");
		view.setUnidadMovilClavePrograma(""+id);
		view.setUnidadMovilTipo("u-unidad_movil_tipo");
		view.setUnidadMovilTipologia("u-unidadMovilTipologia");
		view.setUnidadMovilClaveTipo("u-unidad_movil_clave");
		view.setUnidadMovilClaveTipologia(""+id);
		view.setLongitud("");
		view.setLatitud("");
		view.setNombreInsAdm("u-nombre_ins_adm");
		view.setClaveInsAdm(""+id);
		view.setNivelAtencion("u-nivel_de_atencion");
		view.setEstatusAcreditacion("u-estatus_acreditacion");
		view.setClaveEstatusAcreditacion(""+id);
		view.setAcreditaciones("u-acreditaciones");
		view.setSubacreditacion("u-subacreditacion");
		view.setEstratoUnidad("u-estrato_unidad");
		view.setClaveEstratoUnidad(""+id);
		view.setTipoObra("u-tipo_obra");
		view.setClaveTipoObra(""+id);
		view.setHorarioAtencion("u-horario de atencion");
		view.setAreasServicios("u-areas_servicios");
		view.setUltimoMovimiento("u-ultimo_movimiento");
		tiempo = tiempo + 12000;
		view.setFechaUltimoMovimiento("");
		view.setCertificacionCsg("u-certificacion_csg");
		view.setTipoCertificacion("u-tipo_certficacion");
		tiempo = tiempo + 12000;
		view.setVigenciaCertificacion("");

		mockMvc.perform(MockMvcRequestBuilders.put("/unidad-medica/{idUnidadMedica}",view.getIdUnidadMedica())
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());

		view.setActivo(false);
		mockMvc.perform(MockMvcRequestBuilders.put("/unidad-medica/{idUnidadMedica}",view.getIdUnidadMedica())
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void updateUnidadMedicaNotFound()throws Exception{
		UnidadMedicaView view = new UnidadMedicaView();
		view.setCodigoPostal("123456_chingon");
		view.setNombreUnidad("nombre_chingon");
		view.setClues(UUID.randomUUID().toString());
		mockMvc.perform(MockMvcRequestBuilders.put("/unidad-medica/{idUnidadMedica}",9999999999999999L)
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void postUnidadMedicaConfilict()throws Exception{
		UnidadMedicaView view = createUnidadMedicaParaTest();
		view.setCodigoPostal("123456_chingon");
		view.setNombreUnidad("nombre_chingon");
		view.setClues(view.getClues());
		mockMvc.perform(MockMvcRequestBuilders.post("/unidad-medica/")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
		.andExpect(MockMvcResultMatchers.status().isConflict())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getUnidadMedicaById() throws Exception {
		String idUnidadMedica = "1";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}", idUnidadMedica)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>OK");

	}

	@Test
	public void getServiciosByIdUnidadMedica() throws Exception {
		String idUnidadMedica = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/servicio", idUnidadMedica)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>OK");

		idUnidadMedica = "3";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/servicio", idUnidadMedica)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404-si existe pero NO tiene");

	}

	@Test
	public void getServicioByIdUnidadMedicaByIdServicio() throws Exception {
		String idUnidadMedica = "1";
		String idServicio = "3";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/servicio/{idServicio}", idUnidadMedica, idServicio)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>OK");

		idUnidadMedica = "1";
		idServicio = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/servicio/{idServicio}", idUnidadMedica, idServicio)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404");

		idUnidadMedica = "9999999";
		idServicio = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/servicio/{idServicio}", idUnidadMedica, idServicio)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404");

		idUnidadMedica = "1";
		idServicio = "999999999";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/servicio/{idServicio}", idUnidadMedica, idServicio)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404");
	}

	@Test
	public void getEspecialidadesByIdUnidadMedica() throws Exception {
		String idUnidadMedica = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/especialidad", idUnidadMedica)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>OK");

		idUnidadMedica = "3";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/especialidad", idUnidadMedica)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404-si existe pero NO tiene");

	}
	//TODO cambiar ids estaticos por ids generados en los test
	@Test
	public void getEspecialidadByIdUnidadMedicaByIdEspecialidad() throws Exception {
		String idUnidadMedica = "1";
		String idEspecialidad = "3";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/especialidad/{idEspecialidad}", idUnidadMedica, idEspecialidad)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>OK");

		idUnidadMedica = "1";
		idEspecialidad = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/especialidad/{idEspecialidad}", idUnidadMedica, idEspecialidad)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404");

		idUnidadMedica = "9999999";
		idEspecialidad = "2";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/especialidad/{idEspecialidad}", idUnidadMedica, idEspecialidad)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404");

		idUnidadMedica = "1";
		idEspecialidad = "999999999";
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/{idUnidadMedica}/especialidad/{idEspecialidad}", idUnidadMedica, idEspecialidad)
				.contentType(JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>404");
	}

	@Test
	public void SearchUnidadesMedicas() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		//datosBusqueda
		params = new LinkedMultiValueMap<>();
		params.add("datosBusqueda", "Centros");
		params.add("activo", "");
		params.add("page", "");
		params.add("size", "");
		params.add("orderColumn", "");
		params.add("orderType", "");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());

		//datosBusqueda, activo
		params = new LinkedMultiValueMap<>();
		params.add("datosBusqueda", "nom");
		params.add("activo", "false");
		params.add("page", "");
		params.add("size", "");
		params.add("orderColumn", "");
		params.add("orderType", "");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());

		//activo
		params = new LinkedMultiValueMap<>();
		params.add("datosBusqueda", "");
		params.add("activo", "false");
		params.add("page", "");
		params.add("size", "");
		params.add("orderColumn", "");
		params.add("orderType", "");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		//orderType desc
		params = new LinkedMultiValueMap<>();
		params.add("orderType", "desc");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());

		//orderColumn
		params = new LinkedMultiValueMap<>();
		params.add("orderColumn", "idUnidadMedica");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		params = new LinkedMultiValueMap<>();
		params.add("orderColumn", "clues");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		params = new LinkedMultiValueMap<>();
		params.add("orderColumn", "nombreUnidad");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		params = new LinkedMultiValueMap<>();
		params.add("orderColumn", "codigoPostal");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		params = new LinkedMultiValueMap<>();
		params.add("orderColumn", "activo");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());

		//page, zise, orderColumn, orderType  incorrectos
		params = new LinkedMultiValueMap<>();
		params.add("datosBusqueda", "");
		params.add("activo", "");
		params.add("page", "-1");
		params.add("size", "-1");
		params.add("orderColumn", "dskuldsaklj");
		params.add("orderType", "asdfasdfasdfs");
		mockMvc.perform(MockMvcRequestBuilders.get("/unidad-medica/search")
				.contentType(JSON)
				.params(params))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());

	}

	public UnidadMedicaView createUnidadMedicaParaTest() throws Exception {
		UnidadMedicaView view = new UnidadMedicaView();
		Integer id = r.nextInt(9999999);

		/*view.setClues(UUID.randomUUID().toString());
		view.setFkEntidad(""+id);
		view.setFkCveMunicipio(""+id);
		view.setFkCveLocalidad(""+id);
		view.setNombreJurisdiccion("nombre jurisdiccion");
		view.setClaveJurisdiccion(id);
		view.setNombreInstitucion("nombre_institucion");
		view.setClaveInstitucion(""+id);
		view.setClaveCortaInstitucion(""+id);
		view.setNombreTipoEstablecimiento("Nombre_tipo_establecimiento");
		view.setClaveTipoEstablecimiento(""+id);
		view.setNombreTipologia("nombre_tipologia");
		view.setClaveTipologia(""+id);
		view.setNombreSubtipologia("nombre_subtipologia");
		view.setClaveSubtipologia(id);
		view.setClaveNivelAtencion(id);
		view.setClaveScian(id);
		view.setDescripcionClaveScian("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim, natoque accumsan euismod erat nec mauris ullamcorper, tempus lectus habitasse fusce eget magna fermentum. Consequat urna cursus sociosqu etiam quisque, varius augue gravida iaculis tincidunt, mattis lobortis in fusce. Quis nec curabitur class iaculis massa porta, accumsan vehicula convallis velit enim lacus, tristique tortor pretium ullamcorper posuere.\n" + 
				"\n" + 
				"Dapibus donec euismod iaculis pharetra nulla eu odio viverra mauris ad ullamcorper, non curae morbi ut cubilia blandit urna est litora nullam faucibus quisque, nibh sollicitudin aptent fringilla senectus vehicula egestas sapien consequat placerat. Gravida auctor integer nibh inceptos nostra facilisi euismod ridiculus convallis, turpis quis eget cras facilisis porttitor ultrices est, ut habitant et mollis vestibulum class primis ornare. Eros felis porta purus curabitur parturient at lobortis montes tincidunt suscipit, fusce justo nam etiam interdum fames viverra nascetur lacus, semper vitae mi orci enim aliquet praesent placerat tristique.");
		view.setConsultoriosMedGral(id);
		view.setConsultoriosOtrasAreas(id);
		view.setTotalConsultorios(id);
		view.setCamasAreaHos(id);
		view.setCamasOtrasAreas(id);
		view.setTotalCamas(id);
		view.setNombreUnidad("nombre_unidad");
		view.setClaveVialidad(id);
		view.setTipoVialidad("tipo_vialidad");
		view.setVialidad("vailidad");
		view.setNumeroExterior(""+id);
		view.setNumeroInterior(""+id);
		view.setTipoAsentamiento("Tipo_asentamiento");
		view.setClaveTipoAsentamiento(id);
		view.setAsentamiento("asentamiento");
		view.setEntreTipoVialidad1("entre_tipo_vialidad_1");
		view.setEntreVialidad1("entre_vialidad_1");
		view.setEntreTipoVialidad2("entre_tipo_vialidad_2");
		view.setEntreVialidad2("entre_vailidad2");
		view.setObservacionesDireccion("observacion_descripcion");
		view.setCodigoPostal(""+id);
		view.setEstatusOperacion("estatus_operaciones");
		view.setClaveEstatusOperacion(id);
		view.setTieneLicenciaSanitaria("tipo_licencia_sanitarias");
		view.setNumeroLicenciaSanitaria(""+id);
		view.setTieneAvisoFuncionamiento(true);
		view.setFechaEmisionAvFun(new Date());
		view.setRfcEstablecimiento("rfc_establecimiento");
		Long tiempo = new Date().getTime()+12000;
		view.setFechaConstruccion(new Date(tiempo));
		tiempo = tiempo + 12000;
		view.setFechaInicioOperacion(new Date(tiempo));
		view.setUnidadMovilMarca("unidad_movil_marca");
		view.setUnidadMovilModelo(id);
		view.setUnidadMovilCapacidad(id);
		view.setUnidadMovilPrograma("unidad_movil_programa");
		view.setUnidadMovilClavePrograma(id);
		view.setUnidadMovilTipo("unidad_movil_tipo");
		view.setUnidadMovilTipologia("unidadMovilTipologia");
		view.setUnidadMovilClaveTipo("unidad_movil_clave");
		view.setUnidadMovilClaveTipologia(id);
		view.setLongitud(0.248564);
		view.setLatitud(0.354654);
		view.setNombreInsAdm("nombre_ins_adm");
		view.setClaveInsAdm(id);
		view.setNivelAtencion("nivel_de_atencion");
		view.setEstatusAcreditacion("estatus_acreditacion");
		view.setClaveEstatusAcreditacion(id);
		view.setAcreditaciones("acreditaciones");
		view.setSubacreditacion("subacreditacion");
		view.setEstratoUnidad("estrato_unidad");
		view.setClaveEstratoUnidad(id);
		view.setTipoObra("tipo_obra");
		view.setClaveTipoObra(id);
		view.setHorarioAtencion("horario de atencion");
		view.setAreasServicios("areas_servicios");
		view.setUltimoMovimiento("ultimo_movimiento");
		tiempo = tiempo + 12000;
		view.setFechaUltimoMovimiento(new Date(tiempo));
		view.setCertificacionCsg("certificacion_csg");
		view.setTipoCertificacion("tipo_certficacion");
		tiempo = tiempo + 12000;
		view.setVigenciaCertificacion(new Date(tiempo));*/

		String jsonResponse= mockMvc.perform(MockMvcRequestBuilders.post("/unidad-medica/")
				.contentType(JSON)
				.content(MAPPER.writeValueAsString(view)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn().getResponse().getContentAsString();

		view = MAPPER.readValue(jsonResponse, new TypeReference<UnidadMedicaView>(){});
		return view;
	}


}
