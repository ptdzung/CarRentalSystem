
package ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PartnerWebService", targetNamespace = "http://ws.session.ejb/", wsdlLocation = "http://localhost:8080/PartnerWebService/PartnerWebService?wsdl")
public class PartnerWebService_Service
    extends Service
{

    private final static URL PARTNERWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException PARTNERWEBSERVICE_EXCEPTION;
    private final static QName PARTNERWEBSERVICE_QNAME = new QName("http://ws.session.ejb/", "PartnerWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/PartnerWebService/PartnerWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PARTNERWEBSERVICE_WSDL_LOCATION = url;
        PARTNERWEBSERVICE_EXCEPTION = e;
    }

    public PartnerWebService_Service() {
        super(__getWsdlLocation(), PARTNERWEBSERVICE_QNAME);
    }

    public PartnerWebService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), PARTNERWEBSERVICE_QNAME, features);
    }

    public PartnerWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, PARTNERWEBSERVICE_QNAME);
    }

    public PartnerWebService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PARTNERWEBSERVICE_QNAME, features);
    }

    public PartnerWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PartnerWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PartnerWebService
     */
    @WebEndpoint(name = "PartnerWebServicePort")
    public PartnerWebService getPartnerWebServicePort() {
        return super.getPort(new QName("http://ws.session.ejb/", "PartnerWebServicePort"), PartnerWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PartnerWebService
     */
    @WebEndpoint(name = "PartnerWebServicePort")
    public PartnerWebService getPartnerWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.session.ejb/", "PartnerWebServicePort"), PartnerWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PARTNERWEBSERVICE_EXCEPTION!= null) {
            throw PARTNERWEBSERVICE_EXCEPTION;
        }
        return PARTNERWEBSERVICE_WSDL_LOCATION;
    }

}
