package net.amentum.niomedic.medicos.service;

import net.amentum.niomedic.medicos.exception.DomicilioException;
import net.amentum.niomedic.medicos.views.DomicilioView;

import java.util.ArrayList;

public interface DomicilioService {
   void createDomicilio(ArrayList<DomicilioView> domicilioViewArrayList, String idMedico) throws DomicilioException;

   void updateDomicilio(ArrayList<DomicilioView> domicilioViewArrayList, String idMedico) throws DomicilioException;

   void deleteDomicilio(ArrayList<DomicilioView> domicilioViewArrayList, String idMedico) throws DomicilioException;
}
