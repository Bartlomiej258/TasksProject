package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Mail {

    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

    public static class MailBuilder {

        private String mailTo;
        private String subject;
        private String message;
        private String toCc;

        public MailBuilder toCc(String toCc) {
            this.toCc = toCc;
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, subject, message, toCc);
        }
    }

    public static MailBuilder newBuilder() {
        return new MailBuilder();
    }
}
