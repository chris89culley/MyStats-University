package MPChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;

import Data.Course;

/**
 * Created by Jack on 08/03/2017.
 * A class which holds functions which create charts from the database objects
 */

public class UniversityStatsChartMaker {

    /**
     * A method which gets the percentage assessed of a course assessed by coursework and returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - AssesedByCourseWork
     */
    public static PieData getChartPercentageAssesedByCourseWork(Course course, PieChart chart){
        String[] tags = {"Assesed by CourseWork"};
        return GenericChartMaker.constructPieChart(tags,course.getPercentageAssesedByCourseWork(), chart, "Percentage of course assesed by coursework");
    }

    /**
     * A method which gets the percentage of time spent in lectures and practicals and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PercentageInScheduled
     */
    public static BarData getChartPercentageInScheduled(Course course, BarChart chart){
        String[] tags = {"In lectures or practicals"};
        return GenericChartMaker.constructBarChart(tags,course.getPercentageInScheduled(), chart, "Percentage of time spent in lectures or practicals");
    }

    /**
     * A method which gets the average salary after 6 months from a course object and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AvgSalarySixMonths
     */
    public static BarData getChartAvgSalarySixMonths(Course course, BarChart chart){
        String[] tags = {"Course Lower Quartile Salary","Course Median Salary","Course Upper Quartile Salary"};
        return GenericChartMaker.constructBarChart(tags,course.getAvgSalarySixMonths(), chart, "Average salary after 6 months");
    }

    /**
     * A method which gets the average salary after 40 months from a course object and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AvgSalaryFoutrtyMonths
     */
    public static BarData getAvgSalaryFourtyMonths(Course course, BarChart chart){
        String[] tags = {"Lower Quartile Salary","Median Salary","Upper Quartile Salary"};
        return GenericChartMaker.constructBarChart(tags,course.getAvgSalaryFoutrtyMonths(), chart, "Average salary after 40 months");
    }

    /**
     * A method which gets the percentages of those that go onto work and study and returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - PercentageWorkAndStudy
     */
    public static PieData getChartPercentageWorkAndStudy(Course course, PieChart chart){
        String[] tags = {"Doing further study","Now working","Unemployed","Studying and working","Other"};
        return GenericChartMaker.constructPieChart(tags,course.getPercentageWorkAndStudy(), chart, "Percentages of those that go on to work and study");
    }

    /**
     * A method which gets the percentages of employment six months after finishing the course and returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - EploymentSixMonths
     */
    public static PieData getChartEploymentSixMonths(Course course, PieChart chart){
        String[] tags = {"In professional or managerial job","Not in professional or managerial job","In unknown job type"};
        return GenericChartMaker.constructPieChart(tags,course.getEmploymentSixMonths(), chart, "Employment 6 months after completing the course");
    }

    //Below are the NSS question sets

    /**
     * A method which gets the NSS teaching on my course data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - TeachingOnMyCourse
     */
    public static BarData getChartTeachingOnMyCourse(Course course, BarChart chart){
        String[] tags = {"Staff are good at explaining things","Staff have made the subject interesting","Staff are enthusiastic about what they are teaching","The course is intellectually stimulating"};
        return GenericChartMaker.constructBarChart(tags,course.getTeachingOnMyCourseStats(), chart, "Teaching on my course (% agree)");
    }

    /**
     * A method which gets the NSS assessment and feedback data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AssesmentAndFeedback
     */
    public static BarData getChartAssesmentAndFeedback(Course course, BarChart chart){
        String[] tags = {"The criteria used in marking have been clear in advance","Assessment arrangements and marking have been fair","Feedback on my work has been prompt","I have received detailed comments on my work","Feedback on my work has helped me clarify things I did not understand"};
        return GenericChartMaker.constructBarChart(tags,course.getAssesmentAndFeedbackStats(), chart, "Assessment and feedback (% agree)");
    }

    /**
     * A method which gets the NSS academic support data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AccademicSupport
     */
    public static BarData getChartAccademicSupport(Course course, BarChart chart){
        String[] tags = {"I have received sufficient advice and support with my studies","I have been able to contact staff when I needed to","Good advice was available when I needed to make study choices"};
        return GenericChartMaker.constructBarChart(tags,course.getAccademicSupportStats(), chart, "Academic support (% agree)");
    }

    /**
     * A method which gets the NSS organisation and management data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - OrganisationAndManagement
     */
    public static BarData getChartOrganisationAndManagement(Course course, BarChart chart){
        String[] tags = {"The timetable works efficiently as far as my activities are concerned","Any changes in the course or teaching have been communicated effectively","The course is well organised and is running smoothly"};

        return GenericChartMaker.constructBarChart(tags,course.getOrganisationAndManagementStats(), chart, "Organisation and management (% agree)");
    }

    /**
     * A method which gets the NSS learning and resources data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - LearningResources
     */
    public static BarData getChartLearningResources(Course course, BarChart chart){
        String[] tags = {"The library resources and services are good enough for my needs","I have been able to access general IT resources when I needed to","I have been able to access specialised equipment, facilities, or rooms when I needed to"};

        return GenericChartMaker.constructBarChart(tags,course.getLearningResourcesStats(), chart, "Learning resources (% agree)");
    }

    /**
     * A method which gets the NSS personal development data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PersonalDevelopment
     */
    public static BarData getChartPersonalDevelopment(Course course, BarChart chart){
        String[] tags = {"he course has helped me to present myself with confidence","My communication skills have improved","As a result of the course, I feel confident in tackling unfamiliar problems"};
        return GenericChartMaker.constructBarChart(tags,course.getPersonalDevelopmentStats(), chart, "Personal development (% agree)");
    }


    /**
     * A method which gets the NSS student union data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - StudentUnion
     */
    public static BarData getChartStudentUnion(Course course, BarChart chart){
        String[] tags = {"I am satisfied with the Students' Union at my institution"};
        return GenericChartMaker.constructBarChart(tags,course.getStudentUnionStats(), chart, "Students' Union (Association or Guild) (% agree)");
    }

    /**
     * A method which gets the institutions accomodation costs
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - InstitutionAccomodationCosts
     */
    public static BarData getChartInstitutionalAccomodation(Course course, BarChart chart){
        String[] tags = {"Lower Quartile","Median", "Upper Quartile"};
        return GenericChartMaker.constructBarChart(tags,course.getPrivateAccomodationDetails(), chart, "Insititutional accomodation prices");
    }

    /**
     * A method which gets private accomodation costs
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PrivatenAccomodationCosts
     */
    public static BarData getChartPrivateAccomodation(Course course, BarChart chart){
        String[] tags = {"Lower Quartile","Upper Quartile"};
        return GenericChartMaker.constructBarChart(tags,course.getPrivateAccomodationDetails(), chart, "Private accomodation prices");
    }

    /**
     * A method which gets previous number of successful applicants as a  chart
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PreviousEntries
     */
    public static BarData getChartPreviousEntries(Course course, BarChart chart){
        String[] tags = {"< 48","48 - 63","64 - 79","80 - 95","96 - 111","112 - 127", "128 - 143", "144 - 159", "160 - 175 ","176 - 191","192 - 207", "208 - 223", "224 - 239", "240+"};
        return GenericChartMaker.constructBarChart(tags,course.getPreviousEntry(), chart, "Number of successful applicants in each tariff point bracket");
    }






}
