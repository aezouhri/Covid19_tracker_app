/**
 * @author Ben DeSollar
 * @author Rida Errachidi
 * @author Adnane Ezouhri
 * Organization object is what we use to register a new organization and log in with a registered organization
 */
public class Organization {

    /**
     * organization's name
     */
    private String organization_name;
    /**
     * organization's website link
     */
    private String website_link;
    /**
     * organization's phone number
     */
    private String phone_number;
    /**
     * organization's location
     */
    private String location;
    /**
     * organization vaccination requirement
     */
    private String vaccine_required;
    /**
     * does organization require mask (yes or no)
     */
    private String mask_required;
    /**
     * is test required for vaccination
     */
    private String test_required;
    /**
     * organization user name
     */
    private String username;
    /**
     * organization password
     */
    private String password;
    /**
     * security answer 1 for organization
     */
    private String answer1;
    /**
     * security answer 2 for organization
     */
    private String answer2;

    /**
     * empty constructor for empty inputs
     */
    public Organization() {
    }

    ;

    /**
     * Second Organization constructor for more input parameters
     *
     * @param organization_name name of organization
     * @param website_link      link of organization website
     * @param phone_number      Organization's phone number
     * @param location          Organization's location
     * @param vaccine_required  does the organization require a vaccine
     * @param mask_required     does the organization require a mask
     * @param test_required     does the organization require a test
     */
    public Organization(String organization_name, String website_link, String location, String phone_number, String vaccine_required, String mask_required, String test_required) {
        this.organization_name = organization_name;
        this.website_link = website_link;
        this.phone_number = phone_number;
        this.location = location;
        this.vaccine_required = vaccine_required;
        this.mask_required = mask_required;
        this.test_required = test_required;
        this.username = username;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    /**
     * Organization constructor sets registration input parameters into respective variables
     *
     * @param organization_name name of organization
     * @param website_link      link of organization's website
     * @param phone_number      organization phone number
     * @param location          location of organization
     * @param vaccine_required  does the organization require a vaccine to use/enter
     * @param mask_required     does the organization require a mask to enter
     * @param test_required     does the organization require a covid test to use/enter
     * @param username          organization account username
     * @param password          organization account password
     * @param answer1           organization security question answer 1
     * @param answer2           organization security questions answer 2
     */
    public Organization(String organization_name, String website_link, String location, String phone_number, String vaccine_required, String mask_required, String test_required, String username, String password, String answer1, String answer2) {
        this.organization_name = organization_name;
        this.website_link = website_link;
        this.phone_number = phone_number;
        this.location = location;
        this.vaccine_required = vaccine_required;
        this.mask_required = mask_required;
        this.test_required = test_required;
        this.username = username;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }


    /**
     * get Organization name
     *
     * @return returns organization string name
     */
    public String getOrganization_name() {
        return organization_name;
    }

    /**
     * sets organizations name when registering
     *
     * @param organization_name sets organization name
     */
    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    /**
     * gets website link
     *
     * @return returns website link
     */
    public String getWebsite_link() {
        return website_link;
    }

    /**
     * sets website link
     *
     * @param website_link sets website link
     */
    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }

    /**
     * gets Phone number
     *
     * @return returns phone number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * gets the location
     *
     * @return location of organization
     */
    public String getLocation() {
        return location;
    }

    /**
     * gets the vaccination requirement for that organization
     *
     * @return he vaccination requirement for that organization
     */
    public String getVaccine_required() {
        return vaccine_required;
    }

    /**
     * gets the mask requirement for that organization
     *
     * @return gets the mask requirement for that organization
     */
    public String getMask_required() {
        return mask_required;
    }

    /**
     * gets the test requirement for that organization
     *
     * @return gets the test requirement for that organization
     */
    public String getTest_required() {
        return test_required;
    }

    /**
     * gets the username for that organization
     *
     * @return the username for that organization
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the username for the organization
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets the password for that organization
     *
     * @return the password for that organization
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password for that organization
     *
     * @param password the password for that organization
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets the first security question for that organization
     *
     * @return the first security question for that organization
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * gets the second security question for that organization
     *
     * @return the second security question for that organization
     */
    public String getAnswer2() {
        return answer2;
    }

}



