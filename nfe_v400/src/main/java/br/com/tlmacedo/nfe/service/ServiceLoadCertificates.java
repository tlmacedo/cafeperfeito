package br.com.tlmacedo.nfe.service;

import org.apache.commons.httpclient.protocol.Protocol;

import javax.security.auth.callback.PasswordCallback;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ServiceLoadCertificates {

    private PrivateKey privateKey;
    private X509Certificate x509Certificate;
    private KeyInfo keyInfo;
    private static char[] PIN = System.getProperty("senhaDoCertificado", "4879").toCharArray();

    private Provider p = Security.getProvider("SunPKCS11");
    private KeyStore ks;
    private KeyStore.PrivateKeyEntry pkEntry = null;
    private KeyStore.Builder builder;
    private String alias;

    public ServiceLoadCertificates() {
        String configName = getClass().getClassLoader().getResource("certificados/" + System.getProperty("nfeTokenUtilizado", "tokenSafeNet5100.cfg")).getPath();

        System.out.printf("configName:[%s]\n", configName);

        setP(getP().configure(configName));
        Security.addProvider(getP());

    }

    public void loadToken() throws KeyStoreException, UnrecoverableEntryException, NoSuchAlgorithmException {
        System.out.printf("carregandoToke...\n");
        KeyStore.CallbackHandlerProtection chp =
                new KeyStore.CallbackHandlerProtection(callbacks -> {
                    for (int i = 0; i < callbacks.length; i++) {
                        PasswordCallback pc = (PasswordCallback) callbacks[i];
                        pc.setPassword(PIN);
                    }
                });
        setBuilder(KeyStore.Builder.newInstance("PKCS11", getP(), chp));

        setKs(getBuilder().getKeyStore());

        Enumeration<String> aliasesEnum = getKs().aliases();
        while (aliasesEnum.hasMoreElements()) {
            setAlias(aliasesEnum.nextElement());
            if (getKs().isKeyEntry(getAlias())) {
                setPkEntry((KeyStore.PrivateKeyEntry) getKs().getEntry(getAlias(),
                        new KeyStore.PasswordProtection(PIN)));
                setPrivateKey(getPkEntry().getPrivateKey());
                break;
            }
            System.out.printf("getAliases: %s\n", getAlias());
        }

        setX509Certificate((X509Certificate) getPkEntry().getCertificate());
        loadSocketDinamico();

    }

    public void setKeyInfo(XMLSignatureFactory signatureFactory) {
        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
        List<X509Certificate> x509Content = new ArrayList<X509Certificate>();

        x509Content.add(getX509Certificate());
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);

        setKeyInfo(keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data)));

    }

    public void loadSocketDinamico() {
        System.out.printf("iniciandoSocketDinamico\n");
        ServiceSocketFactoryDinamico socketFactoryDinamico = new ServiceSocketFactoryDinamico(getX509Certificate(), getPrivateKey());
        socketFactoryDinamico.setFileCacerts(getClass().getClassLoader().getResource("certificados/NFeCacerts").getPath());

        Protocol protocol = new Protocol("https", socketFactoryDinamico, 443);
        Protocol.registerProtocol("https", protocol);
        System.out.printf("carregouSocketDinamico\n");

    }

    /**
     * Begin Getters an Setters
     */

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(X509Certificate x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }

    public Provider getP() {
        return p;
    }

    public void setP(Provider p) {
        this.p = p;
    }

    public KeyStore getKs() {
        return ks;
    }

    public void setKs(KeyStore ks) {
        this.ks = ks;
    }

    public KeyStore.PrivateKeyEntry getPkEntry() {
        return pkEntry;
    }

    public void setPkEntry(KeyStore.PrivateKeyEntry pkEntry) {
        this.pkEntry = pkEntry;
    }

    public KeyStore.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(KeyStore.Builder builder) {
        this.builder = builder;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * END Getters an Setters
     */
}
