package com.microservices.Company.Company;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> comapnies=companyRepository.findAll();
        return comapnies.stream().map(elements-> modelMapper.map(elements, CompanyDTO.class)).toList();
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company=companyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Company with id: "+id+" not found!"));
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO dto) {
        Company company=new Company();
        modelMapper.map(dto,company);
        companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO dto) {
        Company company = companyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Company with id: "+id+" not found!"));
        dto.setId(id);
        modelMapper.map(dto,company);
        Company updatedCompany=companyRepository.save(company);
        return modelMapper.map(updatedCompany, CompanyDTO.class);
    }

    @Override
    public void deleteCompany(Long id) {
        if(companyRepository.existsById(id))
        {
            companyRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Company with id: "+id+" not found!");
        }
    }

    @Override
    public CompanyDTO updateCompanyValue(Long id, Map<String, Object> dto) {
        Company company=companyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Company with id: "+id+" not found!"));
        dto.forEach((key,value)->{
            switch (key)
            {
                case "name": company.setName((String)value);
                break;

                case "description": company.setDescription((String) value);
                break;

                default:
                    throw new IllegalArgumentException("Invalid Credentials!");
            }
        });
        companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }
}
