package Data;

import android.util.Log;

/**
 * Created by chris on 06/03/17.
 *
 * This class contains all the information of a course, to find details of the variables please look at
 * Details.pdf stored in the MyStats-University folder
 */

public class Course {

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

