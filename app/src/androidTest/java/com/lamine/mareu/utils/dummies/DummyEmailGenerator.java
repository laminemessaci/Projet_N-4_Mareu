package com.lamine.mareu.utils.dummies;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
public class DummyEmailGenerator {
    public static final String INVALID_EMAIL = "foobar";
    public static final String VALID_EMAIL_1 = "d.doret@gmail.com";
    public static final String VALID_EMAIL_2 = "sonia-decourt@outlook.fr";


    private static final List<String> DUMMY_EMAILS = Arrays.asList(
            "d.doret@gmail.com",
            "m.justal@outlook.com",
            "f.tolken@gmail.com",
            "sonia-decourt@outlook.fr");

    /**
     * Generate a mailing list of four participants
     * @return list of mails
     */
    static List<String> generateEmails() {
        return DUMMY_EMAILS;
    }

    /**
     * Generate a mailing list of participants (max four emails)
     * @return list of mails
     */
    static List<String> generateEmails(int nbEmail) {
        nbEmail--;

        if (nbEmail < 0 || nbEmail >= DUMMY_EMAILS.size())
            return DUMMY_EMAILS;
        else
            return DUMMY_EMAILS.subList(0, nbEmail);
    }
}
