public class A_Teste {

    public static void main(String[] args) {
        new A_Teste();
    }

    public A_Teste() {
        String nameFile = "NFe13201208009246000136550010000002051202020120";
        String pathFile;
        pathFile = String.format("%s/cafeperfeito_v%s%s%s.xml",
                System.getProperty("user.dir"),
                System.getProperty("pathNFeSaveXmlOut", "/src/main/resources/xml/nfe/out/"),
                nameFile);
        System.out.printf("user.dir: %s\n", pathFile);

        pathFile = String.format("%s%s%s.xml",
                getClass().getProtectionDomain().getCodeSource().getLocation(),
                //A_Teste.class.getProtectionDomain().getCodeSource().getLocation(),
                //System.getProperty("user.dir"),
                System.getProperty("pathNFeSaveXmlOut", "xml/nfe/out/"),
                nameFile);
        System.out.printf("getSimpleName: %s\n", pathFile);

    }
}
