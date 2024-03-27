package net.amentum.niomedic.medicos.converter;

import net.amentum.niomedic.medicos.exception.EspecialidadException;
import net.amentum.niomedic.medicos.model.Especialidad;
import net.amentum.niomedic.medicos.model.Medico;
import net.amentum.niomedic.medicos.views.EspecialidadView;
import net.amentum.niomedic.medicos.views.MedicoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

@Component
public class EspecialidadConverter {
   private Logger logger = LoggerFactory.getLogger(EspecialidadConverter.class);
   final private String CEDULA ="Cedula";
   final private String TITULO="Titulo";
   public Collection<Especialidad> toEntity(Collection<EspecialidadView> especialidadViewArrayList, Collection<Especialidad> especialidadArrayList, Medico medico, Boolean update,  String direccion) throws Exception {
      //para meter especialidades en datos_busqueda
      String temporal = "";
      for (EspecialidadView espV : especialidadViewArrayList) {
         Especialidad esp = new Especialidad();
         if (update) {
            esp.setIdEspecialidad(espV.getIdEspecialidad());
         } else {
            logger.debug("----->NO es un update");
         }
         esp.setEspecialidad(espV.getEspecialidad());
          esp.setSubespecialidad(espV.getSubespecialidad());
         esp.setUniversidad(espV.getUniversidad());
         esp.setCedula(espV.getCedula());
         esp.setNombreImagenCedula(CEDULA+"_"+espV.getEspecialidad());
         esp.setNombreImagenDiploma(TITULO+"_"+espV.getEspecialidad());
         //setImgCedula64 y setImgTitulo64 gurdan la direccion donde se almacena las imagenes
         if(espV.getImgCedula64() != null && !espV.getImgCedula64().isEmpty()) {
         esp.setImgCedula64(guardarImagen(espV.getImgCedula64(), direccion+"cedula/"));
         }
         if(espV.getImgTitulo64() != null && !espV.getImgTitulo64().isEmpty()) {
         esp.setImgTitulo64(guardarImagen(espV.getImgTitulo64(), direccion+"titulo/"));
         }
         esp.setValidado(espV.getValidado());
         esp.setFechaCreacion((update) ? medico.getFechaCreacion() : new Date());
         esp.setFechaValidacion(espV.getFechaValidacion());
         esp.setMedico(medico);
         especialidadArrayList.add(esp);
         //para meter especialidades en datos_busqueda
         temporal += " " + espV.getEspecialidad();
      }
      //para meter especialidades en datos_busqueda
      medico.setDatosBusqueda(medico.getDatosBusqueda() + temporal);
      logger.debug("converter especialidadView to Entity: {}", especialidadArrayList);
      return especialidadArrayList;
   }

   public Collection<EspecialidadView> toView(Collection<Especialidad> especialidadArrayList, Boolean completeConversion) throws Exception {
      Collection<EspecialidadView> especialidadViews = new ArrayList<>();
      for (Especialidad esp : especialidadArrayList) {
         EspecialidadView espV = new EspecialidadView();
         espV.setIdEspecialidad(esp.getIdEspecialidad());
         espV.setEspecialidad(esp.getEspecialidad());
          espV.setSubespecialidad(esp.getSubespecialidad());
         espV.setUniversidad(esp.getUniversidad());
         espV.setCedula(esp.getCedula());
         //
         if(esp.getImgCedula64() != null) {
        	 logger.info("toView() - obteniendo imagen cedula en base64");
         espV.setImgCedula64(obtenerImagenBase64(esp.getImgCedula64()));
         
         }
         if(esp.getImgTitulo64() != null) {
        	 logger.info("toView() - obteniendo imagen titulo en base64");
         espV.setImgTitulo64(obtenerImagenBase64(esp.getImgTitulo64()));
         }
         espV.setNombreImagenCedula(esp.getNombreImagenCedula());
         espV.setNombreImagenDiploma(esp.getNombreImagenDiploma());
         espV.setValidado(esp.getValidado());
         espV.setFechaCreacion(esp.getFechaCreacion());
         espV.setFechaValidacion(esp.getFechaValidacion());
         especialidadViews.add(espV);
      }
      logger.debug("converter Especialidad to View: {}", especialidadViews);
      return especialidadViews;
   }

