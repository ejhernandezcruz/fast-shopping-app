package com.applaudo.shop.listener;

import com.applaudo.shop.core.Config;
import lombok.extern.slf4j.Slf4j;
import org.testng.IExecutionListener;

import java.io.IOException;

@Slf4j
public class ExecutionListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        try {
            Config.loadConfig();
        } catch (IOException e) {
            log.error("Capabilities not found", e);
            throw new RuntimeException("Config file not found");
        }
    }
}
