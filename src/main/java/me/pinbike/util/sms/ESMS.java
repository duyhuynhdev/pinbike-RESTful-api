package me.pinbike.util.sms;

import me.pinbike.util.common.HttpRequester;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created by hpduy17 on 1/12/16.
 */
public class ESMS {
    private static final String address_prefix = "http://api.esms.vn/MainService.svc/xml/";
    private static final String method = "POST";
    private static final String apikey = "487A07F851B7105BCCE46515673EDF";
    private static final String secretkey = "AB4A56A110325B27DDDD5E2397540D";
    private String[] phoneNumber;
    private String content;
    private String apiName;
    private int smsType;
    private String requestId;
    //METHOD
    public static final String sendSMS = "SendMultipleMessage_V2";
    //SMS TYPE
    public static final int type_random_phone = 3;
    public static final int type_fixed_phone_19001534 = 4;
    public static final int type_CSKH = 6;

    public ESMS(String[] phoneNumber, String content, String apiName, int smsType, String requestId) {
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.apiName = apiName;
        this.smsType = smsType;
        this.requestId = requestId;
    }

    public void send() throws IOException, ParserConfigurationException, TransformerException {
        HttpRequester requester = new HttpRequester();
        String output = requester.call(address_prefix + apiName, buildData(), method, new HashMap<>());
        System.out.println(output);
    }

    public String buildData() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("RQST");
        doc.appendChild(rootElement);
        //add api
        Element apiKey = doc.createElement("APIKEY");
        apiKey.appendChild(doc.createTextNode(apikey));
        rootElement.appendChild(apiKey);
        //add secret key
        Element secretKey = doc.createElement("SECRETKEY");
        secretKey.appendChild(doc.createTextNode(secretkey));
        rootElement.appendChild(secretKey);
        //add is flash
        Element isFlash = doc.createElement("ISFLASH");
        isFlash.appendChild(doc.createTextNode("0"));
        rootElement.appendChild(isFlash);
        //add unicode
        Element unicode = doc.createElement("UNICODE");
        unicode.appendChild(doc.createTextNode("0"));
        rootElement.appendChild(unicode);
        //add sms type
        Element smstype = doc.createElement("SMSTYPE");
        smstype.appendChild(doc.createTextNode(smsType+""));
        rootElement.appendChild(smstype);
        //add requestId
        Element rqId = doc.createElement("REQUESTID");
        rqId.appendChild(doc.createTextNode(requestId));
        rootElement.appendChild(rqId);

        //add content
        Element ct = doc.createElement("CONTENT");
        ct.appendChild(doc.createTextNode(content));
        rootElement.appendChild(ct);

        //add contact
        Element contacts = doc.createElement("CONTACTS");
        for (String p : phoneNumber) {
            Element customer = doc.createElement("CUSTOMER");
            contacts.appendChild(customer);
            Element phone = doc.createElement("PHONE");
            phone.appendChild(doc.createTextNode(p));
            customer.appendChild(phone);
        }
        rootElement.appendChild(contacts);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.getBuffer().toString();
    }

}
