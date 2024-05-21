package com.zig.hardwork.cyclomaticcomplexity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExampleTwo {

    public void fillEntity(ContextApp contextApp) {
        ApplicationType.PartnerInfo partnerInfo = contextApp.getApplication().getPartnerInfo();
        if (partnerInfo != null) {
            contextApp.getEntity().setPartnerType(partnerInfo.getPartnerType());
            contextApp.getEntity().setPartnerInn(partnerInfo.getPartnerInn());
            if (partnerInfo.getContactPersonInfo() != null) {
                contextApp.getEntity().setPartnerFio(partnerInfo.getContactPersonInfo().getContactPersonFIO());
                contextApp.getEntity().setPartnerPhone(partnerInfo.getContactPersonInfo().getContactPersonPhone());
                contextApp.getEntity().setPartnerEmail(partnerInfo.getContactPersonInfo().getContactPersonEmail());
            }
        }

        ApplicationType.Partner partnerBlock = contextApp.getApplication().getPartner();
        if (partnerBlock != null) {
            PartnerEntity partnerEntity = new PartnerEntity();
            partnerEntity.setPartnerType(partnerBlock.getPartnerType());
            ApplicationType.Partner.AgentPersonInfo agentPersonInfo = contextApp.getApplication().getPartner().getAgentPersonInfo();
            if (agentPersonInfo != null) {
                partnerEntity.setFullName(agentPersonInfo.getAgentFIO());
                if (agentPersonInfo.getAgentName() != null) {
                    partnerEntity.setFirstName(agentPersonInfo.getAgentName().getFirstName());
                    partnerEntity.setLastName(agentPersonInfo.getAgentName().getLastName());
                    partnerEntity.setMiddleName(agentPersonInfo.getAgentName().getMiddleName());
                }
                partnerEntity.setAgentPhoneNumber(agentPersonInfo.getPhone());
                partnerEntity.setEmail(agentPersonInfo.getEmail());
                partnerEntity.setAgentId(agentPersonInfo.getCasID());
                partnerEntity.setAgentInn(agentPersonInfo.getINN());
                if (agentPersonInfo.getPassport() != null) {
                    partnerEntity.setPassSeries(agentPersonInfo.getPassport().getIdSeries());
                    partnerEntity.setPassNum(agentPersonInfo.getPassport().getIdNum());
                    partnerEntity.setIssueCode(agentPersonInfo.getPassport().getIssueCode());
                }
            }

            ApplicationType.Partner.Company company = partnerBlock.getCompany();
            if (company != null) {
                partnerEntity.setCompanyId(company.getCompanyId());
                partnerEntity.setCompanyInn(company.getCompanyInn());
                if (company.getParentCompany() != null) {
                    partnerEntity.setHoldingCompanyId(company.getParentCompany().getParentCompanyID());
                    partnerEntity.setHoldingCompanyName(company.getParentCompany().getName());
                }

                ApplicationType.Partner.Company.Office office = company.getOffice();
                if (office != null) {
                    partnerEntity.setOfficeId(office.getOfficeId());
                    ApplicationType.Partner.Company.Office.Address address = office.getAddress();
                    if (address != null) {
                        partnerEntity.setPostalCode(address.getPostalCode());
                        partnerEntity.setRegionCode(address.getRegionCode());
                    }
                }
            }
            contextApp.getEntity().setPartnerEntity(partnerEntity);
        }
    }
}