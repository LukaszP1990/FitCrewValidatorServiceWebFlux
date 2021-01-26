package com.fitcrew.validatorservice.core.error.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorType {
	public static final String VALUE_ERROR = "VALUE_ERROR";
	public static final String SENDER_ERROR = "SENDER_ERROR";
	public static final String RECIPIENT_ERROR = "RECIPIENT_ERROR";
	public static final String SUBJECT_ERROR = "SUBJECT_ERROR";
	public static final String FIRST_NAME_ERROR = "FIRST_NAME_ERROR";
	public static final String LAST_NAME_ERROR = "LAST_NAME_ERROR";
	public static final String DATE_OF_BIRTH_ERROR = "DATE_OF_BIRTH_ERROR";
	public static final String DESCRIPTION_ERROR = "DESCRIPTION_ERROR";
	public static final String TRAINING_NAME_ERROR = "TRAINING_NAME_ERROR";
	public static final String TRAINING_ERROR = "TRAINING_ERROR";
	public static final String EMAIL_REGEX_ERROR = "EMAIL_REGEX_ERROR";
	public static final String RATING_ERROR = "RATING_ERROR";
	public static final String PHONE_REGEX_ERROR = "PHONE_REGEX_ERROR";
	public static final String ADMIN_ID_ERROR = "ADMIN_ID_ERROR";
	public static final String CLIENT_ID_ERROR = "CLIENT_ID_ERROR";
	public static final String TRAINER_ID_ERROR = "TRAINER_ID_ERROR";
}
