package Domain;

/**
 * Created by AbelN on 15/04/2016.
 */
public interface CustomerInterface {

    long getId();
    CustomerAddress getCustomerAddress();
    PersonalInformation getPersonalInformation();
}
