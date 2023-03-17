package com.eydemo.demo;

import com.eydemo.demo.UsuarioDao;
import com.eydemo.demo.vo.JsonRest;
import com.eydemo.demo.vo.PhoneVO;
import com.eydemo.demo.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/usuarios")
public class UsuarioService {
    @Autowired
    UsuarioDao usuarioDao;
    @GetMapping("/obtenertodos")
    public JsonRest obtenerUsuarios() throws Exception{
        JsonRest responseUsuario = new JsonRest();
        List<UsuarioVO> usuarioVOList = new ArrayList<>();
        try{
            usuarioVOList = usuarioDao.obtenerUsuarios();
            if(usuarioVOList !=null && !usuarioVOList.isEmpty()){
                responseUsuario.setObj(usuarioVOList);
                responseUsuario.setEstado("EXITO");
            }else{
                responseUsuario.setMensaje("NINGUN USUARIO REGISTRADO");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return responseUsuario;
    }
    @PostMapping("/ingresar")
    public JsonRest ingresarUsuario(@RequestBody UsuarioVO vo) throws Exception{
        JsonRest responseUsuario = new JsonRest();
        UsuarioVO usuarioVO = new UsuarioVO();
        List<String> causas = new ArrayList<>();
        try{
            causas = this.validUsuario(vo);
            if(causas.isEmpty()){
                usuarioVO = usuarioDao.ingresarUsuario(vo);
                responseUsuario.setObj(usuarioVO);
                responseUsuario.setEstado("EXITO");
            }else{
                responseUsuario.setMensaje("Entrega de validaciones");
                responseUsuario.setListaValidaciones(causas);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return responseUsuario;
    }
    @PostMapping("/modificar")
    public JsonRest modificarUsuario(@RequestBody UsuarioVO vo) throws Exception{
        JsonRest responseUsuario = new JsonRest();
        UsuarioVO usuarioVO = new UsuarioVO();
        List<String> causas = new ArrayList<>();
        try{
            causas = this.validUsuario(vo);
            if(causas.isEmpty()){
                usuarioVO = usuarioDao.ingresarUsuario(vo);
                responseUsuario.setObj(usuarioVO);
                responseUsuario.setEstado("EXITO");
            }else{
                responseUsuario.setMensaje("Entrega de validaciones");
                responseUsuario.setListaValidaciones(causas);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return responseUsuario;
    }
    @PostMapping("/eliminar")
    public JsonRest eliminarUsuario(Integer id) throws Exception{
        JsonRest responseUsuario = new JsonRest();
        UsuarioVO usuarioVO = new UsuarioVO();
        List<String> causas = new ArrayList<>();
        try{
            usuarioVO = usuarioDao.eliminarUsuario(id);
            if(usuarioVO !=null){
                responseUsuario.setObj(usuarioVO);
                responseUsuario.setEstado("EXITO");
            }else{
                responseUsuario.setEstado("NO SE ELIMINO EL USUARIO");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return responseUsuario;
    }


    @GetMapping("/obtenerphone/todos")
    public JsonRest obtenerPhone() throws Exception{
        JsonRest responseUsuario = new JsonRest();
        List<PhoneVO> phoneVOList = new ArrayList<>();
        try{
            phoneVOList = usuarioDao.obtenerPhone();
            if(phoneVOList !=null && !phoneVOList.isEmpty()){
                responseUsuario.setObj(phoneVOList);
                responseUsuario.setEstado("EXITO");
            }else{
                responseUsuario.setMensaje("NINGUN USUARIO REGISTRADO");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return responseUsuario;
    }

    public List<String> validUsuario(UsuarioVO usuarioVO) throws Exception{
        List<String> causas = new ArrayList<>();
        try{
            List<UsuarioVO>  usuarioVOList = usuarioDao.obtenerUsuarios();
            if(usuarioVOList.stream().anyMatch(u->u.getEmail().equals(usuarioVO.getEmail()))) causas.add("El correo ya existe para otro usuario, ingrese uno distinto. ");
            if(usuarioVO == null) causas.add("El usuario viene nulo. ");
            if(!isValidEmail(usuarioVO.getEmail())) causas.add("El formato de correo ingresado no es valido. ");
            if(!isValidPass(usuarioVO.getPassword())) causas.add("El formato de la contraseña ingresado no es valido. Ingrese una mayuscula, letras minusculas y dos numeros como minimo. ");

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }





        return causas;

    }

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static final String PASS_REGEX =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2})[A-Za-z\\d]*$";

    public static boolean isValidPass(String email) {
        Pattern pattern = Pattern.compile(PASS_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
