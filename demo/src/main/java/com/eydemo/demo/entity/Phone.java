package com.eydemo.demo.entity;
import javax.persistence.*;

@Entity
@Table(name = "phones")
@NamedQueries({
        @NamedQuery(name = "Phone.findAll", query = "select p from Phone p"),
        @NamedQuery(name = "Phone.findById", query = "select p from Phone p where p.id = :id")
})
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "cytycode")
    private String cityCode;

    @Column(name = "countrycode")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "Usuarios_idUsuarios")
    private Usuario usuarios;

    public Phone() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Phone(Integer id, String number, String cityCode, String countryCode, Usuario usuarios) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.usuarios = usuarios;
    }
}

