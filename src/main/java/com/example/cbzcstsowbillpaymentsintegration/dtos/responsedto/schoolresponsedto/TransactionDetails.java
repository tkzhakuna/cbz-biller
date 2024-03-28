package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
public class TransactionDetails {
    @JsonProperty("RELCUST")
    public String rELCUST;
    @JsonProperty("REMACCOUNT")
    public String rEMACCOUNT;
    @JsonProperty("REMBRANCH")
    public String rEMBRANCH;
    @JsonProperty("PRD")
    public String pRD;
    @JsonProperty("TXNACC")
    public long tXNACC;
    @JsonProperty("MOBILE_NUMBER")
    public int mOBILE_NUMBER;
    @JsonProperty("Charge-Details")
    public ArrayList<ChargeDetail> chargeDetails;
    @JsonProperty("ROUTECODE")
    public String rOUTECODE;
    @JsonProperty("FT-Details")
    public FTDetails fTDetails;
    @JsonProperty("OFFSETAMT")
    public int oFFSETAMT;
    @JsonProperty("RECSTAT")
    public String rECSTAT;
    @JsonProperty("CHECKERID")
    public String cHECKERID;
    @JsonProperty("SERIALNO")
    public String sERIALNO;
    @JsonProperty("CHECKERSTAMP")
    public String cHECKERSTAMP;
    @JsonProperty("THEIRACC1")
    public String tHEIRACC1;
    @JsonProperty("THEIRACC2")
    public String tHEIRACC2;
    @JsonProperty("BENFADDR2")
    public int bENFADDR2;
    @JsonProperty("BENFADDR3")
    public String bENFADDR3;
    @JsonProperty("THEIRCHGS4")
    public String tHEIRCHGS4;
    @JsonProperty("THEIRACC3")
    public String tHEIRACC3;
    @JsonProperty("TXNBRN")
    public String tXNBRN;
    @JsonProperty("OFFSETTRN")
    public String oFFSETTRN;
    @JsonProperty("DRINSTCD")
    public String dRINSTCD;
    @JsonProperty("THEIRCHGS3")
    public String tHEIRCHGS3;
    @JsonProperty("THEIRACC4")
    public String tHEIRACC4;
    @JsonProperty("BENFADDR1")
    public String bENFADDR1;
    @JsonProperty("THEIRCHGS2")
    public String tHEIRCHGS2;
    @JsonProperty("THEIRCHGS1")
    public String tHEIRCHGS1;
    @JsonProperty("BENFADDR4")
    public String bENFADDR4;
    @JsonProperty("OFFSETBRN")
    public String oFFSETBRN;
    @JsonProperty("XREF")
    public long xREF;
    @JsonProperty("MODULE")
    public String mODULE;
    @JsonProperty("DOCNUMBER")
    public String dOCNUMBER;
    @JsonProperty("EMAIL_ID")
    public String eMAIL_ID;
    @JsonProperty("TXNCCY")
    public String tXNCCY;
    @JsonProperty("OFFSETCCY")
    public String oFFSETCCY;
    @JsonProperty("ROUTINGNO")
    public String rOUTINGNO;
    @JsonProperty("NARRATIVE")
    public String nARRATIVE;
    @JsonProperty("Message-Details")
    public MessageDetails messageDetails;
    @JsonProperty("THEIRCHGS")
    public String tHEIRCHGS;
    @JsonProperty("MODNO")
    public int mODNO;
    @JsonProperty("EVNTCD")
    public String eVNTCD;
    @JsonProperty("TXNAMT")
    public int tXNAMT;
    @JsonProperty("FCCREF")
    public String fCCREF;
    @JsonProperty("MAKERID")
    public String mAKERID;
    @JsonProperty("OFFSETACC")
    public long oFFSETACC;
    @JsonProperty("TXNTRN")
    public String tXNTRN;
    @JsonProperty("TXNDATE")
    public String tXNDATE;
    @JsonProperty("MAKERSTAMP")
    public String mAKERSTAMP;
    @JsonProperty("ESN")
    public int eSN;
    @JsonProperty("REPREASON")
    public String rEPREASON;
    @JsonProperty("CRINSTCD")
    public String cRINSTCD;
    @JsonProperty("THEIRACC")
    public String tHEIRACC;
    @JsonProperty("REMBANK")
    public String rEMBANK;
    @JsonProperty("SCODE")
    public String sCODE;
    @JsonProperty("BENFNAME")
    public String bENFNAME;
    @JsonProperty("ENDPOINT")
    public String eNDPOINT;
    @JsonProperty("BRN")
    public String bRN;
}

