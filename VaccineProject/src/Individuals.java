/**
 * @author Ben DeSollar
 * @author Rida Errachidi
 * @author Adnane Ezouhri
 */
public class Individuals {
    /**
     * first name
     */
    private String first_name;
    /**
     * last name
     */
    private String last_name;
    /**
     * is individual vaccinated
     */
    private String vaccinated;
    /**
     * individual wear masks
     */
    private String wear_mask;
    /**
     * date of birth of individual
     */
    private String DoB;
    /**
     * ssn of individual
     */
    private String SSN;
    /**
     * first dose, second does, booster, and last shot
     */
    private String first_dose, second_dose, booster, last_shot;
    /**
     * user name, password, answer 1, and answer 2
     */
    private String user_name, password, answer1, answer2;


    /**
     * Individuals Constructor
     *
     * @param first_name  first name
     * @param last_name   last name
     * @param DoB         date of birth
     * @param SSN         social security number
     * @param vaccinated  check if vaccinated
     * @param first_dose  first dose
     * @param second_dose second does
     * @param booster     booster is optional
     * @param last_shot   last shot
     * @param wear_mask   do you wear a mask
     * @param user_name   username
     * @param password    password
     * @param answer1     security answer 1
     * @param answer2     security answer 2
     */
    public Individuals(String first_name, String last_name, String DoB, String SSN, String vaccinated, String first_dose, String second_dose, String booster, String last_shot, String wear_mask, String user_name, String password, String answer1, String answer2) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.vaccinated = vaccinated;
        this.wear_mask = wear_mask;
        this.DoB = DoB;
        this.SSN = SSN;
        this.first_dose = first_dose;
        this.second_dose = second_dose;
        this.booster = booster;
        this.last_shot = last_shot;
        this.user_name = user_name;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    /**
     * individuals constructor
     *
     * @param first_name  first name
     * @param last_name   last name
     * @param DoB         date of birth
     * @param SSN         social security number
     * @param vaccinated  check if vaccinated
     * @param first_dose  first dose
     * @param second_dose second does
     * @param booster     booster is optional
     * @param last_shot   last shot
     * @param wear_mask   do you wear a mask
     */
    public Individuals(String first_name, String last_name, String DoB, String SSN, String vaccinated, String first_dose, String second_dose, String booster, String last_shot, String wear_mask) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.vaccinated = vaccinated;
        this.wear_mask = wear_mask;
        this.DoB = DoB;
        this.SSN = SSN;
        this.first_dose = first_dose;
        this.second_dose = second_dose;
        this.booster = booster;
        this.last_shot = last_shot;
    }

    /**
     * get social security number
     *
     * @return returns ssn
     */
    public String getSSN() {
        return SSN;
    }

    /**
     * set social security number
     *
     * @param SSN
     */
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    /**
     * get date of birth
     *
     * @return return date of birth
     */
    public String getDoB() {
        return DoB;
    }

    /**
     * sets date of birth
     *
     * @param doB set date of birth
     */
    public void setDoB(String doB) {
        DoB = doB;
    }

    /**
     * get first name
     *
     * @return returns first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * sets first name
     *
     * @param first_name first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * gets last name
     *
     * @return return last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * sets last name
     *
     * @param last_name lastname is set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * get vaccinated data
     *
     * @return
     */
    public String getVaccinated() {
        return vaccinated;
    }

    /**
     * set vaccinated
     *
     * @param vaccinated sets vaccinated
     */
    public void setVaccinated(String vaccinated) {
        this.vaccinated = vaccinated;
    }

    /**
     * gets if individual wears masks
     *
     * @return returns if individual wears mask
     */
    public String getWear_mask() {
        return wear_mask;
    }

    /**
     * sets if individual wears masks
     *
     * @param wear_mask set wear mask
     */
    public void setWear_mask(String wear_mask) {
        this.wear_mask = wear_mask;
    }

    /**
     * gets first dose of vaccine
     *
     * @return returns first dose
     */
    public String getFirst_dose() {
        return first_dose;
    }

    /**
     * sets first does
     *
     * @param first_dose sets first dose
     */
    public void setFirst_dose(String first_dose) {
        this.first_dose = first_dose;
    }

    /**
     * gets second dose
     *
     * @return returns second does
     */
    public String getSecond_dose() {
        return second_dose;
    }

    /**
     * sets second does
     *
     * @param second_dose sets second dose
     */
    public void setSecond_dose(String second_dose) {
        this.second_dose = second_dose;
    }

    /**
     * gets Booster
     *
     * @return returns booster
     */
    public String getBooster() {
        return booster;
    }

    /**
     * sets Booster
     *
     * @param booster sets Booster
     */
    public void setBooster(String booster) {
        this.booster = booster;
    }

    /**
     * gets user name
     *
     * @return returns user name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * sets user name
     *
     * @param user_name sets user name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * gets password
     *
     * @return returns password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     *
     * @param password sets password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets first security answer
     *
     * @return returns first security answer
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * sets first answer
     *
     * @param answer1 sets first answer
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * gets second answer
     *
     * @return returns second security answer
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * sets security answer 2
     *
     * @param answer2 sets security answer 2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * gets last shot
     *
     * @return return last shot
     */
    public String getLast_shot() {
        return last_shot;
    }

    /**
     * sets last shot
     *
     * @param last_shot sets last shot
     */
    public void setLast_shot(String last_shot) {
        this.last_shot = last_shot;
    }
}
