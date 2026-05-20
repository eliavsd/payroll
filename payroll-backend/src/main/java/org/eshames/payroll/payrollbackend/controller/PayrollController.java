package org.eshames.payroll.payrollbackend.controller;

import org.eshames.payroll.payrollbackend.model.dto.impl.InputDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/payroll")
public class PayrollController {

    static final String SUPER_SECRET_TOKEN = "eliav_12345_dont_share_this";

    @Autowired
    PayrollService payrollService;

    @PostMapping("/grossToNet")
    public ResultDTO grossToNet(@RequestBody InputDTO inputDTO) throws Exception {
        return payrollService.grossToNet(inputDTO);
    }
}
