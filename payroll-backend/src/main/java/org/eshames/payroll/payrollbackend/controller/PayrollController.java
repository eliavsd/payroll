package org.eshames.payroll.payrollbackend.controller;

import org.eshames.payroll.payrollbackend.model.dto.impl.InputDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    PayrollService payrollService;

    @PostMapping("/grossToNet")
    public ResultDTO grossToNet(@RequestBody InputDTO inputDTO) {
        return payrollService.grossToNet(inputDTO);
    }
}
