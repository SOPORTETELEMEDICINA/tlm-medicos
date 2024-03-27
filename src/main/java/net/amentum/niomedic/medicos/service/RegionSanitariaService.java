package net.amentum.niomedic.medicos.service;

import net.amentum.niomedic.medicos.exception.RegionSanitariaException;
import net.amentum.niomedic.medicos.views.RegionSanitariaView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegionSanitariaService {

    RegionSanitariaView createRegionSanitaria(RegionSanitariaView view) throws RegionSanitariaException;

    RegionSanitariaView updateRegionSanitaria(Integer idRegionSanitaria, RegionSanitariaView view) throws RegionSanitariaException;

    Page<RegionSanitariaView> searchRegionSanitaria(Integer page, Integer size, String orderColumn, String orderType) throws RegionSanitariaException;

    RegionSanitariaView getRegionSanitariaById(Integer idRegionSanitaria) throws RegionSanitariaException;

    List<RegionSanitariaView> getRegionSanitariaByEstado(Integer idEstado) throws RegionSanitariaException;

}
