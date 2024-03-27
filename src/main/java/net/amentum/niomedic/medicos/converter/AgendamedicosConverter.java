package net.amentum.niomedic.medicos.converter;

import net.amentum.niomedic.medicos.model.AgendaMedicos;
import net.amentum.niomedic.medicos.views.AgendaMedicosView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AgendamedicosConverter {

    private final Logger logger = LoggerFactory.getLogger(AgendamedicosConverter.class);

    public AgendaMedicos toEntity(AgendaMedicosView AgendamedicosView, AgendaMedicos Agendamedicos, Boolean update) {

        Agendamedicos.setIdagenda(AgendamedicosView.getIdagenda());
        Agendamedicos.setFechaingresoinst(AgendamedicosView.getFechaingresoinst());
        Agendamedicos.setIdmedico(AgendamedicosView.getIdmedico());
        Agendamedicos.setMeddom(AgendamedicosView.getMeddom());
        Agendamedicos.setMedlun(AgendamedicosView.getMedlun());
        Agendamedicos.setMedmar(AgendamedicosView.getMedmar());
        Agendamedicos.setMedmie(AgendamedicosView.getMedmie());
        Agendamedicos.setMedjue(AgendamedicosView.getMedjue());
        Agendamedicos.setMedvie(AgendamedicosView.getMedvie());
        Agendamedicos.setMedsab(AgendamedicosView.getMedsab());

        Agendamedicos.setLunEntMat(AgendamedicosView.getLunEntMat());
        Agendamedicos.setLunSalMat(AgendamedicosView.getLunSalMat());
        Agendamedicos.setLunEntVesp(AgendamedicosView.getLunEntVesp());
        Agendamedicos.setLunSalVesp(AgendamedicosView.getLunSalVesp());
        Agendamedicos.setLunEntNoct(AgendamedicosView.getLunEntNoct());
        Agendamedicos.setLunSalNoct(AgendamedicosView.getLunSalNoct());

        Agendamedicos.setMarEntMat(AgendamedicosView.getMarEntMat());
        Agendamedicos.setMarSalMat(AgendamedicosView.getMarSalMat());
        Agendamedicos.setMarEntVesp(AgendamedicosView.getMarEntVesp());
        Agendamedicos.setMarSalVesp(AgendamedicosView.getMarSalVesp());
        Agendamedicos.setMarEntNoct(AgendamedicosView.getMarEntNoct());
        Agendamedicos.setMarSalNoct(AgendamedicosView.getMarSalNoct());

        Agendamedicos.setMieEntMat(AgendamedicosView.getMieEntMat());
        Agendamedicos.setMieSalMat(AgendamedicosView.getMieSalMat());
        Agendamedicos.setMieEntVesp(AgendamedicosView.getMieEntVesp());
        Agendamedicos.setMieSalVesp(AgendamedicosView.getMieSalVesp());
        Agendamedicos.setMieEntNoct(AgendamedicosView.getMieEntNoct());
        Agendamedicos.setMieSalNoct(AgendamedicosView.getMieSalNoct());

        Agendamedicos.setJueEntMat(AgendamedicosView.getJueEntMat());
        Agendamedicos.setJueSalMat(AgendamedicosView.getJueSalMat());
        Agendamedicos.setJueEntVesp(AgendamedicosView.getJueEntVesp());
        Agendamedicos.setJueSalVesp(AgendamedicosView.getJueSalVesp());
        Agendamedicos.setJueEntNoct(AgendamedicosView.getJueEntNoct());
        Agendamedicos.setJueSalNoct(AgendamedicosView.getJueSalNoct());

        Agendamedicos.setVieEntMat(AgendamedicosView.getVieEntMat());
        Agendamedicos.setVieSalMat(AgendamedicosView.getVieSalMat());
        Agendamedicos.setVieEntVesp(AgendamedicosView.getVieEntVesp());
        Agendamedicos.setVieSalVesp(AgendamedicosView.getVieSalVesp());
        Agendamedicos.setVieEntNoct(AgendamedicosView.getVieEntNoct());
        Agendamedicos.setVieSalNoct(AgendamedicosView.getVieSalNoct());

        Agendamedicos.setSabEntMat(AgendamedicosView.getSabEntMat());
        Agendamedicos.setSabSalMat(AgendamedicosView.getSabSalMat());
        Agendamedicos.setSabEntVesp(AgendamedicosView.getSabEntVesp());
        Agendamedicos.setSabSalVesp(AgendamedicosView.getSabSalVesp());
        Agendamedicos.setSabEntNoct(AgendamedicosView.getSabEntNoct());
        Agendamedicos.setSabSalNoct(AgendamedicosView.getSabSalNoct());

        Agendamedicos.setDomEntMat(AgendamedicosView.getDomEntMat());
        Agendamedicos.setDomSalMat(AgendamedicosView.getDomSalMat());
        Agendamedicos.setDomEntVesp(AgendamedicosView.getDomEntVesp());
        Agendamedicos.setDomSalVesp(AgendamedicosView.getDomSalVesp());
        Agendamedicos.setDomEntNoct(AgendamedicosView.getDomEntNoct());
        Agendamedicos.setDomSalNoct(AgendamedicosView.getDomSalNoct());
        logger.debug("convertir AgendamedicosView to Entity: {}", Agendamedicos);
        return Agendamedicos;
    }

      public AgendaMedicosView toView(AgendaMedicos Agendamedicos, Boolean complete) {
        AgendaMedicosView AgendamedicosView = new AgendaMedicosView();

          AgendamedicosView.setIdagenda(Agendamedicos.getIdagenda());
          AgendamedicosView.setFechaingresoinst(Agendamedicos.getFechaingresoinst());
          AgendamedicosView.setIdmedico(Agendamedicos.getIdmedico());
          AgendamedicosView.setMeddom(Agendamedicos.getMeddom());
          AgendamedicosView.setMedlun(Agendamedicos.getMedlun());
          AgendamedicosView.setMedmar(Agendamedicos.getMedmar());
          AgendamedicosView.setMedmie(Agendamedicos.getMedmie());
          AgendamedicosView.setMedjue(Agendamedicos.getMedjue());
          AgendamedicosView.setMedvie(Agendamedicos.getMedvie());
          AgendamedicosView.setMedsab(Agendamedicos.getMedsab());

          AgendamedicosView.setLunEntMat(Agendamedicos.getLunEntMat());
          AgendamedicosView.setLunSalMat(Agendamedicos.getLunSalMat());
          AgendamedicosView.setLunEntVesp(Agendamedicos.getLunEntVesp());
          AgendamedicosView.setLunSalVesp(Agendamedicos.getLunSalVesp());
          AgendamedicosView.setLunEntNoct(Agendamedicos.getLunEntNoct());
          AgendamedicosView.setLunSalNoct(Agendamedicos.getLunSalNoct());

          AgendamedicosView.setMarEntMat(Agendamedicos.getMarEntMat());
          AgendamedicosView.setMarSalMat(Agendamedicos.getMarSalMat());
          AgendamedicosView.setMarEntVesp(Agendamedicos.getMarEntVesp());
          AgendamedicosView.setMarSalVesp(Agendamedicos.getMarSalVesp());
          AgendamedicosView.setMarEntNoct(Agendamedicos.getMarEntNoct());
          AgendamedicosView.setMarSalNoct(Agendamedicos.getMarSalNoct());

          AgendamedicosView.setMieEntMat(Agendamedicos.getMieEntMat());
          AgendamedicosView.setMieSalMat(Agendamedicos.getMieSalMat());
          AgendamedicosView.setMieEntVesp(Agendamedicos.getMieEntVesp());
          AgendamedicosView.setMieSalVesp(Agendamedicos.getMieSalVesp());
          AgendamedicosView.setMieEntNoct(Agendamedicos.getMieEntNoct());
          AgendamedicosView.setMieSalNoct(Agendamedicos.getMieSalNoct());

          AgendamedicosView.setJueEntMat(Agendamedicos.getJueEntMat());
          AgendamedicosView.setJueSalMat(Agendamedicos.getJueSalMat());
          AgendamedicosView.setJueEntVesp(Agendamedicos.getJueEntVesp());
          AgendamedicosView.setJueSalVesp(Agendamedicos.getJueSalVesp());
          AgendamedicosView.setJueEntNoct(Agendamedicos.getJueEntNoct());
          AgendamedicosView.setJueSalNoct(Agendamedicos.getJueSalNoct());

          AgendamedicosView.setVieEntMat(Agendamedicos.getVieEntMat());
          AgendamedicosView.setVieSalMat(Agendamedicos.getVieSalMat());
          AgendamedicosView.setVieEntVesp(Agendamedicos.getVieEntVesp());
          AgendamedicosView.setVieSalVesp(Agendamedicos.getVieSalVesp());
          AgendamedicosView.setVieEntNoct(Agendamedicos.getVieEntNoct());
          AgendamedicosView.setVieSalNoct(Agendamedicos.getVieSalNoct());

          AgendamedicosView.setSabEntMat(Agendamedicos.getSabEntMat());
          AgendamedicosView.setSabSalMat(Agendamedicos.getSabSalMat());
          AgendamedicosView.setSabEntVesp(Agendamedicos.getSabEntVesp());
          AgendamedicosView.setSabSalVesp(Agendamedicos.getSabSalVesp());
          AgendamedicosView.setSabEntNoct(Agendamedicos.getSabEntNoct());
          AgendamedicosView.setSabSalNoct(Agendamedicos.getSabSalNoct());

          AgendamedicosView.setDomEntMat(Agendamedicos.getDomEntMat());
          AgendamedicosView.setDomSalMat(Agendamedicos.getDomSalMat());
          AgendamedicosView.setDomEntVesp(Agendamedicos.getDomEntVesp());
          AgendamedicosView.setDomSalVesp(Agendamedicos.getDomSalVesp());
          AgendamedicosView.setDomEntNoct(Agendamedicos.getDomEntNoct());
          AgendamedicosView.setDomSalNoct(Agendamedicos.getDomSalNoct());
        logger.debug("convertir Agendamedicos to View: {}", AgendamedicosView);
        return AgendamedicosView;
    }

}
