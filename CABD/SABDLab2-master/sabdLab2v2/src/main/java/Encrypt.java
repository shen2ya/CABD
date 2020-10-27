import org.bouncycastle.cms.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Encrypt {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, UnrecoverableKeyException, CMSException {
        Security.setProperty("crypto.policy", "unlimited");
        Security.addProvider(new BouncyCastleProvider());
        CertificateFactory crtFctr = CertificateFactory
                .getInstance("X.509", "BC");

        X509Certificate crtf = (X509Certificate) crtFctr
                .generateCertificate(new FileInputStream("pCertificate.cer"));

        char[] keystorePassword = "password".toCharArray();
        char[] keyPassword = "password".toCharArray();
        Encryptor encryptor = new Encryptor();
        Decryptor decryptor = new Decryptor();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("private.p12"), keystorePassword);
        PrivateKey privateKey = (PrivateKey) ks.getKey("baeldung",
                keyPassword);

        String text = "Test text string";
        byte[] forEnc = text.getBytes();
        System.out.println("Start text : " + text);
        byte[] encText = encryptor.enc(forEnc, crtf);

        System.out.println("After Encryption : " + new String(encText));
        byte[] tmp = decryptor.dec(encText, privateKey);
        String decTxt = new String(tmp);
        System.out.println("After Decryption : " + decTxt);
    }





}
