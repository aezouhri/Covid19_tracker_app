/**
 * @author Adnane Ezouhri
 * @author Rida Errachidi
 * @author Ben DeSollar
 */
public class Governments {
    /**
     * government name
     */
    private String government_name;
    /**
     * location
     */
    private String location;
    /**
     * mask mandate (yes or no)
     */
    private String mask_mandate;
    /**
     * vaccination required (yes or no)
     */
    private String vaccination; // yes or no
    /**
     * is covid test required (yes or no)
     */
    private String test_entry;
    /**
     * quarantine required (yes or no)
     */
    private String quarantine;
    /**
     * last vaccine time
     */
    private String last_vaccine;
    /**
     * quarantine time
     */
    private String quarantine_time;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * security answer 1
     */
    private String answer1;
    /**
     * security answer 2
     */
    private String answer2;


    /**
     * Governments constructor
     * @param government_name government name
     * @param location government location (Country
     * @param maskMandate is mask mandated
     * @param vaccination is vaccination required
     * @param last_vaccine last vaccine
     * @param test_entry is covid test required
     * @param quarantine is quarantine required for entry
     * @param quarantine_time length of quarantine for government
     * @param username username
     * @param password password
     * @param answer1 security answer 1
     * @param answer2 security answer 2
     */
    public Governments(String government_name, String location, String maskMandate, String vaccination,String last_vaccine, String test_entry, String quarantine, String quarantine_time, String username, String password, String answer1, String answer2) {
        this.government_name = government_name;
        this.location = location;
        this.mask_mandate = maskMandate;
        this.vaccination = vaccination;
        this.last_vaccine=last_vaccine;
        this.test_entry = test_entry;
        this.quarantine = quarantine;
        this.quarantine_time = quarantine_time;
        this.username = username;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    /**
     * Governments constructor
     * @param government_name government name
     * @param location government location (Country
     * @param mask_mandate is mask mandated
     * @param vaccination is vaccination required
     * @param last_vaccine last vaccine
     * @param test_entry is covid test required
     * @param quarantine is quarantine required for entry
     * @param quarantine_time length of quarantine for government
     */
    public Governments(String government_name, String location, String mask_mandate, String vaccination,String last_vaccine ,String test_entry, String quarantine, String quarantine_time) {
        this.government_name = government_name;
        this.location = location;
        this.mask_mandate = mask_mandate;
        this.vaccination = vaccination;
        this.last_vaccine=last_vaccine;
        this.test_entry = test_entry;
        this.quarantine = quarantine;
        this.quarantine_time = quarantine_time;
    }

    /**
     * get government name
     * @return returns government name
     */
    public String getGovernment_name() {
        return government_name;
    }

    /**
     * sets government name
     * @param government_name input government name
     */
    public void setGovernment_name(String government_name) {
        this.government_name = government_name;
    }

    /**
     * gets location
     * @return returns location
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets location
     * @param location input government location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * gets if mask is mandated
     * @return returns mask mandate
     */
    public String getMask_mandate() {
        return mask_mandate;
    }

    /**
     * sets mask mandate
     * @param mask_mandate inputs yes or no string
     */
    public void setMask_mandate(String mask_mandate) {
        this.mask_mandate = mask_mandate;
    }

    /**
     * gets vaccination
     * @return returns vaccination requirement
     */
    public String getVaccination() {
        return vaccination;
    }

    /**
     * sets vaccination
     * @param vaccination input vaccination string
     */
    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    /**
     * get covid test entry
     * @return returns covid test
     */
    public String getTest_entry() {
        return test_entry;
    }

    /**
     * sets covid test entry
     * @param test_entry sets covid test entry
     */
    public void setTest_entry(String test_entry) {
        this.test_entry = test_entry;
    }

    /**
     * gets quarantine
     * @return returns quarantine requirement
     */
    public String getQuarantine() {
        return quarantine;
    }

    /**
     * sets if government quarantines
     * @param quarantine inputs quarantine
     */
    public void setQuarantine(String quarantine) {
        this.quarantine = quarantine;
    }

    /**
     * sets length of quarantine
     * @return returns length of quarantine
     */
    public String getQuarantine_time() {
        return quarantine_time;
    }

    /**
     * sets length of quarantine
     * @param quarantine_time input length of quarantine
     */
    public void setQuarantine_time(String quarantine_time) {
        this.quarantine_time = quarantine_time;
    }

    /**
     * get username
     * @return returns user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     * @param username input username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets passwords
     * @return returns password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password input password string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get security answer 1
     * @return return security answer 1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * sets security answer 1
     * @param answer1 input security answer  1
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * get security answer 2
     * @return return security answer 2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * sets security answer 2
     * @param answer2 input security answer 2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * gets last vaccine time
     * @return returns last vaccine time
     */
    public String getLast_vaccine() {
        return last_vaccine;
    }

    /**
     * sets last vaccine time
     * @param last_vaccine inputs last vaccine time
     */
    public void setLast_vaccine(String last_vaccine) {
        this.last_vaccine = last_vaccine;
    }
}
