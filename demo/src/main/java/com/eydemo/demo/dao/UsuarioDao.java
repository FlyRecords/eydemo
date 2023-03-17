package com.eydemo.demo.dao;

import com.eydemo.demo.vo.PhoneVO;
import com.eydemo.demo.vo.UsuarioVO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UsuarioDao {

    public List<UsuarioVO> obtenerUsuarios() throws Exception;

    public UsuarioVO obtenerUsuariosById(Integer id) throws Exception;

    public UsuarioVO ingresarUsuario(UsuarioVO usuario) throws Exception;

    public UsuarioVO modificarUsuario(UsuarioVO usuario) throws Exception;

    public UsuarioVO eliminarUsuario(Integer id) throws Exception;

    public List<PhoneVO> obtenerPhone() throws Exception;
}
