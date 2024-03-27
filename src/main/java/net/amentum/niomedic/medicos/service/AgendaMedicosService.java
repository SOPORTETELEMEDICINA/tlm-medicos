package net.amentum.niomedic.medicos.service;

import net.amentum.niomedic.medicos.exception.AgendamedicosException;
import net.amentum.niomedic.medicos.views.AgendaMedicosView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgendaMedicosService {

    void createAgendamedicos(AgendaMedicosView AgendamedicosView) throws AgendamedicosException;

    void updateAgendamedicos(AgendaMedicosView AgendamedicosView) throws AgendamedicosException;

    void deleteAgendamedicos(Integer idAgendamedicos) throws AgendamedicosException;

    List<AgendaMedicosView> findAll() throws AgendamedicosException;

    Page<AgendaMedicosView> getAgendamedicosSearch(String idmedico, Integer page, Integer size, String orderColumn, String orderType) throws AgendamedicosException;

}
