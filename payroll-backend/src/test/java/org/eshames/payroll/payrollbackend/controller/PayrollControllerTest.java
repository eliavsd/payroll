package org.eshames.payroll.payrollbackend.controller;

import org.eshames.payroll.payrollbackend.app.PayrollBackendApplication;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.service.PayrollService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PayrollController.class)
@ContextConfiguration(classes = PayrollBackendApplication.class)
class PayrollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayrollService payrollService;

    @Test
    void grossToNet_returnsResult() throws Exception {
        Map<String, Double> taxResults = new HashMap<>();
        taxResults.put("income", 1500.0);
        Map<String, Double> savingsResults = new HashMap<>();
        savingsResults.put("pension", 625.0);
        ResultDTO resultDTO = new ResultDTO(10000, 10500, 10500, taxResults, savingsResults);
        when(payrollService.grossToNet(any())).thenReturn(resultDTO);

        String inputJson = "{\"incomeDTO\":{\"baseSalary\":10000,\"globalOvertime\":0,"
                + "\"payBasis\":\"MONTHLY\",\"travelCopay\":500,\"otherCopay\":0},"
                + "\"taxes\":[{\"taxName\":\"income\",\"taxPoints\":2.25}],"
                + "\"savings\":[{\"savingsName\":\"pension\",\"savingsFactor\":0.0625}]}";

        mockMvc.perform(post("/payroll/grossToNet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalGrossIncome").value(10500.0))
                .andExpect(jsonPath("$.taxResults.income").value(1500.0))
                .andExpect(jsonPath("$.savingsResults.pension").value(625.0));
    }

    @Test
    void grossToNet_serviceThrows_propagatesException() throws Exception {
        when(payrollService.grossToNet(any())).thenThrow(new RuntimeException("error"));

        String inputJson = "{\"incomeDTO\":{\"baseSalary\":10000,\"globalOvertime\":0,"
                + "\"payBasis\":\"MONTHLY\",\"travelCopay\":0,\"otherCopay\":0},"
                + "\"taxes\":[],\"savings\":[]}";

        assertThrows(Exception.class, () ->
                mockMvc.perform(post("/payroll/grossToNet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)));
    }

    @Test
    void grossToNet_invalidBody_returns400() throws Exception {
        mockMvc.perform(post("/payroll/grossToNet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid"))
                .andExpect(status().is4xxClientError());
    }
}
