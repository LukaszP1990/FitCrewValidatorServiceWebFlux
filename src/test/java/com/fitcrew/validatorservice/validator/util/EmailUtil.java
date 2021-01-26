package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.EmailDto;

public class EmailUtil {

    public static final String SENDER = "sender";
    public static final String RECIPIENT = "fname lname";
    public static final String SUBJECT = "message";
    public static final String BODY_OF_MESSAGE = "body of message";

    public static EmailDto getEmailDto(String sender, String recipient, String subject, String bodyOfMessage) {
        return EmailDto.builder()
                .sender(sender)
                .recipient(recipient)
                .subject(subject)
                .bodyOfMessage(bodyOfMessage)
                .build();
    }

    public static class EmailData {
        private final EmailDto emailDto;
        private final boolean result;

        public EmailData(EmailDto emailDto, boolean result) {
            this.emailDto = emailDto;
            this.result = result;
        }

        public EmailDto getEmailDto() {
            return emailDto;
        }

        public boolean isResult() {
            return result;
        }
    }

    public static class RegexData {
        private final String email;
        private final boolean result;

        public RegexData(String email, boolean result) {
            this.email = email;
            this.result = result;
        }

        public String getEmail() {
            return email;
        }

        public boolean isResult() {
            return result;
        }
    }
}
