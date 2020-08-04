package com.testing.serenityRunner;

import cucumber.api.CucumberOptions;
import io.restassured.RestAssured;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {"com.testing.gherkinsDefinitions"}
)
public class TestRunner {
  private final static Integer PORT = 3000;
  private final static Integer TIMEOUT = 30;
  private final static String URL_LOCAL = "http://localhost:" + PORT;
  private final static String CHECK_IF_PORT_IS_USED_COMMAND = "lsof -P | grep ':" + PORT + "'| grep node | grep LISTEN";
  private final static String SERVER_KILLING_COMMAND = "killall -9 node";

  // Server related variables
  private final static String SERVER_NAME = "juice-shop";
  private final static String SERVER_STARTER = "npm start";
  private final static String SERVER_PROCESS_STARTER_COMMAND = "cd ../" + SERVER_NAME + ";" + SERVER_STARTER;

  private TestRunner() {
  }

  @BeforeClass
  public static void setUp() throws IOException {
    RestAssured.baseURI = URL_LOCAL;
    stopServer();
    startServer();
  }

  @AfterClass
  public static void cleanUp() throws IOException {
    stopServer();
  }

  private static void startServer() throws IOException {
    new ProcessBuilder().command("bash", "-c", SERVER_PROCESS_STARTER_COMMAND).start();
    long start = System.currentTimeMillis();

    while (!isServerRunning()){
      if(System.currentTimeMillis() - start > TIMEOUT * 1000){
        throw new Error("Server starting took longer than " + TIMEOUT);
      }
    }
  }

  private static void stopServer() throws IOException {
    new ProcessBuilder().command("bash", "-c", SERVER_KILLING_COMMAND).start();
  }

  private static boolean isServerRunning() throws IOException {
    Process checkIfPortIsUsed;
    checkIfPortIsUsed = new ProcessBuilder().command("bash", "-c", CHECK_IF_PORT_IS_USED_COMMAND).start();
    return new BufferedReader(new InputStreamReader(checkIfPortIsUsed.getInputStream())).readLine() != null;
  }
}
