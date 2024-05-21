package com.zig.hardwork.cyclomaticcomplexity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public class ExampleOneProceed {
    @Value("$cyclomatic.complexity.white.list.brunches=brunches")
    private String whiteListBrunches;

    private final int existsInnInWhiteList = 1;
    private final int notExistsInnInWhiteList = 0;
    private EmployerWhiteListRepository employerWhiteListRepository;

    private Optional<Integer> getPresentWhiteListInn(ContextAppOne contextApp, ApplicantDataType applicant) {
        String unit = contextApp.getRqType().getApplication().getUnit();
        int result;

        if (brunchMustBeCheckedSign.negate().test(unit, whiteListBrunches))
            return Optional.empty();

        result = Optional.ofNullable(applicant.getPersonInfo().getAdditionalDocumentBlock())
                .map(ApplicantDataType.PersonInfo.AdditionalDocumentBlock::getFinDoc)
                .stream()
                .flatMap(Collection::stream)
                .map(ApplicantDataType.PersonInfo.AdditionalDocumentBlock.FinDoc::getOrganizationInn)
                .filter(Objects::nonNull)
                .map(employerWhiteListRepository::getWhiteInnEmployer)
                .anyMatch(Objects::isNull) ? notExistsInnInWhiteList : existsInnInWhiteList;

        return Optional.of(result);
    }

    private BiPredicate<String, String> brunchMustBeCheckedSign = String::equals;
}

@Getter
class ContextAppOne {
    private ApplicantDataType rqType;
}


@Getter
@Setter
class ApplicantDataType {
    private PersonInfo personInfo;
    private Application application;

    @Getter
    static class Application {
        private String unit;
    }

    @Getter
    static class PersonInfo {
        private AdditionalDocumentBlock additionalDocumentBlock;

        @Getter
        static class AdditionalDocumentBlock {
            private List<FinDoc> finDoc;

            @Getter
            static class FinDoc {
                private String organizationInn;
            }
        }
    }
}

class EmployerWhiteListRepository {
    public String getWhiteInnEmployer(String inn) {
        return "";
    }
}