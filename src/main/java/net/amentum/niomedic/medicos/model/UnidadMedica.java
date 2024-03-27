package net.amentum.niomedic.medicos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "unidad_medica",
indexes = { @Index(name = "unidad_medica_index", columnList = "id_unidad_medica, nombreUnidad", unique = true) },
uniqueConstraints={@UniqueConstraint(columnNames = {"clues"})}
)
public class UnidadMedica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5002575526201515194L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unidad_medica")
	private Long idUnidadMedica;
	@NotBlank(message = "Por favor ingrese el clues")
	private String clues;
	private String fkEntidad;
	private String fkCveMunicipio;
	private String fkCveLocalidad;
	private String nombreJurisdiccion;
	private String claveJurisdiccion;
	private String nombreInstitucion;
	private String claveInstitucion;
	private String claveCortaInstitucion;
	private String nombreTipoEstablecimiento;
	private String claveTipoEstablecimiento;
	private String nombreTipologia;
	private String claveTipologia;
	private String nombreSubtipologia;
	private String claveSubtipologia;
	private String claveScian;
	private String descripcionClaveScian;
	private String consultoriosMedGral;
	private String consultoriosOtrasAreas;
	private String totalConsultorios;
	private String camasAreaHos;
	private String camasOtrasAreas;
	private String totalCamas;
	@NotBlank(message = "Por favor ingrese el nombre de la unidad")
	private String nombreUnidad;
	private String claveVialidad;
	private String tipoVialidad;
	private String vialidad;
	private String numeroExterior;
	private String numeroInterior;
	private String claveTipoAsentamiento;
	private String tipoAsentamiento;
	private String asentamiento;
	private String entreTipoVialidad1;
	private String entreVialidad1;
	private String entreTipoVialidad2;
	private String entreVialidad2;
	private String observacionesDireccion;
	@NotBlank(message="Por favor ingrese el código postal")
	private String codigoPostal;
	private String estatusOperacion;
	private String claveEstatusOperacion;
	private String tieneLicenciaSanitaria;
	private String numeroLicenciaSanitaria;
	private String tieneAvisoFuncionamiento;
	private String fechaEmisionAvFun;
	private String rfcEstablecimiento;
	private String fechaConstruccion;
	private String fechaInicioOperacion;
	private String unidadMovilMarca;
	private String unidadMovilModelo;
	private String unidadMovilCapacidad;
	private String unidadMovilPrograma;
	private String unidadMovilClavePrograma;
	private String unidadMovilTipo;
	private String unidadMovilClaveTipo;
	private String unidadMovilTipologia;
	private String unidadMovilClaveTipologia;
	private String longitud;
	private String latitud;
	private String nombreInsAdm;
	private String claveInsAdm;
	private String nivelAtencion;
	private String claveNivelAtencion;
	private String estatusAcreditacion;
	private String claveEstatusAcreditacion;
	private String acreditaciones;
	private String subacreditacion;
	private String estratoUnidad;
	private String claveEstratoUnidad;
	private String tipoObra;
	private String claveTipoObra;
	private String horarioAtencion;
	private String areasServicios;
	private String ultimoMovimiento;
	private String fechaUltimoMovimiento;
	private String certificacionCsg;
	private String tipoCertificacion;
	private String vigenciaCertificacion;
	private String nombreEntidad;
	private String nombreMunicipio;
	private String nombreLocalidad;
	private String motivoBaja;
	private String fechaEfectivaBaja;
	private Integer idRegionSanitaria;
	//nuevo campo para las busquedas
	@NotNull(message="No puede se nulo")
	private Boolean activo;
	//relaciones
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(
			name = "unidad_medica_especialidad_um",
			joinColumns = {@JoinColumn(name = "id_unidad_medica", referencedColumnName = "id_unidad_medica", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name = "id_especialidad_um", referencedColumnName = "id_especialidad_um", nullable = false)}
			)
	private Set<EspecialidadUm> especialidadUm = new HashSet<>();

	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(
			name = "unidad_medica_servicio_um",
			joinColumns = {@JoinColumn(name = "id_unidad_medica", referencedColumnName = "id_unidad_medica", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name = "id_servicio_um", referencedColumnName = "id_servicio_um", nullable = false)}
			)
	private Set<ServicioUm> servicioUm = new HashSet<>();*/

	/*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "unidadMedica")
	private Set<Medico> medicoList;*/


	@Override
	public int hashCode() {
		return Objects.hash(idUnidadMedica);
	}


}
