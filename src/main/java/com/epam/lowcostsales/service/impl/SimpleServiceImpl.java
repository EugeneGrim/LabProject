package com.epam.lowcostsales.service.impl;

import com.epam.lowcostsales.service.SimpleService;

public class SimpleServiceImpl implements SimpleService {

    @Override
    public String getMessage() {

        return "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World! **********</h3>********** Test message **********</div><br><br>";
    }
}
