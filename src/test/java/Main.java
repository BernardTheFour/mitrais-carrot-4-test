
public class Main {
    // Result JUnit
    // private static Result result;

    // public static void main(String[] args) {
    //     System.out.println("\n....TEST STARTED...");

    //     Global.Init();
    //     runTest();
        
    //     reportTestResult();

    //     System.out.println("\n....TEST COMPLETED...");
    // }

    // static void runTest() {
        // JUnitCore JUnit
    //     JUnitCore jUnitCore = new JUnitCore();
    //     result = jUnitCore.run(
    //     // test classes

    //         TestLogin.class,
    //         TestCreateNewBazaarItem.class,
    //         TestManagerShareCarrot.class
    //     );
    // }


    // static void reportTestResult() {
    //     try {
    //         FileWriter fileWriter = new FileWriter("src/test/testresult\\result_" + LocalDate.now() + ".txt");

    //         if (result.wasSuccessful()) {
    //             fileWriter.write("TESTING PASSED");
    //             fileWriter.close();
    //             return;
    //         }

    //         fileWriter.write("TESTING FAILED: " + result.getFailureCount() + " test(s) \n\n");
    //         int index = 1;

    //         for (Failure failure : result.getFailures()) {
    //             String message = "-> FAIL no."+(index++)+ ": ";
    //             message += "\n" + failure.toString();
    //             message += "\n\n-> " + "TRACE: \n" + failure.getTrace();

    //             fileWriter.write(message);
    //         }

    //         fileWriter.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
