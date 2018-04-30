package OpRouting;
import java.io.*;
import org.springframework.stereotype.Component;


@Component
public class ParseINIRestService {

    public ParseINIRestService(){
        refreshINI();
    }

    public void refreshINI() {
        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonSvn.py" + " checkout");
            String value = "temp\\PartVerifierPartNumbers.ini";
            System.out.println("SVN Checkout: " + value);
        } catch (Exception e) {
            System.out.println("SVN Checkout: ERROR - " + e);
        }
    }

    public String getSingleValue(String group, String key){
        String value = "";
        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " read" + " " + group + " " + key.toUpperCase());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + group + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            System.out.println(e);
        }

        if(value == "")
        {
            //Maybe do something, maybe don't.
        }

        return value;
    }





    public ParseINIResponse GetValueRequest(String partNumber, String key){
        //refreshINI();
        String value = "NOT FOUND";
        String status = "OK";

        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " read" + " " + partNumber + " " + key.toUpperCase());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + partNumber + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            System.out.println(e);
        }

        if(value == "NOT FOUND")
        {
            status = "ERROR " + key + " value NOT FOUND for " + partNumber;
        }
        return new ParseINIResponse(partNumber, key, value, status);
    }

    public ParseINIResponseRaw GetValueRequestRaw(String group, String key){
        //refreshINI();
        String value = "NOT FOUND";
        String status = "OK";

        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " read" + " " + group + " " + key.toUpperCase());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + group + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            System.out.println(e);
        }

        if(value == "NOT FOUND")
        {
            status = "ERROR " + key + " value NOT FOUND for " + group;
        }
        return new ParseINIResponseRaw(group, key, value, status);
    }

    public ParseINIResponse PutValueRequest(String partNumber, String key, String value){
        //refreshINI();
        String status = "OK";
        String inValue = value;

        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " write" + " " + partNumber + " " + key.toUpperCase() + " " + "\"" + value + "\"" );
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + partNumber + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            status = "ERROR " + e;
            System.out.println(e);
        }

        if(value != inValue)
        {
            status = "ERROR Value mismatch. " + value + " is not equal to " + inValue;
        }

        return new ParseINIResponse(partNumber, key, value, status);
    }

    public ParseINIResponseRaw PutValueRequestRaw(String group, String key, String value){
        //refreshINI();
        String status = "OK";
        String inValue = value;

        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " write" + " " + group + " " + key.toUpperCase() + " " + "\"" + value + "\"" );
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + group + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            status = "ERROR " + e;
            System.out.println(e);
        }

        if(value != inValue)
        {
            status = "ERROR Value mismatch. " + value + " is not equal to " + inValue;
        }

        return new ParseINIResponseRaw(group, key, value, status);
    }

    public ParseINIResponse PostGroupRequest(String partNumber, String key, String value){
        //refreshINI();
        String status = "OK";

        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " add" + " " + partNumber + " " + key.toUpperCase() + " " + "\"" + value + "\"" );
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + partNumber + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            status = "ERROR " + e;
            System.out.println(e);
        }

        return new ParseINIResponse(partNumber, key, value, status);
    }

    public ParseINIResponseRaw PostGroupRequestRaw(String group, String key, String value){
        //refreshINI();
        String status = "OK";

        try {
            Process p = Runtime.getRuntime().exec("python" + " PythonConfigParser\\PythonConfigParser.py" + " add" + " " + group + " " + key.toUpperCase() + " " + "\"" + value + "\"" );
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            value = new String(in.readLine()).toString();
            System.out.println("Entry is : " + "[" + group + "]" + " " + key + "= " + value);
        } catch (Exception e) {
            status = "ERROR " + e;
            System.out.println(e);
        }

        return new ParseINIResponseRaw(group, key, value, status);
    }

    public ParseINIResponsePartnumber GetPartNumberRequest(String partNumber) {
        String status = "OK";
        String OPERATIONS = getSingleValue(partNumber, "OPERATIONS");
        String mode = getSingleValue(partNumber, "MODE");
        String railBarcode = getSingleValue(partNumber, "RAILBARCODE");
        String arraySize = getSingleValue(partNumber, "ARRAYSIZE");
        String associatedBarcode = getSingleValue(partNumber, "ASSOCIATEDBARCODE");
        String ictPN = getSingleValue(partNumber, "ICT_PN");
        String bottomSidePN = getSingleValue(partNumber, "BOTTOMSIDE_PN");
        String eaPN = getSingleValue(partNumber, "EA_PN");
        String vctPN = getSingleValue(partNumber, "VCT_PN");

        if(OPERATIONS == "")
        {
            status = "ERROR Operations are empty or " + partNumber + " cannot be found.";
        }

        return new ParseINIResponsePartnumber(status, partNumber, OPERATIONS, mode, railBarcode, arraySize, associatedBarcode, ictPN, bottomSidePN, eaPN, vctPN);
    }
}
