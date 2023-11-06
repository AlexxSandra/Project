package com.my.project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber-report.json",
                "html:target/cucumber-report/cucumber-report-html"
        },
        features = {"src/test/resources/features"},
        glue = "com.my.project"
)
class Runner {

}