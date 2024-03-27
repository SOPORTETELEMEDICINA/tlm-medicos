package net.amentum.niomedic.medicos;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.amentum.niomedic.medicos.views.EspecialidadView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {MedicosApplication.class})
public class EspecialidadTest {
   private final MediaType jsonType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf-8"));
   public MockMvc mockMvc;
   @Autowired
   private WebApplicationContext webApplicationContext;
   @Autowired
   private ObjectMapper objectMapper;

   @Before
   public void setup() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }

   @Test
   public void createEspecialidad() throws Exception {

      String idMedico = "374e4bfb-4e4e-4934-aead-d65da7b6ed7a";
      String rutaImagenCedula = "/var/www/html/niomedic/cedulas/";
      String rutaImagenDiploma = "/var/www/html/niomedic/diplomas/";

      EspecialidadView especialidadView = new EspecialidadView();

      especialidadView.setEspecialidad("setEspecialidad");
      especialidadView.setUniversidad("setUniversidad");
      especialidadView.setCedula("setCedula");
      especialidadView.setNombreImagenCedula(rutaImagenCedula + idMedico + "-EP-imagen-cedula");
      especialidadView.setNombreImagenCedula("setNombreImagenCedula");
      especialidadView.setNombreImagenDiploma(rutaImagenDiploma + idMedico + "-EP-imagen-diploma");
      especialidadView.setNombreImagenDiploma("setNombreImagenDiploma");
      especialidadView.setImgCedula64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAMAAADXqc3KAAAB+FBMVEUAAAA/mUPidDHiLi5Cn0XkNTPmeUrkdUg/m0Q0pEfcpSbwaVdKskg+lUP4zA/iLi3msSHkOjVAmETdJSjtYFE/lkPnRj3sWUs8kkLeqCVIq0fxvhXqUkbVmSjwa1n1yBLepyX1xxP0xRXqUkboST9KukpHpUbuvRrzrhF/ljbwaljuZFM4jELaoSdLtElJrUj1xxP6zwzfqSU4i0HYnydMtUlIqUfywxb60AxZqEXaoifgMCXptR9MtklHpEY2iUHWnSjvvRr70QujkC+pUC/90glMuEnlOjVMt0j70QriLS1LtEnnRj3qUUXfIidOjsxAhcZFo0bjNDH0xxNLr0dIrUdmntVTkMoyfL8jcLBRuErhJyrgKyb4zA/5zg3tYFBBmUTmQTnhMinruBzvvhnxwxZ/st+Ktt5zp9hqota2vtK6y9FemNBblc9HiMiTtMbFtsM6gcPV2r6dwroseLrMrbQrdLGdyKoobKbo3Zh+ynrgVllZulTsXE3rV0pIqUf42UVUo0JyjEHoS0HmsiHRGR/lmRz/1hjqnxjvpRWfwtOhusaz0LRGf7FEfbDVmqHXlJeW0pbXq5bec3fX0nTnzmuJuWvhoFFhm0FtrziBsjaAaDCYWC+uSi6jQS3FsSfLJiTirCOkuCG1KiG+wSC+GBvgyhTszQ64Z77KAAAARXRSTlMAIQRDLyUgCwsE6ebm5ubg2dLR0byXl4FDQzU1NDEuLSUgC+vr6urq6ubb29vb2tra2tG8vLu7u7uXl5eXgYGBgYGBLiUALabIAAABsElEQVQoz12S9VPjQBxHt8VaOA6HE+AOzv1wd7pJk5I2adpCC7RUcHd3d3fXf5PvLkxheD++z+yb7GSRlwD/+Hj/APQCZWxM5M+goF+RMbHK594v+tPoiN1uHxkt+xzt9+R9wnRTZZQpXQ0T5uP1IQxToyOAZiQu5HEpjeA4SWIoksRxNiGC1tRZJ4LNxgHgnU5nJZBDvuDdl8lzQRBsQ+s9PZt7s7Pz8wsL39/DkIfZ4xlB2Gqsq62ta9oxVlVrNZpihFRpGO9fzQw1ms0NDWZz07iGkJmIFH8xxkc3a/WWlubmFkv9AB2SEpDvKxbjidN2faseaNV3zoHXvv7wMODJdkOHAegweAfFPx4G67KluxzottCU9n8CUqXzcIQdXOytAHqXxomvykhEKN9EFutG22p//0rbNvHVxiJywa8yS2KDfV1dfbu31H8jF1RHiTKtWYeHxUvq3bn0pyjCRaiRU6aDO+gb3aEfEeVNsDgm8zzLy9egPa7Qt8TSJdwhjplk06HH43ZNJ3s91KKCHQ5x4sw1fRGYDZ0n1L4FKb9/BP5JLYxToheoFCVxz57PPS8UhhEpLBVeAAAAAElFTkSuQmCC");
      especialidadView.setImgTitulo64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAMAAADXqc3KAAAB+FBMVEUAAAA/mUPidDHiLi5Cn0XkNTPmeUrkdUg/m0Q0pEfcpSbwaVdKskg+lUP4zA/iLi3msSHkOjVAmETdJSjtYFE/lkPnRj3sWUs8kkLeqCVIq0fxvhXqUkbVmSjwa1n1yBLepyX1xxP0xRXqUkboST9KukpHpUbuvRrzrhF/ljbwaljuZFM4jELaoSdLtElJrUj1xxP6zwzfqSU4i0HYnydMtUlIqUfywxb60AxZqEXaoifgMCXptR9MtklHpEY2iUHWnSjvvRr70QujkC+pUC/90glMuEnlOjVMt0j70QriLS1LtEnnRj3qUUXfIidOjsxAhcZFo0bjNDH0xxNLr0dIrUdmntVTkMoyfL8jcLBRuErhJyrgKyb4zA/5zg3tYFBBmUTmQTnhMinruBzvvhnxwxZ/st+Ktt5zp9hqota2vtK6y9FemNBblc9HiMiTtMbFtsM6gcPV2r6dwroseLrMrbQrdLGdyKoobKbo3Zh+ynrgVllZulTsXE3rV0pIqUf42UVUo0JyjEHoS0HmsiHRGR/lmRz/1hjqnxjvpRWfwtOhusaz0LRGf7FEfbDVmqHXlJeW0pbXq5bec3fX0nTnzmuJuWvhoFFhm0FtrziBsjaAaDCYWC+uSi6jQS3FsSfLJiTirCOkuCG1KiG+wSC+GBvgyhTszQ64Z77KAAAARXRSTlMAIQRDLyUgCwsE6ebm5ubg2dLR0byXl4FDQzU1NDEuLSUgC+vr6urq6ubb29vb2tra2tG8vLu7u7uXl5eXgYGBgYGBLiUALabIAAABsElEQVQoz12S9VPjQBxHt8VaOA6HE+AOzv1wd7pJk5I2adpCC7RUcHd3d3fXf5PvLkxheD++z+yb7GSRlwD/+Hj/APQCZWxM5M+goF+RMbHK594v+tPoiN1uHxkt+xzt9+R9wnRTZZQpXQ0T5uP1IQxToyOAZiQu5HEpjeA4SWIoksRxNiGC1tRZJ4LNxgHgnU5nJZBDvuDdl8lzQRBsQ+s9PZt7s7Pz8wsL39/DkIfZ4xlB2Gqsq62ta9oxVlVrNZpihFRpGO9fzQw1ms0NDWZz07iGkJmIFH8xxkc3a/WWlubmFkv9AB2SEpDvKxbjidN2faseaNV3zoHXvv7wMODJdkOHAegweAfFPx4G67KluxzottCU9n8CUqXzcIQdXOytAHqXxomvykhEKN9EFutG22p//0rbNvHVxiJywa8yS2KDfV1dfbu31H8jF1RHiTKtWYeHxUvq3bn0pyjCRaiRU6aDO+gb3aEfEeVNsDgm8zzLy9egPa7Qt8TSJdwhjplk06HH43ZNJ3s91KKCHQ5x4sw1fRGYDZ0n1L4FKb9/BP5JLYxToheoFCVxz57PPS8UhhEpLBVeAAAAAElFTkSuQmCC");
      especialidadView.setValidado(Boolean.FALSE);
      especialidadView.setFechaCreacion(new Date());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
      String dateS = "21/12/1976";
      Date dateD = sdf.parse(dateS);
      especialidadView.setFechaValidacion(dateD);

      ArrayList<EspecialidadView> especialidadViewArrayList = new ArrayList<>();

      especialidadViewArrayList.add(especialidadView);

      System.out.println(objectMapper.writeValueAsString(especialidadViewArrayList));

//      mockMvc.perform(MockMvcRequestBuilders.post("/medicos/" + idMedico + "/especialidad")
//         .contentType(jsonType)
//         .content(objectMapper.writeValueAsString(especialidadViewArrayList)))
//         .andExpect(MockMvcResultMatchers.status().isCreated())
//         .andDo(MockMvcResultHandlers.print());

   }

   @Test
   public void updateEspecialidad() throws Exception {

      String idMedico = "5e1610b7-a41e-44e4-91da-004d322866ff";
      String idEspecialidad = "80845d2b-ec1d-46d5-b7d5-2920fe62239e";
      String rutaImagenCedula = "/var/www/html/niomedic/cedulas/";
      String rutaImagenDiploma = "/var/www/html/niomedic/diplomas/";

      EspecialidadView especialidadView = new EspecialidadView();

      especialidadView.setIdEspecialidad(idEspecialidad);
      especialidadView.setEspecialidad("EP-especilidad-U");
      especialidadView.setUniversidad("EP-universidad-U");
      especialidadView.setCedula("EP-cedula-U");
      especialidadView.setNombreImagenCedula(rutaImagenCedula + idMedico + "-EP-imagen-cedula-U");
      especialidadView.setNombreImagenDiploma(rutaImagenDiploma + idMedico + "-EP-imagen-diploma-U");
      
      especialidadView.setImgCedula64("data:image/png;base64,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");
      especialidadView.setImgTitulo64("data:image/png;base64,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");

      especialidadView.setValidado(Boolean.FALSE);
      especialidadView.setFechaCreacion(new Date());
      SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
      String dateS = "21/12/1999";
      Date dateD = sdf.parse(dateS);
      especialidadView.setFechaValidacion(dateD);

      ArrayList<EspecialidadView> especialidadViewArrayList = new ArrayList<>();

      especialidadViewArrayList.add(especialidadView);

      System.out.println(objectMapper.writeValueAsString(especialidadView));

      mockMvc.perform(MockMvcRequestBuilders.put("/medicos/" + idMedico + "/especialidad")
         .contentType(jsonType)
         .content(objectMapper.writeValueAsString(especialidadViewArrayList)))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());

   }

   @Test
   public void deleteEspecialidad() throws Exception {

      String idMedico = "5e1610b7-a41e-44e4-91da-004d322866ff";
      String idEspecialidad = "78d2c1f7-6af7-4a04-854e-b0bc0cb99fee";

      ArrayList<EspecialidadView> especialidadViewArrayList = new ArrayList<>();

      EspecialidadView especialidadView = new EspecialidadView();
      especialidadView.setIdEspecialidad(idEspecialidad);

      especialidadViewArrayList.add(especialidadView);

      mockMvc.perform(MockMvcRequestBuilders.delete("/medicos/" + idMedico + "/especialidad")
         .contentType(jsonType)
         .content(objectMapper.writeValueAsString(especialidadViewArrayList)))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(MockMvcResultHandlers.print());

   }

   @Test
   public void subirCedula() throws Exception {

      String idMedico = "c4fd1d31-b0c2-4b3a-800f-2c0b9183fbff";

      String nombreArchivo[] = {"codigo.jpg", "lunaTaciturna.jpg"};
      String contentType[] = {"image/jpg", "image/jpg"};

      try {
         MockMultipartFile file[] = new MockMultipartFile[nombreArchivo.length];
         for (int i = 0; i < nombreArchivo.length; i++) {
            byte[] bytes = Files.readAllBytes(Paths.get(".." + File.separator, nombreArchivo[i]));
            file[i] = new MockMultipartFile("file", nombreArchivo[i], contentType[i], bytes);
         }

         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/medicos/subirCedula/" + idMedico)
            .file(file[0]).file(file[1]))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
      } catch (Exception e) {
         System.out.println("---> Error el el test --> {}" + e.getMessage());
      }
   }

   @Test
   public void subirDiploma() throws Exception {

      String idMedico = "c4fd1d31-b0c2-4b3a-800f-2c0b9183fbff";

      String nombreArchivo[] = {"codigo.jpg", "lunaTaciturna.jpg"};
      String contentType[] = {"image/jpg", "image/jpg"};

      try {
         MockMultipartFile file[] = new MockMultipartFile[nombreArchivo.length];
         for (int i = 0; i < nombreArchivo.length; i++) {
            byte[] bytes = Files.readAllBytes(Paths.get(".." + File.separator, nombreArchivo[i]));
            file[i] = new MockMultipartFile("file", nombreArchivo[i], contentType[i], bytes);
         }

         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/medicos/subirDiploma/" + idMedico)
            .file(file[0]).file(file[1]))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
      } catch (Exception e) {
         System.out.println("---> Error el el test --> {}" + e.getMessage());
      }
   }
}
