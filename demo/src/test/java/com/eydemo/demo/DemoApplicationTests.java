package com.eydemo.demo;

import com.eydemo.demo.dao.UsuarioDao;
import com.eydemo.demo.service.UsuarioService;
import com.eydemo.demo.vo.PhoneVO;
import com.eydemo.demo.vo.UsuarioVO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class DemoApplicationTests {

	@InjectMocks
	private UsuarioService usuarioService;

	@Mock
	private UsuarioDao usuarioDao;

	@Test
	public void ingresarUsuarioTest() throws Exception {
		// Preparación de datos de entrada
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setName("Alonso Cerda");
		usuarioVO.setEmail("a1c2erda@gmail.com");
		usuarioVO.setPassword("Holaa123");
		List<PhoneVO> phones = new ArrayList<>();
		phones.add(new PhoneVO("1234567", null, "33"));
		phones.add(new PhoneVO("1234569", null, "33"));
		usuarioVO.setPhones(phones);

		// Mock de los datos de salida del DAO
		UsuarioVO usuarioVOMock = new UsuarioVO();
		usuarioVOMock.setName("Alonso Cerda");
		usuarioVOMock.setEmail("a1c2erda@gmail.com");
		usuarioVOMock.setToken("7af1e679-ab11-4a50-b86e-42152adf5edc");
		usuarioVOMock.setActive(true);

		when(usuarioDao.ingresarUsuario(usuarioVO)).thenReturn(usuarioVOMock);

		// Ejecución del servicio
		JsonRest responseUsuario = usuarioService.ingresarUsuario(usuarioVO);

		// Verificación del resultado
		assertNotNull(responseUsuario);
		assertEquals("EXITO", responseUsuario.getEstado());
		assertNotNull(responseUsuario.getObj());
		assertEquals(1L, responseUsuario.getObj().getId().longValue());
		assertEquals("Alonso Cerda", responseUsuario.getObj().getName());
		assertEquals("a1c2erda@gmail.com", responseUsuario.getObj().getEmail());
		assertEquals("7af1e679-ab11-4a50-b86e-42152adf5edc", responseUsuario.getObj().getToken());
		assertTrue(responseUsuario.getObj().isActive());
	}

}
