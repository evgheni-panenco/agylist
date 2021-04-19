package com.agylist.controller;

import com.agylist.model.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.FileCopyUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.agylist.controller.ProfileController.BASE_URL;
import static com.agylist.controller.ProfileController.ONE_URL;
import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProfileControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testPost_Success() throws Exception {
		postProfile().andExpect(jsonPath("firstName").value("John")).andExpect(jsonPath("lastName").value("Doe"))
				.andExpect(jsonPath("emailAddress").value("john.doe@gmail.com"))
				.andExpect(jsonPath("role").value("Manager"));
	}

	@Test
	void testGet_Success() throws Exception {
		val postResult = postProfile().andReturn();
		val profile = getResponse(postResult);

		mockMvc.perform(get((BASE_URL + ONE_URL).replace("{profileId}", profile.getProfileId().toString())))
				.andExpect(jsonPath("firstName").value("John")).andExpect(jsonPath("lastName").value("Doe"))
				.andExpect(jsonPath("emailAddress").value("john.doe@gmail.com"))
				.andExpect(jsonPath("role").value("Manager"));
	}

	private byte[] readRequestFromFile(final String fileName) throws IOException {
		val file = resourceLoader.getResource(CLASSPATH_URL_PREFIX + "request/" + fileName).getInputStream();
		return FileCopyUtils.copyToByteArray(file);
	}

	private Profile getResponse(final MvcResult postResult) throws UnsupportedEncodingException, JsonProcessingException {
		return  mapper.readValue(postResult.getResponse().getContentAsString(), Profile.class);
	}

	private ResultActions postProfile() throws Exception {
		return mockMvc.perform(post(BASE_URL).content(readRequestFromFile("profile-create-request.json"))
				.contentType(MediaType.APPLICATION_JSON));
	}
}
