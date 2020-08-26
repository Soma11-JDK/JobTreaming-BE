package swm11.jdk.jobtreaming.back.enums.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum PayType implements EnumMapperType {

    REMITTANCE("무통장입금", "무통장입금"),
    CARD("신용카드", "신용카드"),
    POINT("포인트", "포인트"),
    EMPTY("없음", "없음");

    @Getter
    private String title;
    @Getter
    private Object data;

    @Override
    public String getCode() {
        return name();
    }

}
