package com.rubenskj.portfolio.util;

import com.rubenskj.portfolio.dto.ContactDTO;

public class EmailDesign {

    private static final String HEADER_UNTIL_IMG = "<html lang=\"en\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap\" rel=\"stylesheet\">\n" +
            "\n" +
            "  <title>Portfolio Contact E-mail!!!</title>\n" +
            "\n" +
            "  <style>\n" +
            "    .container {\n" +
            "      width: 100%;\n" +
            "      border-radius: 4px;\n" +
            "      border: 1px solid #ecebed;\n" +
            "\n" +
            "      font-family: 'Poppins', sans-serif;\n" +
            "      font-size: 16px;\n" +
            "    }\n" +
            "\n" +
            "    .header-container {\n" +
            "      padding: 5px 15px;\n" +
            "      text-align: center;\n" +
            "      border: 1px solid #ecebed;\n" +
            "    }\n" +
            "\n" +
            "    .content {\n" +
            "      text-align: center;\n" +
            "\n" +
            "      font-family: 'Poppins', sans-serif;\n" +
            "      font-size: 16px;\n" +
            "    }\n" +
            "\n" +
            "    .title {\n" +
            "      color: #5a5e73;\n" +
            "      font-weight: 500;\n" +
            "      font-size: 28px;\n" +
            "    }\n" +
            "\n" +
            "    .description-area {\n" +
            "      display: flex;\n" +
            "      flex-direction: column;\n" +
            "      padding: 5px 15px;\n" +
            "      text-align: left;\n" +
            "    }\n" +
            "\n" +
            "    .description {\n" +
            "      color: #555;\n" +
            "      font-weight: 400;\n" +
            "      font-size: 18px;\n" +
            "    }\n" +
            "\n" +
            "    .from-user {\n" +
            "      margin-top: 5px;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"container\">\n" +
            "    <div class=\"header-container\">\n" +
            "      <img src=\"";

    private static final String HEADER_AFTER_IMAGE = "\" alt=\"Portfolio Logo\">\n" +
            "    </div>\n" +
            "    <div class=\"content\">";

    private static final String TITLE_TAG_BEGIN = "<h1 class=\"title\">";
    private static final String TITLE_TAG_END = "</h1>\n";

    private static final String CONTENT_AREA_BEGIN = "<div class=\"description-area\">\n" +
            "        <span class=\"description\">";

    private static final String CONTENT_AREA_UNTIL_EMAIL = "</span>\n" +
            "        <span class=\"description\">";

    private static final String CONTENT_AREA_FROM_EMAIL_TO_DESCRIPTION = "</span>\n" +
            "        <span class=\"description from-user\">";

    private static final String END_EMAIL_DESIGN = "</span>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>";

    private static final String IMAGE_URL = "https://raw.githubusercontent.com/RubensKj/myportfolio/master/.github/code.png";

    private static final String COMPLETE_NAME_MESSAGE = "Name of the user who sent";
    private static final String EMAIL_MESSAGE = "In case you want to contact, use this E-mail: ";
    private static final String DESCRIPTION_MESSAGE = "Here comes a short description what he says: \n";

    public static String createDesignedEmail(String title, ContactDTO contactDTO) {
        return HEADER_UNTIL_IMG +
                IMAGE_URL +
                HEADER_AFTER_IMAGE +
                TITLE_TAG_BEGIN +
                title +
                TITLE_TAG_END +
                CONTENT_AREA_BEGIN +
                COMPLETE_NAME_MESSAGE + contactDTO.getCompleteName() +
                CONTENT_AREA_UNTIL_EMAIL +
                EMAIL_MESSAGE + contactDTO.getEmail() +
                CONTENT_AREA_FROM_EMAIL_TO_DESCRIPTION +
                DESCRIPTION_MESSAGE + contactDTO.getDescription() +
                END_EMAIL_DESIGN;
    }
}
