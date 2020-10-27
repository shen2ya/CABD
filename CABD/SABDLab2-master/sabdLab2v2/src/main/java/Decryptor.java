import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipientInformation;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipient;

import java.security.PrivateKey;
import java.util.Collection;

public class Decryptor {
    public static byte[] dec(
            byte[] encTxt,
            PrivateKey decryptionKey)
            throws CMSException {

        byte[] decTxt = null;
        if (null != encTxt && null != decryptionKey) {
            CMSEnvelopedData envelopedData = new CMSEnvelopedData(encTxt);

            Collection<RecipientInformation> recipients
                    = envelopedData.getRecipientInfos().getRecipients();
            KeyTransRecipientInformation recipientInfo
                    = (KeyTransRecipientInformation) recipients.iterator().next();
            JceKeyTransRecipient rec
                    = new JceKeyTransEnvelopedRecipient(decryptionKey);

            return recipientInfo.getContent(rec);
        }
        return decTxt;
    }
}
