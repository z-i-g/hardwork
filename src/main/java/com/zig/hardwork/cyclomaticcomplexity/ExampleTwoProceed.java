package com.zig.hardwork.cyclomaticcomplexity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.Optional.ofNullable;

public class ExampleTwoProceed {

    public void fillEntity(ContextApp contextApp) {
//        на сколько корректно использовать Optional.map() для модификации/заполнения?
//        Optional.ofNullable(contextApp.getApplication().getPartnerInfo())
//                .map(p -> {
//                    contextApp.getEntity().setPartnerType(p.getPartnerType());
//                    contextApp.getEntity().setPartnerInn(p.getPartnerInn());
//                    return p.getContactPersonInfo();
//                })
//                .map(p -> {
//                    contextApp.getEntity().setPartnerFio(p.getContactPersonFIO());
//                    contextApp.getEntity().setPartnerPhone(p.getContactPersonPhone());
//                    contextApp.getEntity().setPartnerEmail(p.getContactPersonEmail());
//                    return p;
//                });

        ofNullable(contextApp.getApplication().getPartnerInfo()).ifPresent(partnerInfo -> {
            fillGeneralInfo(contextApp.getEntity(), partnerInfo);

            ofNullable(partnerInfo.getContactPersonInfo()).ifPresent(contactPersonInfo ->
                    fillContactPersonInfo(contextApp.getEntity(), contactPersonInfo));
        });

        ofNullable(contextApp.getApplication().getPartner()).ifPresent(partnerBlock -> {
            PartnerEntity partnerEntity = new PartnerEntity();
            partnerEntity.setPartnerType(partnerBlock.getPartnerType());

            ofNullable(partnerBlock.getAgentPersonInfo()).ifPresent(agentPersonInfo -> {
                fillAgentPersonInfo(partnerEntity, agentPersonInfo);

                ofNullable(agentPersonInfo.getAgentName()).ifPresent(agentName -> fillAgentNameInfo(partnerEntity, agentName));
                ofNullable(agentPersonInfo.getPassport()).ifPresent(passport -> fillAgentPersonPassportInfo(partnerEntity, passport));
            });

            ofNullable(partnerBlock.getCompany()).ifPresent(company -> {
                fillCompanyInfo(partnerEntity, company);

                ofNullable(company.getParentCompany()).ifPresent(parentCompany -> fillParentCompanyInfo(partnerEntity, parentCompany));

                ofNullable(company.getOffice()).ifPresent(office -> {
                    fillOfficeInfo(partnerEntity, office);

                    ofNullable(office.getAddress()).ifPresent(address -> fillOfficeAddressInfo(partnerEntity, address));
                });
            });
            contextApp.getEntity().setPartnerEntity(partnerEntity);
        });
    }

    private void fillGeneralInfo(Entity entity, ApplicationType.PartnerInfo partnerInfo) {
        entity.setPartnerType(partnerInfo.getPartnerType());
        entity.setPartnerInn(partnerInfo.getPartnerInn());
    }

    private void fillContactPersonInfo(Entity entity, ApplicationType.PartnerInfo.ContactPersonInfo contactPersonInfo) {
        entity.setPartnerFio(contactPersonInfo.getContactPersonFIO());
        entity.setPartnerPhone(contactPersonInfo.getContactPersonPhone());
        entity.setPartnerEmail(contactPersonInfo.getContactPersonEmail());
    }

    private void fillAgentPersonInfo(PartnerEntity partnerEntity, ApplicationType.Partner.AgentPersonInfo agentPersonInfo) {
        partnerEntity.setFullName(agentPersonInfo.getAgentFIO());
        partnerEntity.setAgentPhoneNumber(agentPersonInfo.getPhone());
        partnerEntity.setEmail(agentPersonInfo.getEmail());
        partnerEntity.setAgentId(agentPersonInfo.getCasID());
        partnerEntity.setAgentInn(agentPersonInfo.getINN());
    }

