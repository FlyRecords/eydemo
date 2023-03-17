package com.eydemo.demo.transformers;

import com.eydemo.demo.entity.Phone;
import com.eydemo.demo.entity.Usuario;
import com.eydemo.demo.vo.PhoneVO;
import com.eydemo.demo.vo.UsuarioVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioTransformer {
    public static UsuarioVO toVO(Usuario usuario) {
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setId(usuario.getId());
        usuarioVO.setName(usuario.getName() != null ? usuario.getName() : null);
        usuarioVO.setEmail(usuario.getEmail() != null ? usuario.getEmail() : null);
        usuarioVO.setPassword(usuario.getPassword() != null ? usuario.getPassword() : null);
        usuarioVO.setToken(usuario.getToken() != null ? usuario.getToken() : null);
        usuarioVO.setActive(usuario.isActive() != null ? usuario.isActive() : null);
        usuarioVO.setCreated(usuario.getCreated() != null ? usuario.getCreated() : LocalDateTime.now());
        usuarioVO.setModified(usuario.getModified() != null ? usuario.getModified() : null);
        usuarioVO.setLastLogin(usuario.getLastLogin() != null ? usuario.getLastLogin() : null);
        //usuarioVO.setPhones(usuario.getPhones() != null ? PhoneTransformer.phoneVOList(usuario.getPhones()) : null);

        List<PhoneVO> phoneVOList = new ArrayList<>();
        if(usuario.getPhones() !=null){
            for (Phone p : usuario.getPhones()){
                phoneVOList.add(PhoneTransformer.toVO(p));
            }
            usuarioVO.setPhones(phoneVOList);
        }
        return usuarioVO;
    }

    public static Usuario toModel(UsuarioVO usuarioVO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioVO.getId());
        usuario.setName(usuarioVO.getName() != null ? usuarioVO.getName() : null);
        usuario.setEmail(usuarioVO.getEmail() != null ? usuarioVO.getEmail() : null);
        usuario.setPassword(usuarioVO.getPassword() != null ? usuarioVO.getPassword() : null);
        usuario.setToken(usuarioVO.getToken() != null ? usuarioVO.getToken() : null);
        usuario.setActive(usuario.isActive() != null ? usuario.isActive() : null);
        usuario.setCreated(usuarioVO.getCreated() != null ? usuarioVO.getCreated() : LocalDateTime.now());
        usuario.setModified(usuarioVO.getModified() != null ? usuarioVO.getModified() : null);
        usuario.setLastLogin(usuarioVO.getLastLogin() != null ? usuarioVO.getLastLogin() : null);
        usuario.setPhones(usuarioVO.getPhones() != null ? PhoneTransformer.phoneList(usuarioVO.getPhones()) : null);
        return usuario;
    }


}
