package Data;

import android.util.Log;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.lang.reflect.Field;

 * Created by chris on 06/03/17.
 *
 * This class contains all the information of a course, to find details of the variables please look at
 * Details.pdf stored in the MyStats-University folder
 */

public class Course  implements Parcelable{


    public String KISCOURSEID,PUBUKPRN,UKPRN,ASSURL,ASSURLW,CRSEURL,CRSEURLW,DISTANCE,
    EMPLOYURL,EMPLOYURLW,ENGFEE,FEETBC,FOUNDATION,HONOURS,JACS,KISMODE,KISTYPE,LDCS,LEVEL,
    LOCCHNGE,LTURL,LTURLW,MEANSSUP,NHS,NIFEE,NONCREDITASSESS,NUMSTAGE,OTHSUP,RELATEDKIS,
    SANDWICH,SCOTFEE,SUPPORTURL,SUPPORTURLW,TITLE,TITLEW,UCASPROGID,UKPRNAPPLY,
    VARFEE,WAFEE,WAIVER,WELSH,YEARABROAD,KISAIMCODE,AVGWRITTEN,AVGCOURSEWORK,AVGSCHEDULED,
    ASSACT,COURSEWORK,INDEPENDENT,LTACT,PLACEMENT,PRACTICAL,SCHEDULED,STAGE,WRITTEN,ACCTYPE,
    ACCDEPEND,ACCDEPENDURL,ACCDEPENDURLW,CONTPOP,CONTAGG,CONTSBJ,UCONT,UDORMANT,UGAINED,
    ULEFT,ULOWER,LOCID,DEGPOP,DEGAGG,DEGSBJ,UFIRST,UUPPER,UOTHER,UORDINARY,UDISTINCTION,
    UMERIT,UPASS,UNA,EMPPOP,EMPRESP_RATE,EMPAGG,EMPSBJ,WORKSTUDY,STUDY,ASSUNEMP,BOTH,NOAVAIL,
    WORK,JOBPOP,JOBRESPAF8RATE,JOBAGG,JOBSBJ,PROFMAN,OTHERJOB,UNKWN,NSSPOP,NSSRESPAF8RATE,
    NSSAGG,NSSSBJ,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q22,
    NHSPOP,NHSRESPAF8RATE,NHSAGG,NHSSBJ,NHSQ1,NHSQ2,NHSQ3,NHSQ4,NHSQ5,NHSQ6,SALPOP,SALRESPAF8RATE,
    SALAGG,SALSBJ,LDLQ,LDMED,LDUQ,LQ,MED,UQ,INSTLQ,INSTMED,INSTUQ,SBJ,TARPOP,TARAGG,TARSBJ,T001,T048,
            T064,T080,T096,T112,T128,T144,T160,T176,T192,T208,T224,T240,UCASCOURSEID,COMPOP,
            COMRESP_RATE,COMAGG,COMSBJ,ENTPOP,ENTAGG,ENTSBJ,ACCESS,ALEVEL,BACC,DEGREE,FOUNDTN,NOQUALS,
            OTHER,OTHERHE,COUNTRY,PUBUKPRNCOUNTRY,TEFMARKER,SUURL,SUURLW,Q24,Q24POP,Q24RESP_RATE,
            ACCOMURL,ACCOMURLW,INSTBEDS,INSTLOWER,INSTUPPER,LOCNAME,LOCNAMEW,LATITUDE,LONGITUDE,
            LOCUKPRN,LOCCOUNTRY,PRIVATELOWER,PRIVATEUPPER,NAME;


    public Course(){}

    /**
     * Accessor method for Percentage assessed by coursework variable
     * @return COURSEWORK - Percentage assessed by coursework
     */
    public String[] getPercentageAssesedByCourseWork(){
        String[] values = {COURSEWORK};
        return  values;
    }

    /**
     * Accessor method for Percentage of time spent in lectures variable
     * @return SCHEDULED - Percentage of time spent in lectures
     */
    public String[] getPercentageInScheduled(){
        String[] values = {SCHEDULED};
        return  values;
    }

    /**
     * Accessor method for Average salary after 6 months variables variable
     * @return INSTLQ,INSTMED,INSTUQ - Average salary after 6 months variables
     */
    public String[] getAvgSalarySixMonths(){
        String[] values = {INSTLQ,INSTMED,INSTUQ};
        return  values;
    }

