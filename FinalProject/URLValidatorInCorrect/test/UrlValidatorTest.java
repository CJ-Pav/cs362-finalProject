
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase {
    private final boolean printStatus = false;
    private final boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

    String[] validScheme = new String[] {"http://", "ftp://", "https://", ""};
    String[] invalidScheme = new String[] {"3ht://", "http:/", "http:", "http/", "://"};

    String[] validAuthority = new String[] {"www.google.com", "go.com", "go.au", "0.0.0.0", "255.255.255.255", "255.com"};
    String[] invalidAuthority = new String[] {
        "256.256.256.256", "1.2.3.4.5", "1.2.3.4.", "1.2.3", ".1.2.3.4",
        "go.a", "go.a1a", "go.1aa","aaa.", ".aaa", "aaa", "-1.0.0.0", "1.2.3.999"
    };

    String[] validPort = new String[] {":80", ":65535",":0", ""};
    String[] inValidPort = new String[] {":-1", ":65636", ":65a"};

    String[] validPath = new String[] {"/test1", "/t123", "/$23", "/test1/", "", "/test1/file"};
    String[] invalidPath = new String[] {"/..", "/../", "/..//file", "/test1//file"};

    String[] validPathOption = new String[] {"/test1", "/t123", "/test1/", "", "/test1/file", "/t123/file", "/./"};
    String[] invalidPathOption = new String[] {"/..", "/../", "/#", "/../file", "/..//file", "/#/file"};

    String[] validUrlQuery = new String[] {"?action=view", "?action=edit&mode=up", ""};

    String[] validUrlList = new string[] {"https://www.google.com/", "ftp://root@1.1.1.1", "http://go.com"};
    String[] invalidUrlList = new string[] {"https://www.g00@le.com", "www.google.com/test1//file", "10,93.1.72"};

    Object[] testValidUrlParts = {validScheme, validAuthority, validPort, validPath, validUrlQuery};
    Object[] testInvalidUrlParts = {invalidScheme, invalidAuthority, inValidPort, invalidPath, invalidPath, validUrlQuery};

    Object[] testValidUrlPartsOptions = {validScheme, validAuthority, validPort, validPathOption, validUrlQuery};
    Object[] testInvalidUrlPartsOptions = {invalidScheme, invalidAuthority, inValidPort, invalidPath,
                                           invalidPathOption, validUrlQuery};

    int[] testPartsIndex = {0, 0, 0, 0, 0};

    // check if char exists in string
    public boolean isCharInSring(char test, String sample) {
        boolean charIsInString = 0;
        for(int i = 0; i < sample.length(); ++i) {
            if(sample[i] == test) {
                return 1;
            }
        }
        return 0;
    }


    @Override
  	protected void setUp() {
        for (int index = 0; index < testPartsIndex.length - 1; index++) {
            testPartsIndex[index] = 0;
        }
  	}

    public UrlValidatorTest(String testName) {
        super(testName);
    }

    /**
    * Only used to debug the unit tests.
    * @param argv
    */
    public static void main(String[] argv) {
        UrlValidatorTest t1 = new UrlValidatorTest("url tests");
        t1.test1();
    }

    public UrlValidatorTest(String testName) {
        super(testName);
    }

    public void testManualTest() {
        // //You can use this function to implement your manual testing
        // UrlValidator urlVal = new UrlValidator(null, null, allowAllSchemes);
        //
        // // valid
        // assertTrue(urlVal.isValid("http://www.google.com"));
        // assertTrue(urlVal.isValid("http://www.google.com/"));
        // assertTrue(urlVal.isValid("http://www.facebook.com"));
        // assertTrue(urlVal.isValid("http://www.facebook.com/"));
        //
        // // invalid
        // assertTrue(urlVal.isValid(" http:///www.google.com")); // space (mid)
        // assertTrue(urlVal.isValid("http:///www. google.com")); // space (mid)
        // assertTrue(urlVal.isValid("http:///www.google.com ")); // space (mid)
        // assertTrue(urlVal.isValid("#$^&*%http:///www.googlecom")); // symbols (beg)
        // assertTrue(urlVal.isValid("http:///www.google^&*%$#com")); // symbols (mid)
        // assertTrue(urlVal.isValid("http:///www.googlecom^&$#%*/")); // symbols (end)
        // assertTrue(urlVal.isValid("")); //null
        //
        //
        // UrlValidatorTest("http://www.apache.org/licenses/LICENSE-2.0");


        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        //assertTrue(urlVal.isValid("http://www.google.com"));


        boolean[] resultFalse = new boolean[16];
        resultFalse[0] = urlVal.isValid("3ht://256.256.256.256:-1");
        resultFalse[1] = urlVal.isValid("http:/1.2.3.4.5:-1");
        resultFalse[2] = urlVal.isValid("3ht://1.2.3.4.:65636");
        resultFalse[3] = urlVal.isValid("http:/1.2.3:65636");
        resultFalse[4] = urlVal.isValid("http:.1.2.3.4");
        resultFalse[5] = urlVal.isValid("http/go.a1a/..");
        resultFalse[6] = urlVal.isValid("http:go.1aa/..");
        resultFalse[7] = urlVal.isValid("http/aaa.");
        resultFalse[8] = urlVal.isValid("://-1.0.0.0/../file");
        resultFalse[9] = urlVal.isValid("3ht://www.google.com:80/test1");
        resultFalse[10] = urlVal.isValid("http://1.2.3.4.5:65535/test1/");
        resultFalse[11] = urlVal.isValid("http/go.a1a/..");
        resultFalse[12] = urlVal.isValid("http:go.1aa/..");
        resultFalse[13] = urlVal.isValid("http/aaa.");
        resultFalse[14] = urlVal.isValid("3ht://1.2.3.4.:65636");
        resultFalse[15] = urlVal.isValid("http:/1.2.3:65636");

        int falseCount = 0;
        for (int i = 0; i < 7; i++) {
            //assertFalse(resultTrue[i]);
            //if (resultTrue[i] == false) {falseCount++;}
        }
        for (int j = 0; j < 16; j++) {
            //assertFalse(resultFalse[j]);
            if (resultFalse[j] == true) {
                falseCount++;
                System.out.print(j + "\n");
            }
        }
        System.out.println("Num of failed cases: " + falseCount);

    }


    // PARTITION TESTING //
    public void testFirstPartition(String part1) {
        // compare w/ schema (eg. "http://, ftp://, https://")
        int partSize = 8;

        switch(part1) {
            case 1: part1 = "http://";
                partSize = 0;
                break;
            case 2: part1 = "ftp://";
                partSize = 0;
                break;
            case 3: part1 = "https://";
                partSize = 0;
                break;
            case 4: part1 = "";
                partSize = 0;
                break;
            default: part1 = "Invalid first partition: " + part1;
                System.out.println(part1)
                partSize = 1;
                break;
        }
        return partSize;
    }

    public void testSecondPartition(String part2) {
        // this is for the domain (eg. "www.imFunny.com")
        int partSize = part2.length();
        for(int i = 0)
    }
    //You need to create more test cases for your Partitions if you need to

    public void partTester() {
        // parse URL
        int count;
        String[] part1 = new String[8];
        char current = 'a';

        // test good urls
        for(int x = 0; x < 3; ++x) {
            count = 0;
            // find schema
            for(int y = 4; y < 6; ++y) {
                if(validUrlList[x][y] == ':') {
                    if(validUrlList[x][y + 1] == '/' && validUrlList[x][y + 2] == '/') {
                        // store schema
                        for(int i = 0; i < y + 2; ++i) {
                            part1 += validUrlList[x].[i];
                        }
                    }
                }
            }
            //
            do {
                current = validUrlList[x][count]
            } while(current)
        }

        // test bad urls
        for(int x = 0; x < 3; ++x) {

        }
    }

    // TEST IS VALID //
    public void testIsValid() {
        //You can use this function for programming based testing

        // loop through multiple symbol options, nest loops/symbols

    }

    // MAIN //
    public static void main(String[] args) {
        // init variables
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        // run man tests
        testManualTest();

        // run partition tests
        partTester(urlVal);
    }

}
