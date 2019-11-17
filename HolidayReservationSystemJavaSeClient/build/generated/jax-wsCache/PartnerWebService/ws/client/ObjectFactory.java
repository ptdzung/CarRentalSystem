
package ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CancelReservationResponse_QNAME = new QName("http://ws.session.ejb/", "cancelReservationResponse");
    private final static QName _PartnerLogin_QNAME = new QName("http://ws.session.ejb/", "partnerLogin");
    private final static QName _RetrieveRentalRecordsByPartnerResponse_QNAME = new QName("http://ws.session.ejb/", "retrieveRentalRecordsByPartnerResponse");
    private final static QName _RentalRecordNotFoundException_QNAME = new QName("http://ws.session.ejb/", "RentalRecordNotFoundException");
    private final static QName _EntityMismatchException_QNAME = new QName("http://ws.session.ejb/", "EntityMismatchException");
    private final static QName _InvalidLoginCredentialException_QNAME = new QName("http://ws.session.ejb/", "InvalidLoginCredentialException");
    private final static QName _RetrievePartnerReservationDetailsResponse_QNAME = new QName("http://ws.session.ejb/", "retrievePartnerReservationDetailsResponse");
    private final static QName _RetrievePartnerReservationDetails_QNAME = new QName("http://ws.session.ejb/", "retrievePartnerReservationDetails");
    private final static QName _PartnerLoginResponse_QNAME = new QName("http://ws.session.ejb/", "partnerLoginResponse");
    private final static QName _CancelReservation_QNAME = new QName("http://ws.session.ejb/", "cancelReservation");
    private final static QName _RetrieveRentalRecordsByPartner_QNAME = new QName("http://ws.session.ejb/", "retrieveRentalRecordsByPartner");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidLoginCredentialException }
     * 
     */
    public InvalidLoginCredentialException createInvalidLoginCredentialException() {
        return new InvalidLoginCredentialException();
    }

    /**
     * Create an instance of {@link RetrievePartnerReservationDetailsResponse }
     * 
     */
    public RetrievePartnerReservationDetailsResponse createRetrievePartnerReservationDetailsResponse() {
        return new RetrievePartnerReservationDetailsResponse();
    }

    /**
     * Create an instance of {@link RetrievePartnerReservationDetails }
     * 
     */
    public RetrievePartnerReservationDetails createRetrievePartnerReservationDetails() {
        return new RetrievePartnerReservationDetails();
    }

    /**
     * Create an instance of {@link PartnerLoginResponse }
     * 
     */
    public PartnerLoginResponse createPartnerLoginResponse() {
        return new PartnerLoginResponse();
    }

    /**
     * Create an instance of {@link CancelReservation }
     * 
     */
    public CancelReservation createCancelReservation() {
        return new CancelReservation();
    }

    /**
     * Create an instance of {@link RetrieveRentalRecordsByPartner }
     * 
     */
    public RetrieveRentalRecordsByPartner createRetrieveRentalRecordsByPartner() {
        return new RetrieveRentalRecordsByPartner();
    }

    /**
     * Create an instance of {@link CancelReservationResponse }
     * 
     */
    public CancelReservationResponse createCancelReservationResponse() {
        return new CancelReservationResponse();
    }

    /**
     * Create an instance of {@link PartnerLogin }
     * 
     */
    public PartnerLogin createPartnerLogin() {
        return new PartnerLogin();
    }

    /**
     * Create an instance of {@link RetrieveRentalRecordsByPartnerResponse }
     * 
     */
    public RetrieveRentalRecordsByPartnerResponse createRetrieveRentalRecordsByPartnerResponse() {
        return new RetrieveRentalRecordsByPartnerResponse();
    }

    /**
     * Create an instance of {@link RentalRecordNotFoundException }
     * 
     */
    public RentalRecordNotFoundException createRentalRecordNotFoundException() {
        return new RentalRecordNotFoundException();
    }

    /**
     * Create an instance of {@link EntityMismatchException }
     * 
     */
    public EntityMismatchException createEntityMismatchException() {
        return new EntityMismatchException();
    }

    /**
     * Create an instance of {@link RentalDayEntity }
     * 
     */
    public RentalDayEntity createRentalDayEntity() {
        return new RentalDayEntity();
    }

    /**
     * Create an instance of {@link PartnerEntity }
     * 
     */
    public PartnerEntity createPartnerEntity() {
        return new PartnerEntity();
    }

    /**
     * Create an instance of {@link CarEntity }
     * 
     */
    public CarEntity createCarEntity() {
        return new CarEntity();
    }

    /**
     * Create an instance of {@link CarModelEntity }
     * 
     */
    public CarModelEntity createCarModelEntity() {
        return new CarModelEntity();
    }

    /**
     * Create an instance of {@link TravelDispatchRecordEntity }
     * 
     */
    public TravelDispatchRecordEntity createTravelDispatchRecordEntity() {
        return new TravelDispatchRecordEntity();
    }

    /**
     * Create an instance of {@link RentalRecordEntity }
     * 
     */
    public RentalRecordEntity createRentalRecordEntity() {
        return new RentalRecordEntity();
    }

    /**
     * Create an instance of {@link EmployeeEntity }
     * 
     */
    public EmployeeEntity createEmployeeEntity() {
        return new EmployeeEntity();
    }

    /**
     * Create an instance of {@link OutletEntity }
     * 
     */
    public OutletEntity createOutletEntity() {
        return new OutletEntity();
    }

    /**
     * Create an instance of {@link CustomerEntity }
     * 
     */
    public CustomerEntity createCustomerEntity() {
        return new CustomerEntity();
    }

    /**
     * Create an instance of {@link RentalRateEntity }
     * 
     */
    public RentalRateEntity createRentalRateEntity() {
        return new RentalRateEntity();
    }

    /**
     * Create an instance of {@link CarCategoryEntity }
     * 
     */
    public CarCategoryEntity createCarCategoryEntity() {
        return new CarCategoryEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "cancelReservationResponse")
    public JAXBElement<CancelReservationResponse> createCancelReservationResponse(CancelReservationResponse value) {
        return new JAXBElement<CancelReservationResponse>(_CancelReservationResponse_QNAME, CancelReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "partnerLogin")
    public JAXBElement<PartnerLogin> createPartnerLogin(PartnerLogin value) {
        return new JAXBElement<PartnerLogin>(_PartnerLogin_QNAME, PartnerLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveRentalRecordsByPartnerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveRentalRecordsByPartnerResponse")
    public JAXBElement<RetrieveRentalRecordsByPartnerResponse> createRetrieveRentalRecordsByPartnerResponse(RetrieveRentalRecordsByPartnerResponse value) {
        return new JAXBElement<RetrieveRentalRecordsByPartnerResponse>(_RetrieveRentalRecordsByPartnerResponse_QNAME, RetrieveRentalRecordsByPartnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentalRecordNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "RentalRecordNotFoundException")
    public JAXBElement<RentalRecordNotFoundException> createRentalRecordNotFoundException(RentalRecordNotFoundException value) {
        return new JAXBElement<RentalRecordNotFoundException>(_RentalRecordNotFoundException_QNAME, RentalRecordNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityMismatchException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "EntityMismatchException")
    public JAXBElement<EntityMismatchException> createEntityMismatchException(EntityMismatchException value) {
        return new JAXBElement<EntityMismatchException>(_EntityMismatchException_QNAME, EntityMismatchException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidLoginCredentialException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "InvalidLoginCredentialException")
    public JAXBElement<InvalidLoginCredentialException> createInvalidLoginCredentialException(InvalidLoginCredentialException value) {
        return new JAXBElement<InvalidLoginCredentialException>(_InvalidLoginCredentialException_QNAME, InvalidLoginCredentialException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrievePartnerReservationDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrievePartnerReservationDetailsResponse")
    public JAXBElement<RetrievePartnerReservationDetailsResponse> createRetrievePartnerReservationDetailsResponse(RetrievePartnerReservationDetailsResponse value) {
        return new JAXBElement<RetrievePartnerReservationDetailsResponse>(_RetrievePartnerReservationDetailsResponse_QNAME, RetrievePartnerReservationDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrievePartnerReservationDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrievePartnerReservationDetails")
    public JAXBElement<RetrievePartnerReservationDetails> createRetrievePartnerReservationDetails(RetrievePartnerReservationDetails value) {
        return new JAXBElement<RetrievePartnerReservationDetails>(_RetrievePartnerReservationDetails_QNAME, RetrievePartnerReservationDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "partnerLoginResponse")
    public JAXBElement<PartnerLoginResponse> createPartnerLoginResponse(PartnerLoginResponse value) {
        return new JAXBElement<PartnerLoginResponse>(_PartnerLoginResponse_QNAME, PartnerLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "cancelReservation")
    public JAXBElement<CancelReservation> createCancelReservation(CancelReservation value) {
        return new JAXBElement<CancelReservation>(_CancelReservation_QNAME, CancelReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveRentalRecordsByPartner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "retrieveRentalRecordsByPartner")
    public JAXBElement<RetrieveRentalRecordsByPartner> createRetrieveRentalRecordsByPartner(RetrieveRentalRecordsByPartner value) {
        return new JAXBElement<RetrieveRentalRecordsByPartner>(_RetrieveRentalRecordsByPartner_QNAME, RetrieveRentalRecordsByPartner.class, null, value);
    }

}
