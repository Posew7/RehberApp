package com.bilisimegitim.rehberapp.mb;

import com.bilisimegitim.rehberapp.entity.Kisi;
import com.bilisimegitim.rehberapp.session.KisiFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "kisiMB")
@SessionScoped
public class KisiMB implements Serializable {

    @EJB
    private KisiFacade kisiFacade;

    private int no;
    private String ad;
    private String soyad;
    private BigDecimal maas;
    private java.util.Date dogtar;

    private List<Kisi> kisiListesi;

    public KisiMB() {

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public BigDecimal getMaas() {
        return maas;
    }

    public void setMaas(BigDecimal maas) {
        this.maas = maas;
    }

    public Date getDogtar() {
        return dogtar;
    }

    public void setDogtar(Date dogtar) {
        this.dogtar = dogtar;
    }

    public List<Kisi> getKisiListesi() {
        kisiListesi = kisiFacade.findAll();
        return kisiListesi;
    }

    public void setKisiListesi(List<Kisi> kisiListesi) {
        this.kisiListesi = kisiListesi;
    }

    public String ekle() {
        Kisi k = new Kisi();
        k.setAd(ad);
        k.setSoyad(soyad);
        k.setMaas(maas);
        k.setDogtar(dogtar);

        kisiFacade.create(k);

        kisiListesi = kisiFacade.findAll();

        return "kisiListele.xhtml?faces-redirect=true";
    }

    public List<Kisi> listele() {

        if (kisiListesi == null) {
            kisiListesi = kisiFacade.findAll();
            return kisiListesi;
        } else {
            return kisiListesi;
        }

    }

    public String sil() {

        Kisi k = kisiFacade.find(no);
        kisiFacade.remove(k);

        kisiListesi = kisiFacade.findAll();

        return "kisiListele.xhtml?faces-redirect=true";
    }

    public String guncelle() {

        Kisi k = new Kisi();
        
        k.setNo(no);
        k.setAd(ad);
        k.setSoyad(soyad);
        k.setMaas(maas);
        k.setDogtar(dogtar);
        
        kisiFacade.edit(k);
        
        return "";
    }

    public String getir() {

        Kisi k = kisiFacade.find(no);

        this.ad = k.getAd();
        this.soyad = k.getSoyad();
        this.maas = k.getMaas();
        this.dogtar = k.getDogtar();

        return "";
    }

    public String temizle(){
        
        no = 0;
        ad = "";
        soyad = "";
        maas = null;
        dogtar = null;
        
        return "";
    }
    
}
