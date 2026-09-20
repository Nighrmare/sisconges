package com.sisconges.sisconges.config;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppSessionConfig
{
    private final String instanceId = UUID.randomUUID().toString();

    public String getInstanceId()
    {
        return instanceId;
    }
}