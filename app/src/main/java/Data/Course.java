package Data;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Created by chris on 06/03/17.
 *
 * This class contains all the information of a course, to find details of the variables please look at
 * Details.pdf stored in the MyStats-University folder
 */

public class Course  implements Parcelable{


    public String KISCOURSEID,UKPRN,CRSEURL,DISTANCE,FOUNDATION,KISMODE,SANDWICH,TITLE
    ,YEARABROAD,courseLevelCode,CourseLevelName,COURSEWORK,INDEPENDENT,PLACEMENT,PRACTICAL,
            SCHEDULED,WRITTEN,UCONT,UDORMANT,UGAINED,ULEFT,ULOWER,UFIRST,UUPPER,
            UOTHER,UORDINARY,UDISTINCTION,UMERIT,UPASS,UNA,WORKSTUDY,STUDY,ASSUNEMP,
            BOTH,NOAVAIL,WORK,PROFMAN,OTHERJOB,UNKWN,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,
            Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q22,NHSQ1,NHSQ2,NHSQ3,NHSQ4,
            NHSQ5,NHSQ6,LDLQ,LDMED,LDUQ,LQ,MED,UQ,INSTLQ,INSTMED,INSTUQ,T001,T048,T064,
            T080,T096,T112,T128,T144,T160,T176,T192,T208,T224,T240,ACCESS,ALEVEL,BACC,
            DEGREE,FOUNDTN,NOQUALS,OTHER,OTHERHE,Q24,Q24POP,Q24RESP_RATE,INSTLOWER,
            INSTUPPER,LOCNAME,LATITUDE,LONGITUDE,PRIVATELOWER,PRIVATEUPPER,NAME,courseNameUniMatch;



    public Course(){}

    /**
     * Get the name of the course
     * @return - name of the course
     */
    public String getCourseName(){
        return TITLE;
    }

