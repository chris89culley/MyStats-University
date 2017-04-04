package Data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.chris.mystats_univeristy.CourseStats;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Currency;


/*
 * Created by chris on 06/03/17.
 *
 * This class contains all the information of a course, to find details of the variables please look at
 * Details.pdf stored in the MyStats-University folder
 */

public class Course  implements Parcelable,Serializable{


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
     * @return ChartStats - for percentage assessed by coursework
     */
    public ChartStats getPercentageAssesedByCourseWork(){
        ChartStats cs = new ChartStats(new String[]{COURSEWORK},new String[]{"Assesed by CourseWork"},"Percentage of course assesed by coursework","pie");
        return cs;
    }

    /**
     * Accessor method for Percentage of time spent in lectures variable
     * @return Chart stats - for percentage in scheduled
     */
    public ChartStats getPercentageInScheduled(){
        ChartStats cs = new ChartStats(new String[]{SCHEDULED},new String[]{"In lectures or practicals"},"Percentage of time spent in lectures or practicals","");
        return cs;

    }

    /**
     * Accessor method for Average salary after 6 months variables variable
     * @return ChartStats - average salary after 6 months
     */
    public ChartStats getAvgSalarySixMonths(){
        ChartStats cs = new ChartStats(new String[]{INSTLQ,INSTMED,INSTUQ},new String[]{"Course Lower Quartile Salary","Course Median Salary","Course Upper Quartile Salary"},"Average salary after 6 months","");
        return cs;
    }

    /**
     * Accessor method for Average salary after 40 months variables variable
     * @return ChartStats - average salary after 40 months
     */
    public ChartStats getAvgSalaryFoutrtyMonths(){

        ChartStats cs = new ChartStats(new String[]{LDLQ,LDMED,LDUQ},new String[]{"Lower Quartile Salary","Median Salary","Upper Quartile Salary"},"Average salary after 40 months","");
        return cs;
    }

    /**
     * Accessor method for percentage of those that go onto work and studys variables variable
     * @return ChartStats - for percentages of those that go on to work and study
     */
    public ChartStats getPercentageWorkAndStudy(){
        ChartStats cs = new ChartStats(new String[]{STUDY,WORK,ASSUNEMP,BOTH,NOAVAIL},new String[]{"Doing further study","Now working","Unemployed","Studying and working","Other"},"Percentage of those that went on to work and study","pie");
        return cs;
    }

    /**
     * Accessor method for Percentage of those in employment after 6 months variables variable
     ** @return ChartStats - for employment after 6 months
     * */
    public ChartStats getEmploymentSixMonths(){
        ChartStats cs = new ChartStats(new String[]{PROFMAN,OTHERJOB,UNKWN},new String[]{"In professional or managerial job","Not in professional or managerial job","In unknown job type"},"Percentage of those that went on to work and study","pie");
        return cs;
    }

    /**
     * Accessor method for teaching on my course stats variables variable
     * @return ChartStats - Teaching on my course stats
     */
    public ChartStats getTeachingOnMyCourseStats(){
        ChartStats cs = new ChartStats(new String[]{Q1,Q2,Q3,Q4},new String[]{"Staff are good at explaining things","Staff have made the subject interesting","Staff are enthusiastic about what they are teaching","The course is intellectually stimulating"}, "Teaching on my course (% agree)","");
        return cs;
    }

    public String getPercentEnthusiasticAboutTeaching(){
        return Q1;
    }

    public String getPercentThatThingStaffMadeTheSubjectInteresting(){
        return Q2;
    }

    public String getPercentThatThinkStaffAreGoodAtExplaining(){
        return Q3;
    }


    /**
     * Accessor method for Assessment and feedback stats variables variable
     * @return ChartStats - Assessment and feedback stats
     */
    public ChartStats getAssesmentAndFeedbackStats(){
        ChartStats cs = new ChartStats(new String[]{Q5,Q6,Q7,Q8,Q9},new String[]{"The criteria used in marking have been clear in advance","Assessment arrangements and marking have been fair","Feedback on my work has been prompt","I have received detailed comments on my work","Feedback on my work has helped me clarify things I did not understand"}, "Assessment and feedback (% agree)","");
        return cs;
    }

    /**
     * Accessor method for Academic support stats variables variable
     * @return ChartStats - Academic support stats
     */
    public ChartStats getAccademicSupportStats(){
        ChartStats cs = new ChartStats(new String[]{Q10,Q11,Q12},new String[]{"I have received sufficient advice and support with my studies","I have been able to contact staff when I needed to","Good advice was available when I needed to make study choices"}, "Assessment and feedback (% agree)","");
        return cs;
    }

