/**
 * NfeRetAutorizacao4CallbackHandler.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package br.inf.portalfiscal.wsdl.nfe.prod.NfeRetAutorizacao4;


/**
 * NfeRetAutorizacao4CallbackHandler Callback class, Users can extend this class and implement
 * their own receiveResult and receiveError methods.
 */
public abstract class NfeRetAutorizacao4CallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     *
     * @param clientData Object mechanism by which the user can pass in user data
     *                   that will be avilable at the time this callback is called.
     */
    public NfeRetAutorizacao4CallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public NfeRetAutorizacao4CallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for nfeRetAutorizacaoLote method
     * override this method for handling normal response from nfeRetAutorizacaoLote operation
     */
    public void receiveResultnfeRetAutorizacaoLote(
            br.inf.portalfiscal.wsdl.nfe.prod.NfeRetAutorizacao4.NfeRetAutorizacao4Stub.NfeResultMsg result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from nfeRetAutorizacaoLote operation
     */
    public void receiveErrornfeRetAutorizacaoLote(java.lang.Exception e) {
    }
}
