package com.phonebook.core;

import com.phonebook.utils.PropertiesLoader;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.swing.plaf.PanelUI;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;


public class TestBase {

    //public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZ29zaGFAZG14LmRlIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3NTc0MzQ5NTIsImlhdCI6MTc1NjgzNDk1Mn0.gBfFzZsUUMMfmBLBYQ_tf6Z_CGMMgsuMdlU8sYhmOQ4";
    public static final String AUTH = "Authorization";

    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void init(Method method, Object[] p) {
        RestAssured.baseURI = PropertiesLoader.loadProperties("uri");
        RestAssured.basePath = PropertiesLoader.loadProperties("path");

        logger.info("Start test: {} with data: {}", method.getName(), Arrays.asList());
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: {}", result.getMethod().getMethodName());
        } else {
            logger.info("FAILED: {}", result.getMethod().getMethodName());
        }
        logger.info("*************************************");
    }
}
