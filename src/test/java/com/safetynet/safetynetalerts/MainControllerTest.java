package com.safetynet.safetynetalerts;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.controller.MainController;
import com.safetynet.safetynetalerts.dao.URLsDao;

@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private URLsDao urlsDao;
	
	@Test
	public void testGetPersonInfoJohnBoyd() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
			.andExpect(status().isOk());
	}

}
