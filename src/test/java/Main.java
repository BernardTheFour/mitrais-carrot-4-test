import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Pages.Global;
import Test.TestLogin;


public class Main {
    private static Result result;

    public static void main(String[] args) {
        System.out.println("\n....TEST STARTED...");

        Global.Init();
        runTest();
        
        reportTestResult();

        System.out.println("\n....TEST COMPLETED...");
    }

    static void runTest() {
        JUnitCore jUnitCore = new JUnitCore();
        result = jUnitCore.run(
        // test classes
            TestLogin.class
        );
    }


    static void reportTestResult() {
        try {
            FileWriter fileWriter = new FileWriter("src/test/testresult\\result_" + LocalDate.now() + ".txt");

            if (result.wasSuccessful()) {
                fileWriter.write("TESTING PASSED");
                fileWriter.close();
                return;
            }

            fileWriter.write("TESTING FAILED: " + result.getFailureCount() + " test(s) \n\n");
            int index = 1;

            for (Failure failure : result.getFailures()) {
                String message = "-> FAIL no."+(index++)+ ": ";
                message += "\n" + failure.toString();
                message += "\n\n-> " + "TRACE: \n" + failure.getTrace();

                fileWriter.write(message);
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
