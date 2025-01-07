public enum TesterMessagesEnum {
    INPUT("Input/Scenario: "),
    EXPECTED("Expected: "),
    ACTUAL("Actual: "),
    PASSED("Passed"),
    FAILED("Failed"),
    ERROR("Error: "),
    ERROR_TA_ATTENTION("Error in tester, contact TA: "),
    CASE_NUMBER("Case number: "), 
    RESULT("Result: "); 

    private final String message;
    TesterMessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        
        return this.message;
    }
}
