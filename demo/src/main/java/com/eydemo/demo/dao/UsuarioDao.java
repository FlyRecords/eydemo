package com.eydemo.demo.dao;

import com.eydemo.demo.vo.UsuarioVO;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
public interface UsuarioDao {

    public List<UsuarioVO> obtenerUsuarios() throws Exception;

    public UsuarioVO obtenerUsuariosById(Integer id) throws Exception;
}
