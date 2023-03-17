package com.eydemo.demo;

import com.eydemo.demo.entity.Phone;
import com.eydemo.demo.entity.Usuario;
import com.eydemo.demo.transformers.PhoneTransformer;
import com.eydemo.demo.transformers.UsuarioTransformer;
import com.eydemo.demo.vo.PhoneVO;
import com.eydemo.demo.vo.UsuarioVO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioDaoImpl  implements UsuarioDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UsuarioVO> obtenerUsuarios() throws Exception{
        Session session = sessionFactory.openSession();
        List<UsuarioVO> usuarioVOList = new ArrayList<>();
        try {
            Query query =session.createNamedQuery("Usuario.findAll");
            List<Usuario> usuarioList = query.getResultList();

            if(usuarioList !=null && !usuarioList.isEmpty()){
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
        Session session = sessionFactory.openSession();
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

    @Override
    public UsuarioVO ingresarUsuario(UsuarioVO usuario) throws Exception{
        Session session = sessionFactory.openSession();
        try{
            if(usuario !=null){
                session.beginTransaction();
                Usuario model = new Usuario();
                model = UsuarioTransformer.toModel(usuario);

                ZoneId zoneChile = ZoneId.of("America/Santiago");
                ZonedDateTime fechaChile = ZonedDateTime.now(zoneChile);

                model.setCreated(fechaChile.toLocalDateTime());
                model.setLastLogin(fechaChile.toLocalDateTime());
                session.save(model);
                for (Phone p : model.getPhones()){
                    p.setUsuarios(model);
                    session.save(p);
                }
                usuario.setId(model.getId());
                session.getTransaction().commit();
            }
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }

        return usuario;

    }

    @Override
    public UsuarioVO modificarUsuario(UsuarioVO usuario) throws Exception{
        Session session = sessionFactory.openSession();
        try{
            if(usuario !=null && usuario.getId() !=null){
                session.beginTransaction();
                Usuario model = new Usuario();
                model = UsuarioTransformer.toModel(usuario);

                ZoneId zoneChile = ZoneId.of("America/Santiago");
                ZonedDateTime fechaChile = ZonedDateTime.now(zoneChile);
                model.setModified(fechaChile.toLocalDateTime());
                model.setLastLogin(fechaChile.toLocalDateTime());

                session.update(model);
                for (Phone p : model.getPhones()){
                    p.setUsuarios(model);
                    session.update(p);
                }
                usuario.setId(model.getId());
                session.getTransaction().commit();
            }
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }

        return usuario;

    }

    @Override
    public UsuarioVO eliminarUsuario(Integer id) throws Exception{
        Session session = sessionFactory.openSession();
        UsuarioVO vo = new UsuarioVO();
        try{
            session.beginTransaction();
            Usuario model = new Usuario();
            Query query =session.createNamedQuery("Usuario.findById").setParameter("id", id);
            model = (Usuario) query.getResultList().get(0);
            if(model !=null){
                model.setActive(false);
                ZoneId zoneChile = ZoneId.of("America/Santiago");
                ZonedDateTime fechaChile = ZonedDateTime.now(zoneChile);
                model.setModified(fechaChile.toLocalDateTime());
                model.setLastLogin(fechaChile.toLocalDateTime());

                session.update(model);
                model.setId(model.getId());

                vo= UsuarioTransformer.toVO(model);
                session.getTransaction().commit();
            }




        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }

        return vo;

    }


    @Override
    public List<PhoneVO> obtenerPhone() throws Exception{
        Session session = sessionFactory.openSession();
        List<PhoneVO> phoneVOList = new ArrayList<>();
        try {
            Query query =session.createNamedQuery("Phone.findAll");
            List<Phone> phoneList = query.getResultList();

            if(phoneList !=null || !phoneList.isEmpty()){
                for(Phone model : phoneList){
                    PhoneVO vo = new PhoneVO();
                    vo = PhoneTransformer.toVO(model);
                    phoneVOList.add(vo);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            session.close();
        }


        return phoneVOList;
    }
}
