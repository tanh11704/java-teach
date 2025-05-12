package security;

import java.security.PublicKey;

public class SignatureVerifier {
    public boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {
        try {
            return CryptoUtils.verify(data, signature, publicKey);
        } catch (Exception e) {
            System.out.println("Xác minh chữ ký không thành công: " + e);
            return false;
        }
    }
}
