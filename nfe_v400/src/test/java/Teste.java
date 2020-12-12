import br.com.tlmacedo.nfe.service.NFeAssinar;
import br.com.tlmacedo.nfe.service.NFeAutorizacao;
import br.com.tlmacedo.nfe.service.NFeRetAutorizacao;
import br.com.tlmacedo.nfe.service.NFev400;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Teste {

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><enviNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><idLote>000000000000184</idLote><indSinc>0</indSinc><NFe><infNFe versao=\"4.00\" Id=\"NFe13201108009246000136550010000008511202020114\"><ide><cUF>13</cUF><cNF>20202011</cNF><natOp>VENDA Dentro Do Estado</natOp><mod>55</mod><serie>1</serie><nNF>851</nNF><dhEmi>2020-11-24T11:28:00-04:00</dhEmi><dhSaiEnt>2020-11-24T11:28:00-04:00</dhSaiEnt><tpNF>1</tpNF><idDest>1</idDest><cMunFG>1302603</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>4</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>0</indFinal><indPres>3</indPres><procEmi>0</procEmi><verProc>2.02</verProc></ide><emit><CNPJ>08009246000136</CNPJ><xNome>T. L. MACEDO</xNome><xFant>CAFE PERFEITO</xFant><enderEmit><xLgr>R TREZE DE MAIO</xLgr><nro>159</nro><xBairro>COROADO</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69080440</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>92981686148</fone></enderEmit><IE>042171865</IE><CRT>3</CRT></emit><dest><CNPJ>18357854000195</CNPJ><xNome>RUTH MEIRE LIMA VALENTE (DOCE CAFE)</xNome><enderDest><xLgr>AVENIDA RODRIGO OTÁVIO</xLgr><nro>4050</nro><xCpl>BOX B</xCpl><xBairro>JAPIIM</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69077000</CEP><cPais>1058</cPais><xPais>BRASIL</xPais></enderDest><indIEDest>1</indIEDest><IE>054171288</IE></dest><det nItem=\"1\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>5.0000</qCom><vUnCom>43.0000000000</vUnCom><vProd>215.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>5.0000</qTrib><vUnTrib>43.0000000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vFCPUFDest>0.00</vFCPUFDest><vICMSUFDest>0.00</vICMSUFDest><vICMSUFRemet>0.00</vICMSUFRemet><vFCP>0.00</vFCP><vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet><vProd>215.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>215.00</vNF><vTotTrib>0.00</vTotTrib></ICMSTot></total><transp><modFrete>9</modFrete></transp><cobr><fat><nFat>1</nFat><vOrig>215.00</vOrig><vDesc>0.00</vDesc><vLiq>215.00</vLiq></fat></cobr><pag><detPag><indPag>1</indPag><tPag>99</tPag><vPag>215.00</vPag></detPag></pag><infAdic><infCpl>TOTAL R$ 215,00*T*DT. VENC.: DT. VENC.: 01/12/2020*N*DADOS BANCARIOS PARA TRANSFERENCIA*N*BANCO BANCO NUBANK AG: 0001 C/C: 90.846.088-3</infCpl></infAdic><infRespTec><CNPJ>08009246000136</CNPJ><xContato>THIAGO LEIROS MACEDO</xContato><email>tl.macedo@hotmail.com</email><fone>92981686148</fone></infRespTec></infNFe></NFe></enviNFe>";
        String xmlAssinado = "<enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><idLote>000000000000184</idLote><indSinc>0</indSinc><NFe><infNFe Id=\"NFe13201108009246000136550010000008511202020114\" versao=\"4.00\"><ide><cUF>13</cUF><cNF>20202011</cNF><natOp>VENDA Dentro Do Estado</natOp><mod>55</mod><serie>1</serie><nNF>851</nNF><dhEmi>2020-11-24T11:28:00-04:00</dhEmi><dhSaiEnt>2020-11-24T11:28:00-04:00</dhSaiEnt><tpNF>1</tpNF><idDest>1</idDest><cMunFG>1302603</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>4</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><indFinal>0</indFinal><indPres>3</indPres><procEmi>0</procEmi><verProc>2.02</verProc></ide><emit><CNPJ>08009246000136</CNPJ><xNome>T. L. MACEDO</xNome><xFant>CAFE PERFEITO</xFant><enderEmit><xLgr>R TREZE DE MAIO</xLgr><nro>159</nro><xBairro>COROADO</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69080440</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>92981686148</fone></enderEmit><IE>042171865</IE><CRT>3</CRT></emit><dest><CNPJ>18357854000195</CNPJ><xNome>RUTH MEIRE LIMA VALENTE (DOCE CAFE)</xNome><enderDest><xLgr>AVENIDA RODRIGO OTÁVIO</xLgr><nro>4050</nro><xCpl>BOX B</xCpl><xBairro>JAPIIM</xBairro><cMun>1302603</cMun><xMun>MANAUS</xMun><UF>AM</UF><CEP>69077000</CEP><cPais>1058</cPais><xPais>BRASIL</xPais></enderDest><indIEDest>1</indIEDest><IE>054171288</IE></dest><det nItem=\"1\"><prod><cProd>1137</cProd><cEAN>7898903647025</cEAN><xProd>CAFE SUPREMO ARABICA TRADICIONAL 1 KG</xProd><NCM>09011110</NCM><CEST>1709602</CEST><CFOP>5102</CFOP><uCom>PCT</uCom><qCom>5.0000</qCom><vUnCom>43.0000000000</vUnCom><vProd>215.00</vProd><cEANTrib>7898903647025</cEANTrib><uTrib>PCT</uTrib><qTrib>5.0000</qTrib><vUnTrib>43.0000000000</vUnTrib><indTot>1</indTot></prod><imposto><ICMS><ICMS60><orig>0</orig><CST>60</CST></ICMS60></ICMS><PIS><PISNT><CST>06</CST></PISNT></PIS><COFINS><COFINSNT><CST>06</CST></COFINSNT></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vFCPUFDest>0.00</vFCPUFDest><vICMSUFDest>0.00</vICMSUFDest><vICMSUFRemet>0.00</vICMSUFRemet><vFCP>0.00</vFCP><vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet><vProd>215.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>215.00</vNF><vTotTrib>0.00</vTotTrib></ICMSTot></total><transp><modFrete>9</modFrete></transp><cobr><fat><nFat>1</nFat><vOrig>215.00</vOrig><vDesc>0.00</vDesc><vLiq>215.00</vLiq></fat></cobr><pag><detPag><indPag>1</indPag><tPag>99</tPag><vPag>215.00</vPag></detPag></pag><infAdic><infCpl>TOTAL R$ 215,00*T*DT. VENC.: DT. VENC.: 01/12/2020*N*DADOS BANCARIOS PARA TRANSFERENCIA*N*BANCO BANCO NUBANK AG: 0001 C/C: 90.846.088-3</infCpl></infAdic><infRespTec><CNPJ>08009246000136</CNPJ><xContato>THIAGO LEIROS MACEDO</xContato><email>tl.macedo@hotmail.com</email><fone>92981686148</fone></infRespTec></infNFe><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe13201108009246000136550010000008511202020114\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>bLTaCOODkyc3jfLHfyKb+o91csY=</DigestValue></Reference></SignedInfo><SignatureValue>tdC3ySqZnEZ0JDEu7HBCQWq1spWB2fJTMDbJCXS9p2wFJFtxEAa737C8thhjER1pvYxfmKyS14lx&#13;\n" +
                "PxgzCtT8CNAH+kIYO9MfMOyz9fJKp1cJXH1oUZ/4+3HFZ0jET+XFBWhfgci7rJHowcXVU0nTyqST&#13;\n" +
                "UatEMU1Ni2jwE/SZGmgpGZeVblyVFhnFtfOnRIzNfKukNi/2T6dLWSaYC1PcY869e/NFqkBLJ8eR&#13;\n" +
                "gq2ufBqnh5k9HkKFzBf/XsTs7bqrhbXco1kN9N/+jqeUXGORBCcfQ6iDY7c8Z/VrhFqJEMZ5m7Zu&#13;\n" +
                "j9bYNml7w+/mUP2NXYkHw05InQ7LJbhLUsn74Q==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIH9jCCBd6gAwIBAgIILOIUUKlZVGEwDQYJKoZIhvcNAQELBQAwdTELMAkGA1UEBhMCQlIxEzAR&#13;\n" +
                "BgNVBAoMCklDUC1CcmFzaWwxNjA0BgNVBAsMLVNlY3JldGFyaWEgZGEgUmVjZWl0YSBGZWRlcmFs&#13;\n" +
                "IGRvIEJyYXNpbCAtIFJGQjEZMBcGA1UEAwwQQUMgU0VSQVNBIFJGQiB2NTAeFw0yMDA0MTYyMTAw&#13;\n" +
                "MDBaFw0yMzA0MTYyMTAwMDBaMIIBGjELMAkGA1UEBhMCQlIxCzAJBgNVBAgMAkFNMQ8wDQYDVQQH&#13;\n" +
                "DAZNQU5BVVMxEzARBgNVBAoMCklDUC1CcmFzaWwxGDAWBgNVBAsMDzAwMDAwMTAwOTU4MDgzMzE2&#13;\n" +
                "MDQGA1UECwwtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVyYWwgZG8gQnJhc2lsIC0gUkZCMRYw&#13;\n" +
                "FAYDVQQLDA1SRkIgZS1DTlBKIEEzMRkwFwYDVQQLDBBBQyBTRVJBU0EgUkZCIHY1MRcwFQYDVQQL&#13;\n" +
                "DA4wMTE0Njk1NzAwMDEwMzEWMBQGA1UECwwNQVIgVFJFVk9DSEVDSzEiMCAGA1UEAwwZVCBMIE1B&#13;\n" +
                "Q0VETzowODAwOTI0NjAwMDEzNjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALs8aZPR&#13;\n" +
                "eSRXREv9KovxzTwyttErAiPsgOYV6XG4ZpgsywK8HB15C8L2aw8X1LY5bx9xqMMCFWIxsjos5yJa&#13;\n" +
                "LlHrb5Urcr2+WHZ3gRLno2AbfGv6nRVyXCnuZaVwb3yCH70xDPyNsYnd/PcW4X7Bb6rqZ+kKj142&#13;\n" +
                "0A2PrHuynmvftPz8mHoLxhHCp0mJsQ4X8lMJTD57F3XUWgJDAwgxPSXNyUjq9d2fwigm46KU9Fdm&#13;\n" +
                "4TbysDrHiC5CybfAKEHewxQWKDyN1BBKCpwP41ooRC9rw/BemOwEIgvpUwTRX1LPBgb+bII9K0sC&#13;\n" +
                "7PH/4is6KlgHh4+bGDKMLpcOXaN4WcsCAwEAAaOCAuEwggLdMAkGA1UdEwQCMAAwHwYDVR0jBBgw&#13;\n" +
                "FoAU7PFBUVeo5jrpXrOgIvkIirU6h48wgZkGCCsGAQUFBwEBBIGMMIGJMEgGCCsGAQUFBzAChjxo&#13;\n" +
                "dHRwOi8vd3d3LmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvY2FkZWlhcy9zZXJhc2FyZmJ2NS5w&#13;\n" +
                "N2IwPQYIKwYBBQUHMAGGMWh0dHA6Ly9vY3NwLmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvc2Vy&#13;\n" +
                "YXNhcmZidjUwgbEGA1UdEQSBqTCBpoEVVEwuTUFDRURPQEhPVE1BSUwuQ09NoB8GBWBMAQMCoBYT&#13;\n" +
                "FFRISUFHTyBMRUlST1MgTUFDRURPoBkGBWBMAQMDoBATDjA4MDA5MjQ2MDAwMTM2oDgGBWBMAQME&#13;\n" +
                "oC8TLTAyMDQxOTgxNTIzMDk1NTAyMzAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMKAXBgVgTAED&#13;\n" +
                "B6AOEwwwMDAwMDAwMDAwMDAwcQYDVR0gBGowaDBmBgZgTAECAwowXDBaBggrBgEFBQcCARZOaHR0&#13;\n" +
                "cDovL3B1YmxpY2FjYW8uY2VydGlmaWNhZG9kaWdpdGFsLmNvbS5ici9yZXBvc2l0b3Jpby9kcGMv&#13;\n" +
                "ZGVjbGFyYWNhby1yZmIucGRmMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDCBnQYDVR0f&#13;\n" +
                "BIGVMIGSMEqgSKBGhkRodHRwOi8vd3d3LmNlcnRpZmljYWRvZGlnaXRhbC5jb20uYnIvcmVwb3Np&#13;\n" +
                "dG9yaW8vbGNyL3NlcmFzYXJmYnY1LmNybDBEoEKgQIY+aHR0cDovL2xjci5jZXJ0aWZpY2Fkb3Mu&#13;\n" +
                "Y29tLmJyL3JlcG9zaXRvcmlvL2xjci9zZXJhc2FyZmJ2NS5jcmwwHQYDVR0OBBYEFIIEYDiE2ogr&#13;\n" +
                "cC/XQd28cccn4ugOMA4GA1UdDwEB/wQEAwIF4DANBgkqhkiG9w0BAQsFAAOCAgEAghuyfpNLTb4H&#13;\n" +
                "tTSUjB5XyCNWlEwk8oDrBrm0QFVxpL7ekuCPYaltYkk2nap07RzyrIW+8ygL4P9P/9T4eX+pST6G&#13;\n" +
                "OS6EwrQN0IXRS8a05h60sbKEg0Yo0amFY7e/+4g8MfThUGiqlvif5XSOC0x/lQLZdiMDC1N9tqDP&#13;\n" +
                "zvfheW4MUtH7YnRNCbnKAP6kKHwiwzYoH0ZaoFUPoY/MEjE6xiJHahN6py79TSLSW3hXZq3zzWSs&#13;\n" +
                "tdLnm1zAVKmZLUsx6tM3dFnWz52f2V56j9par07uDqQBolebUZOFOgAJCiOCmuUjviDROnItkv5r&#13;\n" +
                "MfpZtaMVuKx2o2ay5wl6ebkXO4SmrIAmA4UuBrvY14lEBx2u9zX0IJddfddKHzN8GybHMcrJR601&#13;\n" +
                "tbMFFPusphX8Gj/ClnU94P0bvi8u4daJKQApZQr6lnwKZ3TWuNwpCPx6JhP+7BfSmbZEwDgiIYas&#13;\n" +
                "Wpe2d4HHhinzrIYEAgkcDJ72C6SJWDr7c8vBiXVxXVdmqykuMVg+8ANlsFnrd9MVxa65rY9Giwdy&#13;\n" +
                "6zcOBffaHLHcLR2nyCqkC6O8t2ZsKpR07fEXwy3loFKnujtpnVGMeGq7fKeXP5WSKAp4jeVOQfTl&#13;\n" +
                "VhoTC9XdnKE03w4VXVyGQ47tJETSKNd6FdnLq6bA25loHJrBVye2BXNyf97HTn0=</X509Certificate></X509Data></KeyInfo></Signature></NFe></enviNFe>";


        try {
            Locale LOCALE = new Locale("pt", "br");
            String LOCAL_TIME_ZONE = "America/Manaus";
            ZoneId MY_ZONE_TIME = ZoneId.of(LOCAL_TIME_ZONE);
            DateTimeFormatter DTF_MYSQL_DATAHORAFUSO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", LOCALE);
            NFev400 nFev400 = new NFev400(false, true);
            nFev400.errNoCertificado();
            nFev400.setXml(xml);
            nFev400.setXmlAssinado(new NFeAssinar(nFev400.getXml()).getXmlAssinado());
            nFev400.setXmlAutorizado(new NFeAutorizacao(nFev400.getXmlAssinado()).getXmlAutorizado());
            nFev400.setXmlRetAutorizacao(new NFeRetAutorizacao(nFev400.getXmlAutorizado()).getXmlRetornoAutorizacao());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