    private void fillAgentNameInfo(PartnerEntity partnerEntity, ApplicationType.Partner.AgentPersonInfo.PersonNameType agentName) {
        partnerEntity.setFirstName(agentName.getFirstName());
        partnerEntity.setLastName(agentName.getLastName());
        partnerEntity.setMiddleName(agentName.getMiddleName());
    }

    private void fillAgentPersonPassportInfo(PartnerEntity partnerEntity, ApplicationType.Partner.AgentPersonInfo.Passport passport) {
        partnerEntity.setPassSeries(passport.getIdSeries());
        partnerEntity.setPassNum(passport.getIdNum());
        partnerEntity.setIssueCode(passport.getIssueCode());
    }

    private void fillCompanyInfo(PartnerEntity partnerEntity, ApplicationType.Partner.Company company) {
        partnerEntity.setCompanyId(company.getCompanyId());
        partnerEntity.setCompanyInn(company.getCompanyInn());
    }

    private void fillParentCompanyInfo(PartnerEntity partnerEntity, ApplicationType.Partner.Company.ParentCompany parentCompany) {
        partnerEntity.setHoldingCompanyId(parentCompany.getParentCompanyID());
        partnerEntity.setHoldingCompanyName(parentCompany.getName());
    }

    private void fillOfficeInfo(PartnerEntity partnerEntity, ApplicationType.Partner.Company.Office office) {
        partnerEntity.setOfficeId(office.getOfficeId());
    }

    private void fillOfficeAddressInfo(PartnerEntity partnerEntity, ApplicationType.Partner.Company.Office.Address address) {
        partnerEntity.setPostalCode(address.getPostalCode());
        partnerEntity.setRegionCode(address.getRegionCode());
    }
}

@Getter
class ContextApp {
    private ApplicationType application;
    private Entity entity;
}

@Getter
@Setter
class Entity {
    private String partnerType;
    private String partnerInn;
    private String partnerFio;
    private String partnerPhone;
    private String partnerEmail;
    private PartnerEntity partnerEntity;
}

@Getter
@Setter
class PartnerEntity {
    private String partnerType;
    private String fullName;
    private String lastName;
    private String firstName;
    private String middleName;
    private String agentPhoneNumber;
    private String email;
    private String agentId;
    private String agentInn;
    private String passSeries;
    private String passNum;
    private String issueCode;
    private String companyId;
    private String companyInn;
    private String holdingCompanyId;
    private String holdingCompanyName;
    private String officeId;
    private String postalCode;
    private String regionCode;
}

@Getter
class ApplicationType {
    private PartnerInfo partnerInfo;
    private Partner partner;

    @Getter
    static class PartnerInfo {
        private String partnerType;
        private String partnerInn;
        private ContactPersonInfo contactPersonInfo;

        @Getter
        static class ContactPersonInfo {
            private String contactPersonFIO;
            private String contactPersonPhone;
            private String contactPersonEmail;
        }
    }

    @Getter
    static class Partner {
        private String partnerType;
        private AgentPersonInfo agentPersonInfo;
        private Company company;

        @Getter
        static class AgentPersonInfo {
            private String agentFIO;
            private String phone;
            private String email;
            private String casID;
            private String INN;
            private PersonNameType agentName;
            private Passport passport;

            @Getter
            static class PersonNameType {
                private String lastName;
                private String firstName;
                private String middleName;
                private String phone;
                private String email;
                private String casID;
                private String inn;
            }

            @Getter
            static class Passport {
                private String idSeries;
                private String idNum;
                private String issueCode;
            }
        }

        @Getter
        static class Company {
            private String companyId;
            private String companyInn;
            private ParentCompany parentCompany;
            private Office office;

            @Getter
            static class ParentCompany {
                private String parentCompanyID;
                private String name;
            }

            @Getter
            static class Office {
                private String officeId;
                private Address address;

                @Getter
                static class Address {
                    private String postalCode;
                    private String regionCode;
                }
            }
        }
    }
}