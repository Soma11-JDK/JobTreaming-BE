package swm11.jdk.jobtreaming.back.enums.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperType;

import java.util.Arrays;
import java.util.Collections;

@AllArgsConstructor
public enum PayGroup implements EnumMapperType {

    CASH("현금", Arrays.asList(PayType.REMITTANCE)),
    CARD("카드", Arrays.asList(PayType.CARD)),
    ETC("기타", Arrays.asList(PayType.POINT)),
    EMPTY("없음", Collections.EMPTY_LIST);

    @Getter
    private String title;
    @Getter
    private Object data;

    @Override
    public String getCode() {
        return name();
    }

}
