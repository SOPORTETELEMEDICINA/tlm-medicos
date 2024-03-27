package net.amentum.niomedic.medicos.service;

import net.amentum.niomedic.medicos.exception.EspecialidadException;
import net.amentum.niomedic.medicos.views.EspecialidadView;

import java.util.ArrayList;

public interface EspecialidadService {
   void createEspecialidad(ArrayList<EspecialidadView> domicilioViewArrayList, String idMedico, String direccion) throws EspecialidadException;

   void updateEspecialidad(ArrayList<EspecialidadView> domicilioViewArrayList, String idMedico, String Direccion) throws EspecialidadException;

   void deleteEspecialidad(ArrayList<EspecialidadView> domicilioViewArrayList, String idMedico) throws EspecialidadException;
}
