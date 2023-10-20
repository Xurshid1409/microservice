package uz.jurayev.account.constants;

import lombok.Getter;

@Getter
public enum AccountConstant {

    CREATED("Created"),
    SUCCESSFULLY("Successfully"),
    NOT_FOUND("Not found"),
    ERROR("Error"),

    STATUS_CODE_200("200"),
    STATUS_CODE_201("201"),
    STATUS_CODE_500("500"),
    STATUS_CODE_400("400"),
    STATUS_CODE_404("404");

    private final String message;

    AccountConstant(String message) {
        this.message = message;
    }

}
