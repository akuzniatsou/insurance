package com.dww.insurance.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static ServiceLocator instance;

    private Map<Class<?>, Object> services = new HashMap<>();

    public void registerService(Class<?> serviceClass, Object service) {
        services.put(serviceClass, service);
    }

    public static void setLocator(ServiceLocator locator) {
        instance = locator;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(Class<T> serviceClass) {
        return (T) instance.services.get(serviceClass);
    }

}
