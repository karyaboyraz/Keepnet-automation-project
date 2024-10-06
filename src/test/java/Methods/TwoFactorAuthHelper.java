package Methods;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.IGoogleAuthenticator;

public class TwoFactorAuthHelper {
    private IGoogleAuthenticator gAuth;
    private String secretKey = "NRDHQZTYPFWEIQTVKRDXI3SKLE";


    public TwoFactorAuthHelper() {
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setTimeStepSizeInMillis(30000)
                .setWindowSize(1)
                .build();
        gAuth = new GoogleAuthenticator(config);
    }

    public static void main(String[] args) {
        TwoFactorAuthHelper twoFactorAuthHelper = new TwoFactorAuthHelper();
        var asd = twoFactorAuthHelper.GetCode();
        System.out.println(asd);
    }

    public int GetCode() {
        int totpCode = generateCurrentNumber(secretKey);
        String formattedCode = String.format("%06d", totpCode);
        System.out.println("Generated TOTP Code: " + formattedCode);
        return Integer.parseInt(formattedCode);
    }

    /**
     * Generates the current TOTP code based on the provided secret key.
     *
     * @param secretKey The shared secret key (base32 encoded)
     * @return The 6-digit TOTP code
     */
    public int generateCurrentNumber(String secretKey) {
        return gAuth.getTotpPassword(secretKey);
    }
}