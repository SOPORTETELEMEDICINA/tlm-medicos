package net.amentum.niomedic.medicos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cat_dir_reg_sanitaria")
public class RegionSanitaria implements Serializable {

    private static final long serialVersionUID = 5002575526201515194L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rs")
    private Integer idRs;
    @Column(name = "rs_num")
    private String rsNum;
    @Column(name = "rs_edo")
    private Integer rsEdo;
    @Column(name = "rs_nom")
    private String rsNom;
    @Column(name = "rs_dom")
    private String rsDom;
    @Column(name = "rs_cp")
    private Integer rsCp;
    @Column(name = "rs_mun")
    private String rsMun;
    @Column(name = "rs_dir")
    private String rsDir;
    @Column(name = "rs_est")
    private String rsEst;
    @Column(name = "rs_email")
    private String rsEmail;
    @Column(name = "rs_con1")
    private String rsCon1;
    @Column(name = "rs_con1_lada")
    private Integer rsCon1Lada;
    @Column(name = "rs_con1_tel")
    private Integer rsCon1Tel;
    @Column(name = "rs_con1_ext")
    private Integer rsCon1Ext;
    @Column(name = "rs_con2")
    private String rsCon2;
    @Column(name = "rs_con2_lada")
    private Integer rsCon2Lada;
    @Column(name = "rs_con2_tel")
    private Integer rsCon2Tel;
    @Column(name = "rs_con2_ext")
    private Integer rsCon2Ext;
    @Column(name = "rs_activo")
    private Integer rsActivo;


}
