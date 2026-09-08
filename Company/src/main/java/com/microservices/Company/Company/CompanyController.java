package com.microservices.Company.Company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies()
    {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id)
    {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO dto)
    {
        return ResponseEntity.ok(companyService.createCompany(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO dto)
    {
        return ResponseEntity.ok(companyService.updateCompany(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id)
    {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompanyValue(@PathVariable Long id, @RequestBody Map<String,Object> dto)
    {
        return ResponseEntity.ok(companyService.updateCompanyValue(id,dto));
    }
}
