package net.amentum.niomedic.medicos.converter;

import lombok.extern.slf4j.Slf4j;
import net.amentum.niomedic.medicos.model.RegionSanitaria;
import net.amentum.niomedic.medicos.views.RegionSanitariaView;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegionSanitariaConverter {

    public RegionSanitaria toEntity(RegionSanitariaView view) {
        RegionSanitaria entity = new RegionSanitaria();
        entity.setIdRs(view.getIdRs());
        entity.setRsNum(view.getRsNum());
        entity.setRsEdo(view.getRsEdo());
        entity.setRsNom(view.getRsNom());
        entity.setRsDom(view.getRsDom());
        entity.setRsCp(view.getRsCp());
        entity.setRsMun(view.getRsMun());
        entity.setRsDir(view.getRsDir());
        entity.setRsEst(view.getRsEst());
        entity.setRsEmail(view.getRsEmail());
        entity.setRsCon1(view.getRsCon1());
        entity.setRsCon1Tel(view.getRsCon1Tel());
        entity.setRsCon1Ext(view.getRsCon1Ext());
        entity.setRsCon2(view.getRsCon2());
        entity.setRsCon2Lada(view.getRsCon2Lada());
        entity.setRsCon2Tel(view.getRsCon2Tel());
        entity.setRsCon2Ext(view.getRsCon2Ext());
        entity.setRsActivo(view.getRsActivo());
        return entity;
    }

    public RegionSanitariaView toView(RegionSanitaria entity) {
        RegionSanitariaView view = new RegionSanitariaView();
        view.setIdRs(entity.getIdRs());
        view.setRsNum(entity.getRsNum());
        view.setRsEdo(entity.getRsEdo());
        view.setRsNom(entity.getRsNom());
        view.setRsDom(entity.getRsDom());
        view.setRsCp(entity.getRsCp());
        view.setRsMun(entity.getRsMun());
        view.setRsDir(entity.getRsDir());
        view.setRsEst(entity.getRsEst());
        view.setRsEmail(entity.getRsEmail());
        view.setRsCon1(entity.getRsCon1());
        view.setRsCon1Tel(entity.getRsCon1Tel());
        view.setRsCon1Ext(entity.getRsCon1Ext());
        view.setRsCon2(entity.getRsCon2());
        view.setRsCon2Lada(entity.getRsCon2Lada());
        view.setRsCon2Tel(entity.getRsCon2Tel());
        view.setRsCon2Ext(entity.getRsCon2Ext());
        view.setRsActivo(entity.getRsActivo());
        return view;
    }

}
