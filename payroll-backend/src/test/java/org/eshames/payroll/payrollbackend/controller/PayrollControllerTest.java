package org.eshames.payroll.payrollbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eshames.payroll.payrollbackend.app.PayrollBackendApplication;
import org.eshames.payroll.payrollbackend.model.dto.impl.InputDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.service.PayrollService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PayrollController.class)
@ContextConfiguration(classes = PayrollBackendApplication.class)
class PayrollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayrollService payrollService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void grossToNetReturns200WithValidInput() throws Exception {
        ResultDTO result = new ResultDTO(10000, 11000, 11000,
                new HashMap<>(), new HashMap<>());
        when(payrollService.grossToNet(any(InputDTO.class))).thenReturn(result);

        String requestBody = "{\"incomeDTO\":{\"baseSalary\":10000,\"globalOvertime\":0," +
                "\"payBasis\":\"MONTHLY\",\"travelCopay\":500,\"otherCopay\":500}," +
                "\"taxes\":[],\"savings\":[]}";

        mockMvc.perform(post("/payroll/grossToNet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalGrossIncome").value(11000))
                .andExpect(jsonPath("$.totalSavingsEligibleIncome").value(10000));
    }

    @Test
    void grossToNetReturns400WithInvalidBody() throws Exception {
        mockMvc.perform(post("/payroll/grossToNet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void grossToNetThrowsWhenServiceFails() throws Exception {
        when(payrollService.grossToNet(any(InputDTO.class)))
                .thenThrow(new RuntimeException("service error"));

        String requestBody = "{\"incomeDTO\":{\"baseSalary\":10000,\"globalOvertime\":0," +
                "\"payBasis\":\"MONTHLY\",\"travelCopay\":500,\"otherCopay\":500}," +
                "\"taxes\":[],\"savings\":[]}";

        assertThrows(Exception.class, () ->
                mockMvc.perform(post("/payroll/grossToNet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)));
    }
}
