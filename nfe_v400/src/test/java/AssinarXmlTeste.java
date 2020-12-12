import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-06-05
 * Time: 20:32
 */

public class AssinarXmlTeste {

//    public static void main(String[] args) {
//                String fileNFe[] = new String[2];
//        fileNFe[0] = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito/src/main/resources/xml/nfe/out/NFe13190608009246000136550010000008671201906058.xml";
//        fileNFe[1] = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito/src/main/resources/xml/nfe/out/NFe13190608009246000136550010000008681201906055.xml";
//        //String xmlEnviNFe = ServiceXmlUtil.leXml(new FileInputStream(fileNFe[0]));
//        SignatureXML.signatureXML(fileNFe[0], "NFe13190608009246000136550010000008671201906058");
//    }

    private static class MyGuiCallbackHandler implements CallbackHandler {

        public MyGuiCallbackHandler() {
            System.out.println("Sending PIN from callback...");
        }

        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
            for (int i = 0; i < callbacks.length; i++) {
                PasswordCallback pc = (PasswordCallback) callbacks[i];
                String pin = System.getProperty("senhaDoCertificado");
                pc.setPassword(pin.toCharArray());
            }
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, InvalidAlgorithmParameterException, TransformerException, MarshalException, XMLSignatureException, KeyException, KeyStoreException, CertificateException, UnrecoverableEntryException, CertificateException {

        System.setProperty("senhaDoCertificado", "4879");

        String configName = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito_v2.02/src/main/resources/certificado/tokenSafeNet5100.cfg";

        Provider p = Security.getProvider("SunPKCS11");
        p = p.configure(configName);
        Security.addProvider(p);

        KeyStore.PasswordProtection pin = new KeyStore.PasswordProtection(System.getProperty("senhaDoCertificado").toCharArray());

        KeyStore.CallbackHandlerProtection chp =
                new KeyStore.CallbackHandlerProtection(new MyGuiCallbackHandler());
        KeyStore.Builder builder =
                KeyStore.Builder.newInstance("PKCS11", p, chp);

        KeyStore ks = builder.getKeyStore();

        String alias = null;
        PrivateKey privateKey = null;
        KeyStore.PrivateKeyEntry pkEntry = null;
        Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            alias = aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, pin);
                privateKey = pkEntry.getPrivateKey();
                break;
            }
        }

        Certificate cert = ks.getCertificate(alias);
//        PublicKey publicKey = cert.getPublicKey();
//        privateKey = privateKeyEntry.getPrivateKey();
//        KeyPair pair = new KeyPair(publicKey, privateKey);

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        String fileNFe[] = new String[2];
        fileNFe[0] = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito_v1.03/src/main/resources/xml/nfe/out/NFe13190608009246000136550010000008671201906058.xml";
        fileNFe[1] = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito_v1.03/src/main/resources/xml/nfe/out/NFe13190608009246000136550010000008681201906055.xml";
//        //String xmlEnviNFe = ServiceXmlUtil.leXml(new FileInputStream(fileNFe[0]));


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);

        Document doc = null;
        try (FileInputStream fis = new FileInputStream(fileNFe[0])) {
            doc = dbf.newDocumentBuilder().parse(fis);
        }

        DOMSignContext dsc = new DOMSignContext
                (pair.getPrivate(), doc.getDocumentElement());

        Element el = (Element) doc.getElementsByTagName("infNFe").item(0);
        el.setIdAttribute("Id", true);

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        Reference ref = fac.newReference
                ("#" + el.getAttribute("Id"), fac.newDigestMethod(DigestMethod.SHA1, null),
                        List.of
                                (fac.newTransform
                                        (Transform.ENVELOPED, (TransformParameterSpec) null)),
                        null, null);

        SignedInfo si = fac.newSignedInfo
                (fac.newCanonicalizationMethod
                                (CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS,
                                        (C14NMethodParameterSpec) null),
                        fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                        List.of(ref));

        KeyInfoFactory kif = fac.getKeyInfoFactory();

        KeyValue kv = kif.newKeyValue(pair.getPublic());
        KeyInfo ki = kif.newKeyInfo(List.of(kv));

        XMLSignature signature = fac.newXMLSignature(si, ki);

        signature.sign(dsc);

        OutputStream os;
        if (args.length > 1) {
            os = new FileOutputStream(args[1]);
        } else {
            os = System.out;
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
    }

//    private static CallbackHandler callbackHandler = new CallbackHandler() {
//        @Override
//        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
//            for (Callback callback : callbacks)
//                if (callback instanceof PasswordCallback) {
//                    PasswordCallback passwordCallback = (PasswordCallback) callback;
//                    System.out.println(passwordCallback.getPrompt());
//                    passwordCallback.setPassword(System.getProperty("senhaDoCertificado").toCharArray());
//                }
//
//        }
//    };
//
//    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
//        for (Callback callback : callbacks)
//            if (callback instanceof PasswordCallback) {
//
//            }
//    }

}
