package OpRouting;

public class ParseINIResponsePartnumber {
    final private String status;
    final private String partNumber;
    final private String OPERATIONS;
    final private String MODE;
    final private String RAILBARCODE;
    final private String ARRAYSIZE;
    final private String ASSOCIATEDBARCODE;
    final private String ICT_PN;
    final private String BOTTOMSIDE_PN;
    final private String EA_PN;
    final private String VCT_PN;


    public ParseINIResponsePartnumber(String status, String partNumber, String OPERATIONS, String MODE, String RAILBARCODE, String ARRAYSIZE, String ASSOCIATEDBARCODE, String ICT_PN, String BOTTOMSIDE_PN, String EA_PN, String VCT_PN) {
        this.status = status;
        this.partNumber = partNumber;
        this.OPERATIONS = OPERATIONS;
        this.MODE = MODE;
        this.RAILBARCODE = RAILBARCODE;
        this.ARRAYSIZE = ARRAYSIZE;
        this.ASSOCIATEDBARCODE = ASSOCIATEDBARCODE;
        this.ICT_PN = ICT_PN;
        this.BOTTOMSIDE_PN = BOTTOMSIDE_PN;
        this.EA_PN = EA_PN;
        this.VCT_PN = VCT_PN;
    }

    public String getStatus() {
        return status;
    }
    public String getPartNumber() {
        return partNumber;
    }
    public String getOPERATIONS() {
        return OPERATIONS;
    }
    public String getMode() {
        return MODE;
    }
    public String getRailBarcode() {
        return RAILBARCODE;
    }
    public String getArraySize() {
        return ARRAYSIZE;
    }
    public String getAssociatedBarcode() {
        return ASSOCIATEDBARCODE;
    }
    public String getICT_PN() {
        return ICT_PN;
    }
    public String getBOTTOMSIDE_PN() {
        return BOTTOMSIDE_PN;
    }
    public String getEA_PN() {
        return EA_PN;
    }
    public String getVCT_PN() { return VCT_PN; }
}