    /**
     * Accessor method for Organisation and management stats variables variable
     * @return ChartStats - Organisation and management stats
     */
    public ChartStats getOrganisationAndManagementStats(){

        ChartStats cs = new ChartStats(new String[]{Q13,Q14,Q15},new String[]{"The timetable works efficiently as far as my activities are concerned","Any changes in the course or teaching have been communicated effectively","The course is well organised and is running smoothly"}, "Organisation and management (% agree)","");
        return cs;
    }

    /**
     * Accessor method for Learning resource stats variables variable
     * @return ChartStats - Learning resource stats
     */
    public ChartStats getLearningResourcesStats(){
        ChartStats cs = new ChartStats(new String[]{Q16,Q17,Q18},new String[]{"The library resources and services are good enough for my needs","I have been able to access general IT resources when I needed to","I have been able to access specialised equipment, facilities, or rooms when I needed to"},"Learning resources (% agree)","");
        return cs;
    }

    /**
     * Accessor method for Personal development stats variables variable
     * @return ChartStats - Personal development stats
     */
    public ChartStats getPersonalDevelopmentStats(){
        ChartStats cs = new ChartStats(new String[]{Q19,Q20,Q21},new String[]{"The course has helped me to present myself with confidence","My communication skills have improved","As a result of the course, I feel confident in tackling unfamiliar problems"},"Personal development (% agree)","");
        return cs;
    }

    /**
     * Accessor method for Student union stats variables variable
     * @return ChartStats - Student union stats
     */
    public ChartStats getStudentUnionStats(){
        ChartStats cs = new ChartStats(new String[]{Q24},new String[]{"I am satisfied with the Students' Union at my institution"},"Students' Union % satisfied with","");
        return cs;
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
        return (!INSTMED.isEmpty() && INSTMED.length() > 1 ) ? "£" + INSTMED   : " N/A";
    }

    /**
     * Gets the average salary after 40 months or returns no stats if there isn't any information
     * @return - The average salary after 40 months as text or no current stats if there isn't any info
     */
    public String getAverageSalaryAfter40MonthsText(){
        return (!LDMED.isEmpty() && LDMED.length() > 1 ) ? "£" + LDMED   : " no current stats";
    }

    /**
     * Gets a percentage of those that go on to work or study or no current stats if there isn't any info
     * @return - A text version of the percentage that go on to work or study
     */
    public String getPercentageTheWorkOrStudyText(){
        return (!WORKSTUDY.isEmpty() ) ? WORKSTUDY + "% " : " N/A";
    }

    /**
     * Gets the percentage of students that are satisfied with the course if there is info otherwise returns a
     * string informing so
     * @return - A displayable version of the results of the satisfaction question
     */
    public String getPercentageThatAreSatisfiedText(){
        return (!Q22.isEmpty() ) ? Q22 + "%" : " N/A";
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
     * @return ChartStats - The accommodation costs
     */
    public ChartStats getPrivateAccomodationDetails(){
        ChartStats cs = new ChartStats(new String[]{PRIVATELOWER, PRIVATEUPPER},new String[]{"Lower Quartile","Upper Quartile"},"Private accomodation prices","");
        return cs;
    }

    /**
     * Get the institutional accommodation costs, lower quartile , average and upper
     * @return ChartStats - The accommodation costs
     */
    public ChartStats getInstitutionalAccomDetails(){
        ChartStats cs = new ChartStats(new String[]{INSTLOWER, INSTMED, INSTUPPER},new String[]{"Lower Quartile","Median", "Upper Quartile"},"Insititutional accomodation prices","");
        return cs;
    }

    /**
     * Get the degree class of students who finished the course
     * @return ChartStats - The degree class
     */
    public ChartStats getDegreeClass(){
        ChartStats cs = new ChartStats(new String[]{UFIRST, UUPPER,UOTHER},new String[]{"First ","2:1", "Other"},"Degree classifications on graduation","pie");
        return cs;
    }

    /**
     * Get the degree class of students who finished the course
     * @return ChartStats - The degree class
     */
    public ChartStats getContinuationStats(){
        ChartStats cs = new ChartStats(new String[]{UGAINED, ULEFT, UCONT,UDORMANT},new String[]{"Completed the course","Left before completing the course","Continued at the place of study", "Are taking a break from their studies"},"What students are doing a year into their studies","pie");

        return cs;
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
        String[] vals = {T001,T048,T064,T080,T096,T112,T128,T144,T160,T176,T192,T208,T224,T240};
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

