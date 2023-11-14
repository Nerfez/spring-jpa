package fr.iocean.species.dto;

import java.util.Date;
import java.util.List;

public class InvalidEntityErrorDto extends ErrorDto {
    private List<String> globalErrors;
    private List<String> fieldErrors;

    public InvalidEntityErrorDto(Date timestamp, String message, String url, List<String> globalErrors, List<String> fieldErrors) {
        super(timestamp, message, url);
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
