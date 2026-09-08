package com.microservices.Company.Company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CompanyService {
    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(Long id);

    CompanyDTO createCompany(CompanyDTO dto);

    CompanyDTO updateCompany(Long id, CompanyDTO dto);

    void deleteCompany(Long id);

    CompanyDTO updateCompanyValue(Long id, Map<String, Object> dto);
}
