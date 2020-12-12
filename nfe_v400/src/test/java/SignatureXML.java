import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe respons�vel pela assinatura do XML.
 * <p>
 * Este classe e respons�vel em recuperar o certificado e assina o xml.
 *
 * @author Pitbull
 */
public class SignatureXML {

    private static Logger LOG = Logger.getLogger(SignatureXML.class);
    private static KeyStore ks;

    /**
     * Este m�todo recupera o certificado e assina o xml (String).
     *
     * @param xml          XML da nota fiscal eletr�nica.
     * @param uriReference Identificado �nico da notafiscal eletr�nica.
     * @return XML assinado.
     */
    public static String signatureXML(String xml, String uriReference) {

        try {


            String filePath = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito/src/main/resources/certificado/tokenSafeNet5100.cfg";
            String password = System.getProperty("senhaDoCertificado");
            String keystoretype;// = configuracaoEntity.getKeyStoreType();

            InputStream filePFX = new FileInputStream(filePath);

            // Create a DOM XMLSignatureFactory that will be used to  
            // generate the enveloped signature.  
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a Reference to the enveloped document (in this case,  
            // you are signing the whole document, so a URI of "" signifies  
            // that, and also specify the SHA1 digest algorithm and  
            // the ENVELOPED Transform.  

            List<Transform> transforms = new ArrayList<Transform>();
            transforms.add(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
            transforms.add(fac.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (TransformParameterSpec) null));

            Reference ref = fac.newReference
                    ("#" + uriReference, fac.newDigestMethod(DigestMethod.SHA1, null),
                            transforms,
                            null, null);

            // Create the SignedInfo.  
            SignedInfo si = fac.newSignedInfo
                    (fac.newCanonicalizationMethod
                                    (CanonicalizationMethod.INCLUSIVE,
                                            (C14NMethodParameterSpec) null),
                            fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                            Collections.singletonList(ref));

            // Code Sample 2  


            Provider p = null;
//            if ("PKCS12".equals(keystoretype.toUpperCase())) {
//                try{
//                    System.out.println(filePath);
//                    //   p = new sun.security.pkcs11.SunPKCS11(filePath);
//
//                }catch(Exception e){
//                    System.out.println(e);
//                }
//                System.out.println("Passo aqui");
//                //Security.addProvider(p);
//
//                //    ks = KeyStore.getInstance(keystoretype,p);
//                ks = KeyStore.getInstance(keystoretype);
//                ks.load(filePFX, password.toCharArray());
//
//            } else {

            //ks=new KeyStoreFactory().instanceOfA3(filePath,password);
            // ks.load(null, password.toCharArray());
//            }

            String aliasStr = (String) ks.aliases().nextElement();
            KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(aliasStr, new KeyStore.PasswordProtection(password.toCharArray()));

            X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

            // Create the KeyInfo containing the X509Data.  
            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List<X509Certificate> x509Content = new ArrayList<X509Certificate>();
            x509Content.add(cert);
            X509Data xd = kif.newX509Data(x509Content);
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

            // Code Sample 3  

            // Instantiate the document to be signed.  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));//"UTF-8")));//FileInputStream("E:/exemplo.xml"));  

            // Create a DOMSignContext and specify the RSA PrivateKey and  
            // location of the resulting XMLSignature's parent element.  
            DOMSignContext dsc = new DOMSignContext
                    (keyEntry.getPrivateKey(), doc.getDocumentElement());

            // Create the XMLSignature, but don't sign it yet.  
            XMLSignature signature = fac.newXMLSignature(si, ki);

            // Marshal, generate, and sign the enveloped signature.  
            signature.sign(dsc);

            // Code example 4  

            // Output the resulting document.  
            OutputStream os = new ByteArrayOutputStream();//new FileOutputStream("E:/signedPurchaseOrder2.xml");  
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.transform(new DOMSource(doc), new StreamResult(os));

            String retorno = os.toString();
            return retorno;


            //ks.deleteEntry(aliasStr);  
        } catch (Throwable e) {
            System.out.println(e);
            ///JOptionPane.showMessageDialog(null,e.toString(),"Erro na assinatura",JOptionPane.ERROR_MESSAGE);
            LOG.fatal("Erro na assinatura do XML", e);
            throw new RuntimeException("Erro na assinatura do XML", e);

        }

    }

} 