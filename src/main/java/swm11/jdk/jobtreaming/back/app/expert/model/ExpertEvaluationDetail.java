package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ExpertEvaluationDetail implements Serializable {

    private int isSelected1 = 0;                                            // 첫번째 항목 선택 여부

    private int isSelected2 = 0;                                            // 두번째 항목 선택 여부

    private int isSelected3 = 0;                                            // 세번째 항목 선택 여부

    private int isSelected4 = 0;                                            // 네번째 항목 선택 여부

    private int isSelected5 = 0;                                            // 다섯번째 항목 선택 여부

    private int isSelected6 = 0;                                            // 여섯번째 항목 선택 여부

    public void add(ExpertEvaluationDetail expertEvaluationDetail) {
        this.isSelected1 += expertEvaluationDetail.isSelected1;
        this.isSelected2 += expertEvaluationDetail.isSelected2;
        this.isSelected3 += expertEvaluationDetail.isSelected3;
        this.isSelected4 += expertEvaluationDetail.isSelected4;
        this.isSelected5 += expertEvaluationDetail.isSelected5;
        this.isSelected6 += expertEvaluationDetail.isSelected6;
    }

}
