package net.amentum.niomedic.medicos.converter;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.medicos.model.ServicioUm;
import net.amentum.niomedic.medicos.views.ServicioUmView;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServicioUmConverter {

   public ServicioUm toEntity(ServicioUmView servicioUmView, ServicioUm servicioUm, Boolean update) {
      servicioUm.setNombre(servicioUmView.getNombre());

      log.debug("convertir servicioUmView to Entity: {}", servicioUm);
      return servicioUm;
   }

   public ServicioUmView toView(ServicioUm servicioUm, Boolean completeConversion){
      ServicioUmView servicioUmView = new ServicioUmView();
      servicioUmView.setIdServicioUm(servicioUm.getIdServicioUm());
      servicioUmView.setNombre(servicioUm.getNombre());

      log.debug("convertir servicioUm to View: {}", servicioUmView);
      return servicioUmView;
   }

}
