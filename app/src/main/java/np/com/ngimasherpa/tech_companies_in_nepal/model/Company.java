package np.com.ngimasherpa.tech_companies_in_nepal.model;

/**
 * Created by ngima on 9/19/17.
 */

public class Company {
    public String name;
    public String website;
    public String location;
    public String description;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
