package com.zig.hardwork.cyclomaticcomplexity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ExampleOne {
    @Value("$cyclomatic.complexity.white.list.brunches=brunches")
    private String whiteListBrunches;
    private EmployerWhiteListRepository employerWhiteListRepository;

    //6
    private Integer getPresentWhiteListInn(ContextAppOne contextApp, ApplicantDataType applicant) {
        String unit = contextApp.getRqType().getApplication().getUnit();

        boolean brunchMustBeCheckedSign = brunchMustBeCheckedSign(unit, whiteListBrunches);
        if (!brunchMustBeCheckedSign)
            return null;

        List<String> innList = new ArrayList<>();
        if (applicant.getPersonInfo().getAdditionalDocumentBlock() != null && applicant.getPersonInfo().getAdditionalDocumentBlock().getFinDoc() != null) {
            innList = applicant.getPersonInfo().getAdditionalDocumentBlock().getFinDoc().stream()
                    .filter(doc -> doc.getOrganizationInn() != null)
                    .map(ApplicantDataType.PersonInfo.AdditionalDocumentBlock.FinDoc::getOrganizationInn)
                    .collect(Collectors.toList());
        }

        for (String inn: innList) {
            String innFromReference = employerWhiteListRepository.getWhiteInnEmployer(inn.trim());
            if (innFromReference == null) {
                log.debug("ИНН {} в справочнике не найден", inn);
                return 0;
            }
        }
        return 1;
    }

    private boolean brunchMustBeCheckedSign(String unit, String whiteListBrunches) {
        //какая-то логика
        return unit.equals(whiteListBrunches);
    }
}