import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Sign {

    public static void main(String[] args) throws Exception {
        Security.setProperty("crypto.policy", "unlimited");
        Security.addProvider(new BouncyCastleProvider());
        CertificateFactory certFactory = CertificateFactory
                .getInstance("X.509", "BC");
        Signer signer = new Signer();
        Veryfier veryfier = new Veryfier();
        X509Certificate crtf = (X509Certificate) certFactory
                .generateCertificate(new FileInputStream("pCertificate.cer"));

        char[] ksPass = "password".toCharArray();
        char[] keyPass = "password".toCharArray();

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("private.p12"), ksPass);
        PrivateKey privateKey = (PrivateKey) ks.getKey("baeldung",
                keyPass);

        String inTxt = "test sign";
        byte[] r = inTxt.getBytes();
        byte[] sgnd = signer.DataSigner(r, crtf, privateKey);
        Boolean flag = veryfier.verifier(sgnd);
        System.out.println(flag);
    }




}
