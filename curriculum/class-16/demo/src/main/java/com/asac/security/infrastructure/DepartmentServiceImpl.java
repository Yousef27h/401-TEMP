package com.asac.security.infrastructure;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void calculateExamMarks() {
        System.out.print("Marks calculated");
    }
}