    /**
     * Accessor method for Average salary after 40 months variables variable
     * @return LDLQ,LDMED,LDUQ - Average salary after 40 months variables
     */
    public String[] getAvgSalaryFoutrtyMonths(){
        String[] values = {LDLQ,LDMED,LDUQ};
        return  values;
    }

    /**
     * Accessor method for percentage of those that go onto work and studys variables variable
     * @return STUDY,WORK,ASSUNEMP,BOTH,NOAVAIL - Percentage of those that go onto work and study
     */
    public String[] getPercentageWorkAndStudy(){
        String[] values = {STUDY,WORK,ASSUNEMP,BOTH,NOAVAIL};
        return values;
    }

    /**
     * Accessor method for Percentage of those in employment after 6 months variables variable
     * @return PROFMAN,OTHERJOB,UNKWN - Percentage of those in employment after 6 months
     */
    public String[] getEmploymentSixMonths(){
        String[] values = {PROFMAN,OTHERJOB,UNKWN};
        return values;
    }

    /**
     * Accessor method for teaching on my course stats variables variable
     * @return Q1,Q2,Q3,Q4 - Teaching on my course stats
     */
    public String[] getTeachingOnMyCourseStats(){
        String[] values = {Q1,Q2,Q3,Q4};
        return values;
    }

    /**
     * Accessor method for Assessment and feedback stats variables variable
     * @return Q5,Q6,Q7,Q8,Q9 - Assessment and feedback stats
     */
    public String[] getAssesmentAndFeedbackStats(){
        String[] values = {Q5,Q6,Q7,Q8,Q9};
        return values;
    }

    /**
     * Accessor method for Academic support stats variables variable
     * @return Q10,Q11,Q12 - Academic support stats
     */
    public String[] getAccademicSupportStats(){
        String[] values = {Q10,Q11,Q12};
        return values;
    }

    /**
     * Accessor method for Organisation and management stats variables variable
     * @return Q13,Q14,Q15 - Organisation and management stats
     */
    public String[] getOrganisationAndManagementStats(){
        String[] values = {Q13,Q14,Q15};
        return values;
    }

    /**
     * Accessor method for Learning resource stats variables variable
     * @return Q16,Q17,Q18 - Learning resource stats
     */
    public String[] getLearningResourcesStats(){
        String[] values = {Q16,Q17,Q18};
        return values;
    }

    /**
     * Accessor method for Personal development stats variables variable
     * @return Q19,Q20,Q21 - Personal development stats
     */
    public String[] getPersonalDevelopmentStats(){
        String[] values = {Q19,Q20,Q21};
        return values;
    }

