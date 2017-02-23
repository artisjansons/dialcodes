package com.neotech.dialcodes.unit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neotech.dialcodes.DialCodesApplication;
import com.neotech.dialcodes.data.DataContainer;
import com.neotech.dialcodes.data.dto.PhoneRequestDto;
import com.neotech.dialcodes.data.model.Country;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Artis on 2/22/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DialCodesApplication.class)
@WebAppConfiguration
public class PhoneNumberControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataContainer dataContainer;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper();

        // fill data container with sample data
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country("Latvia", "371"));
        dataContainer.setCountries(countryList);
    }

    @Test
    public void testGetCountry() throws Exception {

        PhoneRequestDto phoneRequestDto = new PhoneRequestDto("+3712345678");

        mockMvc.perform(post("/phone-number/lookup")
                .content(objectMapper.writeValueAsString(phoneRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Latvia")))
                .andExpect(jsonPath("$.dialCode", is("371")));
    }

    @Test
    public void testGetCountryByInvalidPhoneNumber() throws Exception {
        PhoneRequestDto phoneRequestDto = new PhoneRequestDto("2345678");

        mockMvc.perform(post("/phone-number/lookup")
                .content(objectMapper.writeValueAsString(phoneRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid phone number")));
    }

    @Test
    public void testGetCountryByNonExistingCountryCode() throws Exception {
        PhoneRequestDto phoneRequestDto = new PhoneRequestDto("+10023456789");

        MvcResult result = mockMvc.perform(post("/phone-number/lookup")
                .content(objectMapper.writeValueAsString(phoneRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        assertThat(result.getResponse().getErrorMessage(), is("Country not found"));
    }
}
