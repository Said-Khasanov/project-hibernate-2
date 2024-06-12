package com.khasanov.util;

import com.khasanov.entity.Rating;
import jakarta.persistence.AttributeConverter;

public class RatingAttributeConverter implements AttributeConverter<Rating, String> {

    public static final char ENUM_DELIMITER = '_';
    public static final char DB_DELIMITER = '-';

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.name().replace(ENUM_DELIMITER, DB_DELIMITER);
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        return Rating.valueOf(s.replace(DB_DELIMITER, ENUM_DELIMITER));
    }
}