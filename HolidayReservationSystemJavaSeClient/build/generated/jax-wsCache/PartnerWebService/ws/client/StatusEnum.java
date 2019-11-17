
package ws.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statusEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="statusEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AVAILABLE"/>
 *     &lt;enumeration value="RENTED"/>
 *     &lt;enumeration value="REPAIR"/>
 *     &lt;enumeration value="DISABLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "statusEnum")
@XmlEnum
public enum StatusEnum {

    AVAILABLE,
    RENTED,
    REPAIR,
    DISABLED;

    public String value() {
        return name();
    }

    public static StatusEnum fromValue(String v) {
        return valueOf(v);
    }

}
