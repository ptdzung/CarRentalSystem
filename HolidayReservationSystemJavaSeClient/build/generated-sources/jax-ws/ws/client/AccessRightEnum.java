
package ws.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accessRightEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="accessRightEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SALES"/>
 *     &lt;enumeration value="OPERATIONS"/>
 *     &lt;enumeration value="SERVICE"/>
 *     &lt;enumeration value="STANDARD"/>
 *     &lt;enumeration value="SYSADMIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "accessRightEnum")
@XmlEnum
public enum AccessRightEnum {

    SALES,
    OPERATIONS,
    SERVICE,
    STANDARD,
    SYSADMIN;

    public String value() {
        return name();
    }

    public static AccessRightEnum fromValue(String v) {
        return valueOf(v);
    }

}