    /**
     * Gets the name of the university where the course is taught
     * @return - The name of the university
     */
    public String getUniversityWhereCourseIsTaught(){
        return NAME;
    }

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
        String[] values = {Q24};
        return values;
    }

    /**
     * Gets a text version on the course name with title and course type
     * @return - A user friendly version of the course name
     */
    public String getFullCourseName(){
        return getDegreeType() + " " + getCourseName() + " " +  getCourseTypeText();
    }


    /**
     * Gets the mode of study (either full time of part time) as text
     * @return - Either full time or part time
     */
    public String getModeText(){
        return (Integer.valueOf(KISMODE) == 1) ? "full time" : "part time";
    }

    /**
     * Gets the average salary after 6 months or returns no stats if there isn't any information
     * @return - The average salary after 6 months as text or no current stats if there isn't any info
     */
    public String getAverageSalaryAfter6MonthsText(){
        return (!INSTMED.isEmpty() && INSTMED.length() > 1 ) ? "£" + INSTMED   : " no current stats";
    }

    /**
     * Gets a percentage of those that go on to work or study or no current stats if there isn't any info
     * @return - A text version of the percentage that go on to work or study
     */
    public String getPercentageTheWorkOrStudyText(){
        return (!WORKSTUDY.isEmpty() ) ? WORKSTUDY + "% " : "no current stats";
    }

    /**
     * Gets the percentage of students that are satisfied with the course if there is info otherwise returns a
     * string informing so
     * @return - A displayable version of the results of the satisfaction question
     */
    public String getPercentageThatAreSatisfiedText(){
        return (!Q22.isEmpty() ) ? Q22 + "%" : "no current stats";
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
        dest.writeStringArray(new String[]{KISCOURSEID,UKPRN,CRSEURL,DISTANCE,FOUNDATION,KISMODE,SANDWICH,TITLE
                ,YEARABROAD,courseLevelCode,CourseLevelName,COURSEWORK,INDEPENDENT,PLACEMENT,PRACTICAL,
                SCHEDULED,WRITTEN,UCONT,UDORMANT,UGAINED,ULEFT,ULOWER,UFIRST,UUPPER,
                UOTHER,UORDINARY,UDISTINCTION,UMERIT,UPASS,UNA,WORKSTUDY,STUDY,ASSUNEMP,
                BOTH,NOAVAIL,WORK,PROFMAN,OTHERJOB,UNKWN,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,
                Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19,Q20,Q21,Q22,NHSQ1,NHSQ2,NHSQ3,NHSQ4,
                NHSQ5,NHSQ6,LDLQ,LDMED,LDUQ,LQ,MED,UQ,INSTLQ,INSTMED,INSTUQ,T001,T048,T064,
                T080,T096,T112,T128,T144,T160,T176,T192,T208,T224,T240,ACCESS,ALEVEL,BACC,
                DEGREE,FOUNDTN,NOQUALS,OTHER,OTHERHE,Q24,Q24POP,Q24RESP_RATE,INSTLOWER,
                INSTUPPER,LOCNAME,LATITUDE,LONGITUDE,PRIVATELOWER,PRIVATEUPPER,NAME,courseNameUniMatch


        });

    }

    /**
     * Gets the accommodation costs for private rents near the uni, lower quartile , average and upper
     * @return - The accommodation costs
     */
    public String[] getPrivateAccomodationDetails(){

        String[] vals = new String[]{PRIVATELOWER, PRIVATEUPPER};
        return vals;
    }

    /**
     * Get the institutional accommodation costs, lower quartile , average and upper
     * @return - The accommodation costs
     */
    public String[] getInstitutionalAccomDetails(){
        String upper = INSTUPPER;
        String lower = INSTLOWER;
        double med = ((Double.parseDouble(upper)) / (Double.parseDouble(lower)));
        String Med = Double.toString(med);
        String[] vals = new String[]{upper, Med, lower};
        return vals;
    }

    /**
     * Gets the % that will continue the course after the first year
     * @return - The % of students that will continue after the first year
     */
    public String[] getThePercentageOfStudentsTheContinueAfterFirstYear(){
        String[] vals = new String[]{UCONT};
        return vals;
    }

    /**
     * Gets the number of entrants that where accepted with each amount of UCAS points
     * @return - Successfull aplicants in each UCAS points bracket
     */
    public String[] getPreviousEntry(){
        String[] vals = {T001,T048,T064,T080,T096,T112,T144,T160,T176,T192,T208,T224};
        return vals;
    }

    /**
     * This method gets the course type and gives returns additional information about whether the
     * course involves a foundation or sandwich year
     * @return - A string with the full course type and additions
     */
    public String getCourseTypeText(){

        boolean hasSomething = false;
        String out =  "";
        String foundation = getFoundationType();
        String placement = getPlacementType();
        String sandwich = getSandwichType();
        if(!foundation.isEmpty()){
            out += " with " + foundation;
            hasSomething = true;
        }
        if(!placement.isEmpty()){
            out += (hasSomething) ? " and " + placement : " with " + placement;
            hasSomething = true;
        }
        if(!sandwich.isEmpty()){
            out += (hasSomething) ? " and " + sandwich : " with " + sandwich;
        }
        return out ;
    }


    /**
     * Extracts information from the passed type of study option as to whether it is  an option
     * @param nameOfType - The name of the option ie placement
     * @param option - The value of that option ie 2 = optional
     * @return - A string description of that option
     */
        private  String getStudyType(String nameOfType, String option){
            switch (option){

                case "0" :
                    return  "";
                case "1" :
                    return String.format(" %s year", nameOfType);
                case "2" :
                    return String.format(" %s year (optional) ", nameOfType);
                default:
                    return  "";
            }
        }

    /**
     * Gets the type of course as text the sandwich field implies
     * @return - The type of course with respect to its sandwich status
     */
    public String getSandwichType(){
            return getStudyType("sandwich" , SANDWICH);
        }

    /**
     * Gets a string indicating if the course has a foundation element option
     * @return - Whether the course has a foundation option
     */
    public String getFoundationType(){
            return getStudyType("foundation" , FOUNDATION);
        }

    /**
     * Gets a string indicating if the course has a placement option
     * @return - Text giving information about placement option
     */
    public String getPlacementType(){
            return getStudyType("placement" , PLACEMENT);
        }

        public boolean hasSandwichYear(){
            return  SANDWICH.equals("1");
        }

        public  boolean hasFoundationYear(){
            return FOUNDATION.equals("1");
        }

       public boolean hasPlacementYear(){
           return PLACEMENT.equals("1");
       }
        public String getDegreeType(){
            return courseLevelCode;
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
        UKPRN = test[1];
                CRSEURL = test[2];
                DISTANCE = test[3];
                FOUNDATION = test[4];
                KISMODE = test[5];
                SANDWICH = test[6];
                TITLE = test[7];
                YEARABROAD = test[8];
                courseLevelCode = test[9];
                CourseLevelName = test[10];
                COURSEWORK = test[11];
                INDEPENDENT = test[12];
                PLACEMENT = test[13];
                PRACTICAL = test[14];
                SCHEDULED = test[15];
                WRITTEN = test[16];
                UCONT = test[17];
                UDORMANT = test[18];
                UGAINED = test[19];
                ULEFT = test[20];
                ULOWER = test[21];
                UFIRST = test[22];
                UUPPER = test[23];
                UOTHER = test[24];
                UORDINARY = test[25];
                UDISTINCTION = test[26];
                UMERIT = test[27];
                UPASS = test[28];
                UNA = test[29];
                WORKSTUDY = test[30];
                STUDY = test[31];
                ASSUNEMP = test[32];
                BOTH = test[33];
                NOAVAIL = test[34];
                WORK = test[35];
                PROFMAN = test[36];
                OTHERJOB = test[37];
                UNKWN = test[38];
                Q1 = test[39];
                Q2 = test[40];
                Q3 = test[41];
                Q4 = test[42];
                Q5 = test[43];
                Q6 = test[44];
                Q7 = test[45];
                 Q8 = test[46];
                Q9 = test[47];
                Q10 = test[48];
                Q11 = test[49];
                Q12 = test[50];
                Q13 = test[51];
                Q14 = test[52];
                Q15 = test[53];
                Q16 = test[54];
                Q17 = test[55];
                Q18 = test[56];
                Q19 = test[57];
                Q20 = test[58];
                Q21 = test[59];
                Q22 = test[60];
                NHSQ1 = test[61];
                NHSQ2  = test[62];
                NHSQ3 =  test[63];
                NHSQ4 =  test[64];
                NHSQ5 =  test[65];
                NHSQ6 = test[66];
                LDLQ = test[67];
                LDMED = test[68];
                 LDUQ = test[69];
                LQ = test[70];
                MED = test[71];
                UQ = test[72];
                INSTLQ = test[73];
                INSTMED = test[74];
                INSTUQ = test[75];
                T001 = test[76];
                T048 = test[77];
                T064 = test[78];
                T080 = test[79];
                T096 = test[80];
                T112 = test[81];
                T128 = test[82];
                T144 = test[83];
                T160 = test[84];
                T176 = test[85];
                T192 = test[86];
                T208 = test[87];
                T224 = test[88];
                T240 = test[89];
                ACCESS = test[90];
                ALEVEL = test[91];
                BACC = test[92];
                DEGREE = test[93];
                FOUNDTN = test[94];
                NOQUALS = test[95];
                OTHER = test[96];
                OTHERHE = test[97];
                Q24 = test[98];
                Q24POP = test[99];
                Q24RESP_RATE = test[100];
                INSTLOWER = test[101];
                INSTUPPER = test[102];
                LOCNAME = test[103];
                LATITUDE = test[104];
                LONGITUDE = test[105];
                PRIVATELOWER = test[106];
                PRIVATEUPPER = test[107];
                NAME = test[108];
                courseNameUniMatch = test[109];
    }
}