   public Collection<String> obtenerIDNoExistentesEspecialidad(Medico medico, MedicoView medicoView) {
//      IDs de DB
      Collection<String> ids = new ArrayList<>();
      ids.addAll(
         medico.getEspecialidadList().stream()
            .map(pv -> {
               String id = pv.getIdEspecialidad();
               return id;
            }).collect(Collectors.toList())
      );

//      IDs de View
      Collection<String> idsView = new ArrayList<>();
      idsView.addAll(
         medicoView.getEspecialidadViewList().stream()
            .map(pvV -> {
               String idV = pvV.getIdEspecialidad();
               return idV;
            }).collect(Collectors.toList())
      );

//      Obtener los no existentes
      Collection<String> noExisten = new ArrayList<>(ids);
      noExisten.removeAll(idsView);

//      logger.info("--->ids--->{}", ids);
//      logger.info("--->idsView--->{}", idsView);
//      logger.info("--->noExisten--->{}", noExisten);

      return noExisten;
   }
   
   public String guardarImagen(String imagenBase64,String direccion) throws Exception{
	   try {
		   String imgtipo= imagenBase64.substring(imagenBase64.indexOf('/')+1,imagenBase64.indexOf(';')); 
		   String temporal= imagenBase64.substring(imagenBase64.indexOf(',')+1, imagenBase64.length());
		   BufferedImage image = null;
		   byte[] imageByte;
		   //decodifica imagenBase64 y lo combierte en bytes 
		   imageByte = Base64.getDecoder().decode(temporal);
		   ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		   image = ImageIO.read(bis);
		   if(image==null) {
		   EspecialidadException esp = new EspecialidadException("El formato Base64 de la imagen es incorrecto", EspecialidadException.LAYER_DAO, EspecialidadException.ACTION_VALIDATE);
		   throw esp;
		   }
		   bis.close();
		   direccotrios(direccion);
		   direccion+=idImgane()+"."+imgtipo;
		   File outputfile= new File(direccion);
		   ImageIO.write(image, imgtipo, outputfile);   
		   return direccion; 
	   }catch(Exception e){
		   logger.error("guardarImagen() - Ocurrio un erro al guardar la imagen del File System - "+e.getMessage());
		   throw e;
	   }
   }
   public void direccotrios(String imagenBase64) {
		  File folder = new File(imagenBase64);
		  if(!folder.exists()) {
			  folder.mkdir();
		  }
	  }
    public UUID idImgane() {
	   UUID idOne = UUID.randomUUID();
	   return idOne;
   }
   public String obtenerImagenBase64(String imagenBase64) throws Exception {
	   String encodedBase64;
	   //se obtiene la imagen
	   File inputFile= new File(imagenBase64);
	   if(inputFile.exists()) {
		   try {
		   logger.info("obtenerImagenBase64() - direccion de la imagen: "+ imagenBase64);
		   FileInputStream fileInputStreamReader = new FileInputStream(inputFile);
		   logger.info("obtenerImagenBase64() - se obtine el archivo : "+ imagenBase64);
		   //se conbierte a bytes
		   byte[] bytes = new byte[(int)inputFile.length()];
		   logger.info("obtenerImagenBase64() - convercion a bytes del archivo: "+ bytes);
		   fileInputStreamReader.read(bytes);
		   //el conjunto de bytes se le da formato base64 
		   encodedBase64 = Base64.getEncoder().encodeToString(bytes);
		   logger.info("obtenerImagenBase64() - convercion de bytes del archivo a base64");
		   //obtencion del mime
		   MimetypesFileTypeMap mapper=new MimetypesFileTypeMap();
		   String typeFile=mapper.getContentType(inputFile);
		   String name=inputFile.getName();
		   logger.info("obtenerImagenBase64() - obtencin del mime base64: "+ "data:"+typeFile+";base64," );
		   String extension = typeFile.substring(0, typeFile.lastIndexOf("/")+1)+""+name.substring(name.lastIndexOf(".")+1);
		   encodedBase64 = "data:"+extension+";base64,"+encodedBase64.trim();
		   return encodedBase64;
		   }catch(Exception e){
			   logger.error("obtenerImagenBase64() - Ocurrio un error al obtener la imagen del File System - "+e);
			   throw e;
		   }
	   }else {
		   return null;
	   }
   }
   
   
   public void eliminarImagen(String direccion) {
	   try {
		   File inputFile= new File(direccion);
		   logger.info("Existe el archivo: {}",inputFile.exists());
		   if(inputFile.exists()) {
			   inputFile.delete();
		   }else {
			   logger.info("eliminarImagen() - La imagen en la direccion:{} no existe", direccion);
		   }
	   }catch(Exception e) {
		   logger.info("eliminarImagen() - Ocurrio un error al eliminar la imagen en la direccion:{} del fileSystem - error:{}",direccion,e);
	   }
	   
   }
}
