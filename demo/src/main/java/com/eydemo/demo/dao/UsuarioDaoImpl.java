package com.eydemo.demo.dao;

import com.eydemo.demo.entity.Usuario;
import com.eydemo.demo.transformers.UsuarioTransformer;
import com.eydemo.demo.vo.UsuarioVO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl  implements  UsuarioDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UsuarioVO> obtenerUsuarios() throws Exception{
        Session session = sessionFactory.getCurrentSession();
        List<UsuarioVO> usuarioVOList = new ArrayList<>();
        try {
            Query query =session.createNamedQuery("Usuario.findAll");
            List<Usuario> usuarioList = query.getResultList();

            if(usuarioList !=null || !usuarioList.isEmpty()){
                for(Usuario model : usuarioList){
                    UsuarioVO vo = new UsuarioVO();
                    vo = UsuarioTransformer.toVO(model);
                    usuarioVOList.add(vo);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            session.close();
        }


        return usuarioVOList;
    }

    @Override
    public UsuarioVO obtenerUsuariosById(Integer id) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        UsuarioVO usuarioVO = new UsuarioVO();
        try {
            Query query =session.createNamedQuery("Usuario.findById").setParameter("id", id);
            Usuario usuario = (Usuario) query.getResultList().get(0);
            usuarioVO = UsuarioTransformer.toVO(usuario);



        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            session.close();
        }


        return usuarioVO;
    }
}
