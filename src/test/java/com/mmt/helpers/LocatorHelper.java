package com.mmt.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.mmt.annotations.Locators;
import com.mmt.locators.HomePageLocator;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class LocatorHelper {

    public static void main(String[] args) {
        String homePageLocatorFile = "locators/homepage.yml";
        HomePageLocator homePageLocator = getLocators(homePageLocatorFile);
        Method[] declaredMethods = HomePageLocator.class.getDeclaredMethods();
        Arrays.asList(declaredMethods).forEach(method -> {
            try {
                if (method.getParameterCount() == 0) {
                    System.out.println(method.invoke(homePageLocator));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }


    @SuppressWarnings("unchecked")
    static <T> T getLocators(String locatorFile) {
        InputStream resourceAsStream = LocatorHelper.class.getClassLoader().getResourceAsStream(locatorFile);
        if (resourceAsStream == null) {
            throw new RuntimeException("Invalid locator file path");
        }
        String locatorFileName = locatorFile.split(File.separator)[1].split("\\.")[0];
        Reflections reflections = new Reflections("com.mmt");
        Set<Class<?>> setOfLocatorsClass = reflections.getTypesAnnotatedWith(Locators.class);
        Class<?> locatorClass = setOfLocatorsClass
                .stream()
                .filter(aClass -> aClass.getName().toLowerCase().contains(locatorFileName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Locator file is not present"));
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            return (T) objectMapper.readValue(resourceAsStream, locatorClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}