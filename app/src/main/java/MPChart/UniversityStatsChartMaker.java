package MPChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;

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
        return GenericChartMaker.constructPieChart(course.getPercentageAssesedByCourseWork(), chart, "Percentage of course assesed by coursework");
    }
    /**
     * A method which gets the degree class data returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - AssesedByCourseWork
     */
    public static PieData getChartContinuationStats(Course course, PieChart chart) {
        return GenericChartMaker.constructPieChart(course.getContinuationStats(), chart, "What students are doing 1 year into the course");
    }
    /**
     * A method which gets the degree class data returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - AssesedByCourseWork
     */
    public static PieData getChartDegreeClass(Course course, PieChart chart){
        return GenericChartMaker.constructPieChart(course.getDegreeClass(), chart, "Degree classifications");}




    /**
     * A method which gets the percentage of time spent in lectures and practicals and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PercentageInScheduled
     */
    public static BarData getChartPercentageInScheduled(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getPercentageInScheduled(), chart, "Percentage of time spent in lectures or practicals");
    }

    /**
     * A method which gets the average salary after 6 months from a course object and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AvgSalarySixMonths
     */
    public static BarData getChartAvgSalarySixMonths(Course course, BarChart chart){
        String[] tags = {"Course Lower Quartile Salary","Course Median Salary","Course Upper Quartile Salary"};
        return GenericChartMaker.constructBarChart(course.getAvgSalarySixMonths(), chart, "Average salary after 6 months");
    }

    /**
     * A method which gets the average salary after 40 months from a course object and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AvgSalaryFoutrtyMonths
     */
    public static BarData getAvgSalaryFourtyMonths(Course course, BarChart chart){
        String[] tags = {"Lower Quartile Salary","Median Salary","Upper Quartile Salary"};
        return GenericChartMaker.constructBarChart(course.getAvgSalaryFoutrtyMonths(), chart, "Average salary after 40 months");
    }

    /**
     * A method which gets the percentages of those that go onto work and study and returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - PercentageWorkAndStudy
     */
    public static PieData getChartPercentageWorkAndStudy(Course course, PieChart chart){
        return GenericChartMaker.constructPieChart(course.getPercentageWorkAndStudy(), chart, "Percentages of those that go on to work and study");
    }

    /**
     * A method which gets the percentages of employment six months after finishing the course and returns the pie data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return PieData for pie chart - EploymentSixMonths
     */
    public static PieData getChartEploymentSixMonths(Course course, PieChart chart){
        return GenericChartMaker.constructPieChart(course.getEmploymentSixMonths(), chart, "Employment 6 months after completing the course");
    }

    //Below are the NSS question sets

    /**
     * A method which gets the NSS teaching on my course data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - TeachingOnMyCourse
     */
    public static BarData getChartTeachingOnMyCourse(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getTeachingOnMyCourseStats(), chart, "Teaching on my course (% agree)");
    }

    /**
     * A method which gets the NSS assessment and feedback data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AssesmentAndFeedback
     */
    public static BarData getChartAssesmentAndFeedback(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getAssesmentAndFeedbackStats(), chart, "Assessment and feedback (% agree)");
    }

    /**
     * A method which gets the NSS academic support data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - AccademicSupport
     */
    public static BarData getChartAccademicSupport(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getAccademicSupportStats(), chart, "Academic support (% agree)");
    }

    /**
     * A method which gets the NSS organisation and management data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - OrganisationAndManagement
     */
    public static BarData getChartOrganisationAndManagement(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getOrganisationAndManagementStats(), chart, "Organisation and management (% agree)");
    }

    /**
     * A method which gets the NSS learning and resources data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - LearningResources
     */
    public static BarData getChartLearningResources(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getLearningResourcesStats(), chart, "Learning resources (% agree)");
    }

    /**
     * A method which gets the NSS personal development data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PersonalDevelopment
     */
    public static BarData getChartPersonalDevelopment(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getPersonalDevelopmentStats(), chart, "Personal development (% agree)");
    }


    /**
     * A method which gets the NSS student union data and returns the bar data for it
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - StudentUnion
     */
    public static BarData getChartStudentUnion(Course course, BarChart chart){
        return GenericChartMaker.constructBarChart(course.getStudentUnionStats(), chart, "Students' Union (Association or Guild) (% agree)");
    }

    /**
     * A method which gets the institutions accomodation costs
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - InstitutionAccomodationCosts
     */
    public static BarData getChartInstitutionalAccomodation(Course course, BarChart chart){
        String[] tags = {"Lower Quartile","Median", "Upper Quartile"};
        return GenericChartMaker.constructBarChart(course.getPrivateAccomodationDetails(), chart, "Insititutional accomodation prices");
    }

    /**
     * A method which gets private accomodation costs
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PrivatenAccomodationCosts
     */
    public static BarData getChartPrivateAccomodation(Course course, BarChart chart){
        String[] tags = {"Lower Quartile","Upper Quartile"};
        return GenericChartMaker.constructBarChart(course.getPrivateAccomodationDetails(), chart, "Private accomodation prices");
    }

    /**
     * A method which gets previous number of successful applicants as a  chart
     * @param course - data from the database with the course information
     * @param chart - The chart it will be applied to
     * @return BarData for bar chart - PreviousEntries
     */
    public static LineData getChartPreviousEntries(Course course, LineChart chart) {
        String[] keys = {"48", "55", "71", "87", "103", "119", "135", "151", "167", "183", "199", "215", "231", "240"};
        ArrayList<String> tags = new ArrayList<>();
        {
            tags.add("< 48");
            tags.add("48 - 63");
            tags.add("64 - 79");
            tags.add("80 - 95");
            tags.add("96 - 111");
            tags.add("112 - 127");
            tags.add("128 - 143");
            tags.add("144 - 159");
            tags.add("160 - 175");
            tags.add("176 - 191");
            tags.add("192 - 207");
            tags.add("208 - 223");
            tags.add("224 - 239");
            tags.add("240+");
            return GenericChartMaker.constructLineChart(keys, course.getPreviousEntry(), chart);
        }


    }



}
