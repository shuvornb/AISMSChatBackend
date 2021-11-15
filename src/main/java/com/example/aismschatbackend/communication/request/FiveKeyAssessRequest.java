package com.example.aismschatbackend.communication.request;

import java.io.Serializable;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class FiveKeyAssessRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    public String cogFlexInven;
    public String empathy;
    public String hope;
    public String coping;
    public String family;
    public String community;
    public String work;
    public String friends;
    public String cloestPerson;
    public String mostTimePerson;
    public String worth;
    public String timeUsage;
    public String comAndLeisure;
    public String workTraj;

    public FiveKeyAssessRequest() {}

    // check if the result of the assessment is valid
    public boolean isResultValid() {
        // string length checking
        if (cogFlexInven.length() != 20 || empathy.length() != 16 || hope.length() != 12 || coping.length() != 28)
            return false;

        // check Cognitive Flexibility Inventory
        for (char c : cogFlexInven.toCharArray())
            if (c - '0' < 1 || c - '0' > 7)
                return false;

        // check Empathy
        for (char c : empathy.toCharArray())
            if (c - '0' < 0 || c - '0' > 4)
                return false;

        // check Hope
        for (char c : hope.toCharArray())
            if (c - '0' < 1 || c - '0' > 4)
                return false;

        // check Effective Coping Strategies
        for (char c : coping.toCharArray())
            if (c - '0' < 1 || c - '0' > 4)
                return false;

        return true;
    }

    // get the collected result into one string and store into database
    public String getRawResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(cogFlexInven).append("|");
        sb.append(empathy).append("|");
        sb.append(hope).append("|");
        sb.append(coping).append("|");
        sb.append(family).append("|");
        sb.append(community).append("|");
        sb.append(work).append("|");
        sb.append(friends).append("|");
        sb.append(cloestPerson).append("|");
        sb.append(mostTimePerson).append("|");
        sb.append(worth).append("|");
        sb.append(timeUsage).append("|");
        sb.append(comAndLeisure).append("|");
        sb.append(workTraj);

        return sb.toString();
    }

    public double getEmployScore() {
        double score = 0;
        for (int i = 7; i < 13; i++) {
            score += (double) workTraj.charAt(i) - '0';
        }
        return score / 6;
    }

    public double getEducationScore() {
        double score = 0;
        for (int i = 0; i < 7; i++) {
            score += (double) workTraj.charAt(i) - '0';
        }
        return score / 7;
    }

    /** getComAndLeisureScore()
     * Will get the score for the "community and leisure" measure.
     * The score does not include sub-question, which is just indicating who the participant is engaging in the activity
     * with.
     *
     * @return the score for the "community and leisure" measure
     */
    public int getComAndLeisureScore() {
        int score = 0;
        for (int i = 0; i < comAndLeisure.length(); i += 2)
            score += comAndLeisure.charAt(i) - '0';
        return score;
    }

    /** getDailyActivity()
     * Will return sorted activity participants do in their daily life. We have 10 pre-defined categories of
     * activity (can be found in the 5key manual):
     *      - sleeping
     *      - school
     *      - paid_work
     *      - household_chores
     *      - community_activities
     *      - recreational_activities
     *      - commuting
     *      - personal_relationships
     *      - health_and_self_maintenance
     *      - spiritual_religious_activities
     *
     * Also we may have other activity types.
     *
     *
     * The activity is sorted based on the relative difference between time they spent on a certain
     * activity and time they'd like to spend:
     *      diff = |timeSpent - timeWantToSpend| * 100 / timeWantToSpend
     * We are calculating absolute value here.
     *
     * @return a string contains all activities, with delimiter "|"
     */
    public String getDailyActivity() {
        String[] activities = timeUsage.split("-");
        Queue<String> heap = new PriorityQueue<>((o1, o2) -> (getRelativeDiff(o2) - getRelativeDiff(o1)));
        Collections.addAll(heap, activities);

        // format the result
        StringBuilder sb = new StringBuilder();
        while (heap.size() > 1) {
            sb.append(heap.poll()).append("|");
        }
        sb.append(heap.poll());

        return sb.toString();
    }

    /** getRelativeDiff()
     * Will calculate the relative difference between time spent on a certain activity and time like to spend:
     *      diff = |timeSpent - timeWantToSpend| * 100 / timeWantToSpend
     *
     * @param activity: record of a single activity in following format: activity:timeSpent:timeWantToSpend
     * @return the relative difference between time spent on a certain activity and time like to spend
     */
    public int getRelativeDiff(String activity) {
        String[] temp = activity.split(":");
        int timeSpent = Integer.parseInt(temp[1]);
        int timeWish = Integer.parseInt(temp[2]);

        if (timeWish == 0)
            return timeSpent == 0 ? 0 : Integer.MAX_VALUE;
        return Math.abs(timeSpent - timeWish) * 100 / timeWish;
    }

    /** getPerceptionOfWorthScore()
     * Will calculate the score for perception of worth. Item 1 and 2 are reverse scored
     * @return the perception of worth score
     */
    public int getPerceptionOfWorthScore() {
        int score = 0;
        for (int i = 0; i < worth.length(); i++) {
            if (i == 0 || i == 1)
                score += reverseScore(worth.charAt(i), 1, 4);
            else
                score += worth.charAt(i) - '0';
        }
        return score;
    }

    /** getQualityOfRelation()
     * Calculate the score for the quality of relation for cloest person and person spent most time with
     * @return a double array, containing the score for cloest person and person spent most time with
     *          ret[0]: score for support person the participant is closest to
     *          ret[1]: score for support person the participant spends the most time with
     */
    public double[] getQualityOfRelation() {

        double[] ret = new double[2];

        // get score for cloest person
        int len = cloestPerson.length();
        if (len == 0) {
            ret[0] = -1;
        } else {
            int i = len - 1, score = 0;
            while (cloestPerson.charAt(i - 1) != '-')
                --i;
            ret[0] = (double) scoreCount(cloestPerson.substring(i)) / 6.0;
        }

        // get score for person spent the most time with
        len = mostTimePerson.length();
        if (len == 0) {
            ret[1] = ret[0];
        } else {
            int i = len - 1, score = 0;
            while (mostTimePerson.charAt(i - 1) != '-')
                --i;
            ret[1] = (double) scoreCount(mostTimePerson.substring(i)) / 6.0;
        }

        return ret;

    }

    /** scoreCount()
     * Will count the score for a string of score.
     * @param s: score string. [s] should contain digit only
     * @return the sum of each digit. e.g. "12321" -> 9.
     *          -1: if [s] contains characters other than digit
     */
    public int scoreCount(String s) {
        int score = 0;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c))
                return -1;
            score += c - '0';
        }
        return score;
    }

    // ret[0]: total number of positive relationship (not counting the mix relationship)
    // ret[1]: percentage of positive relationship over all relationships
    public int[] getSocialNetworkScore() {
        String delimiter = "-";
        String[] familyRelations = family.split(delimiter);
        String[] comRelations = community.split(delimiter);
        String[] workRelations = work.split(delimiter);
        String[] friendRelations = friends.split(delimiter);

        // count number of relations of different quality
        int[] relationCount = new int[4];
        for (String s : familyRelations) {
            relationCount[s.charAt(s.length() - 1) - '0']++;
        }
        for (String s : comRelations) {
            relationCount[s.charAt(s.length() - 1) - '0']++;
        }
        for (String s : workRelations) {
            relationCount[s.charAt(s.length() - 1) - '0']++;
        }
        for (String s : friendRelations) {
            relationCount[s.charAt(s.length() - 1) - '0']++;
        }
        int totalCount = 0;
        for (int r : relationCount)
            totalCount += r;

        return new int[] { relationCount[2], relationCount[2] * 100 / totalCount };
    }

    /**
     * getCopScore()
     *
     * @param type specifies the type of the cope score
     *             0: problem focused coping
     *             1: emotion focuesed coping
     *             2: dysfunctional coping
     * @return
     */
    public int getCopScore(int type) {
        int score = 0;

        // determine index list based on coping type
        int[] index;
        if (type == 0)
            index = new int[] { 2, 7, 10, 14, 22, 23, 25, 27 };     // problem focused coping
        else if (type == 1)
            index = new int[] { 5, 9, 12, 15, 17, 18, 20, 21, 24, 28 };     // emotion focused coping
        else
            index = new int[] { 1, 3, 4, 6, 8, 11, 13, 16, 19, 26 };        // dysfunctional coping

        // get score
        for (int i : index)
            score += coping.charAt(i - 1) - '0';
        return score;
    }

    public int getHopeScore() {
        int score = 0;
        for (int i = 0; i < hope.length(); i++) {
            if (i == 2 || i == 5)
                score += reverseScore(hope.charAt(i), 1, 4);
            else
                score += hope.charAt(i) - '0';
        }
        return score;
    }

    public int getEmpathyScore() {
        int score = 0;
        for (int i = 0; i < empathy.length(); i++) {
            if (i == 6 || i == 10 || i == 14)
                score += reverseScore(empathy.charAt(i), 0, 4);
            else
                score += empathy.charAt(i) - '0';
        }
        return score;
    }

    public int getCogFlexScore() {
        int score = 0;
        for (int i = 0; i < cogFlexInven.length(); i++) {
            if (i == 1 || i == 3 || i == 6 || i == 8 || i == 10 || i == 16)
                score += reverseScore(cogFlexInven.charAt(i), 1, 7);
            else
                score += cogFlexInven.charAt(i) - '0';
        }
        return score;
    }

    /** reverseScore()
     * Will calculate the reverse score for a response to a problem.
     * For example, if possible response for a problem is [1:7], and the response is 2. Normal score is 2, the reversed
     * score is 6. Reverse score mapping:
     *      digit: 1 2 3 4 5 6 7
     *      score: 7 6 5 4 3 2 1
     *
     * @param digit: the response for a problem
     * @param lo: lowest choice for the problem
     * @param hi: highest choice for the problem
     * @return the reversed score. -1 for error
     */
    public int reverseScore(char digit, int lo, int hi) {
        if (lo >= hi)
            return -1;
        int score = hi;
        int num = digit - '0';
        while (--num > lo)
            --score;
        return score;
    }
}