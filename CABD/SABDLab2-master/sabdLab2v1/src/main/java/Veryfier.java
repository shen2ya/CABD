import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;

import java.io.ByteArrayInputStream;
import java.util.Collection;

public class Veryfier {
    public static boolean verifier(byte[] txt)
            throws Exception {
        ByteArrayInputStream in
                = new ByteArrayInputStream(txt);
        ASN1InputStream asnIn = new ASN1InputStream(in);
        CMSSignedData cmsSignedData = new CMSSignedData(
                ContentInfo.getInstance(asnIn.readObject()));
        System.out.println("Signed data is : " + new String((byte[]) cmsSignedData.getSignedContent().getContent()));
        SignerInformationStore sgnrs
                = cmsSignedData.getSignerInfos();
        SignerInformation sgnr = sgnrs.getSigners().iterator().next();
        Collection<X509CertificateHolder> certCollection
                = cmsSignedData.getCertificates().getMatches(sgnr.getSID());
        X509CertificateHolder certHolder = certCollection.iterator().next();

        return sgnr.verify(new JcaSimpleSignerInfoVerifierBuilder()
                .build(certHolder));
    }
}
