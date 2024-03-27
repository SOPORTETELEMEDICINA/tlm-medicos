package net.amentum.niomedic.medicos.converter;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.medicos.model.EspecialidadUm;
import net.amentum.niomedic.medicos.views.EspecialidadUmView;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EspecialidadUmConverter {

   public EspecialidadUm toEntity(EspecialidadUmView especialidadUmView, EspecialidadUm especialidadUm, Boolean update) {
      especialidadUm.setNombre(especialidadUmView.getNombre());

      log.debug("convertir especialidadUmView to Entity: {}", especialidadUm);
      return especialidadUm;
   }

   public EspecialidadUmView toView(EspecialidadUm especialidadUm, Boolean completeConversion){
      EspecialidadUmView especialidadUmView = new EspecialidadUmView();
      especialidadUmView.setIdEspecialidadUm(especialidadUm.getIdEspecialidadUm());
      especialidadUmView.setNombre(especialidadUm.getNombre());

      log.debug("convertir especialidadUm to View: {}", especialidadUmView);
      return especialidadUmView;
   }

}
