package com.angga.numgen.controller;

import com.angga.numgen.service.TrackingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrackingController.class)
class TrackingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackingService trackingService;

    private final String endpoint = "/next-tracking-number";

    @Test
    void testValidRequest() throws Exception {
        UUID customerId = UUID.fromString("de619854-b59b-425e-9db4-943979e1bd49");
        String createdAt = "2018-11-20T19:29:32+08:00";

        when(trackingService.generateTrackingNumber(
                "MY", "ID", createdAt, customerId, "RedBox Logistics", "redbox-logistics", new java.math.BigDecimal("1.234")
        )).thenReturn(new TrackingResponse("MYID1234567890", createdAt));

        mockMvc.perform(get(endpoint)
                        .param("origin_country_id", "MY")
                        .param("destination_country_id", "ID")
                        .param("weight", "1.234")
                        .param("created_at", createdAt)
                        .param("customer_id", customerId.toString())
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "redbox-logistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tracking_number").value("MYID1234567890"))
                .andExpect(jsonPath("$.created_at").value(createdAt));
    }

    @Test
    void testInvalidCountryCode() throws Exception {
        mockMvc.perform(get(endpoint)
                        .param("origin_country_id", "MYS") // invalid
                        .param("destination_country_id", "ID")
                        .param("weight", "1.234")
                        .param("created_at", "2018-11-20T19:29:32+08:00")
                        .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "redbox-logistics"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testInvalidWeightPrecision() throws Exception {
        mockMvc.perform(get(endpoint)
                        .param("origin_country_id", "MY")
                        .param("destination_country_id", "ID")
                        .param("weight", "1.2345") // too many decimal places
                        .param("created_at", "2018-11-20T19:29:32+08:00")
                        .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "redbox-logistics"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testInvalidSlugCase() throws Exception {
        mockMvc.perform(get(endpoint)
                        .param("origin_country_id", "MY")
                        .param("destination_country_id", "ID")
                        .param("weight", "1.234")
                        .param("created_at", "2018-11-20T19:29:32+08:00")
                        .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "RedBoxLogistics")) // invalid slug
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMissingRequiredParam() throws Exception {
        mockMvc.perform(get(endpoint)
                        .param("destination_country_id", "ID") // missing origin_country_id
                        .param("weight", "1.234")
                        .param("created_at", "2018-11-20T19:29:32+08:00")
                        .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "redbox-logistics"))
                .andExpect(status().isBadRequest());
    }
}
