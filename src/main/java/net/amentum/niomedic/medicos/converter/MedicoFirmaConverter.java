package net.amentum.niomedic.medicos.converter;

import net.amentum.niomedic.medicos.model.MedicoFirma;
import net.amentum.niomedic.medicos.views.MedicoFirmaView;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MedicoFirmaConverter {
   public MedicoFirma toEntity(MedicoFirmaView medicoFirmaView) {
      MedicoFirma medicoFirma = new MedicoFirma();

      if (medicoFirmaView.getIdFirmaMedico() != null) {
         medicoFirma.setIdMedicoFirma(medicoFirmaView.getIdFirmaMedico());
      }

      medicoFirma.setContenido(medicoFirmaView.getContenido());
      medicoFirma.setNombre(medicoFirmaView.getNombre());
      medicoFirma.setFechaCreacion(new Date());

      return medicoFirma;
   }
}