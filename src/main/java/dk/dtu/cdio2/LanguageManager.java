package dk.dtu.cdio2;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class language manager with handles whether you want the language in danish or english
 */
public class LanguageManager {
    private Locale locale = Locale.getDefault();
    private ResourceBundle messages = getMessages();

    /**
     * Method that if language is set to english the program will be in english
     * if the language is set to danish it will be in danish, if language is not
     * chosen it will be english
     */
    public LanguageManager() {
        if (locale.getLanguage().equals(new Locale("en").getLanguage())) {
            locale = new Locale("en", "US");
        }
        else if (locale.getLanguage().equals(new Locale("da").getLanguage())) {
            locale = new Locale("da", "DK");
        }
        else {
            locale = new Locale("en", "US");
        }
    }

    /**
     * Function that takes either language of country, and by either language
     * or country decide the language of the game
     * @param language the language you want
     * @param country the country you want
     */
    public void setLocale(String language, String country) {
        locale = new Locale(language, country);
        messages = getMessages();
    }

    public void setLocale(String language) {
        if (language.contains("_")) {
            setLocale(language.split("_")[0], language.split("_")[1]);
        }
        else {
            locale = new Locale(language);
            messages = getMessages();
        }
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        messages = getMessages();
    }

    /**
     * function that returns the messages
     * @return all the messages in the locale
     */
    private ResourceBundle getMessages() {
        return ResourceBundle.getBundle("GameMessages", locale);
    }

    /**
     * A function that takes a key for a specific message and returns
     * that message
     * @param messageKey key for a specific message
     * @return returns the specific message
     */
    public String getString(String messageKey) {
        String message = "";
        try {
            message = messages.getString(messageKey);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "Could not read message '" + messageKey + "' from locale " + locale.getLanguage();
        }
        return message;
    }
}
