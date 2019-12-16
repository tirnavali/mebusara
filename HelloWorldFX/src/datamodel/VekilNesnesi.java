package datamodel;

import java.time.LocalDate;

public class VekilNesnesi {
    private String ad;
    private String soyad;
    private String ad_2;
    private LocalDate dogum_t;

    public VekilNesnesi(String ad, String soyad, String ad_2, LocalDate dogum_t) {
        this.ad = ad;
        this.soyad = soyad;
        this.ad_2 = ad_2;
        this.dogum_t = dogum_t;
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

    public String getAd_2() {
        return ad_2;
    }

    public void setAd_2(String ad_2) {
        this.ad_2 = ad_2;
    }

    public LocalDate getDogum_t() {
        return dogum_t;
    }

    public void setDogum_t(LocalDate dogum_t) {
        this.dogum_t = dogum_t;
    }

    @Override
    public String toString(){
        return ad+"\t"+ad_2+"\t"+soyad+"\t" +dogum_t.toString();
    }
}
