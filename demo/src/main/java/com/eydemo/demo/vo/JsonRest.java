package com.eydemo.demo.vo;

import java.util.List;

public class JsonRest {
    Object obj;
    String estado;
    String mensaje;
    String mensajeSecundario;
    List<String> listaValidaciones;

    public List<String> getListaValidaciones() {
        return listaValidaciones;
    }

    public void setListaValidaciones(List<String> listaValidaciones) {
        this.listaValidaciones = listaValidaciones;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeSecundario() {
        return mensajeSecundario;
    }

    public void setMensajeSecundario(String mensajeSecundario) {
        this.mensajeSecundario = mensajeSecundario;
    }
}