    /**
     * Accessor method for Student union stats variables variable
     * @return Q22- Student union stats
     */
    public String[] getStudentUnionStats(){
        String[] values = {Q22};
        return values;
    }







}

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * In order to pass objects between activities we need to implement the methods in parcelable, this
     * method writes all the variables to the parcel
     * @param dest - The parcel being written to
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{KISCOURSEID,PUBUKPRN,UKPRN,ASSURL,ASSURLW,CRSEURL,CRSEURLW,DISTANCE,
                EMPLOYURL,EMPLOYURLW,ENGFEE,FEETBC,FOUNDATION,HONOURS,JACS,KISMODE,KISTYPE,LDCS,LEVEL,
                LOCCHNGE,LTURL,LTURLW,MEANSSUP,NHS,NIFEE,NONCREDITASSESS,NUMSTAGE,OTHSUP,RELATEDKIS,
                SANDWICH,SCOTFEE,SUPPORTURL,SUPPORTURLW,TITLE,TITLEW,UCASPROGID,UKPRNAPPLY,
                VARFEE,WAFEE,WAIVER,WELSH,YEARABROAD,KISAIMCODE,AVGWRITTEN,AVGCOURSEWORK,AVGSCHEDULED,
                ASSACT,COURSEWORK,INDEPENDENT,LTACT,PLACEMENT,PRACTICAL,SCHEDULED,STAGE,WRITTEN,ACCTYPE,
                ACCDEPEND,ACCDEPENDURL,ACCDEPENDURLW,CONTPOP,CONTAGG,CONTSBJ,UCONT,UDORMANT,UGAINED,
                ULEFT,ULOWER,LOCID,DEGPOP,DEGAGG,DEGSBJ,UFIRST,UUPPER,UOTHER,UORDINARY,UDISTINCTION,
                UMERIT,UPASS,UNA,EMPPOP,EMPRESP_RATE,EMPAGG,EMPSBJ,WORKSTUDY,STUDY,ASSUNEMP,BOTH,NOAVAIL,
                WORK,JOBPOP,JOBRESPAF8RATE,JOBAGG,JOBSBJ,PROFMAN,OTHERJOB,UNKWN,NSSPOP,NSSRESPAF8RATE,
                NSSAGG,NSSSBJ,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q22,
                NHSPOP,NHSRESPAF8RATE,NHSAGG,NHSSBJ,NHSQ1,NHSQ2,NHSQ3,NHSQ4,NHSQ5,NHSQ6,SALPOP,SALRESPAF8RATE,
                SALAGG,SALSBJ,LDLQ,LDMED,LDUQ,LQ,MED,UQ,INSTLQ,INSTMED,INSTUQ,SBJ,TARPOP,TARAGG,TARSBJ,T001,T048,
                T064,T080,T096,T112,T128,T144,T160,T176,T192,T208,T224,T240,UCASCOURSEID,COMPOP,
                COMRESP_RATE,COMAGG,COMSBJ,ENTPOP,ENTAGG,ENTSBJ,ACCESS,ALEVEL,BACC,DEGREE,FOUNDTN,NOQUALS,
                OTHER,OTHERHE,COUNTRY,PUBUKPRNCOUNTRY,TEFMARKER,SUURL,SUURLW,Q24,Q24POP,Q24RESP_RATE,
                ACCOMURL,ACCOMURLW,INSTBEDS,INSTLOWER,INSTUPPER,LOCNAME,LOCNAMEW,LATITUDE,LONGITUDE,
                LOCUKPRN,LOCCOUNTRY,PRIVATELOWER,PRIVATEUPPER,NAME


        });

    }

    /**
     * This method is called when we want to convert back from a parcel in the object
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Course createFromParcel(Parcel in){
            return new Course(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };

    /**
     * This constructor is called when unpacking a parcel back into the new object
     * @param in
     */
    public Course(Parcel in){
                String[] test = in.createStringArray();
        KISCOURSEID = test[0];
        PUBUKPRN = test[1];
        UKPRN = test[2];
        ASSURL = test[3];
        ASSURLW = test[4];
        CRSEURL = test[5];
        CRSEURLW = test[6];
        DISTANCE = test[7];
        EMPLOYURL = test[8];
        EMPLOYURLW = test[9];
        ENGFEE = test[10];
        FEETBC  = test[11];
        FOUNDATION = test[12];
        HONOURS = test[13];
        JACS = test[14];
        KISMODE = test[15];
        KISTYPE = test[16];
        LDCS = test[17];
        LEVEL = test[18];
        LOCCHNGE = test[19];
        LTURL = test[20];
        LTURLW = test[21];
        MEANSSUP = test[22];
        NHS = test[23];
        NIFEE = test[24];
        NONCREDITASSESS = test[25];
        NUMSTAGE = test[26];
        OTHSUP = test[27];
        RELATEDKIS = test[28];
        SANDWICH = test[29];
        SCOTFEE = test[30];
        SUPPORTURL = test[31];
        SUPPORTURLW = test[32];
        TITLE = test[33];
        TITLEW = test[34];
        UCASPROGID = test[35];
        UKPRNAPPLY = test[36];
        VARFEE = test[37];
        WAFEE = test[38];
        WAIVER = test[39];
        WELSH = test[40];
        YEARABROAD = test[41];
        KISAIMCODE = test[42];
        AVGWRITTEN = test[43];
        AVGCOURSEWORK = test[44];
        AVGSCHEDULED = test[45];
        ASSACT = test[46];
        COURSEWORK = test[47];
        INDEPENDENT = test[48];
        LTACT = test[49];
        PLACEMENT = test[50];
        PRACTICAL = test[51];
        SCHEDULED = test[52];
        STAGE = test[53];
        WRITTEN = test[54];
        ACCTYPE = test[55];
        ACCDEPEND = test[56];
        ACCDEPENDURL = test[57];
        ACCDEPENDURLW = test[58];
        CONTPOP  = test[59];
        CONTAGG = test[60];
        CONTSBJ = test[61];
        UCONT = test[62];
        UDORMANT = test[63];
        UGAINED = test[64];
        ULEFT = test[65];
        ULOWER = test[66];
        LOCID = test[67];
        DEGPOP = test[68];
        DEGAGG = test[69];
        DEGSBJ = test[70];
        UFIRST = test[71];
        UUPPER = test[72];
        UOTHER = test[73];
        UORDINARY = test[74];
        UDISTINCTION = test[75];
        UMERIT = test[76];
        UPASS = test[77];
        UNA = test[78];
        EMPPOP = test[79];
        EMPRESP_RATE = test[80];
        EMPAGG = test[81];
        EMPSBJ = test[82];
        WORKSTUDY = test[83];
        STUDY = test[84];
        ASSUNEMP = test[85];
        BOTH = test[86];
        NOAVAIL = test[87];
        WORK = test[88];
        JOBPOP = test[89];
        JOBRESPAF8RATE = test[90];
        JOBAGG = test[91];
        JOBSBJ = test[92];
        PROFMAN = test[93];
        OTHERJOB = test[94];
        UNKWN = test[95];
        NSSPOP = test[96];
        NSSRESPAF8RATE = test[97];
        NSSAGG = test[98];
        NSSSBJ = test[99];
        Q1 = test[100];
        Q2 = test[101];
        Q3 = test[102];
        Q4 = test[103];
        Q5 = test[104];
        Q6 = test[105];
        Q7 = test[106];
        Q8 = test[107];
        Q9 = test[108];
        Q10 = test[109];
        Q11 = test[110];
        Q12 = test[111];
        Q13 = test[112];
        Q14 = test[113];
        Q15 = test[114];
        Q16 = test[115];
        Q17 = test[116];
        Q18 = test[117];
        Q19 = test[118];
        Q20 = test[119];
        Q21 = test[120];
        Q22 = test[121];
        NHSPOP = test[122];
        NHSRESPAF8RATE = test[123];
        NHSAGG = test[124];
        NHSSBJ = test[125];
        NHSQ1 = test[126];
        NHSQ2 = test[127];
        NHSQ3 = test[128];
        NHSQ4 = test[129];
        NHSQ5 = test[130];
        NHSQ6 = test[131];
        SALPOP = test[132];
        SALRESPAF8RATE = test[133];
        SALAGG = test[134];
        SALSBJ = test[135];
        LDLQ = test[136];
        LDMED = test[137];
        LDUQ = test[138];
        LQ = test[139];
        MED = test[140];
        UQ = test[141];
        INSTLQ = test[142];
        INSTMED= test[143];
        INSTUQ = test[144];
        SBJ = test[145];
        TARPOP = test[146];
        TARAGG = test[147];
        TARSBJ = test[148];
        T001 = test[149];
        T048 = test[150];
        T064 = test[151];
        T080 = test[152];
        T096 = test[153];
        T112 = test[154];
        T128 = test[155];
        T144 = test[156];
        T160 = test[157];
        T176 = test[158];
        T192 = test[159];
        T208 = test[160];
        T224 = test[161];
        T240 = test[162];
        UCASCOURSEID= test[163];
        COMPOP = test[164];
        COMRESP_RATE = test[165];
        COMAGG = test[166];
        COMSBJ = test[167];
        ENTPOP = test[168];
        ENTAGG = test[169];
        ENTSBJ = test[170];
        ACCESS = test[171];
        ALEVEL = test[172];
        BACC = test[173];
        DEGREE = test[174];
        FOUNDTN = test[175];
        NOQUALS = test[176];
        OTHER = test[177];
        OTHERHE = test[178];
        COUNTRY = test[179];
        PUBUKPRNCOUNTRY = test[180];
        TEFMARKER = test[181];
        SUURL = test[181];
        SUURLW = test[182];
        Q24 = test[183];
        Q24POP = test[184];
        Q24RESP_RATE = test[184];
        ACCOMURL = test[185];
        ACCOMURLW = test[186];
        INSTBEDS = test[187];
        INSTLOWER = test[188];
        INSTUPPER = test[189];
        LOCNAME = test[190];
        LOCNAMEW = test[191];
        LATITUDE = test[192];
        LONGITUDE = test[193];
        LOCUKPRN = test[194];
        LOCCOUNTRY = test[195];
        PRIVATELOWER = test[196];
        PRIVATEUPPER  = test[197];
        NAME = test[198];
    }
}

