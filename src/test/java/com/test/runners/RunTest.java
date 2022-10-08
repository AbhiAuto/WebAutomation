package com.test.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features", plugin = "json:target/html", glue = {
		"com.test.stepdef" }, monochrome = true, publish = true, tags = "@test")

public class RunTest {

}
