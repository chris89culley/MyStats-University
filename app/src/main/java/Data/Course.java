package Data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.lang.reflect.Field;

/**
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

    @Override
    public int describeContents() {
        return 0;
    }

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

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Course createFromParcel(Parcel in){
            return new Course(in);
        }
    }

    public Course(Parcel in){
                KISCOURSEID = in.readString();
                PUBUKPRN = in.readString();
                UKPRN = in.readString();
                ASSURL = in.readString();
                ASSURLW = in.readString();
                CRSEURL = in.readString();
                CRSEURLW = in.readString();
                DISTANCE = in.readString();
                EMPLOYURL = in.readString();
                EMPLOYURLW = in.readString();
                ENGFEE = in.readString();
                FEETBC  = in.readString();
                FOUNDATION = in.readString();
                HONOURS = in.readString();
                JACS = in.readString();
                KISMODE = in.readString();
                KISTYPE = in.readString();
                LDCS = in.readString();
                LEVEL = in.readString();
                LOCCHNGE = in.readString();
                LTURL = in.readString();
                LTURLW = in.readString();
                MEANSSUP = in.readString();
                NHS = in.readString();
                NIFEE = in.readString();
                NONCREDITASSESS = in.readString();
                NUMSTAGE = in.readString();
                OTHSUP = in.readString();
                RELATEDKIS = in.readString();
                SANDWICH = in.readString();
                SCOTFEE = in.readString();
                SUPPORTURL = in.readString();
                SUPPORTURLW = in.readString();
                TITLE = in.readString();
                TITLEW = in.readString();
                UCASPROGID = in.readString();
                UKPRNAPPLY = in.readString();
                VARFEE = in.readString();
                WAFEE = in.readString();
                WAIVER = in.readString();
                WELSH = in.readString();
                YEARABROAD = in.readString();
                KISAIMCODE = in.readString();
                AVGWRITTEN = in.readString();
                AVGCOURSEWORK = in.readString();
                AVGSCHEDULED = in.readString();
                ASSACT = in.readString();
                COURSEWORK = in.readString();
                INDEPENDENT = in.readString();
                LTACT = in.readString();
                PLACEMENT = in.readString();
                PRACTICAL = in.readString();
                SCHEDULED = in.readString();
                STAGE = in.readString();
                WRITTEN = in.readString();
                ACCTYPE = in.readString();
                ACCDEPEND = in.readString();
                ACCDEPENDURL = in.readString();
                ACCDEPENDURLW = in.readString();
                CONTPOP  = in.readString();
                CONTAGG = in.readString();
                CONTSBJ = in.readString();
                UCONT = in.readString();
                UDORMANT = in.readString();
                UGAINED = in.readString();
                ULEFT = in.readString();
                ULOWER = in.readString();
                LOCID = in.readString();
                DEGPOP = in.readString();
                DEGAGG = in.readString();
                DEGSBJ = in.readString();
                UFIRST = in.readString();
                UUPPER = in.readString();
                UOTHER = in.readString();
                UORDINARY = in.readString();
                UDISTINCTION = in.readString();
                UMERIT = in.readString();
                UPASS = in.readString();
                UNA = in.readString();
                EMPPOP = in.readString();
                EMPRESP_RATE = in.readString();
                EMPAGG = in.readString();
                EMPSBJ = in.readString();
                WORKSTUDY = in.readString();
                STUDY = in.readString();
                ASSUNEMP = in.readString();
                BOTH = in.readString();
                NOAVAIL = in.readString();
                WORK = in.readString();
                JOBPOP = in.readString();
                JOBRESPAF8RATE = in.readString();
                JOBAGG = in.readString();
                JOBSBJ = in.readString();
                PROFMAN = in.readString();
                OTHERJOB = in.readString();
                UNKWN = in.readString();
                NSSPOP = in.readString();
                NSSRESPAF8RATE = in.readString();
                NSSAGG = in.readString();
                NSSSBJ = in.readString()
                Q1 = in.readString()
                Q2 = in.readString();
                Q3 = in.readString();
                Q4 = in.readString();
                Q5 = in.readString();
                Q6 = in.readString();
                Q7 = in.readString();
                Q8 = in.readString();
                Q9 = in.readString();
                Q10 = in.readString();
                Q11 = in.readString();
                Q12 = in.readString();
                Q13 = in.readString();
                Q14 = in.readString();
                Q15 = in.readString();
                Q16 = in.readString();
                Q17 = in.readString();
                Q18 = in.readString();
                Q19 = in.readString();
                Q20 = in.readString();
                Q21 = in.readString();
                Q22 = in.readString();
                NHSPOP = in.readString();
                NHSRESPAF8RATE = in.readString();
                NHSAGG = in.readString();
                NHSSBJ = in.readString();
                NHSQ1 = in.readString();
                NHSQ2 = in.readString();
                NHSQ3 = in.readString();
                NHSQ4 = in.readString();
                NHSQ5 = in.readString();
                NHSQ6 = in.readString();
                SALPOP = in.readString();
                SALRESPAF8RATE = in.readString();
                SALAGG = in.readString();
                SALSBJ = in.readString();
                LDLQ = in.readString();
                LDMED = in.readString();
                LDUQ = in.readString();
                LQ = in.readString();
                MED = in.readString();
                UQ = in.readString();
                INSTLQ = in.readString();
                INSTMED= in.readString();
                INSTUQ = in.readString();
                SBJ = in.readString();
                TARPOP = in.readString();
                TARAGG = in.readString();
                TARSBJ = in.readString();
                T001 = in.readString();
                T048 = in.readString();
                T064 = in.readString();
                T080 = in.readString();
                T096 = in.readString();
                T112 = in.readString();
                T128 = in.readString();
                T144 = in.readString();
                T160 = in.readString();
                T176 = in.readString();
                T192 = in.readString();
                T208 = in.readString();
                T224 = in.readString();
                T240 = in.readString();
                UCASCOURSEID= in.readString();
                COMPOP = in.readString();
                COMRESP_RATE = in.readString();
                COMAGG = in.readString();
                COMSBJ = in.readString();
                ENTPOP = in.readString();
                ENTAGG = in.readString();
                ENTSBJ = in.readString();
                ACCESS = in.readString();
                ALEVEL = in.readString();
                BACC = in.readString();
                DEGREE = in.readString();
                FOUNDTN = in.readString();
                NOQUALS = in.readString();
                OTHER = in.readString();
                OTHERHE = in.readString();
                COUNTRY = in.readString();
                PUBUKPRNCOUNTRY = in.readString();
                TEFMARKER = in.readString();
                SUURL = in.readString();
                SUURLW = in.readString();
                Q24 = in.readString();
                Q24POP = in.readString();
                Q24RESP_RATE = in.readString();
                ACCOMURL = in.readString();
                ACCOMURLW = in.readString();
                INSTBEDS = in.readString();
                INSTLOWER = in.readString();
                INSTUPPER = in.readString();
                LOCNAME = in.readString();
                LOCNAMEW = in.readString();
                LATITUDE = in.readString();
                LONGITUDE = in.readString();
                LOCUKPRN = in.readString();
                LOCCOUNTRY = in.readString();
                PRIVATELOWER = in.readString();
                PRIVATEUPPER  = in.readString();
                NAME = in.readString();
    }
}
