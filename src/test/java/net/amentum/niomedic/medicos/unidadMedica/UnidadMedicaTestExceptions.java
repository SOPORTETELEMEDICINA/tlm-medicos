package net.amentum.niomedic.medicos.unidadMedica;

import net.amentum.niomedic.medicos.converter.UnidadMedicaConverter;
import net.amentum.niomedic.medicos.exception.UnidadMedicaException;
import net.amentum.niomedic.medicos.model.UnidadMedica;
import net.amentum.niomedic.medicos.persistence.EspecialidadUmRepository;
import net.amentum.niomedic.medicos.persistence.ServicioUmRepository;
import net.amentum.niomedic.medicos.persistence.UnidadMedicaRepository;
import net.amentum.niomedic.medicos.service.impl.UnidadMedicaServiceImpl;
import net.amentum.niomedic.medicos.views.UnidadMedicaView;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class UnidadMedicaTestExceptions {

	private UnidadMedicaServiceImpl unidadMedicaServiceImpl;
	@Mock
	private UnidadMedicaRepository unidadMedicaRepository;
	@Mock
	private UnidadMedicaConverter unidadMedicaConverter;
	@Mock
	private ServicioUmRepository servicioUmRepository;
	@Mock
	private EspecialidadUmRepository especialidadUmRepository;
	@Mock
	private UnidadMedica unidadMedica;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		unidadMedicaServiceImpl = new UnidadMedicaServiceImpl();
		unidadMedicaServiceImpl.setUnidadMedicaRepository(unidadMedicaRepository);
		unidadMedicaServiceImpl.setUnidadMedicaConverter(unidadMedicaConverter);
		unidadMedicaServiceImpl.setServicioUmRepository(servicioUmRepository);
		unidadMedicaServiceImpl.setEspecialidadUmRepository(especialidadUmRepository);
	}

	///////////////////////////////////////////////////POST/////////////////////////////////////////////////////
	@Test(expected = UnidadMedicaException.class)
	public void createUnidadMedica() throws Exception {
		Mockito.when(unidadMedicaConverter.toEntity(Matchers.any(UnidadMedicaView.class), Matchers.any(UnidadMedica.class), Matchers.anyBoolean()))
		.thenThrow(new IllegalArgumentException());
		UnidadMedicaView view = new UnidadMedicaView();
		unidadMedicaServiceImpl.createUnidadMedica(view);
	}

	@Test(expected = UnidadMedicaException.class)
	public void createUnidadMedicaConstraint()throws Exception {
		//Funcionalida para obtener las validaciones de javax.validation
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		UnidadMedica entity= new UnidadMedica();
		Set<ConstraintViolation<UnidadMedica>> violations = validator.validate(entity);
		//	
		Mockito.when(unidadMedicaConverter.toEntity(Matchers.any(UnidadMedicaView.class), Matchers.any(UnidadMedica.class), Matchers.anyBoolean()))
		.thenReturn(new UnidadMedica());
		Mockito.when(unidadMedicaRepository.save(Matchers.any(UnidadMedica.class)))
		.thenThrow(new ConstraintViolationException("Error de validacion", violations));

		UnidadMedicaView view = new UnidadMedicaView();
		unidadMedicaServiceImpl.createUnidadMedica(view);
	}

	@Test(expected = UnidadMedicaException.class)
	public void createUnidadMedicaDataIntegrity()throws Exception {

		Mockito.when(unidadMedicaConverter.toEntity(Matchers.any(UnidadMedicaView.class), Matchers.any(UnidadMedica.class), Matchers.anyBoolean()))
		.thenReturn(new UnidadMedica());
		Mockito.when(unidadMedicaRepository.save(Matchers.any(UnidadMedica.class)))
		.thenThrow(new DataIntegrityViolationException("Erorr de Integridad", new Throwable("Error al crear la unidad medica")));

		UnidadMedicaView view = new UnidadMedicaView();
		unidadMedicaServiceImpl.createUnidadMedica(view);
	}

	///////////////////////////////////////////////////////////////////////UPDATE///////////////////////////////////////////////

	@Test(expected = UnidadMedicaException.class)
	public void updateUnidadMedica() throws Exception {
		Mockito.when(unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.findOne(Matchers.anyLong())).thenReturn(new UnidadMedica());
		Mockito.when(unidadMedicaConverter.toEntity(Matchers.any(UnidadMedicaView.class), Matchers.any(UnidadMedica.class), Matchers.anyBoolean()))
		.thenThrow(new IllegalArgumentException());

		unidadMedicaServiceImpl.updateUnidadMedica(1l, new UnidadMedicaView());
	}

	@Test(expected = UnidadMedicaException.class)
	public void updateUnidadMedicaConstraint()throws Exception {
		//Funcionalida para obtener las validaciones de javax.validation
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		UnidadMedica entity= new UnidadMedica();
		Set<ConstraintViolation<UnidadMedica>> violations = validator.validate(entity);
		//	
		Mockito.when(unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.findOne(Matchers.anyLong())).thenReturn(new UnidadMedica());

		Mockito.when(unidadMedicaConverter.toEntity(Matchers.any(UnidadMedicaView.class), Matchers.any(UnidadMedica.class), Matchers.anyBoolean()))
		.thenReturn(new UnidadMedica());
		Mockito.when(unidadMedicaRepository.save(Matchers.any(UnidadMedica.class)))
		.thenThrow(new ConstraintViolationException("Error de validacion", violations));

		unidadMedicaServiceImpl.updateUnidadMedica(1l, new UnidadMedicaView());
	}

	@Test(expected = UnidadMedicaException.class)
	public void updateUnidadMedicaDataIntegrity()throws Exception {

		Mockito.when(unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.findOne(Matchers.anyLong())).thenReturn(new UnidadMedica());

		Mockito.when(unidadMedicaConverter.toEntity(Matchers.any(UnidadMedicaView.class), Matchers.any(UnidadMedica.class), Matchers.anyBoolean()))
		.thenReturn(new UnidadMedica());
		Mockito.when(unidadMedicaRepository.save(Matchers.any(UnidadMedica.class)))
		.thenThrow(new DataIntegrityViolationException("Erorr de Integridad", new Throwable("Error al crear la unidad medica")));

		unidadMedicaServiceImpl.updateUnidadMedica(1l, new UnidadMedicaView());
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////GET POR ID
	@Test(expected = UnidadMedicaException.class)
	public void getUnidadMedicaById_UnidadMedicaException() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(false);

		unidadMedicaServiceImpl.getUnidadMedicaById(Matchers.anyLong());
	}

	@Test(expected = UnidadMedicaException.class)
	public void getUnidadMedicaById_Exception() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.findOne(Matchers.anyLong()))
		.thenReturn(unidadMedica);
		Mockito.when(unidadMedicaConverter.toView(unidadMedica, Boolean.TRUE))
		.thenThrow(new IllegalArgumentException());

		unidadMedicaServiceImpl.getUnidadMedicaById(Matchers.anyLong());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////GET SERVICIOS POR ID UNIDADMEDICA
	@Test(expected = UnidadMedicaException.class)
	public void getServiciosByIdUnidadMedica_UnidadMedicaException() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(false);

		unidadMedicaServiceImpl.getServiciosByIdUnidadMedica(Matchers.anyLong());
	}

	@Test(expected = UnidadMedicaException.class)
	public void getServiciosByIdUnidadMedica_Exception() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.getServiciosByIdUnidadMedica(Matchers.anyLong()))
		.thenReturn(null)
		.thenThrow(new IllegalArgumentException());

		unidadMedicaServiceImpl.getServiciosByIdUnidadMedica(Matchers.anyLong());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////GET SERVICIOS POR ID AMBOS
	@Test(expected = UnidadMedicaException.class)
	public void getServicioByIdUnidadMedicaByIdServicio_Exception() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(!servicioUmRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.getServicioByIdUnidadMedicaByIdServicio(Matchers.anyLong(),Matchers.anyLong()))
		.thenReturn(null)
		.thenThrow(new IllegalArgumentException());

		unidadMedicaServiceImpl.getServicioByIdUnidadMedicaByIdServicio(Matchers.anyLong(),Matchers.anyLong());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////GET ESPECIALIDADES POR ID UNIDADMEDICA
	@Test(expected = UnidadMedicaException.class)
	public void getEspecialidadesByIdUnidadMedica_UnidadMedicaException() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(false);

		unidadMedicaServiceImpl.getEspecialidadesByIdUnidadMedica(Matchers.anyLong());
	}

	@Test(expected = UnidadMedicaException.class)
	public void getEspecialidadesByIdUnidadMedica_Exception() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.getEspecialidadesByIdUnidadMedica(Matchers.anyLong()))
		.thenReturn(null)
		.thenThrow(new IllegalArgumentException());

		unidadMedicaServiceImpl.getEspecialidadesByIdUnidadMedica(Matchers.anyLong());

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////GET ESPECIALIDAD POR ID AMBOS
	@Test(expected = UnidadMedicaException.class)
	public void getEspecialidadByIdUnidadMedicaByIdEspecialidad_Exception() throws Exception{
		Mockito.when(!unidadMedicaRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(!especialidadUmRepository.exists(Matchers.anyLong()))
		.thenReturn(true);
		Mockito.when(unidadMedicaRepository.getEspecialidadByIdUnidadMedicaByIdEspecialidad(Matchers.anyLong(),Matchers.anyLong()))
		.thenReturn(null)
		.thenThrow(new IllegalArgumentException());

		unidadMedicaServiceImpl.getEspecialidadByIdUnidadMedicaByIdEspecialidad(Matchers.anyLong(),Matchers.anyLong());
	}
	/////////////////////////////////////////////////////////Search/////////////////////////////////////////

	@Test(expected = UnidadMedicaException.class)
	public void searchUnidadMedica() throws Exception {
		Mockito.when(((JpaSpecificationExecutor<?>)unidadMedicaRepository).findAll(Matchers.anyObject(),Matchers.any(PageRequest.class)))
		.thenThrow(new IllegalArgumentException());
		unidadMedicaServiceImpl.searchUnidadMedica("", null, 0, 10, "idUnidadMedica", "asc");
	}

	@Test(expected = UnidadMedicaException.class)
	public void searchCatLenguasIndigenasDataIntegrity() throws Exception {
		Mockito.when(((JpaSpecificationExecutor<?>)unidadMedicaRepository).findAll(Matchers.anyObject(), Matchers.any(PageRequest.class)))
		.thenThrow(new DataIntegrityViolationException("Error de Integridad",new Throwable("Error al filtrar")));
		unidadMedicaServiceImpl.searchUnidadMedica("", null, 0, 10, "idUnidadMedica", "asc");
	}

}
